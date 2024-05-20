public class WeatherDevice implements Runnable{
    private ThreadSafeLinkedList sharedList;
    private String deviceId;

    public WeatherDevice(ThreadSafeLinkedList sharedList, String deviceId) {
        this.sharedList = sharedList;
        this.deviceId = deviceId;
    }
    @Override
    public void run() {
        sharedList.addRecord(deviceId + "-10FEB2020-08:00", 65.4);
        sharedList.addRecord(deviceId + "-10FEB2020-09:00", 66.7);
        sharedList.addRecord(deviceId + "-10FEB2020-10:00", 67.1);
    }



}
