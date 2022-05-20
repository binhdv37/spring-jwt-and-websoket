package com.example.jwtandwebsocket.utils.validator;

import com.example.jwtandwebsocket.common.constant.RespCode;
import com.example.jwtandwebsocket.common.exception.MyValidationException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FieldConstraintValidator {

    private Validator fieldsValidator;

    @PostConstruct
    public void initializeValidators() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        fieldsValidator = factory.getValidator();
    }

    public void validateFields(Object data) {
        Set<ConstraintViolation<Object>> constraintsViolations = fieldsValidator.validate(data);
        List<String> validationErrors = constraintsViolations.stream()
                .map(ConstraintViolation::getMessage)
                .distinct()
                .collect(Collectors.toList());
        if (!validationErrors.isEmpty()) {
            throw new MyValidationException("Validation error: "
                    + String.join(", ", validationErrors), RespCode.BAD_REQUEST_PARAMS);
        }
    }

}
