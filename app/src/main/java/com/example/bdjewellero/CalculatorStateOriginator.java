package com.example.bdjewellero;


public class CalculatorStateOriginator {
    private String ed1, ed2, ed3, ed4, ed5, ed6;
    private String result;

    public void setState(String ed1, String ed2, String ed3, String ed4, 
                        String ed5, String ed6, String result) {
        this.ed1 = ed1;
        this.ed2 = ed2;
        this.ed3 = ed3;
        this.ed4 = ed4;
        this.ed5 = ed5;
        this.ed6 = ed6;
        this.result = result;
    }

    public CalculatorMemento saveToMemento() {
        return new CalculatorMemento(ed1, ed2, ed3, ed4, ed5, ed6, result);
    }

    public void restoreFromMemento(CalculatorMemento memento) {
        this.ed1 = memento.getEd1();
        this.ed2 = memento.getEd2();
        this.ed3 = memento.getEd3();
        this.ed4 = memento.getEd4();
        this.ed5 = memento.getEd5();
        this.ed6 = memento.getEd6();
        this.result = memento.getResult();
    }
}
