package edu.ntnu.idatt2105.backend.Service;

import edu.ntnu.idatt2105.backend.model.Equation;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    private double answer;
    private Equation equation;
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
}
