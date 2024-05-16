package Command;

interface Command {
    public void execute();
}
class LightBulb {
    public void on() { System.out.println("Light is on"); }
    public void off() { System.out.println("Light is off"); }
}
class LightBulbOnCommand implements Command {
    private LightBulb lightb;
    public LightBulbOnCommand(LightBulb lightb) {
        this.lightb = lightb;
    }
    public void execute() { lightb.on(); }
}
class LightBulbOffCommand implements Command {
    private LightBulb lightb;
    public LightBulbOffCommand(LightBulb lightb) {
        this.lightb = lightb;
    }
    public void execute() { lightb.off(); }
}
class Television {
    public void on() { System.out.println("Television is on"); }
    public void off() { System.out.println("Television is off"); }
    public int getStoredPreferences(String param) {
        System.out.println("retrieving parameters for "+param);
        return 1;
    }
    public void setChannel(int channel) { }
    public void setVolume(int volume) { }
}
class TelevisionOnCommand implements Command {
    private Television tv;
    public TelevisionOnCommand(Television tv) { this.tv = tv; }
    public void execute() {
        int channel = tv.getStoredPreferences("channel");
        int volume = tv.getStoredPreferences("volume");
        tv.setChannel(channel);
        tv.setVolume(volume);
        tv.on();
    }
}
class TelevisionOffCommand implements Command {
    private Television tv;
    public TelevisionOffCommand(Television tv) { this.tv = tv; }
    public void execute() { tv.off(); }
}
class Remote {
    private Command cmd;
    public void setCommand(Command cmd) { this.cmd = cmd; }
    public void operate() { cmd.execute(); }
}
public class CommandTest2 {
    public static void main (String [] args) {
        LightBulb bulb1 = new LightBulb(); // we create a light bulb
        Television tv1 = new Television(); // we create a television
        Remote remote = new Remote(); // we create a "common" remote
// the remote is "dynamically" passed a specfic command for a
// particular device. Thereafter, the remote operates in a
// "generic" manner. This leads to "loose coupling".
        remote.setCommand(new LightBulbOnCommand(bulb1));
        remote.operate();
        remote.setCommand(new TelevisionOnCommand(tv1));
        remote.operate();
        remote.setCommand(new TelevisionOffCommand(tv1));
        remote.operate();
        remote.setCommand(new LightBulbOffCommand(bulb1));
        remote.operate();
    }
}