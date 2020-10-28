package BlackJack.controller;

import BlackJack.model.Card;
import BlackJack.view.IView;
import BlackJack.model.Game;

import java.util.ArrayList;
import java.util.List;

public class PlayGame implements Subject{
  List<Observer> observerList = new ArrayList<>();

  public boolean Play(Game a_game, IView a_view) {
    a_view.DisplayWelcomeMessage();
    
    a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
    a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());

    if (a_game.IsGameOver())
    {
        a_view.DisplayGameOver(a_game.IsDealerWinner());
    }

    IView.InputMenu input = a_view.GetInput();

    if (input == IView.InputMenu.p) {
      a_game.NewGame();
    } else if (input == IView.InputMenu.h) {
      a_game.Hit();
    } else if (input == IView.InputMenu.s) {
      Card card = null;
      for(Card c : a_game.GetDealerHand()) {
        card = c;
      }
      card.Show(true);
      Notify("Dealer hidden card: " + card.GetValue().toString() + " of " + card.GetColor().toString());
      a_game.Stand();
    }

    return input != IView.InputMenu.q;
  }



  @Override
  public void Attach(Observer observer) {
    observerList.add(observer);
  }

  @Override
  public void Deattach(Observer observer) {
    observerList.remove(observer);
  }

  @Override
  public void Notify(String message) {
    for (Observer observer : observerList) {
      observer.Update(message);
    }
  }
}
