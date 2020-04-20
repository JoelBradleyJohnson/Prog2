package model;

public class Circuit {
    private double resistance;
    private double voltage;

    public double getAmperage() {
        return voltage / resistance;
    }

    public double getResistance() {
        return resistance;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setResistance(double resistance) {
        this.resistance = resistance;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }
}