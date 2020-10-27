package BlackJack.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class EventSource {

    public interface Observer {
        void update(String event);
    }

    final List<Observer> observers = new ArrayList<>();

    private void notifyObservers(String event) {
        observers.forEach(observer -> observer.update(event)); //alternative lambda expression: observers.forEach(Observer::update);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void scanSystemIn() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            notifyObservers(line);
        }
    }
}
