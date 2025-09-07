package application;

import java.util.Arrays;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuApp extends Application {

	String couleurs[] = { "noir", "rouge", "vert", "bleu" };
	Color valeursCouleurs[] = { Color.BLACK, Color.RED, Color.GREEN, Color.BLUE };
	RadioMenuItem optionsCouleur[] = new RadioMenuItem[couleurs.length];
	String nomsStyle[] = { "Gras", "Italique" };
	CheckMenuItem optionsstyle[] = new CheckMenuItem[nomsStyle.length];
	TextArea txtContenu;

	private Menu creerMnuFichier() {

		Menu menuFichier = new Menu("_Fichier");
		menuFichier.setMnemonicParsing(true);

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
		return menuFichier;
	}

	private Menu creerMnuEdition() {
		Menu mnuEdition = new Menu("_Edition");
		MenuItem mnuItemCopier = new MenuItem("_Copier");
		mnuItemCopier.setMnemonicParsing(true);
		MenuItem mnuItemColler = new MenuItem("Coller");
		MenuItem mnuItemCouper = new MenuItem("Couper");

		GestionEdition gestionEdition = new GestionEdition();

		mnuItemCopier.setOnAction(gestionEdition);
		mnuItemColler.setOnAction(gestionEdition);
		mnuItemCouper.setOnAction(gestionEdition);

		mnuEdition.getItems().addAll(mnuItemCopier, mnuItemCouper, mnuItemColler);

		return mnuEdition;
	}

	private Menu creerMnuFormat() {

		Menu mnuFormat = new Menu("Format");

		Menu mnuCouleur = new Menu("Couleur de la bordure");
		MenuItem mnuItemPolice = new MenuItem("Police");

		ToggleGroup toggleGroup = new ToggleGroup();

		GestionStyle gestionStyle = new GestionStyle();

		for (int i = 0; i < couleurs.length; i++) {

			RadioMenuItem radioMnuItem = new RadioMenuItem(couleurs[i]);
			radioMnuItem.setToggleGroup(toggleGroup);
			radioMnuItem.setOnAction(gestionStyle);
			mnuCouleur.getItems().add(radioMnuItem);
		}

		mnuFormat.getItems().add(mnuCouleur);

		return mnuFormat;

	}

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();

			MenuBar barreMenu = new MenuBar();
			barreMenu.getMenus().addAll(creerMnuFichier(), creerMnuEdition(), creerMnuFormat());
			root.setTop(barreMenu);

			txtContenu = new TextArea();

			root.setCenter(txtContenu);

			Scene scene = new Scene(root, 400, 400);
			primaryStage.setTitle("Utilisation des menus");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class GestionEdition implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent e) {
			MenuItem menuItem = (MenuItem) e.getSource();
			String action = menuItem.getText().replace("_", "").toLowerCase();

			switch (action) {
			case "copier":
				txtContenu.copy();
				break;
			case "coller":
				txtContenu.paste();
				break;

			case "couper":
				txtContenu.cut();
				break;

			default:
				break;
			}
		}
	}

	class GestionStyle implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent e) {
			RadioMenuItem item = (RadioMenuItem) e.getSource();
			String couleur = item.getText();
			int indexCouleur = Arrays.asList(couleurs).indexOf(couleur);

			txtContenu.setBorder(new Border(new BorderStroke(valeursCouleurs[indexCouleur], BorderStrokeStyle.SOLID,
					CornerRadii.EMPTY, new BorderWidths(10))));
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
