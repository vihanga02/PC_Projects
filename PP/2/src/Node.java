public class Node {
    private String dateTime;
    private double measurement;
    private Node next;

    Node(String dateTime, double measurement){
        this.dateTime = dateTime;
        this.measurement = measurement;
        this.next = null;
    }

    public Node getNext() {
        return next;
    }

    public double getMeasurement() {
        return measurement;
    }

     public String getDateTime(){
        return dateTime;
     }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setMeasurement(double measurement) {
        this.measurement = measurement;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
