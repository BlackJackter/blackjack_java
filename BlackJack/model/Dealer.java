package BlackJack.model;

import BlackJack.model.rules.*;

public class Dealer extends Player {

  private Deck m_deck;
  private INewGameStrategy m_newGameRule;
  private IHitStrategy m_hitRule;
  private IWinStrategy winStrat;

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
      m_deck = new Deck();
      ClearHand();
      a_player.ClearHand();
      return m_newGameRule.NewGame(m_deck, this, a_player);
    }
    return false;
  }

  public boolean Hit(Player a_player) {
    if (m_deck != null && a_player.CalcScore() < g_maxScore && !IsGameOver()) {
      Card c;
      c = m_deck.GetCard();
      c.Show(true);
      a_player.DealCard(c);

      return true;
    }
    return false;
  }

  public boolean IsDealerWinner(Player a_player) {
    return winStrat.DoWin(CalcScore(), a_player.CalcScore(), g_maxScore);
  }

  public boolean IsGameOver() {
    if (m_deck != null && m_hitRule.DoHit(this) != true) {
      return true;
    }
    return false;
  }

  public boolean Stand() {
    Card c;

    if (m_deck != null) {
      ShowHand();

      while (m_hitRule.DoHit(this)) {
        c = this.m_deck.GetCard();
        c.Show(true);
        DealCard(c);
      }
    }
    return true;
  }
}