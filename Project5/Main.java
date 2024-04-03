public class Main {
    public static void main(String[] args) {
        StudyRoomReservationSystem reservationSystem = new StudyRoomReservationSystem();

        reservationSystem.displayStudyRoomStatus();

        // Create reservation and release tasks
        ReservationTask reservationTask1 = new ReservationTask(reservationSystem,10);
        ReservationTask reservationTask2 = new ReservationTask(reservationSystem,2);

        ReleaseTask releaseTask1 = new ReleaseTask(reservationSystem, 2);
        ReleaseTask releaseTask2 = new ReleaseTask(reservationSystem, 3);

        // Create and start threads
        Thread thread1 = new Thread(reservationTask1,"User1");
        Thread thread2 = new Thread(reservationTask2,"User2");
        Thread thread3 = new Thread(releaseTask1,"User1");
        Thread thread4 = new Thread(releaseTask2,"User3");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        // Wait for threads to finish
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        reservationSystem.saveInAFile();
    }
}
