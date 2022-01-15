package com.example.inheritance.service;

import java.util.Locale;
import java.util.Objects;

import com.example.inheritance.exception.BusinessException;
import com.example.inheritance.model.InheritanceInput;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InheritanceValidationServiceImpl implements InheritanceValidationService {

    private final MessageSource messageSource;

    @Override
    public void execute(InheritanceInput input) throws BusinessException {
        if (Objects.isNull(input) || !StringUtils.hasText(input.getMessage())) {
            throw new BusinessException("BE00001",
                    messageSource.getMessage("BE0001", new Object[] { "メッセージ" }, Locale.getDefault()));
        }
    }
}
