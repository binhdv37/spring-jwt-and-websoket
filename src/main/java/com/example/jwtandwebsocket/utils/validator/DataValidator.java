package com.example.jwtandwebsocket.utils.validator;

import com.example.jwtandwebsocket.common.constant.RespCode;
import com.example.jwtandwebsocket.common.exception.MyValidationException;
import com.example.jwtandwebsocket.dto.BaseDto;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public abstract class DataValidator<T extends BaseDto> { // T: dto

    public static final String phoneRegex = "^[0-9]{6,20}$"; // 6 -> 2o ki tu so

    public abstract FieldConstraintValidator getValidator();

    public void validateSave(T dto) {
        if (dto == null) {
            throw new MyValidationException("Data object cannot be null", RespCode.VALIDATION_FAIL);
        }

        getValidator().validateFields(dto);

        if (dto.getId() == null) {
            validateCreate(dto);
        } else {
            validateUpdate(dto);
        }
    }

    public abstract void validateCreate(T dto);

    public abstract void validateUpdate(T dto);

    public boolean isBlank(String data) {
        return data == null || data.trim().equals("");
    }

    public boolean isValidPhone(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        Pattern p = Pattern.compile(phoneRegex);
        return p.matcher(phoneNumber).matches();
    }

}
