package blackjack;

import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Black Jack Demo Java File
 * @author Jaime Fleishauer 
 * 
 * This class is designed to demonstrate how the BlackJack API works.
 * Using an instance of the class (Not needed as we extend BlackJack class, BUT
 * easier to see what each method does, without referencing the API documentation PDF)
 * 
 * * Docs = Fleishauer_API_BlackJack.docx
 */
public class BlackJack_Demo extends BlackJack {

    /**
     * This is a private class level variable. 
     * 
     * @@ - i - Count variable used to initialize the scene so we don't duplicate
     * @@ - deck - This is a Deck object made up of Card objects.
     */
    private int i = 0;
    private DeckOCards deck = new DeckOCards();
    
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
        //Variables for players scoreboard & object for methods
        int compScore = 0, playScore = 0;
        BlackJack blackJack = new BlackJack();
        
        //Create a pane
        GridPane pane = new GridPane();
        
        //Create hands for both players
        blackJack.dealer = new Hand(blackJack.compCards.getChildren());
        blackJack.player = new Hand(blackJack.playerCards.getChildren());

        //Verify first time creating the scene. If so, set the table
        if(i == 0) {
            pane = blackJack.settingUpTable(pane);
            i++;
        } 
        
        /**
         * Action buttons - This is where the game functions.
         * The hit button is made to add a card to the player's hand, and display
         * The stay button is when the player is done making their move, and 
         *      ends the game. The computer then draws until it busts or reaches
         *      its own limit.
         * The reset button is used to create a new game.
         */
        //HIT BUTTON
        blackJack.hit.setOnAction(event -> { 
            Card drawOne = deck.dealTopCard();
            blackJack.player.takeCard(drawOne);
            blackJack.playerCards.getChildren().add(new ImageView(drawOne.getCardImg()));
        });
        
        //STAY/HOLD BUTTON
        blackJack.stay.setOnAction(event -> { 
           while (blackJack.dealer.valueProperty().get() < 17) {
               Card compTurn = deck.dealTopCard();
               blackJack.dealer.takeCard(compTurn);
               blackJack.compCards.getChildren().add(new ImageView(compTurn.getCardImg()));               
           } 
           blackJack.getWinner();
        });
        
        //RESET or NEW GAME BUTTON
        blackJack.reset.setOnAction(event -> {
            blackJack.startGame();
        });
        
        
        /**
         * Evaluates player & computer scores based on cards drawn during round.
         * This is where the winner is displayed and determined by a points system.
         */
        blackJack.player.valueProperty().addListener((obs, old, newValue) -> {
            if (newValue.intValue() >= 21) {
                blackJack.getWinner();
            }
        });

        blackJack.dealer.valueProperty().addListener((obs, old, newValue) -> {
            if (newValue.intValue() >= 21) {
                blackJack.getWinner();
            }
        });
        
        /**
         * Create a scene and place it in the stage.
         * Here is where we set the pane to the scene and display in a pop up window.
         * We made sure that the user cannot resize the view.         * 
         */
        Scene scene = new Scene(pane);
        stage.setResizable(false);
        stage.setTitle("Black Jack | 21");        
        stage.setScene(scene);
        stage.show();
    }
}