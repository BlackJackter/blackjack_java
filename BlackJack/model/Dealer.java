package BlackJack.model;

import BlackJack.model.rules.*;
import BlackJack.view.SimpleView;

public class Dealer extends Player {

  private Deck m_deck;
  private INewGameStrategy m_newGameRule;
  private IHitStrategy m_hitRule;
  private IWinStrategy winStrat;
  private SimpleView view = new SimpleView();

  public Dealer(RulesFactory a_rulesFactory) {

    m_newGameRule = a_rulesFactory.GetNewGameRule();
    m_hitRule = a_rulesFactory.GetHitRule();
    winStrat = a_rulesFactory.getWinner();

    /*for(Card c : m_deck.GetCards()) {
      c.Show(true);
      System.out.println("" + c.GetValue() + " of " + c.GetColor());
    }    */
  }


  public boolean NewGame(Player a_player) {
    if (m_deck == null || IsGameOver()) {
      Attach(view);
      m_deck = new Deck();
      ClearHand();
      a_player.ClearHand();
      return m_newGameRule.NewGame(m_deck, this, a_player);
    }
    return false;
  }

  public boolean Hit(Player a_player) {
    if (m_deck != null && a_player.CalcScore() < g_maxScore && !IsGameOver()) {
      RefactorHit(a_player);
      return true;
    }
    return false;
  }

  public void RefactorHit(Player a_player) {
    Card c;
    c = m_deck.GetCard();
    c.Show(true);
    if (a_player == null) { // Dealer card
      DealCard(c);
      Notify("Dealer: " + card(c));
    } else { // Player card
      a_player.DealCard(c);
      Notify("Player: " + card(c));
    }
  }

  public boolean IsDealerWinner(Player a_player) {
    return winStrat.DoWin(CalcScore(), a_player.CalcScore(), g_maxScore);
  }

  public boolean IsGameOver() {
    if (m_deck != null && m_hitRule.DoHit(this) != true) {
      Deattach(view);
      return true;
    }
    return false;
  }

  public String card(Card c) {
    return c.GetValue().toString() + " of " + c.GetColor().toString();
  }

  public boolean Stand() {
    if (m_deck != null) {
      ShowHand();

      while (m_hitRule.DoHit(this)) {
        RefactorHit(null);
      }
    }
    return true;
  }
}