package com.example.jwtandwebsocket.service.role;

import com.example.jwtandwebsocket.common.constant.RespCode;
import com.example.jwtandwebsocket.common.exception.MyAppException;
import com.example.jwtandwebsocket.common.exception.MyValidationException;
import com.example.jwtandwebsocket.dao.permission.PermissionDao;
import com.example.jwtandwebsocket.dao.role.RoleDao;
import com.example.jwtandwebsocket.dao.roleAndPermission.RoleAndPermisionDao;
import com.example.jwtandwebsocket.dto.permission.PermissionDto;
import com.example.jwtandwebsocket.dto.role.RoleDto;
import com.example.jwtandwebsocket.utils.service.TransactionProxyService;
import com.example.jwtandwebsocket.utils.validator.DataValidator;
import com.example.jwtandwebsocket.utils.validator.FieldConstraintValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;
    private final PermissionDao permissionDao;
    private final RoleAndPermisionDao roleAndPermisionDao;
    private final FieldConstraintValidator validator;
    private final TransactionProxyService transactionProxyService;

    @Autowired
    public RoleServiceImpl(
            RoleDao roleDao,
            PermissionDao permissionDao,
            RoleAndPermisionDao roleAndPermisionDao,
            FieldConstraintValidator validator,
            TransactionProxyService transactionProxyService) {
        this.roleDao = roleDao;
        this.permissionDao = permissionDao;
        this.roleAndPermisionDao = roleAndPermisionDao;
        this.validator = validator;
        this.transactionProxyService = transactionProxyService;
    }

    @Override
    public RoleDto findById(UUID id) {
        RoleDto roleDto = roleDao.findById(id);
        if (roleDto == null) {
            return null;
        }
        List<PermissionDto> permissionDtoList = permissionDao.findAllByRoleId(id);
        roleDto.setPermissionDtoList(permissionDtoList);
        return roleDto;
    }

    @Override
    public List<RoleDto> findAll() {
        List<RoleDto> roleDtoList = roleDao.findAll();
        for (RoleDto r : roleDtoList) {
            List<PermissionDto> permissionDtoList = permissionDao.findAllByRoleId(r.getId());
            r.setPermissionDtoList(permissionDtoList);
        }
        return roleDtoList;
    }

    @Override
    public RoleDto save(RoleDto roleDto, UUID actioner) {
        roleValidator.validateSave(roleDto);
        if (roleDto.getId() != null) { // update
            RoleDto current = roleDao.findById(roleDto.getId());
            if (current == null) {
                return null;
            }
            current.setName(roleDto.getName());
            current.setUpdatedBy(actioner);
            current.setUpdatedTime(System.currentTimeMillis());
            RoleDto saved = transactionProxyService.saveRole(roleDao, roleAndPermisionDao, current, roleDto.getListPermissionId());
            List<PermissionDto> permissionDtoList = permissionDao.findAllByRoleId(saved.getId());
            saved.setPermissionDtoList(permissionDtoList);
            return saved;
        }
        // create
        roleDto.setCreatedBy(actioner);
        RoleDto saved = transactionProxyService.saveRole(roleDao, roleAndPermisionDao, roleDto, roleDto.getListPermissionId());
        List<PermissionDto> permissionDtoList = permissionDao.findAllByRoleId(saved.getId());
        saved.setPermissionDtoList(permissionDtoList);
        return saved;
    }

    @Override
    public boolean deleteById(UUID id) throws MyAppException {
        return transactionProxyService.deleteRoleById(roleDao, roleAndPermisionDao, id);
    }

    private final DataValidator<RoleDto> roleValidator = new DataValidator<RoleDto>() {

        @Override
        public FieldConstraintValidator getValidator() {
            return validator;
        }

        @Override
        public void validateCreate(RoleDto dto) {
            /*
                - name exist
                - list permissionId valid ( check permission id exit )
             */
            if (roleDao.existsByName(dto.getName())) {
                throw new MyValidationException("Role name already exists!", RespCode.VALIDATION_FAIL);
            }

            if (dto.getListPermissionId() != null && dto.getListPermissionId().size() != 0) {
                Set<UUID> listPermissionId = dto.getListPermissionId();
                for (UUID id : listPermissionId) {
                    if (!permissionDao.existsById(id)) {
                        throw new MyValidationException("Permission " + id + " does not exist!", RespCode.VALIDATION_FAIL);
                    }
                }
            }

        }

        @Override
        public void validateUpdate(RoleDto dto) {
            /*
                - name exist
                - list permissionId valid ( check permission id exit )
             */
            if (roleDao.existsByName(dto.getName(), dto.getId())) {
                throw new MyValidationException("Role name already exists!", RespCode.VALIDATION_FAIL);
            }

            if (dto.getListPermissionId() != null && dto.getListPermissionId().size() != 0) {
                Set<UUID> listPermissionId = dto.getListPermissionId();
                for (UUID id : listPermissionId) {
                    if (!permissionDao.existsById(id)) {
                        throw new MyValidationException("Permission " + id + " does not exist!", RespCode.VALIDATION_FAIL);
                    }
                }
            }
        }

    };

}
