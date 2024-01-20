package com.devmarvin.noticias.validators;

import com.devmarvin.noticias.annotations.UniqueEmail;
import com.devmarvin.noticias.entities.Usuario;
import com.devmarvin.noticias.repositories.UserRepository;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.ConstraintValidator;

@Slf4j
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        Usuario user = userRepository.findByUsername(value).orElse(null);

        if(user == null) {
            return true;
        }
        return false;


    }
}

