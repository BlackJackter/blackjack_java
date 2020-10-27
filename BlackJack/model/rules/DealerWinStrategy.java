package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

public class DealerWinStrategy implements IWinStrategy{

    // Take code from already existing method from Dealer class
    @Override
    public boolean DoWin(int a_dealerScore, int a_playerScore, int g_max) {
        if (a_playerScore > g_max) return true;
        else if (a_dealerScore > g_max) return false;

        return a_dealerScore >= a_playerScore;

    }
}
