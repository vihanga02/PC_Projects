public class LinkedList {
    private Node head;

    LinkedList(){
        this.head = null;
    }

    public void addRecord(String dateTime, double measurement){
        if (head == null){
            head = new Node(dateTime, measurement);
        }
        else{
            Node current = head;
            while (current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(new Node(dateTime, measurement));
        }
    }

    public void printRecords() {
        Node current = head;
        while (current != null) {
            System.out.println("DateTime: " + current.getDateTime() + ", Measurement: " + current.getMeasurement());
            current = current.getNext();
        }
    }
}
