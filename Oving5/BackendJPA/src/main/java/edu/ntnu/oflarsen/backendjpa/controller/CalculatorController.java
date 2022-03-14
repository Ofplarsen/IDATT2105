package edu.ntnu.oflarsen.backendjpa.controller;

import edu.ntnu.oflarsen.backendjpa.model.Equation;
import edu.ntnu.oflarsen.backendjpa.model.Tutorial;
import edu.ntnu.oflarsen.backendjpa.service.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/calc")
public class CalculatorController {

    @Autowired
    private CalculatorService service;

    Logger logger = LoggerFactory.getLogger(CalculatorController.class);


    @GetMapping("/ans")
    public double answer(){
        logger.info("Retrieved answer: " + service.getAnswer());
        return service.getAnswer();
    }

    @PostMapping("/solve")
    public double solve(@RequestBody Equation equation){
        Equation solvedE = service.solve(equation);
        logger.info("Equation: n1: " + equation.getN1() +", n2: " +  equation.getN2()
        + ", operator: " + equation.getOperator());
        logger.info("Answer: " + service.getAnswer());
        solvedE.setUser(LoginController.getLoggedIn());
        if(service.addToLog(solvedE)){
            logger.info("Added to log: " + service.toString());
        }

        return service.getAnswer();
    }


    @GetMapping("/log/{page}")
    public ArrayList<String> log(@PathVariable("page") long page){
        //logger.info("Returned log: " + service.toString());
        logger.info(String.valueOf(page));
        ArrayList<Equation> equations = (ArrayList<Equation>) service.getLog(page);

        ArrayList<String> strings  = new ArrayList<>();

        for (Equation e : equations){
            strings.add(e.toString());
        }

        return strings;
    }

}
