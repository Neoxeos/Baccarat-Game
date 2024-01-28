import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.BackgroundImage;


public class BaccaratGame extends Application {
	
	private Button btn1, btn2, btn3, btn_bot, returnBack, finish;
	private BorderPane p1, p2, mainArea;
	private TextField t1;
	private Label main_banker, main_player, score, displayHand, displayHand2, displayHand3, main_player2, main_banker2, score2, displayHand4;
	private MenuBar top;
	private Menu topMenu;
	static int bet;
	private String betName;
	private BaccaratDealer gameDeck;
	private ArrayList<Card> playerH, bankerH;
	private BaccaratGameLogic mainDriver;
	private HBox p, b;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Welcome to Bacarrat");
		
		//buttons
		Font font = Font.font("Courier New", FontWeight.BOLD, 36);
		btn1 = new Button("PLAY");
		btn2 = new Button("RULES");

		btn1.setFont(font);
		btn2.setFont(font);

		btn1.setMaxWidth(Double.MAX_VALUE);
		btn2.setMaxWidth(Double.MAX_VALUE);

		VBox buttonBox = new VBox();
		buttonBox.setSpacing(10);
		buttonBox.setPadding(new Insets(0, 20, 10, 20)); 
		buttonBox.getChildren().addAll(btn1, btn2);
		
		
		//border pane
		p1 = new BorderPane();
		p1.setLeft(buttonBox);
		//p1.setStyle("-fx-background-color: beige;");
		
		// Set background
	    Image image2 = new Image("https://img.freepik.com/free-vector/casino-background-design_1314-186.jpg?w=740&t=st=1698610132~exp=1698610732~hmac=613dbd9b23dc1bde895121a8c990a047212e50aba57c58c9395d11d8e966e781");
	    BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true);
	    Background background2 = new Background(new BackgroundImage(image2,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.CENTER,
	            bSize));
	     
	 
		//two separate parts for banker and player
		// make baccarat dealer
		gameDeck = new BaccaratDealer();
		gameDeck.shuffleDeck();
		mainDriver = new BaccaratGameLogic();
		playerH = gameDeck.dealHand();
		bankerH = gameDeck.dealHand();
		
		
		//Events
		//text fields
		Label betLabel = new Label("Enter bet amount: ");
		t1 = new TextField("");
		Label pickLabel = new Label("Pick Banker or Player to win: ");
		btn_bot = new Button("ENTER");
		
        // player or banker
        String playerOrBanker[] = { "Player", "Banker" };
 
        // Create a combo box
        ComboBox combo_box = new ComboBox(FXCollections.observableArrayList(playerOrBanker));
        
        // Grouping
        VBox container = new VBox();
		t1.setFont(font);
		pickLabel.setFont(font);
		btn_bot.setFont(font);
		t1.setMaxWidth(Double.MAX_VALUE);
		pickLabel.setMaxWidth(Double.MAX_VALUE);
		btn_bot.setMaxWidth(Double.MAX_VALUE);

		container.setSpacing(10);
		container.setPadding(new Insets(0, 20, 10, 20)); 
		container.getChildren().addAll(t1, pickLabel, combo_box, btn_bot);
		
		
	     // menu bar
	     top = new MenuBar();
	     topMenu = new Menu("Options");
	     MenuItem menuItem1 = new MenuItem("Quit");
	     MenuItem menuItem2 = new MenuItem("Restart");
	     topMenu.getItems().add(menuItem1);
	     topMenu.getItems().add(menuItem2);
	     menuItem1.setOnAction(e -> {
	         Platform.exit();
	         System.exit(0);
	     });
	     
	    // restart game
		menuItem2.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent action) {
					// create new root and pass to scene
					BorderPane root2 = new BorderPane();
					
					// Set background
				    Image image3 = new Image("https://images.unsplash.com/photo-1605870445919-838d190e8e1b?auto=format&fit=crop&q=80&w=2072&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
				    BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true);
				    Background background3 = new Background(new BackgroundImage(image3,
				            BackgroundRepeat.NO_REPEAT,
				            BackgroundRepeat.NO_REPEAT,
				            BackgroundPosition.CENTER,
				            bSize));
				    
					gameDeck.shuffleDeck();
					playerH.clear();
					bankerH.clear();
					playerH = gameDeck.dealHand();
					bankerH = gameDeck.dealHand();

					
					// add to root
					root2.setTop(top);
					root2.setCenter(container);
					root2.setBackground(background3);
					primaryStage.getScene().setRoot(root2);
					
				}
			});
	     top.getMenus().add(topMenu);
	     
	     
	     // Main window that shows when application starts
	     BorderPane root = new BorderPane();
	     root.setTop(top);
	     root.setLeft(p1);
	     root.setBackground(background2);
	     
	     
	     // set main scene
	     Scene scene = new Scene(root, 800,800);
			primaryStage.setScene(scene);
			primaryStage.show();
		
		
		// Play button transition


	        
			btn1.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent action) {
					// create new root and pass to scene
					BorderPane root2 = new BorderPane();
					
					// Set background
				    Image image3 = new Image("https://images.unsplash.com/photo-1605870445919-838d190e8e1b?auto=format&fit=crop&q=80&w=2072&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
				    BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true);
				    Background background3 = new Background(new BackgroundImage(image3,
				            BackgroundRepeat.NO_REPEAT,
				            BackgroundRepeat.NO_REPEAT,
				            BackgroundPosition.CENTER,
				            bSize));
					
					// add to root
					root2.setTop(top);
					root2.setCenter(container);
					root2.setBackground(background3);
					primaryStage.getScene().setRoot(root2);
					
				}
			});
			
			
			//rules section
			Label rules = new Label("• We use six decks of in a card shoe to enable easy,\n"
					+ "controlled dealing.\n"
					+ "• The suits of the cards are irrelevant.\n"
					+ "• Face cards and 10’s count as zero (’Baccarat’ pronounced ‘Bak-Ar-Ah’).\n"
					+ "• Aces always count as one.\n"
					+ "• When two cards are dealt and the total of the cards\n"
					+ "equals more than nine, remove the first number of\n"
					+ "the total. For example, if the Player's hand is a 9 and\n"
					+ "a 6, the total is 15, which means your Baccarat hand\n"
					+ "is 5.\n"
					+ "• To even out the odds, the casino charges up to five\n"
					+ "percent commission when a bet is placed on the\n"
					+ "Banker and the Banker's hand wins. No commission\n"
					+ "is charged on bets placed on the Players hand.\n"
					+ "• In the event of a tie and no one bet on tie, the hand\n"
					+ "is considered a ‘push’— all wagers are returned.\n"
					+ "Game Play\n"
					+ "• After shuffling the cards, the dealer asks a player to\n"
					+ "cut the cards using the cut-card. The Cards are then\n"
					+ "placed in the show. The dealer draws and shows the\n"
					+ "first card. Further cards are drawn and burned according to the value of the first card drawn (e.g. draw\n"
					+ "‘3’, burn three further cards).\n"
					+ "• Place your bet on either the section labelled Player,\n"
					+ "the section labelled Banker (Dealer) or the section\n"
					+ "labelled Tie.\n"
					+ "• The game is started by dealing two cards for the\n"
					+ "player hand and two cards for the bank hand. An initial\n"
					+ "hand with a value of 8 or 9 is called a \"natural.\"\n"
					+ "• If either hand is a natural, its holder must expose it\n"
					+ "and the game ends. Otherwise play continues, first\n"
					+ "with the player hand and then with the banker hand.\n"
					+ "• The winning hand is the one with a total of 9 or as\n"
					+ "close to 9 as possible.\n"
					+ "• Rules for the player hand: If the player's first two cards\n"
					+ "total 6 or more, then the player must stand without\n"
					+ "drawing a card. If the player's first two cards total 5 or\n"
					+ "less, the player must draw one additional card.\n"
					+ "• Rules for the banker hand: If the banker's first two cards\n"
					+ "total 7 or more, then the banker must stand without\n"
					+ "drawing a card. If the banker's first two cards total 0, 1, or 2, then the banker must draw one card. If the\n"
					+ "banker's first two cards total 3, 4, 5, or 6, then whether the banker draws is determined by the\n"
					+ "whether the player drew");
			returnBack = new Button("Back");
			btn2.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent action) {
					// create new root and pass to scene
					BorderPane root4 = new BorderPane();
					
					// add to root
					root4.setTop(top);
					root4.setCenter(rules);
					root4.setBottom(returnBack);
					root4.setStyle("-fx-background-color: cyan;");
					primaryStage.getScene().setRoot(root4);
					
				}
			});
			
			// Go Back to main screen
			returnBack.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent action) {
					primaryStage.getScene().setRoot(root);		
				}
			});
			
			

			
			// Player
			main_player = new Label("Player");
			main_player.setStyle("-fx-font-weight: bold;");
			main_player.setFont(new Font(20));
			main_player.setStyle("-fx-text-fill: red"); 
			displayHand = new Label("Player hand: " + String.valueOf(mainDriver.handTotal(playerH)));
			displayHand.setStyle("-fx-font-weight: bold;");
			displayHand.setStyle("-fx-text-fill: red"); 
			displayHand.setFont(new Font(20));
			Button continueB = new Button("Continue");
			p = new HBox(150, main_player, displayHand, continueB);
			
			// Banker
			main_banker = new Label("Banker");
			main_banker.setStyle("-fx-font-weight: bold;");
			main_banker.setStyle("-fx-text-fill: red");
			main_banker.setFont(new Font(20));
			displayHand2 = new Label("Banker hand: " + String.valueOf(mainDriver.handTotal(bankerH)));
			displayHand2.setStyle("-fx-font-weight: bold;");
			displayHand2.setStyle("-fx-text-fill: red"); 
			displayHand2.setFont(new Font(20));
			score = new Label();
			score.setStyle("-fx-font-weight: bold;");
			score.setStyle("-fx-text-fill: red"); 
			score.setFont(new Font(20));
			b = new HBox(150, main_banker, displayHand2, score);
			
			// Main area
			mainArea = new BorderPane();
			

			
			btn_bot.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent action) {
					// create new root and pass to scene
					BorderPane root3 = new BorderPane();
					
					// Set background
				    Image image4 = new Image("https://i.ytimg.com/vi/svbfeOyXfPI/maxresdefault.jpg");
				    BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true);
				    Background background4 = new Background(new BackgroundImage(image4,
				            BackgroundRepeat.NO_REPEAT,
				            BackgroundRepeat.NO_REPEAT,
				            BackgroundPosition.CENTER,
				            bSize));
					
					// get money amount
					bet = Integer.parseInt(t1.getText());
					betName = (String) combo_box.getValue();
					score.setText("The current bet is: " + bet);
					displayHand = new Label("Player hand: " + String.valueOf(mainDriver.handTotal(playerH)));
					displayHand.setStyle("-fx-font-weight: bold;");
					displayHand.setStyle("-fx-text-fill: red"); 
					displayHand.setFont(new Font(20));
					displayHand2 = new Label("Banker hand: " + String.valueOf(mainDriver.handTotal(bankerH)));
					displayHand2.setStyle("-fx-font-weight: bold;");
					displayHand2.setStyle("-fx-text-fill: red"); 
					displayHand2.setFont(new Font(20));
					p = new HBox(150, main_player, displayHand, continueB);
					b = new HBox(150, main_banker, displayHand2, score);
					
					// add to root
					mainArea = new BorderPane();
					mainArea.setTop(b);
					mainArea.setBottom(p);
					root3.setTop(top);
					root3.setCenter(mainArea);
					root3.setBackground(background4);
					//root3.setStyle("-fx-background-color: green;");
					primaryStage.getScene().setRoot(root3);
				}
		
			});
			// Player
			main_player2 = new Label("Player");
			main_player2.setStyle("-fx-font-weight: bold;");
			main_player2.setFont(new Font(20));
			main_player2.setStyle("-fx-text-fill: red"); 
			displayHand3 = new Label("Player hand: " + String.valueOf(mainDriver.handTotal(playerH)));
			displayHand3.setStyle("-fx-font-weight: bold;");
			displayHand3.setStyle("-fx-text-fill: red"); 
			displayHand3.setFont(new Font(20));
			finish = new Button("Finish");
			HBox pl2 = new HBox(150, main_player2, displayHand3, finish);
			
			// Banker
			main_banker2 = new Label("Banker");
			main_banker2.setStyle("-fx-font-weight: bold;");
			main_banker2.setStyle("-fx-text-fill: red");
			main_banker2.setFont(new Font(20));
			displayHand4 = new Label("Banker hand: " + String.valueOf(mainDriver.handTotal(bankerH)));
			displayHand4.setStyle("-fx-font-weight: bold;");
			displayHand4.setStyle("-fx-text-fill: red"); 
			displayHand4.setFont(new Font(20));
			score2 = new Label();
			score2.setStyle("-fx-font-weight: bold;");
			score2.setStyle("-fx-text-fill: red"); 
			score2.setFont(new Font(20));
			HBox ba2 = new HBox(150, main_banker2, displayHand4, score2);
			
			// Main area
			BorderPane mainArea2 = new BorderPane();
			
			
			// Continue
			continueB.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent action) {
					// create new root and pass to scene
					BorderPane root4 = new BorderPane();
					
					// Set background
				    Image image5 = new Image("https://i.ytimg.com/vi/svbfeOyXfPI/maxresdefault.jpg");
				    BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true);
				    Background background5 = new Background(new BackgroundImage(image5,
				            BackgroundRepeat.NO_REPEAT,
				            BackgroundRepeat.NO_REPEAT,
				            BackgroundPosition.CENTER,
				            bSize));
					
					// get money amount
					bet = Integer.parseInt(t1.getText());
					betName = (String) combo_box.getValue();
					score2.setText("The current bet is: " + bet);
					
					Card drawn = new Card();
					if (mainDriver.handTotal(playerH) == 8 | mainDriver.handTotal(playerH) == 9) {
						// Set background
					    Image image6 = new Image("https://img.freepik.com/free-photo/realistic-casino-background-with-flying-chips-golden-coins-dice-ai_91128-2306.jpg?size=626&ext=jpg&ga=GA1.1.115887927.1698610129&semt=ais");
					    bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true);
					    Background background4 = new Background(new BackgroundImage(image6,
					            BackgroundRepeat.NO_REPEAT,
					            BackgroundRepeat.NO_REPEAT,
					            BackgroundPosition.CENTER,
					            bSize));
					    
						Label winner = new Label("Congratulations the Player won!");
						winner.setStyle("-fx-font-weight: bold;");
						winner.setFont(new Font(60));
						winner.setStyle("-fx-text-fill: red"); 
						winner.setMaxWidth(Double.MAX_VALUE);
						HBox container2 = new HBox(winner);
						
						// add to root
						root4.setCenter(container2);
						root4.setTop(top);
						root4.setBackground(background4);
						//root3.setStyle("-fx-background-color: green;");
						primaryStage.getScene().setRoot(root4);
						return;
					}
					// check cards
					if (mainDriver.evaluatePlayerDraw(playerH)) {
						playerH.add(gameDeck.drawOne());
						drawn = playerH.get(2);
					} 
					if (mainDriver.evaluateBankerDraw(bankerH, drawn)) {
						bankerH.add(gameDeck.drawOne());
					}
					
					
					// add to root
					displayHand3.setText("Player hand: " + String.valueOf(mainDriver.handTotal(playerH)));
					displayHand4.setText("Banker hand: " + String.valueOf(mainDriver.handTotal(bankerH)));
					mainArea2.setTop(ba2);
					mainArea2.setBottom(pl2);
					root4.setTop(top);
					root4.setCenter(mainArea2);
					root4.setBackground(background5);
					//root3.setStyle("-fx-background-color: green;");
					primaryStage.getScene().setRoot(root4);
				}
		
			});

			finish.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent action) {
					// create new root and pass to scene
					BorderPane root5 = new BorderPane();
					
					// Set background
				    Image image6 = new Image("https://img.freepik.com/free-photo/realistic-casino-background-with-flying-chips-golden-coins-dice-ai_91128-2306.jpg?size=626&ext=jpg&ga=GA1.1.115887927.1698610129&semt=ais");
				    BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true);
				    Background background4 = new Background(new BackgroundImage(image6,
				            BackgroundRepeat.NO_REPEAT,
				            BackgroundRepeat.NO_REPEAT,
				            BackgroundPosition.CENTER,
				            bSize));
					
					// get money amount
					bet = Integer.parseInt(t1.getText());
					betName = (String) combo_box.getValue();
					score.setText("The current bet is: " + bet);
					
					String whoWins = mainDriver.whoWon(playerH, bankerH);
					if (whoWins == "Draw") {
						whoWins = "The game is a draw!";
					}else if (whoWins == betName) {
						whoWins = "Congratulations the Banker won!";
					} else {
						whoWins = "Congratulations the Player won!";
					}
					
					Label winner = new Label(whoWins);
					winner.setStyle("-fx-font-weight: bold;");
					winner.setFont(new Font(60));
					winner.setStyle("-fx-text-fill: red"); 
					winner.setMaxWidth(Double.MAX_VALUE);
					HBox container2 = new HBox(winner);
					
					// add to root
					root5.setCenter(container2);
					root5.setTop(top);
					root5.setBackground(background4);
					//root3.setStyle("-fx-background-color: green;");
					primaryStage.getScene().setRoot(root5);
				}
		
			});
			
	}
}
