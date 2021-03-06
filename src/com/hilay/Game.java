package com.hilay;


import java.util.ArrayList;
/**
 * Created by Hilay on 26-Feb-17.
 */
public class Game
{
    Deck mDeck;
    Hand playerHand;
    Hand dealerHand;
    boolean isGameOn;// boolean isGameInProgress
    boolean isGameOver;
    boolean isBlackjack;
    int dealerScore;
    int playerScore;
    int winner;// => 0(Player won), 1(dealer won), 2(Tie), 3(Not determined yet)
    String whoWon;

    boolean checkBlackJack()
    {
        if(((playerHand.getCardsNum() == 2) && (playerHand.getValue() == 21))
                && ((dealerHand.getCardsNum() == 2) && (dealerHand.getValue() < 21)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    boolean checkDrawBlackJack()
    {
        if(((playerHand.getCardsNum() == 2) && (playerHand.getValue() == 21))
                && ((dealerHand.getCardsNum() == 2) && (dealerHand.getValue() == 21) ) )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    Game()
    {
        mDeck = new Deck();//init the deck //
        mDeck.shuffle();

        //init the hands
        playerHand = new Hand();
        dealerHand = new Hand();

        playerHand.addCard(mDeck.draw());
        dealerHand.addCard(mDeck.draw());
        playerHand.addCard(mDeck.draw());
        dealerHand.addCard(mDeck.draw());

        System.out.println("*********");
        System.out.println("Player's sum is: " + playerHand.getValue());
        IO.print(playerHand.getCards());
        System.out.println();

        System.out.println("Dealer's sum is: " + dealerHand.getCards().get(0).getValue());
        IO.print(dealerHand.getCards(), 1);
        System.out.println("*Hidden*");
        System.out.println("*********");
        System.out.println();

        if(checkBlackJack() == true)
        {
            isBlackjack = true;
            isGameOver = true;
            whoWon = "Player";
        }
        else
        {
            if (checkDrawBlackJack() == true) {
                isBlackjack = false;
                isGameOver = true;
                whoWon = "Tie";
            }
            else
            {
                isBlackjack = false;
                isGameOver = false;
            }
        }
    }

    void play() {
        isGameOver = false;
        do {
            System.out.println("Press '1' - for hit");
            System.out.println("Press '2' - for stand");

            int option = IO.getInt("Enter your option: ", 1, 2);
            switch (option) {
                case 1:
                    hit();
                    break;
                case 2:
                    stand();
                    isGameOver = true;
                    break;
                default:
                    break;
            }
        } while(!isGameOver);
    }

    void hit()
    {
        playerHand.addCard(mDeck.draw());
        System.out.println("*********");
        System.out.println("Player's sum is: " + playerHand.getValue());
        IO.print(playerHand.getCards());
        System.out.println("*********");
        System.out.println();

        if(playerHand.getValue() > 21 )
        {
            isGameOver = true;
            whoWon = "Dealer";
        }
        else
        {
            if(playerHand.getValue() == 21)
            {
                stand();
            }
        }
    }

    //add cards to the dealer using hit until dealer reaches 17
    void stand()
    {
        while(dealerHand.getValue() < 17)
        {
            dealerHand.addCard(mDeck.draw());
        }

        if(dealerHand.getValue() > 21 )
        {
            whoWon = "Player";
        }
        else
        {
            if (playerHand.getValue() > dealerHand.getValue()) {
                whoWon = "Player";
            }
            else
            {
                if (playerHand.getValue() < dealerHand.getValue()) {
                    whoWon = "Dealer";
                }
                else
                {
                    whoWon = "Tie";
                }
            }
        }
    }

    //if the player has 21 with 2 cards

    Hand getPlayerHand()
    {
        return playerHand;
    }

    Hand getDealerHand()
    {
        return dealerHand;
    }

    // restart the game
    void restart()
    {
        mDeck = new Deck();//init the deck //
        //init the hands
        playerHand = new Hand();
        dealerHand = new Hand();

        playerHand.addCard(mDeck.draw());
        dealerHand.addCard(mDeck.draw());
        playerHand.addCard(mDeck.draw());
        dealerHand.addCard(mDeck.draw());

        if(checkBlackJack() == true)
        {
            isBlackjack = true;
            isGameOver = true;
        }
        else
        {
            isBlackjack = false;
            isGameOver = false;
        }
    }

    ArrayList<Card> getPlayerCards()
    {
        return dealerHand.getCards();
    }

    ArrayList<Card> getDealerCards()
    {
        return dealerHand.getCards();
    }

    boolean getIsBlackjack()
    {
        return isBlackjack;
    }

    boolean getIsGameOver()
    {
        return isGameOver;
    }

    String getWhoWon()
    {
        return whoWon;
    }

}