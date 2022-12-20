package app.entities;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import app.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;


class PassportTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testRightPassport() {

        Passport passport1 = new Passport("Test", Gender.FEMALE, "3333 333333",
                LocalDate.of(2006, 3, 30), "Russia");
        Set<ConstraintViolation<Passport>> violations1 = validator.validate(passport1);
        Assertions.assertTrue(violations1.isEmpty());

        Passport passport2 = new Passport("Test1", Gender.MALE, "1233 567890",
                LocalDate.of(2006, 3, 30), "Russia");
        Set<ConstraintViolation<Passport>> violations2 = validator.validate(passport2);
        Assertions.assertTrue(violations2.isEmpty());
    }

    @Test
    public void testWrongPassport() {

        Passport passport1 = new Passport("T", Gender.FEMALE, "333L 333333",
                LocalDate.of(2006, 3, 30), "Russia");
        Set<ConstraintViolation<Passport>> violations1 = validator.validate(passport1);
        Assertions.assertFalse(violations1.isEmpty());

        Passport passport2 = new Passport("Test", Gender.MALE, "333L333333",
                LocalDate.of(2006, 3, 30), "Russia");
        Set<ConstraintViolation<Passport>> violations2 = validator.validate(passport2);
        Assertions.assertFalse(violations2.isEmpty());
    }
}