package com.example.bdjewellero;


public class CalculatorMemento {
    private final String ed1, ed2, ed3, ed4, ed5, ed6;
    private final String result;

    public CalculatorMemento(String ed1, String ed2, String ed3, String ed4, 
                           String ed5, String ed6, String result) {
        this.ed1 = ed1;
        this.ed2 = ed2;
        this.ed3 = ed3;
        this.ed4 = ed4;
        this.ed5 = ed5;
        this.ed6 = ed6;
        this.result = result;
    }

    public String getEd1() { return ed1; }
    public String getEd2() { return ed2; }
    public String getEd3() { return ed3; }
    public String getEd4() { return ed4; }
    public String getEd5() { return ed5; }
    public String getEd6() { return ed6; }
    public String getResult() { return result; }
}
