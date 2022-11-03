package com.example.usersystem.validation;

import com.example.usersystem.models.User;
import org.passay.*;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Valid;
import java.util.Arrays;

public  class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, User> {

    @Override
    public void initialize(final ValidPassword arg0) {

    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {

        String password = user.getPassword();

     //   MessageResolver resolver = new PropertiesMessageResolver(password)
        PasswordValidator passwordValidator = new PasswordValidator (
                (Rule) Arrays.asList(
                new LengthRule(6, 50),

        new CharacterRule(EnglishCharacterData.UpperCase, 1),

        new CharacterRule(EnglishCharacterData.LowerCase, 1),

        new CharacterRule(EnglishCharacterData.Digit, 1),

        new CharacterRule(EnglishCharacterData.Special, 1)));

//                new NumericalSequenceRule(3,false),
//                new AlphabeticalSequenceRule(3,false),
//                new QwertySequenceRule(3,false),
//                new WhitespaceRule()));

        RuleResult result = passwordValidator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
    }

        //Sending one message each time failed validation.
        context.buildConstraintViolationWithTemplate(passwordValidator.getMessages(result).stream().findFirst().get())
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;

    }
}
