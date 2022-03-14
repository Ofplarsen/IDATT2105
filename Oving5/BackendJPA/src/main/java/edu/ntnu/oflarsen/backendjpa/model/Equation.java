package edu.ntnu.oflarsen.backendjpa.model;

import javax.persistence.*;

@Entity
@Table(name = "equations")
public class Equation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "n1")
    private double n1;
    @Column(name = "n2")
    private double n2;
    @Column(name = "operator")
    private char operator;
    @Column(name = "answer")
    private double answer;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Login user;

    public Equation() {
    }

    public Equation(int id, double n1, double n2, char operator, double answer, Login user) {
        this.id = id;
        this.n1 = n1;
        this.n2 = n2;
        this.operator = operator;
        this.answer = answer;
        this.user = user;
    }

    public Equation(Equation e){
        this.id = e.getId();
        this.n1 = e.getN1();
        this.n2 = e.getN2();
        this.operator = e.getOperator();
        this.answer = e.getAnswer();
        this.user = e.getUser();
    }

    public Equation(double n1, double n2, char operator, double answer, Login user) {
        this.n1 = n1;
        this.n2 = n2;
        this.operator = operator;
        this.answer = answer;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public Login getUser() {
        return user;
    }

    public double getN1() {
        return n1;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public double getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public void setUser(Login user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return n1 + " " + operator + " " + n2 + " = " + answer;
    }
}
