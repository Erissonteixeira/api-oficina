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
import static org.junit.jupiter.api.Assertions.assertFalse;

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
    @Test
    @DisplayName("Deve falhar na validação quando campos obrigatórios forem nulos ou vazios")
    void shouldFailValidationWhenFieldsAreEmptyOrNull(){
        ServicoRequestDTO dto = new ServicoRequestDTO("", null, null);

        Set<ConstraintViolation<ServicoRequestDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }
    @Test
    @DisplayName("Deve falhar na validação quando valor for zero ou negativo")
    void shouldFailValidationWhenValueIsZeroOrNegative(){
        ServicoRequestDTO dto = new ServicoRequestDTO("Alinhamento", new BigDecimal("-100.00"), "ATIVO");

        Set<ConstraintViolation<ServicoRequestDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }
}
