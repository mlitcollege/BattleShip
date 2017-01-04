package Main;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameBoard {

	GridPane gridPane = new GridPane();
	
	int lengthInPixels = 1000;
	int lengthInShips = 8;
	Navy navy;
	Scene scene = new Scene(gridPane, lengthInPixels, lengthInPixels);
	
	

	// Main -> GameBoard
	
	public GameBoard(int howManyShips) {
		openStage();
		
		// making new ships
		navy = new Navy(howManyShips, lengthInShips); 
		
		// show ships on GameBoard
		refreshGameBoard();
		
		// saves mouse click
		setClickEvent(); 
	}

	// this is the main stage
	
	public void openStage() {
		Stage gameBoardStage = new Stage();
		gameBoardStage.setScene(scene);
		gameBoardStage.setTitle("B   A   T   T   L   E   S   H   I   P");
		gameBoardStage.show();
		
		
	}


	// saves mouseclick/shot
	
	private void setClickEvent() {

		gridPane.setOnMouseClicked(event -> {

			Rectangle square = (Rectangle) event.getTarget();
			int y = GridPane.getRowIndex(square);
			int x = GridPane.getColumnIndex(square);
			
			navy.shot(new int[] { x, y });
			
			if (navy.isGameOver())
				gameOver();
			else
				refreshGameBoard();

		});
	}
	
	// takes Navy and displays ships
	private void refreshGameBoard() {
		for (int i = 0; i < lengthInShips; i++) {
			for (int j = 0; j < lengthInShips; j++) {
				
				// Navy object returns correct color.
				
				Color colorStatus = navy.getPosColor(i, j);
				double shipLength = lengthInPixels / lengthInShips;
				Rectangle square = new Rectangle(shipLength, shipLength, colorStatus);
				square.setStroke(Color.CADETBLUE);
				gridPane.add(square, i, j);
			}
		}
	}
	
	
	// if game is over
	
	private void gameOver() {

		Label label = new Label("KÕIK LAEVAD PÕHJAS!");
		label.setFont(new Font("Serif", 50));
		VBox gameOverBox = new VBox();
		Button exitButton = new Button("Välju Mängust");
		// Button startButton = new Button("Mängi uuesti");
		exitButton.setMaxSize(150, 50);
		// startButton.setMaxSize(150, 50);
		exitButton.setOnAction(e -> Platform.exit());
		// startButton.setOnAction(e -> );
		gameOverBox.getChildren().addAll(label, exitButton);
		scene.setRoot(gameOverBox);

	}

}
