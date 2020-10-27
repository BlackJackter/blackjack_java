package BlackJack.model.rules;

import BlackJack.model.Card;
import BlackJack.model.Player;

public class Soft17 implements IHitStrategy {
    private final int g_hitLimit = 17;

    @Override
    public boolean DoHit(Player a_dealer) {

        if (a_dealer.CalcScore() < g_hitLimit) {
            return true; // Score is 18 or higher, CANT TAKE CARD

        } else if (a_dealer.CalcScore() == g_hitLimit) { // Score is 17! Take one card if you posses an ace
            int aceCounter = 0;
            Iterable<Card> hand = a_dealer.GetHand();

            for (Card card : hand) {
                if (card.GetValue() == Card.Value.Ace) aceCounter++;
            }

            if (aceCounter > 0) return false; // You found an ace! Take one card!
        }
        return false;
    }
}
