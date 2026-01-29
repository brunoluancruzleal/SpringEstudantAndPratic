package com.bruno.spring.Project.Spring.controller;

import com.bruno.spring.Project.Spring.exceptions.ArgumentMathillegalExeception;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/math")
public class MathController {


    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable String numberOne,
            @PathVariable String numberTwo) {
        if (!isNumber(numberOne) || isNumber(numberTwo))
            throw new ArgumentMathillegalExeception("Illegal argument found");

        return Double.parseDouble(numberOne) + Double.parseDouble(numberTwo);
    }

    @RequestMapping("/sub/{numberOne}/{numberTwo}")
    public Double subitracao(
            @PathVariable String numberOne,
            @PathVariable String numberTwo) {
        if (!isNumber(numberOne) || !isNumber(numberTwo))
            throw new ArgumentMathillegalExeception("Illegal argument found");

        return Double.parseDouble(numberOne) - Double.parseDouble(numberTwo);
    }

    @RequestMapping("/mult/{numberOne}/{numberTwo}")
    public Double mult(
            @PathVariable String numberOne,
            @PathVariable String numberTwo) {
        if (!isNumber(numberOne) || !isNumber(numberTwo))
            throw new ArgumentMathillegalExeception("Illegal argument found");

        return Double.parseDouble(numberOne) * Double.parseDouble(numberTwo);
    }

    @RequestMapping("/div/{numberOne}/{numberTwo}")
    public Double div(
            @PathVariable String numberOne,
            @PathVariable String numberTwo) {
        if (!isNumber(numberOne) || !isNumber(numberTwo))
            throw new ArgumentMathillegalExeception("Illegal argument found");

        if (!validNumberZero(numberTwo)) throw new ArgumentMathillegalExeception("Illegal argument found");

        return Double.parseDouble(numberOne) / Double.parseDouble(numberTwo);
    }

    @GetMapping("/media")
    public Double media(@RequestParam List<Double> value) {

        if (value == null || value.isEmpty()) throw new ArgumentMathillegalExeception("The array cannot be empty.");

        double soma = 0;
        for (Double listMedia : value) {
            soma += listMedia;
        }

        return soma / value.size();
    }

    @GetMapping("/raiz/{number}")
    public Double raizQuadrada(
            @PathVariable String number){

        if (!isNumber(number)) throw new ArgumentMathillegalExeception("Illegal argument found");

        return Math.sqrt(Double.parseDouble(number));
    }


    private boolean validNumberZero(String numberTwo) {
        if (Integer.parseInt(numberTwo) == 0) {
            throw new ArgumentMathillegalExeception(
                    "The second argument cannot be zero ");
        }
        return false;
    }


    private boolean isNumber(String number) {
        if (number == null || number.isEmpty()) return false;

        try {
            Double.parseDouble(number.replace(",", "."));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
