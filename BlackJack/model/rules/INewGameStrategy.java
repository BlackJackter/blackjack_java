package BlackJack.model.rules;

import BlackJack.controller.Subject;
import BlackJack.model.Deck;
import BlackJack.model.Dealer;
import BlackJack.model.Player;

public interface INewGameStrategy extends Subject {
    boolean NewGame(Deck a_deck, Dealer a_dealer, Player a_player);
}