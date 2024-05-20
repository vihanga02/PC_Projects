public class Main {
    public static void main(String[] args) {
        LinkedList weatherDataList = new LinkedList();

        ThreadSafeLinkedList sharedList = new ThreadSafeLinkedList();

        WeatherDevice device1 = new WeatherDevice(sharedList, "ID#01");
        WeatherDevice device2 = new WeatherDevice(sharedList, "ID#02");

        Thread thread1 = new Thread(device1);
        Thread thread2 = new Thread(device2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

//        weatherDataList.addRecord("07FEB2020-07:52", 75.0);
//        weatherDataList.addRecord("08FEB2020-09:15", 80.5);
//        weatherDataList.addRecord("09FEB2020-06:30", 78.2);

        System.out.println("Weather Data Records in Chronological Order:");
        // weatherDataList.printRecords();
        sharedList.printRecords();
    }
}