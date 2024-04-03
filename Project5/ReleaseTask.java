// ReleaseTask class to represent the release operation
class ReleaseTask implements Runnable {
    private StudyRoomReservationSystem reservationSystem;
    private int roomNO;

    public ReleaseTask(StudyRoomReservationSystem reservationSystem, int roomNO) {
        this.reservationSystem = reservationSystem;
        this.roomNO = roomNO;
    }

    @Override
    public void run() {
        try {
            System.out.println("Running the release thread");
            Thread.sleep(120); // Simulate work
            reservationSystem.releaseStudyRoom(roomNO);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
