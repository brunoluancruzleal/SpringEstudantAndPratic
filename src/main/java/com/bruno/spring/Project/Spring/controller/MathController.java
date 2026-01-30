package com.bruno.spring.Project.Spring.controller;

import com.bruno.spring.Project.Spring.services.MathServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/math")
public class MathController {


    private MathServices mathServices;

    public MathController(MathServices mathServices) {
        this.mathServices = mathServices;
    }

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable String numberOne,
            @PathVariable String numberTwo
    ) {
        return mathServices.sum(numberOne, numberTwo);
    }

    @GetMapping("/subi/{numberOne}/{numberTwo}")
    public Double subitracao(
            @PathVariable String numberOne,
            @PathVariable String numberTwo
    ) {
        return mathServices.subtracao(numberOne, numberTwo);
    }

    @GetMapping("/multi/{numberOne}/{numberTwo}")
    public Double multi(
            @PathVariable String numberOne,
            @PathVariable String numberTwo
    ) {
        return mathServices.mult(numberOne, numberTwo);
    }

    @GetMapping("/div/{numberOne}/{numberTwo}")
    public Double divisao(
            @PathVariable String numberOne,
            @PathVariable String numberTwo
    ) {
        return mathServices.div(numberOne, numberTwo);
    }

    @GetMapping("/raiz/{number}")
    public Double raizQuadrada(
            @PathVariable String number

    ) {
        return mathServices.raizQuadrada(number);
    }

    @GetMapping("/media")
    public Double media(
            @RequestParam List<Double> value

    ) {
        return mathServices.media(value);
    }

}
