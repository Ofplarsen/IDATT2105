package edu.ntnu.oflarsen.backendjpa.service;


import edu.ntnu.oflarsen.backendjpa.controller.CalculatorController;
import edu.ntnu.oflarsen.backendjpa.controller.LoginController;
import edu.ntnu.oflarsen.backendjpa.model.Equation;
import edu.ntnu.oflarsen.backendjpa.repository.EquationRepository;
import edu.ntnu.oflarsen.backendjpa.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculatorService {

    @Autowired
    EquationRepository equationRepository;

    private double answer;
    private Equation equation;
    private ArrayList<String>  log = new ArrayList<>();

    public Equation solve(Equation equation){
        this.equation = equation;
        answer = 0;
        if(equation.getOperator() == '+'){
            answer = equation.getN1() + equation.getN2();
            equation.setAnswer(answer);
        }else if(equation.getOperator() == '-'){
            answer = equation.getN1() - equation.getN2();
        }else if(equation.getOperator() == '*'){
            answer = equation.getN1() * equation.getN2();
        }else if(equation.getOperator() == '/'){
            answer = equation.getN1() / equation.getN2();
        }
        equation.setAnswer(answer);
        return equation;
    }

    public double getAnswer() {
        return answer;
    }

    public String toString(){
        return equation.toString();
    }

    public boolean addToLog(Equation e){
        //if(equation.equals(e))
        //    return false;
        equationRepository.save(new Equation(e.getN1(), e.getN2(), e.getOperator(), e.getAnswer(), e.getUser()));
        return true;
    }

    public List<Equation> getLog(long page) {
        if(LoginController.getLoggedIn() == null)
            return new ArrayList<Equation>();
        return equationRepository.findById(LoginController.getLoggedIn().getId(), page);
    }
}
