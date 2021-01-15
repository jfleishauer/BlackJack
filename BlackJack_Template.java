package blackjack;

import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Black Jack Template Java File
 * @author Jaime Fleishauer 
 * 
 * This class is designed to allow the programmer to create a game of Black Jack.
 * User will implement methods to generate game. There are only 3 buttons on the
 * field, which will tell the game what to do when.
 * 
 * * Docs = Fleishauer_API_BlackJack.docx
 */
public class BlackJack_Template extends BlackJack {

    /**
     * This is a private class level variable. This 
     * 
     * @@ - i - Count variable used to initialize the scene so we don't duplicate
     */
    private int round = 0;
    private DeckOCards deck = new DeckOCards(); // Creates a new deck of cards
    
    
    /**
     * This is the main method. This is required to use in an IDE.
     * If this were the console, we do not need a main method.
     * 
     * @param args Runs Program - Launches GUI
     */
    public static void main(String[] args) {        
        launch(args);
    }

    /**
     * This is the start method to set the Stage, and where the game takes place.
     * Here is where the scene is made, and the game functions are added in.
     * The scene loads a blank table, until you hit the "New Game" button.
     * 
     * @param stage primaryStage
     */
    @Override
    public void start(Stage stage) {
        //Variables for program
        int compScore = 0, playScore = 0; //Tallies up score
        BlackJack blackJack = new BlackJack();
        GridPane pane = new GridPane();
        
        //Create hands for both players
        blackJack.dealer = new Hand(blackJack.compCards.getChildren());
        blackJack.player = new Hand(blackJack.playerCards.getChildren());

        /****TODO: Create Scene****/
        //Verify 1st time creating a scene. - blackJack.settingUpTable();
        //Create this control structure statment around pane
        // *IF* so, set the table & increment the private "round" counter.
        
            //pane = 
         
        
            
            
            
        /**
         * Action buttons - This is where the game functions.
         * The hit button is made to add a card to the player's hand, and display
         * The stay button is when the player is done making their move, and 
         *      ends the game. The computer then draws until it busts or reaches
         *      its own limit.
         * The reset button is used to create a new game.
         */
        
        
        /****TODO: HIT BUTTON****/
        //We need to do the following when a button is pressed:
        //1) Draw a card from the deck - deck.dealTopCard()
        //2) Add that same card to the players hand - blackJack.player.takeCard();
        //3) Last, add the image to the scene - (drawOne.getCardImg())
        
        blackJack.hit.setOnAction(event -> { 
            //Card drawOne
            //blackJack.player
            //blackJack.playerCards.getChildren().add(new ImageView
        });
        
        /****TODO: STAY/HOLD BUTTON****/
        //We need to do the following while dealer still draws:
        //1) Draw a card from the deck - deck.dealTopCard();
        //2) Add that card to the players hand - blackJack.dealer.takeCard();
        //3) Add the image to the scene - (compTurn.getCardImg())
        //4) Once computer is done dealing, time to calculate & display winner - blackJack.getWinner();
        
        blackJack.stay.setOnAction(event -> { 
           while (blackJack.dealer.valueProperty().get() < 17) {
               //Card compTurn = 
               //blackJack.dealer
               //blackJack.compCards.getChildren().add(new ImageView              
           } 
           //blackJack
        });
        
        /****TODO: RESET/NEW GAME BUTTON****/
        //Implement a new game using a method - blackJack.startGame();
        blackJack.reset.setOnAction(event -> {
            //blackJack
        });
        
        
        /**
         * Evaluates player & computer scores based on cards drawn during round.
         * This is where the winner is displayed and determined by a points system.
         */
        
        /****TODO: WINNER****/
        //Call the method that helps us determine & display the winner - blackJack.getWinner();
        blackJack.player.valueProperty().addListener((obs, old, newValue) -> {
            if (newValue.intValue() >= 21) {
                //blackJack
            }
        });

        /****TODO: WINNER****/
        //Call the method that helps us determine & display the winner - blackJack.getWinner();
        blackJack.dealer.valueProperty().addListener((obs, old, newValue) -> {
            if (newValue.intValue() >= 21) {
                //blackJack
            }
        });
        
        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        stage.setResizable(false);
        stage.setTitle("Black Jack | 21");        
        stage.setScene(scene);
        stage.show();
    }
}