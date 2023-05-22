package application;
	
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.geometry.Pos;
//import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.event.*;

public class Main extends Application {
	
	public static Player[] readPlayerFile() throws IOException, ClassNotFoundException {
		try {																// verifies if the file exists and is not empty
			FileInputStream fis = new FileInputStream("data/Player.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ois.close();
		}
		catch (EOFException |FileNotFoundException e) {						// if not it creates it with an empty arrayList serialized
			Player[] playerEmptyArray = {null,null,null,null,null};
			FileOutputStream fos = new FileOutputStream("data/Player.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(playerEmptyArray);
		}
		
		FileInputStream fis = new FileInputStream("data/Player.txt"); 
		ObjectInputStream ois = new ObjectInputStream(fis);
		Player[] playerArray = (Player[]) ois.readObject(); // sets the arrayList of players
		ois.close();
		return playerArray;
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
		try {
			BorderPane root = new BorderPane();
			
			VBox vbox1=new VBox();
			VBox vbox2=new VBox();
			
			Label label = new Label("CY-SLIDE");
			label.setFont(new Font("Berlin Sans FB",164));
			
			Button button= new Button("PLAY");
			button.setFont(new Font("Berlin Sans FB",64));
			Label play = new Label("Choose your player");
			VBox vboxPlayer=new VBox();
	        button.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                root.getChildren().setAll(vboxPlayer);
	            }
	        });
	        
			vbox1.getChildren().add(label);
			vbox2.getChildren().add(button);
			vboxPlayer.getChildren().add(play);
			vbox1.setAlignment(Pos.CENTER);
			vbox2.setAlignment(Pos.CENTER);
			root.setTop(vbox1);
			root.setCenter(vbox2);
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
			Scene scene = new Scene(root,screenBounds.getWidth(), screenBounds.getHeight()-25);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//primaryStage.setFullScreen(true);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

