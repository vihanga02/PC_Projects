public class RobotArm implements Runnable{

    public void pickup() throws InterruptedException {
        String popcornBag = PopCornMachine.outputBin.take();
        System.out.println("Robot arm picked: " + popcornBag);
        PopCornMachine.Bell.ring();
        PopCornMachine.Light.blink();
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
