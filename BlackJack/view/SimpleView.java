package BlackJack.view;

import BlackJack.controller.Subject;

public class SimpleView implements IView
{

    public void DisplayWelcomeMessage()
    {
        for(int i = 0; i < 50; i++) {System.out.print("\n");};
        System.out.println("Hello Black Jack World");
        System.out.println("Type 'p' to Play, 'h' to Hit, 's' to Stand or 'q' to Quit\n");
    }

    public InputMenu GetInput()
    {
        try {
            int c = System.in.read();
            while (c == '\r' || c =='\n') {
                c = System.in.read();
            }
            if (c == 'p') {
                return InputMenu.p;
            } else if (c == 'h') {
                return InputMenu.h;
            } else if (c == 's') {
                return InputMenu.s;
            } else if (c == 'q') {
                return InputMenu.q;
            } else {
                return InputMenu.invalid;
            }
        } catch (java.io.IOException e) {
            System.out.println("" + e);
            return InputMenu.invalid;
        }
    }

    public void DisplayCard(BlackJack.model.Card a_card)
    {
        System.out.println("" + a_card.GetValue() + " of " + a_card.GetColor());
    }

    public void DisplayPlayerHand(Iterable<BlackJack.model.Card> a_hand, int a_score)
    {
        DisplayHand("Player", a_hand, a_score);
    }

    public void DisplayDealerHand(Iterable<BlackJack.model.Card> a_hand, int a_score)
    {
        DisplayHand("Dealer", a_hand, a_score);
    }

    private void DisplayHand(String a_name, Iterable<BlackJack.model.Card> a_hand, int a_score)
    {
        System.out.println(a_name + " Has: ");
        for(BlackJack.model.Card c : a_hand)
        {
            DisplayCard(c);
        }
        System.out.println("Score: " + a_score);
        System.out.println("");
    }

    public void DisplayGameOver(boolean a_dealerIsWinner)
    {
        System.out.println("GameOver: ");
        if (a_dealerIsWinner)
        {
            System.out.println("Dealer Won!");
        }
        else
        {
            System.out.println("You Won!");
        }

    }

    @Override
    public void Update(String message) {
        try {
            System.out.println(message);
            System.out.println("*pause*");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
