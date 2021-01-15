package blackjack;

import java.util.Arrays;
import java.util.List;
import javafx.scene.image.*;

/**
 * The Card Class Java File
 * @author Jaime Fleishaur
 * 
 * This is a class file to help create the Card object. Instead of creating
 * individual Image & ImageView, it is easier to use and manipulate an object.
 */

public class Card extends ImageView {
    /**
     * These are the class level private variables. We do not want these 
     * variables to be used outside of this class.
     * 
     * @@ - image - Used to display the front of the drawn card
     * @@ - faceValue - Used to determine the front of the card. (Ace thru King)
     * @@ - suit - Used to determine the suit (Heart, Club, Spade, Diamond)
     * @@ - value - Used to find out the actual value of the face card
     */
    private Image image; 
    private String faceValue, suit;
    private int value; 
    
    
    /**
     * This is the constructor. 
     * Validation is being used instead of the class variables. 
     * The pictures are stored into it's own folder. Cards are listed as 
     * 2/3 alphanumeric values to represent the face card and the suit. 
     * For example: 3 of Clubs is listed as "3C.png". 
     *  
     * @param rank Used to determine the face card name & value
     * @param suit Used to determine the suit of the card.
     */
    public Card(String rank, String suit) {
        suit = suit.toUpperCase();
        setFaceValue(rank);
        setSuit(suit);
        setCardValue(rank);
        String filename = "card/" + rank + suit + ".png";
        image = new Image(filename, 100, 100, true, true);        
    }    
    
    /**
     * This method finds out which card is being drawn. 
     * @return Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King
     */
    public String getFaceValue() {
        return faceValue;
    }
    
    /**
     * Validates & sets Face card name. 
     * If card does not exist, an exception is thrown.
     * 
     * @param r Face card name  
     */
    public void setFaceValue(String r) {
        List<String> validRank = getValidRank();
        
        if(validRank.contains(r)) {
            this.faceValue = r;
        } else {
            throw new IllegalArgumentException("\nValid ranks are: " + validRank);
        }
    }
    
    /**
     * This method retrieves the card's suit.
     * 
     * @return Hearts, Clubs, Spades, Diamonds
     */
    public String getSuit() {
        return suit;
    }
    
    /**
     * Validates & sets card's suit.
     * If card does not exist, an exception is thrown.
     * 
     * @param s The suit of the card
     */
    public void setSuit(String s) {
        List<String> validSuits = getValidSuits();
        
        if(validSuits.contains(s)) {
            this.suit = s;
        } else {
            throw new IllegalArgumentException("\nValid suits are: " + validSuits);
        }
    }
    
    /**
     * This method retrieves the integer value of the Card based on the card's
     * face name. 
     * 
     * @return Value can range between 2 through 11
     */
    public int getCardValue() {
        return value;
    }
    
    //Setting the Card value for BlackJack - arg takes the faceValue
    //Values are set by the rules of BlackJack. Ace is either 1 or 11.
    
    /**
     * This is the point system to determine the value of the drawn card.
     * These rules are based off of the Black Jack rules.
     * The private class variable is set by the following switch statement.
     * The Ace's value is handled in the takeCard() in the Hand class.
     * 
     * @param rank Used to determine the face card's value
     */
    public void setCardValue(String rank){
        switch(rank){
            case "2": value = 2; break;
            case "3": value = 3; break;
            case "4": value = 4; break;
            case "5": value = 5; break;
            case "6": value = 6; break;
            case "7": value = 7; break;
            case "8": value = 8; break;
            case "9": value = 9; break;
            case "10": value = 10; break;
            case "J": value = 10; break;
            case "Q": value = 10; break;
            case "K": value = 10; break;
            case "A": value = 11; break;
            default: value = 1; //This is for the joker
        }
    }
    
    /**
     * This returns a list of valid face names for the card objects.
     * Some names have been reduced to one letter for easy accessibility
     * Currently, there are no Jokers, but they can be easily added in later
     * 2, 3, 4, 5, 6, 7, 8, 9, 10, J-Jack, Q-Queen, K-King, A-Ace
     * 
     * @return Card's face name
     */
    public static List<String> getValidRank() {
        return Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10",
                              "J", "Q", "K", "A");
    }
        
    /**
     * This returns a list of valid suits for card objects.
     * Suit names have been reduced to a single letter for easy accessibility
     * C-Clubs, D-Diamonds, H-Hearts, S-Spades
     * 
     * @return Suit Name
     */
    public static List<String> getValidSuits() {
        return Arrays.asList("C", "D", "H", "S");
    }
    
    /**
     * This method gets the image of the card from the card folder.
     * 
     * @return .png Image
     */
    public Image getCardImg() {
        return image;
    }
    
    /**
     * This method sets the image of the card.
     * 
     * @param CardImg .png image
     */
    public void setCardImg(Image CardImg) {
        this.image = CardImg;
    }
    
    
    /**
     * This method is used to print the Card object in text to the console.
     * This isn't used in the actual game. This was used as a debugging tool to 
     * make sure that the Cards were inputting & outputting correctly.
     * 
     * @return String output on the console of the values of the card name & suit
     */
    @Override
    public String toString() {
        return String.format("%s of %s", faceValue, suit);
    }
    
}
