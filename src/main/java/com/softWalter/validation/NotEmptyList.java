package com.softWalter.validation;

import com.softWalter.validation.constraintvalidation.NotEmptyListValidador;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//anotação para verificar em tempo de execução.
@Target(ElementType.FIELD)// Para dizer onde colocar esta anotação FIELD=Campo=var.
@Constraint(validatedBy =
        NotEmptyListValidador.class)// Dizer qual calsse que vai fazer a validação.
public @interface NotEmptyList {
    String message() default "A Lista não pode ser vazia.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
