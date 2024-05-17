package Bridge;


// The "abstraction" defines the interface for the "control"
// part of the two class hierarchies. It maintains a reference
// to an object of the "implementation" hierarchy and delegates
// all the real work to this object
class RemoteControl{
    protected Device device;

    RemoteControl(Device device){
        this.device = device;
    }

    public void togglePower(){
        if (device.isEnabled()){
            device.disable();
        }
        else{
            device.enable();
        }
    }

    public void volumeDown() {
        device.setVolume(device.getVolume() - 10);
    }

    public void volumeUp() {
        device.setVolume(device.getVolume() + 10);
    }

    public void channelDown() {
        device.setChannel(device.getChannel() - 1);
    }

    public void channelUp() {
        device.setChannel(device.getChannel() + 1);
    }
}

// You can extend classes from the abstraction hierarchy
// independently from device classes.
class AdvancedRemoteControl extends RemoteControl{
    AdvancedRemoteControl(Device device){
        super(device);
    }

    public void mute() {
        device.setVolume(0);
    }
}

// In fact, the two interfaces can be entirely different.
// Typically the implementation interface
// provides only primitive operations, while the abstraction
// defines higher-level operations based on those primitives.
interface Device{
    boolean isEnabled();
    void enable();
    void disable();
    int getVolume();
    void setVolume(int percent);
    int getChannel();
    void setChannel(int channel);
}

class TV implements Device{

    private boolean on = false;
    private int volume = 30;
    private int channel = 1;

    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        on = true;
        System.out.println("TV is Enabled,");
    }

    @Override
    public void disable() {
        on = false;
        System.out.println("TV is disabled.");
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int percent) {
        this.volume = percent;
        System.out.println("TV volume set to " + volume);
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("TV channel set to " + channel);
    }
}

class Radio implements Device{
    private boolean on = false;
    private int volume = 30;
    private int channel = 1;


    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        on = true;
        System.out.println("Radio is Enabled,");
    }

    @Override
    public void disable() {
        on = false;
        System.out.println("Radio is disabled.");
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int percent) {
        this.volume = percent;
        System.out.println("Radio volume set to " + volume);
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("Radio channel set to " + channel);
    }
}


// Somewhere in client code.
public class Application {
    public static void main(String[] args){
        TV tv = new TV();
        RemoteControl remoteControl = new RemoteControl(tv);
        remoteControl.channelDown();
        remoteControl.channelUp();
        remoteControl.togglePower();

        Radio radio = new Radio();
        remoteControl = new AdvancedRemoteControl(radio);
        remoteControl.togglePower();
        remoteControl.channelUp();

        ((AdvancedRemoteControl) remoteControl).mute();
    }
}
