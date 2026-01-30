package com.bruno.spring.Project.Spring.services;

import com.bruno.spring.Project.Spring.exceptions.ArgumentMathillegalExeception;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MathServices {

    public Double sum(String numberOne, String numberTwo) {
        validateNumbers(numberOne, numberTwo);
        return parse(numberOne) + parse(numberTwo);
    }

    public Double subtracao(String numberOne, String numberTwo) {
        validateNumbers(numberOne, numberTwo);
        return parse(numberOne) - parse(numberTwo);
    }

    public Double mult(String numberOne, String numberTwo) {
        validateNumbers(numberOne, numberTwo);
        return parse(numberOne) * parse(numberTwo);
    }

    public Double div(String numberOne, String numberTwo) {
        validateNumbers(numberOne, numberTwo);

        if (isZero(numberTwo)) {
            throw new ArgumentMathillegalExeception("Division by zero is not allowed");
        }

        return parse(numberOne) / parse(numberTwo);
    }

    public Double media(List<Double> values) {
        if (values == null || values.isEmpty()) {
            throw new ArgumentMathillegalExeception("The array cannot be empty");
        }

        return values.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElseThrow();
    }

    public Double raizQuadrada(String number) {
        if (!isNumber(number)) {
            throw new ArgumentMathillegalExeception("Illegal argument found");
        }

        return Math.sqrt(parse(number));
    }

    // ===== Helpers =====

    private void validateNumbers(String n1, String n2) {
        if (!isNumber(n1) || !isNumber(n2)) {
            throw new ArgumentMathillegalExeception("Illegal argument found");
        }
    }

    private boolean isZero(String number) {
        return parse(number) == 0;
    }

    private boolean isNumber(String number) {
        if (number == null || number.isEmpty()) return false;

        try {
            parse(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Double parse(String number) {
        return Double.parseDouble(number.replace(",", "."));
    }
}

