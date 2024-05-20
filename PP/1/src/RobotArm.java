public class RobotArm implements Runnable{

    public void pickup() throws InterruptedException {
        String popcornBag = PopCornMachine.outputBin.take();
        System.out.println("Robot arm picked: " + popcornBag);
        Thread.sleep(1000);
    }

    @Override
    public void run() {
        try {
            while (true) {
                pickup();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
