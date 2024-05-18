package Observer;

import java.util.ArrayList;
import java.util.List;

// The “Subject" interface outlines the operations a subject (like “WeatherStation") should support.
interface Subject{
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver();
}

// this is the observer interface which need to notify when a chanje happens in the subject
interface Observer{
    void update(String weather);
}

// concrete implementation of the subject
class WeatherStation implements Subject{
    // maintain a list of observers who are need to notify
    private List<Observer> observers = new ArrayList<>();
    private String weather;

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers){
            observer.update(weather);
        }
    }

    public void setWeather(String weather) {
        this.weather = weather;
        notifyObserver();
    }
}

// concrete implementation of the observer
class PhoneDisplay implements Observer{
    private String weather;

    @Override
    public void update(String weather) {
        this.weather = weather;
        display();
    }

    private void display() {
        System.out.println("Phone Display: Weather updated - " + weather);
    }
}

// ConcreteObserver Class
class TVDisplay implements Observer {
    private String weather;

    @Override
    public void update(String weather) {
        this.weather = weather;
        display();
    }

    private void display() {
        System.out.println("TV Display: Weather updated - " + weather);
    }
}

public class Main {
    public static void main(String[] args){
        WeatherStation weatherStation = new WeatherStation();

        Observer phone = new PhoneDisplay();
        Observer tv = new TVDisplay();

        weatherStation.addObserver(phone);
        weatherStation.setWeather("Sunny");

        weatherStation.addObserver(tv);
        weatherStation.setWeather("Rain");

        weatherStation.removeObserver(tv);
        weatherStation.setWeather("Cloudy");
    }
}