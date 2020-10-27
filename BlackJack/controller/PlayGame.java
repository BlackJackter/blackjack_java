package BlackJack.controller;

import BlackJack.view.IView;
import BlackJack.model.Game;

public class PlayGame implements Observer{

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
      a_game.Stand();
    }

    return input != IView.InputMenu.q;
  }

  @Override
  public void update() {

  }
}
