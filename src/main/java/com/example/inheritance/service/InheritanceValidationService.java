package com.example.inheritance.service;

import com.example.inheritance.exception.BusinessException;
import com.example.inheritance.model.InheritanceInput;

public interface InheritanceValidationService {
    public void execute(InheritanceInput input) throws BusinessException;
}
