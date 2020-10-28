package BlackJack.controller;

import BlackJack.model.Card;

public interface Subject {
    void Attach(Observer observer);
    void Notify(String message);
    void Deattach(Observer observer);
}
