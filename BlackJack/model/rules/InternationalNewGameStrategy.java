package BlackJack.model.rules;

import BlackJack.controller.Observer;
import BlackJack.model.Deck;
import BlackJack.model.Dealer;
import BlackJack.model.Player;
import BlackJack.model.Card;
import BlackJack.view.SimpleView;

import java.util.ArrayList;
import java.util.List;

class InternationalNewGameStrategy implements INewGameStrategy {
  List<Observer> observerArrayList = new ArrayList<>();

  public boolean NewGame(Deck a_deck, Dealer a_dealer, Player a_player) {
    Attach(new SimpleView());

    Card c1 = a_deck.GetCard();
    c1.Show(true);
    a_player.DealCard(c1);
    Notify("Player: " + card(c1));

    Card c2 = a_deck.GetCard();
    c2.Show(true);
    a_dealer.DealCard(c2);
    Notify("Player: " + card(c1) + "\nDealer: " + card(c2));

    Card c3 = a_deck.GetCard();
    c3.Show(true);
    a_player.DealCard(c3);
    Notify("Player: " + card(c1) + ", " + card(c3) + "\nDealer: " + card(c2));
  
    return true;
  }

  private String card(Card c) {
    return c.GetValue().toString() + " of " + c.GetColor().toString();
  }

  @Override
  public void Deattach(Observer observer) {
    observerArrayList.remove(observer);
  }
  @Override
  public void Attach(Observer observer) {
    observerArrayList.add(observer);
  }

  @Override
  public void Notify(String message) {
    for (Observer observer : observerArrayList) {
      observer.Update(message);
    }
  }
}