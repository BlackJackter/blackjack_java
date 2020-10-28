package BlackJack.controller;

public interface Subject {
    void Attach(Observer observer);
    void Notify(String message);
    void Deattach(Observer observer);
}
