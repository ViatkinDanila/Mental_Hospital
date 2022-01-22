package com.epam.mentalhospital.service.validator;

import com.epam.mentalhospital.model.Entity;

public interface Validator<T extends Entity> {
    boolean isValid(T entity);
}
