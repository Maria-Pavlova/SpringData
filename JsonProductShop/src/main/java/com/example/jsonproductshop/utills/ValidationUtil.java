package com.example.jsonproductshop.utills;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidationUtil {
    <E> Set<ConstraintViolation<E>> violation(E entity);

    <E> boolean isValid(E entity);
}