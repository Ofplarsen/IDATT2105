package edu.ntnu.idatt2105.backend.model;

public class Equation {
    private double n1;
    private int n2;
    private double operator;

    public double getN1() {
        return n1;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public int getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public double getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return n1 + "" + operator + "" + n2;
    }
}
