package com.exam.examserver.Entity.exam;

public class Result {
    double marksGot;
    int correctAnsers;
    int attempted;

    public Result() {
    }

    public Result(double marksGot, int correctAnsers, int attempted) {
        this.marksGot = marksGot;
        this.correctAnsers = correctAnsers;
        this.attempted = attempted;
    }

    public double getMarksGot() {
        return marksGot;
    }

    public void setMarksGot(double marksGot) {
        this.marksGot = marksGot;
    }

    public int getCorrectAnsers() {
        return correctAnsers;
    }

    public void setCorrectAnsers(int correctAnsers) {
        this.correctAnsers = correctAnsers;
    }

    public int getAttempted() {
        return attempted;
    }

    public void setAttempted(int attempted) {
        this.attempted = attempted;
    }
}
