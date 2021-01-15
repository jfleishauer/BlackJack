package blackjack;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 * Black Jack API Java File
 * @author Jaime Fleishauer 
 * 
 * This class is designed to allow the programmer to create a game of Black Jack.
 * User will implement methods to generate game. There are only 3 buttons on the
 * field, which will tell the game what to do when.
 * 
 * * Docs = Fleishauer_API_BlackJack.docx
 */
public class BlackJack extends Application {

    
    /**
     * These are the public class level variables. Many methods use these vars,
     * so we put them all here so that each method can use the variables and update
     * accordingly.
     * Define SimpleBooleanProperty: Uses full implementation of a Property 
     * wrapping a boolean value
     * 
     * @@ - deck - This is a Deck object made up of Card objects.
     * @@ - dealer - This is the computer's hand of cards
     * @@ - player - This is the player's hand of cards
     * @@ - message - Used to display whom the winner is.
     * @@ - playable - Boolean that tells the program when to stop
     * @@ - compCards - Box to contain computer's hand
     * @@ - playerCards - Box to contain player's hand
     * @@ - hit - Button to add a card to the player's hand
     * @@ - stay - Button to end player turn, deal cards to computer & total scores
     * @@ - reset - Button to start a new game.
     * @@ - count - Count variable used to initialize the scene so we don't duplicate
     */
    private DeckOCards deck = new DeckOCards();
    public Hand dealer, player;
    public Text message = new Text();
    public SimpleBooleanProperty playable = new SimpleBooleanProperty(false); 
    public HBox compCards = new HBox(20), playerCards = new HBox(20);
    public Button hit, stay, reset;
    private int count = 0;
    
    /**
     * This is the main method. This is required to use in an IDE.
     * If this were the console, we do not need a main method.
     * 
     * @param args Runs Program - Launches GUI
     */
    public static void main(String[] args) {        
        //Launch deleted to be overridden in the demo/template class
    }
    
    /**
     * This is the default constructor for when we extend this class to the demo.
     */
    public BlackJack(){
        
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
        //Variables for players scoreboard
        int compScore = 0, playScore = 0;
        
        //Create a pane
        GridPane pane = new GridPane();
       
        //Create Deck of Cards Ready to play with - will be shuffled later
        deck = new DeckOCards();
        
        //Create hands for both players
        dealer = new Hand(compCards.getChildren());
        player = new Hand(playerCards.getChildren());

        //Verify first time creating the scene. If so, set the table
        if(count == 0) {
            pane = settingUpTable(pane);
            count++;
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
        hit.setOnAction(event -> { 
            Card drawOne = deck.dealTopCard();
            player.takeCard(drawOne);
            playerCards.getChildren().add(new ImageView(drawOne.getCardImg()));
        });
        
        //STAY/HOLD BUTTON
        stay.setOnAction(event -> { 
           while (dealer.valueProperty().get() < 17) {
               Card compTurn = deck.dealTopCard();
               dealer.takeCard(compTurn);
               compCards.getChildren().add(new ImageView(compTurn.getCardImg()));               
           } 
           getWinner();
        });
        
        //RESET or NEW GAME BUTTON
        reset.setOnAction(event -> {
            startGame();
        });
        
        
        /**
         * Evaluates player & computer scores based on cards drawn during round.
         * This is where the winner is displayed and determined by a points system.
         */
        player.valueProperty().addListener((obs, old, newValue) -> {
            if (newValue.intValue() >= 21) {
                getWinner();
            }
        });

        dealer.valueProperty().addListener((obs, old, newValue) -> {
            if (newValue.intValue() >= 21) {
                getWinner();
            }
        });
        
        /**
         * Create a scene and place it in the stage.
         */
        Scene scene = new Scene(pane);
        stage.setResizable(false);
        stage.setTitle("Black Jack | 21");        
        stage.setScene(scene);
        stage.show();
    }
    
    
    /**
     * This method is what is used in the reset button to create a new game.
     * This makes sure that the game is playable, the message and players' hands
     * have been reset, and the deck has been redrawn and shuffled.
     * Cards are dealt out one at a time, for a total of 2 cards to each player.
     * Cards are then added to the hands.
     */
    public void startGame() {
        playable.set(true);
        message.setText("");
        
        deck = new DeckOCards();
        deck.shuffleCards();
        dealer.reset();
        player.reset();
        
        //Dealer and player take turns drawing cards.
        Card pc1 = deck.dealTopCard();
        player.takeCard(pc1);
        
        Card dc1 = deck.dealTopCard();
        dealer.takeCard(dc1);        
        
        Card pc2 = deck.dealTopCard();
        player.takeCard(pc2);
        
        Card dc2 = deck.dealTopCard();
        dealer.takeCard(dc2);
        
        playerCards.getChildren().add(new ImageView(pc1.getCardImg()));
        compCards.getChildren().add(new ImageView(dc1.getCardImg()));
        playerCards.getChildren().add(new ImageView(pc2.getCardImg()));
        compCards.getChildren().add(new ImageView(dc2.getCardImg()));
    }

    /**
     * This method is used at the end of the game after the cards have been dealt.
     * We compare the final score value to each of the players and determine who
     * won. Winner is displayed in the middle of the board.
     */
    public void getWinner() {
        playable.set(false);

        int dealerValue = dealer.valueProperty().get();
        int playerValue = player.valueProperty().get();
        String winner = "Exceptional case: dealer: " + dealerValue + " player: " + playerValue;

        // the order of checking is important
        if (dealerValue == 21 || playerValue > 21 || dealerValue == playerValue
                || (dealerValue < 21 && dealerValue > playerValue)) {
            winner = "DEALER";
        }
        else if (playerValue == 21 || dealerValue > 21 || playerValue > dealerValue) {
            winner = "PLAYER";
        }

        message.setText(winner + " WON!!!!!");
    }
    
    /**
     * This method is used to create the scene to help shorten the amount of code
     * in the start method. Below is a short diagram of what each cell should be:
     * 0,0 - Title Text
     * 0,1 - Computer/Dealer's Hand of Cards HBox
     * 0,2 - Computer/Dealer's Score Text
     * 0,3 - Winner Message Text (Blank when not being used) 
     * 0,4 - Player's Hand of Cards HBox
     * 0,5 - Player's Score Text
     * 0,6 - Buttons
     * 
     * @param gp GridPane used in start method
     * @return Fully plotted arg GridPane/table - Could be Parent instead
     */
    public GridPane settingUpTable(GridPane gp){
        //Text objects used to display numerous things. 
        Text title = new Text("Welcome to BlackJack"); 
        title.setFont(Font.font("Small Caps", FontWeight.BOLD, 
                FontPosture.ITALIC, 40));
        Text playerScore = new Text("Player Score: "); 
        playerScore.setFont(Font.font("Comic Sans MS", 20));
        Text computerScore = new Text("Computer Score: "); 
        computerScore.setFont(Font.font("Comic Sans MS", 20));
        message.setFont(Font.font("Lucida Handwriting", FontWeight.BOLD, 50)); 
        
        //Setting up pane
        gp.setPrefSize(750, 500);
        gp.setStyle("-fx-background-color: FORESTGREEN");
        gp.setPadding(new Insets(5, 5, 5, 5));
                
        //TitleBox - Welcome to BlackJack
        HBox titleBox = new HBox(20);
        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.TOP_CENTER);
        
        //MessageBox to display winner
        HBox messageBox = new HBox(20);
        messageBox.getChildren().add(message);
        messageBox.setAlignment(Pos.TOP_CENTER);
        
        /**
         * Buttons - Hit, Stay, Reset/New Game.
         * There is also a rectangle here to act as a spacer between the buttons.         * 
         */
        HBox buttons = new HBox(15);
        hit = new Button("Hit me!");
        stay = new Button("Stay");
        reset = new Button("New Game");
        Rectangle r = new Rectangle();
        r.setWidth(500);
        r.setHeight(15);
        r.setFill(Color.FORESTGREEN);
        buttons.setPadding(new Insets(15, 15, 15, 15));
        buttons.getChildren().addAll(hit, stay, r, reset);
        buttons.setAlignment(Pos.CENTER);
        
        
        //Adding to pane
        gp.add(titleBox, 0, 0);
        gp.add(compCards, 0, 1);
        gp.add(computerScore, 0, 2); 
        gp.add(messageBox, 0, 3);
        gp.add(playerCards, 0, 4);
        gp.add(playerScore, 0, 5); 
        gp.add(buttons, 0, 6);
        
        /**
         * Binding Properties.
         * These are used in conjuction with the JavaFX program. This is what makes
         * the game work as playable or not, and to dynamically update the score
         * boards for both players.
         */ 
        hit.disableProperty().bind(playable.not());
        stay.disableProperty().bind(playable.not());
        reset.disableProperty().bind(playable);
        playerScore.textProperty().bind(new SimpleStringProperty("Player: ").concat(player.valueProperty().asString()));
        computerScore.textProperty().bind(new SimpleStringProperty("Dealer: ").concat(dealer.valueProperty().asString()));
        
        return gp;
    }
    
   
}