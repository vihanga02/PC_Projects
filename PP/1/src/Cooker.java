public class Cooker implements Runnable{
    private String name;

    Cooker(String name){
        this.name = name;
    }
    private void produce() throws InterruptedException {
        String popcornBag = "Popcorn bag from " + name;
        PopCornMachine.outputBin.put(popcornBag);
        System.out.println(name + " produced: " + popcornBag);
        Thread.sleep(2000);
    }

    @Override
    public void run() {
        try {
            while (true){
                produce();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
