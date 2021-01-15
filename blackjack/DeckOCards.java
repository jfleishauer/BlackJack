package blackjack;

import java.util.*;
import javafx.scene.image.*;

/**
 * The Deck of Cards Java Class File
 * @author Jaime Fleishauer
 * 
 * This is a class file to help create the Deck of Cards object. We are using the
 * Card objects to create this full deck. This class is set up to not only use a
 * full 52 card deck, but can also be used for special smaller decks.
 */

public class DeckOCards extends ImageView {
    /**
     * These are the class level private variables. We do not want these 
     * variables to be used outside of this class.
     * 
     * @@ - deck - Used to house all of the Card objects
     * @@ - backOfCardImg - Used to default the top card of the deck
     */
    private ArrayList<Card> deck;
    private Image backOfCardImg; 
    
    /**
     * This is the default constructor to make a full 52/54 card deck.
     * This takes the card's information, suit & face name, and fills a deck using
     * the fore loop. We are using static methods instead of making individual card
     * instances. This deck is currently not shuffled.
     * Also, setting the top card to show the back of the card. 
     * We do not have the deck on the scene, but this can be used for other games.     * 
     */
    public DeckOCards() {
        List<String> suits = Card.getValidSuits(); 
        List<String> faceValue = Card.getValidRank();
        
        deck = new ArrayList<>();
        
        //Creating the deck using nested for loops
        for (String suit : suits) {            
            for (String face : faceValue) {
                deck.add(new Card(face, suit));//creating new Card object and adding to deck
            }
        }
        
        backOfCardImg = new Image("card/b1fv.png", 100, 100, false, false);
    }
    
    //Constructor that passes in a collection of cards for games that do not require the full deck
    /**
     * This is an overloaded constructor for a smaller deck.
     * The deck has already been made elsewhere, and the cards are all in order, 
     * but not shuffled. Also, setting the top card to show the back of the card.
     * We do not have the deck on the scene, but this can be used for other games.   
     * 
     * @param d Smaller deck of cards (less than 52/54 cards)
     */
    public DeckOCards(ArrayList<Card> d) {
        this.deck = d;
        backOfCardImg = new Image("card/b1fv.png", 100, 100, false, false);
    }
    
    /**
     * This method gives a deck to the user.
     * The number of cards are set elsewhere
     * 
     * @return A Deck of Cards
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }
    
    /**
     * This method makes or sets the deck.
     * 
     * @param deck A Deck of Cards
     */
    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }
    
    /**
     * This method finds the back of the card picture in the card folder
     * 
     * @return png Image
     */
    public Image getBackOfCardImg() {
        return backOfCardImg;
    }
    
    /**
     * This method sets the picture to an object.
     * 
     * @param backOfCardImage png Image
     */
    public void setBackOfCardImg(Image backOfCardImage) {
        this.backOfCardImg = backOfCardImage;
    }
    
    //Dealing cards for game. This will return a Card object on top
    /**
     * This is a draw card method. We are "popping" off 1 card at a time.
     * First, it validates if the deck is empty so it doesn't draw a blank or
     * non-existing card. Then, it draws and delete the top card on the deck.
     * This will be used with takeCard() in Hand class.
     * 
     * @return Card object at position 0 in ArrayList
     */
    public Card dealTopCard(){
        if(!deck.isEmpty()){//As long as the deck is not empty
            //System.out.println(deck.remove(0));
            return deck.remove(0);
        } else {//If deck empty, return nothing
            return null;
        }
    }
    
    //Shuffle Cards using the ArrayList shuffle function
    /**
     * This method shuffles the cards. When you start a new game, this method is
     * called before the game begins. This method does not split/cut the deck
     * like in real life. This is a void method that takes no args, but uses the
     * deck in question.
     */
    public void shuffleCards() {
        Collections.shuffle(deck);
    }
    
    
}





