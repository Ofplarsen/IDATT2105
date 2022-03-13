package edu.ntnu.oflarsen.backendjpa.service;


import edu.ntnu.oflarsen.backendjpa.model.Equation;
import edu.ntnu.oflarsen.backendjpa.repository.EquationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
        return equation.toString() + " = " + answer;
    }

    public boolean addToLog(Equation e){
        if(equation.equals(e))
            return false;
        equationRepository.save(new Equation(e));
        return true;
    }

    public ArrayList<String> getLog() {
        return log;
    }
}
