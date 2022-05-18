package com.example.jwtandwebsocket.dto.role;

import com.example.jwtandwebsocket.dto.BaseDto;
import com.example.jwtandwebsocket.dto.permission.PermissionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto implements BaseDto {
    private UUID id;

    @NotBlank(message = "name can not be blank")
    private String name;

    private Long createdTime;
    private UUID createdBy;
    private Long updatedTime;
    private UUID updatedBy;
    private List<PermissionDto> permissionDtos;
}
