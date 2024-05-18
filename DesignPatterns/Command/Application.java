package Command;

// the command interface declares methods
interface Commander{
    void execute();
}

// concrete command classes witch are doing specific operation
class TurnOnCommnd implements Commander{
    private Device device;

    public TurnOnCommnd(Device device){
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOn();
    }
}

// concrete command classes witch are doing specific operation
class TurnOffCommnd implements Commander{
    private Device device;

    public TurnOffCommnd(Device device){
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOff();
    }
}

// Concrete command for adjusting the volume of a stereo
class AdjustVolumeCommand implements Commander {
    private Stereo stereo;

    public AdjustVolumeCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.adjustVolume();
    }
}

// Concrete command for changing the channel of a TV
class ChangeChannelCommand implements Commander {
    private TV tv;

    public ChangeChannelCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.changeChannel();
    }
}

// The Device interface declares methods related to device functionality, such as turnOn() and turnOff().
interface Device{
    void turnOn();
    void turnOff();
}

class TV implements Device{
    @Override
    public void turnOn() {
        System.out.println("TV is now on");
    }

    @Override
    public void turnOff() {
        System.out.println("TV is now off");
    }

    public void changeChannel() {
        System.out.println("Channel changed");
    }
}

class Stereo implements Device{

    @Override
    public void turnOn() {
        System.out.println("Stereo is now on");
    }

    @Override
    public void turnOff() {
        System.out.println("Stereo is now off");
    }

    void adjustVolume(){
        System.out.println("Volume adjusted");
    }
}

// The invoker class holds a reference to a Command object and triggers its execution through the execute() method.
class RemoteControl {
    private Commander commander;

    public void setCommander(Commander commander){
        this.commander = commander;
    }

    public void pressButton(){
        commander.execute();
    }
}

public class Application {
    public static void main(String[] args){
        TV tv = new TV();
        Stereo stereo = new Stereo();

        Commander turnOn = new TurnOnCommnd(tv);
        Commander turnOff = new TurnOffCommnd(tv);
        Commander channel = new ChangeChannelCommand(tv);
        Commander volume = new AdjustVolumeCommand(stereo);

        RemoteControl remoteControl = new RemoteControl();

        remoteControl.setCommander(turnOn);
        remoteControl.pressButton();

        remoteControl.setCommander(turnOff);
        remoteControl.pressButton();

        remoteControl.setCommander(channel);
        remoteControl.pressButton();

        remoteControl.setCommander(volume);
        remoteControl.pressButton();
    }
}
