package com.tranphunguyen.mymall.model;

public class RatingModel {
    private int start;
    private int amount;
    private int percentProgress;

    public RatingModel(int start, int amount, int percentProgress) {
        this.start = start;
        this.amount = amount;
        this.percentProgress = percentProgress;
    }

    public int getPercentProgress() {
        return percentProgress;
    }

    public void setPercentProgress(int percentProgress) {
        this.percentProgress = percentProgress;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
