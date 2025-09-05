package application;

import javafx.application.Application;
import javafx.scene.Scene;
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

	public static void main(String[] args) {
		launch(args);
	}
}
