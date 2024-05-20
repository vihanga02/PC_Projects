public class Main {
    public static void main(String[] args) {
        Cooker cooker1 = new Cooker("Cooker1");
        Cooker cooker2 = new Cooker("Cooker2");

        RobotArm robotArm = new RobotArm();

        Thread cookerThread1 = new Thread(cooker1);
        Thread cookerThread2 = new Thread(cooker2);

        Thread roboThread = new Thread(robotArm);

        cookerThread1.start();
        cookerThread2.start();
        roboThread.start();
    }
}