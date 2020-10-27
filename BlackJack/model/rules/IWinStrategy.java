package BlackJack.model.rules;

// Take code from already existing method from Dealer class
public interface IWinStrategy {
    boolean DoWin(int a_dealerScore, int a_playerScore, int g_max);
}
