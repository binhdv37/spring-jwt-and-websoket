package com.example.jwtandwebsocket.utils.service;

import com.example.jwtandwebsocket.common.exception.MyAppException;
import com.example.jwtandwebsocket.dao.role.RoleDao;
import com.example.jwtandwebsocket.dao.roleAndPermission.RoleAndPermisionDao;
import com.example.jwtandwebsocket.dto.role.RoleDto;
import com.example.jwtandwebsocket.dto.roleAndPermission.RoleAndPermissionDto;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Component
public class TransactionProxyService { // dinh nghia 1 so ham can chay transactional trong day

    @Transactional
    public RoleDto saveRole(RoleDao roleDao,
                            RoleAndPermisionDao roleAndPermisionDao,
                            RoleDto roleDto,
                            Set<UUID> listPermisisonId) {
        if (roleDto.getId() != null) { // update
            roleAndPermisionDao.deleteByRoleId(roleDto.getId()); // remove record in role_and_permission
        }
        // save to role table, and then role_and_permission table
        RoleDto savedRole = roleDao.save(roleDto);
        if (listPermisisonId != null && listPermisisonId.size() != 0) {
            for (UUID id : listPermisisonId) {
                RoleAndPermissionDto roleAndPermissionDto = new RoleAndPermissionDto();
                roleAndPermissionDto.setRoleId(savedRole.getId());
                roleAndPermissionDto.setPermissionId(id);
                roleAndPermisionDao.save(roleAndPermissionDto);
            }
        }
        return savedRole;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRoleById(RoleDao roleDao, RoleAndPermisionDao roleAndPermisionDao, UUID roleId) throws MyAppException {
        // remove in role_and_permission, then remove role
        roleAndPermisionDao.deleteByRoleId(roleId);
        return roleDao.deleteById(roleId);
    }

}
