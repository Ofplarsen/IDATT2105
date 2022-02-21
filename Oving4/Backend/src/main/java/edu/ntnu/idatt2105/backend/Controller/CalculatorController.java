package edu.ntnu.idatt2105.backend.Controller;

import edu.ntnu.idatt2105.backend.Service.CalculatorService;
import edu.ntnu.idatt2105.backend.model.Equation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @Autowired
    private CalculatorService service;

    Logger logger = LoggerFactory.getLogger(CalculatorController.class);


    @GetMapping("calc/ans")
    public double answer(){
        logger.info("Retrieved answer: " + service.getAnswer());
        return service.getAnswer();
    }

    @PostMapping("calc/solve")
    public double solve(@RequestBody Equation equation){
        service.solve(equation);
        logger.info("Equation: n1: " + equation.getN1() +", n2: " +  equation.getN2()
        + ", operator: " + equation.getOperator());
        logger.info("Answer: " + service.getAnswer());
        return service.getAnswer();
    }


}
