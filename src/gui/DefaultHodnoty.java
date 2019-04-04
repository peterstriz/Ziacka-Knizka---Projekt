package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Pouzivatelia.Pouzivatel;
import ZiackaKnizka.ZiackaKnizkaSingleton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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
	private Menu vyhladavanie = new Menu();
	protected HBox menuBar;

	protected VBox informacia = new VBox();
	private Button submit = new Button("X");
	private Text meno = new Text("");
	private Text email = new Text("");

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
		} catch (Exception e) {
			labelMenu = new Label("");
		}
		labelMenu.setFont(new Font(18));

		TextField vyhladaj = new TextField();
		vyhladaj.setPromptText("H¾ada...");
		vyhladaj.setMaxWidth(velkostPolickaX * 3);

		vyhladaj.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER)
				otvorInformaciu(vyhladaj.getText());
		});

		vyhladavanie.setGraphic(vyhladaj);
		menuAktualnyPouzivatel.setGraphic(labelMenu);
		menuLogout.setGraphic(imageView);

		MenuBar leftBar = new MenuBar();
		leftBar.setMinHeight(40);
		leftBar.setMaxHeight(40);
		leftBar.getMenus().addAll(menuAktualnyPouzivatel, vyhladavanie);
		MenuBar rightBar = new MenuBar();
		rightBar.getMenus().addAll(menuLogout);
		rightBar.setMinHeight(40);
		rightBar.setMaxHeight(40);
		Region spacer = new Region();
		spacer.getStyleClass().add("menu-bar");
		rightBar.setMinHeight(40);
		HBox.setHgrow(spacer, Priority.SOMETIMES);
		menuBar = new HBox(leftBar, spacer, rightBar);

		vytvorInformaciu();
	}

	protected void logout() {
		Scena scena = new Scena(new ScenaLogin());
		singleton.getStage().setScene(scena.nastavScene(null));
		ziackaKnizka.serializuj();
	}

	private void vytvorInformaciu() {
		informacia.setVisible(false);
		informacia.setMaxSize(200, 100);

		meno.setWrappingWidth(100);
		meno.setFill(Color.WHITE);
		meno.setTextAlignment(TextAlignment.CENTER);
		email.setWrappingWidth(100);
		email.setFill(Color.WHITE);
		email.setTextAlignment(TextAlignment.CENTER);
		submit.setMaxWidth(20);

		submit.setOnAction(e -> {
			zavriInformaciu();
		});

		informacia.setAlignment(Pos.CENTER);
		informacia.getChildren().addAll(meno, email, submit);
		informacia.setStyle(
				"-fx-background-color: rgba(70, 70, 70, 0.7); -fx-background-radius: 6; -fx-border-color: black; -fx-border-radius: 6;");
	}

	private void zavriInformaciu() {
		informacia.setVisible(false);
	}

	private void otvorInformaciu(String menoInput) {
		String m = null, p = null;
		try {
			m = menoInput.split(" (?!.* )")[0];
			p = menoInput.split(" (?!.* )")[1];

			ZiackaKnizkaSingleton z = ZiackaKnizkaSingleton.getInstance();
			Pouzivatel pouzivatel = z.getZiackaKnizka().vratPouzivatelaPodlaMena(m, p);

			if (pouzivatel == null)
				;
			else {
				meno.setText(pouzivatel.vratCeleMeno());
				email.setText(pouzivatel.getEmail());

				informacia.setVisible(true);
			}
		} catch (Exception e) {

		}
	}
}
