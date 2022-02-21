package edu.ntnu.idatt2105.backend.Service;

import edu.ntnu.idatt2105.backend.model.Equation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CalculatorService {

    private double answer;
    private Equation equation;
    private ArrayList<String>  log = new ArrayList<>();
    /*
    @Autowired
    private CalculatorRepo repo;
    //Used for databse etc
     */

    public double solve(Equation equation){
        this.equation = equation;
        if(equation.getOperator() == '+'){
            return answer = equation.getN1() + equation.getN2();
        }else if(equation.getOperator() == '-'){
            return answer = equation.getN1() - equation.getN2();
        }else if(equation.getOperator() == '*'){
            return answer = equation.getN1() * equation.getN2();
        }else if(equation.getOperator() == '/'){
            return answer = equation.getN1() / equation.getN2();
        }
        return answer = 0;
    }

    public double getAnswer() {
        return answer;
    }

    public String toString(){
        return equation.toString() + " = " + answer;
    }

    public boolean addToLog(String toAdd){
        if(log.size() == 0 || !toAdd.equals(log.get(log.size()-1))){
            log.add(toAdd);
            return true;
        }
        return false;
    }

    public ArrayList<String> getLog() {
        return log;
    }
}
