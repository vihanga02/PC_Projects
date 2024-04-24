package Adapter;

class PIRGP2d120 { // Adaptee
    private double vout;
    public PIRGP2d120() { vout = 0.0; }
    public double getVoltage() {
// code for reading Vo pin of GP2d120 module
        return vout;
    }
}
interface DistanceSensor { // Target
    public double getDistance();
}

class PIRDistanceSensor implements DistanceSensor { // Adapter
    private PIRGP2d120 sensor;
    PIRDistanceSensor() { sensor = new PIRGP2d120(); }
    public double getDistance() {
        double v = sensor.getVoltage();
        double d=0.0;
//code to convert voltage to distance using graph in datasheet
        d=12.34; // example
        return d;
    }
}
public class RobotCar {
    public static void main(String [] args) {
        DistanceSensor ds = new PIRDistanceSensor();
        System.out.println("Obstacle at "+ds.getDistance()+" cm");
    }
}
