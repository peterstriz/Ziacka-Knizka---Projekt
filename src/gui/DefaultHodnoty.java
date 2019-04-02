package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Pouzivatelia.Pouzivatel;
import ZiackaKnizka.ZiackaKnizkaSingleton;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

public abstract class DefaultHodnoty {
	private HlavnyStage singleton = HlavnyStage.getInstance();
	private ZiackaKnizkaSingleton ziackaKnizka = ZiackaKnizkaSingleton.getInstance();

	protected int width = 800;
	protected int height = 600;

	protected int velkostTabulky = 150;
	protected int stredTabulky = 0;
	protected int velkostPolickaX = 100;
	protected int velkostPolickaY = 30;
	protected int medzera = 10;

	protected Pouzivatel aktualnyPouzivatel = null;

	private Menu menuLogout = new Menu();
	private Menu menuAktualnyPouzivatel = new Menu();
	protected HBox menuBar;

	protected void menu() {
		Image image = null;
		try {
			image = new Image(new FileInputStream("obrazky/exit.png"));
		} catch (FileNotFoundException e1) {
		}
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(30);
		imageView.setFitHeight(30);

		MenuItem dummy = new MenuItem("Dummy_menuItem");
		dummy.setOnAction(e -> {
			logout();
		});

		menuLogout.getItems().add(dummy);
		menuLogout.showingProperty().addListener((observableValue, oldValue, newValue) -> {
			if (newValue) {
				menuLogout.getItems().get(0).fire();
			}
		});

		Label labelMenu;
		try {
			labelMenu = new Label("Prihlasený: " + aktualnyPouzivatel.vratCeleMeno());
		}catch(Exception e) {
			labelMenu = new Label("");
		}
		labelMenu.setFont(new Font(18));

		menuAktualnyPouzivatel.setGraphic(labelMenu);
		menuLogout.setGraphic(imageView);
		
		MenuBar leftBar = new MenuBar();
		leftBar.setMinHeight(40);
		leftBar.getMenus().addAll(menuAktualnyPouzivatel);
		MenuBar rightBar = new MenuBar();
		rightBar.getMenus().addAll(menuLogout);
		rightBar.setMinHeight(40);
		Region spacer = new Region();
		spacer.getStyleClass().add("menu-bar");
		HBox.setHgrow(spacer, Priority.SOMETIMES);
		menuBar = new HBox(leftBar, spacer, rightBar);
	}

	protected void logout() {
		Scena scena = new Scena(new ScenaLogin());
		singleton.getStage().setScene(scena.nastavScene(null));
		ziackaKnizka.serializuj();
	}
}
