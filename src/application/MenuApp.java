package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuApp extends Application {

	String couleurs[] = { "noir", "rouge", "vert", "bleu" };
	Color valeursCouleurs[] = { Color.BLACK, Color.RED, Color.GREEN, Color.BLUE };
	RadioMenuItem optionsCouleur[] = new RadioMenuItem[couleurs.length];
	String nomsStyle[] = { "Gras", "Italique" };
	CheckMenuItem optionsstyle[] = new CheckMenuItem[nomsStyle.length];

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();

			MenuBar barreMenu = new MenuBar();

			Menu menuFichier = new Menu("_Fichier");
			MenuItem mnuItemAPropos = new MenuItem("À propose de ...");
			MenuItem mnuItemQuitter = new MenuItem("Quitter");
			
			mnuItemQuitter.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent e) {
					Platform.exit();	
				}
			});
			
			mnuItemAPropos.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent e) {
					Alert alertInfo = new Alert(AlertType.INFORMATION);
					alertInfo.setTitle("Message");
					alertInfo.setHeaderText(null);
					alertInfo.setContentText("Ceci est un exemple d’utilisation des menus");
					alertInfo.showAndWait();
				}
			});

			menuFichier.getItems().addAll(mnuItemAPropos, mnuItemQuitter);
			
			barreMenu.getMenus().addAll(menuFichier);

			root.setTop(barreMenu);
			Scene scene = new Scene(root, 400, 400);
			primaryStage.setTitle("Utilisation des menus");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	class GestionEdition implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent e) {
			
		}	
	}

	class GestionStyle implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent e) {
			
		}	
	}
	public static void main(String[] args) {
		launch(args);
	}
}
