package io.github.com.Erissonteixeira.api_crudoficina.dto;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.MatcherAssert.assertThat;

public class ServicoRequestDTOTest {

    private Validator validator;
    @BeforeEach
    void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    @DisplayName("Deve passar na validação quando todos os campos são válidos")
    void shouldPassValidationWhenFieldAreValid(){
        ServicoRequestDTO dto = new ServicoRequestDTO(
                "Troca de óleo",
                new BigDecimal("150.00"),
                "ATIVO"
        );

        Set<ConstraintViolation<ServicoRequestDTO>> violations = validator.validate(dto);

        assertThat(violations, empty());
    }
}
