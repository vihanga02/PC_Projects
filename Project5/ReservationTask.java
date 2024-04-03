// ReservationTask class to represent the reservation operation
class ReservationTask implements Runnable {
    private StudyRoomReservationSystem reservationSystem;
    private int roomNo;
    private int temp;

    public ReservationTask (StudyRoomReservationSystem reservationSystem, int roomNo) {
        this.reservationSystem = reservationSystem;
        this.roomNo = roomNo;
        this.temp = roomNo;
    }

    @Override
    public void run() {
        try {
            System.out.println("Running reservation thread");
            boolean reserved = false;
            while (!reserved) {
                try {
                    Thread.sleep(120); // Simulate work
                    reservationSystem.reserveStudyRoom(roomNo);
                    reserved = true; // Set to true if reservation succeeds
                } catch (StudyRoomUnavailableException e) {
                    roomNo++;
                    // Handle the exception without printing the stack trace
                    System.out.println(e.getMessage());
                    if (roomNo == 11){
                        roomNo = 1;
                    }
                    if (roomNo == temp){
                        reserved = true;
                    }

                    Thread.sleep(300); // Wait before attempting reservation again
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
