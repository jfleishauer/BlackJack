package blackjack;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.*;
        
/**
 * The Hand Class Java File
 * @author Jaime Fleishauer
 * 
 * This class file is used to create a player's hand of cards. Cards continuously 
 * being added into Hands, and then stuffed and into Boxes in the main method.
 */


public class Hand extends ImageView {
    /**
     * These are the class level private variables. We do not want these 
     * variables to be used outside of this class. This is used to add cards
     * dynamically.
     * 
     * @@ - cards - Objects used to create hand. Allows listeners to track changes.
     * @@ - value - Uses full implementation of a Property wrapping a int value
     * @@ - aces - Counter to tally aces for scoring purposes
     */
    private ObservableList<Node> cards;
    private SimpleIntegerProperty value = new SimpleIntegerProperty(0);
    private int aces = 0;
    
    /**
     * This is a constructor.
     * This is used to create a hand full of cards, or however many drawn
     * 
     * @param hand Hand full of Card Objects
     */
    public Hand(ObservableList<Node> hand) {
        this.cards = hand;
    }

    /**
     * This method is used in conjunction with the drawTopCard() from DeckOCards.
     * First, we "push" the card from the deck and add it into a hand.
     * Here, Aces are evaluated and counted for the following algo. 
     * Aces are set to 11 originally, so if the hand exceeds 21 while we have an
     * Ace, then the value of the Ace is turned to 1. This is to make sure we
     * don't bust.
     * Last, add the total points of the card to the player's score.
     * 
     * 
     * @param card Card Object drawn from the top of the deck
     */
    public void takeCard(Card card) {
        cards.add(card);       
        
        if (card.getCardValue() == 11) {
            aces++;
        }

        if (value.get() + card.getCardValue() > 21 && aces > 0) {
            //then count ace as '1' not '11'
            value.set(value.get() + card.getCardValue() - 10);    
            aces--;
        }
        else {
            value.set(value.get() + card.getCardValue());
        }
        
        
    }

    //Restart for the new round
    /**
     * This is how a new round is made.
     * We make sure to clear all of the values before starting a new game.
     * 
     * @@ - cards - Clears hand array
     * @@ - value - Reset hand value to 0
     * @@ - aces - Reset Ace counter
     */
    public void reset() {
        cards.clear();
        value.set(0);
        aces = 0;
    }
    
    /**
     * This method is used to bind to the hand, and is needed for JavaFX.
     * This is used dynamically for both dealer & player scores.     * 
     * 
     * @return int value of player's score
     */
    public SimpleIntegerProperty valueProperty() {
        return value;
    }
    
    

}
