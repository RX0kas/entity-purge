package fr.rxokas.entitypurge.util;

public class Accumulator {
    /**Tick value**/
    float total;
    float threshold;

    /**
     *
     * @param threshold In minutes
     */
    public Accumulator(float threshold) {
        this.threshold = threshold*1200;
    }

    public boolean canProcess() {
        return total >= threshold;
    }

    public void reduce() {
        total -= threshold;
    }

    public void add() {
        total += 1;
    }

    public boolean lessThan10SecRemaining() {return total+400>=threshold;}
}