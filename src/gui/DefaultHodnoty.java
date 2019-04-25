package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import guiAplikacnaLogika.ManazerDefaultHodnoty;
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
import pouzivatelia.Pouzivatel;

/**
 * Abstract class na ukladanie default hodnot pre kazdy jeden sscene.
 * 
 * @author Peter Striz
 *
 */
public abstract class DefaultHodnoty {
	private ManazerDefaultHodnoty mojManazer = new ManazerDefaultHodnoty();

	/** Sirka okna. */
	protected int width = 800;
	/** Vyska okna. */
	protected int height = 600;

	protected int velkostTabulky = 150;
	protected int stredTabulky = 0;
	protected int velkostPolickaX = 100;
	protected int velkostPolickaY = 30;
	protected int medzera = 10;

	/** Prihlaseny pouzivatel. */
	protected Pouzivatel aktualnyPouzivatel = null;

	private Menu menuLogout = new Menu();
	private Menu menuAktualnyPouzivatel = new Menu();
	private Menu vyhladavanie = new Menu();
	private Menu menuSetting = new Menu();
	/** Cely menubar. */
	protected HBox menuBar;

	protected VBox informacia = new VBox();
	private Button submit = new Button("X");
	private Text meno = new Text("");
	private Text email = new Text("");

	/** Vygeneruje cele menu. */
	protected void menu() {
		Image imageLogout = null;
		try {
			imageLogout = new Image(new FileInputStream("obrazky/exit.png"));
		} catch (FileNotFoundException e1) {
		}

		ImageView imageViewLogout = new ImageView(imageLogout);
		imageViewLogout.setFitWidth(30);
		imageViewLogout.setFitHeight(30);

		Image imageSetting = null;
		try {
			imageSetting = new Image(new FileInputStream("obrazky/setting.png"));
		} catch (FileNotFoundException e1) {
		}

		ImageView imageViewSetting = new ImageView(imageSetting);
		imageViewSetting.setFitWidth(30);
		imageViewSetting.setFitHeight(30);

		MenuItem zmenEmail = new MenuItem();
		TextField zmenEmailField = new TextField("");
		zmenEmailField.setPromptText("Nový email...");
		zmenEmail.setGraphic(zmenEmailField);

		zmenEmailField.setOnKeyPressed((event) -> {
			if (event.getCode() == KeyCode.ENTER) {
				mojManazer.zmenEmail(aktualnyPouzivatel, zmenEmailField.getText());
				zmenEmail.setText(Character.toString((char) 0x2714));
			}
		});

		MenuItem stareHeslo = new MenuItem();
		TextField stareHesloField = new TextField("");
		stareHesloField.setPromptText("Staré heslo...");
		stareHeslo.setGraphic(stareHesloField);

		MenuItem noveHeslo = new MenuItem();
		TextField noveHesloField = new TextField("");
		noveHesloField.setPromptText("Nové heslo...");
		noveHeslo.setGraphic(noveHesloField);

		noveHesloField.setOnKeyPressed((event) -> {
			if (event.getCode() == KeyCode.ENTER) {
				if (mojManazer.zmenHeslo(aktualnyPouzivatel, stareHesloField.getText(), noveHesloField.getText())) {
					noveHeslo.setText(Character.toString((char) 0x2714));
					stareHeslo.setText("");
				} else {
					stareHeslo.setText(Character.toString((char) 0x274C));
					noveHeslo.setText("");
				}
			}
		});

		menuSetting.getItems().addAll(zmenEmail, stareHeslo, noveHeslo);

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

		Label labelPrihlaseny;
		try {
			labelPrihlaseny = new Label("Prihlasený: " + aktualnyPouzivatel.vratCeleMeno());
		} catch (Exception e) {
			labelPrihlaseny = new Label("");
		}
		labelPrihlaseny.setFont(new Font(18));

		TextField vyhladaj = new TextField();
		vyhladaj.setPromptText("H¾ada...");
		vyhladaj.setMaxWidth(velkostPolickaX * 3);

		vyhladaj.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER)
				otvorInformaciu(vyhladaj.getText());
		});

		vyhladavanie.setGraphic(vyhladaj);
		menuAktualnyPouzivatel.setGraphic(labelPrihlaseny);
		menuLogout.setGraphic(imageViewLogout);
		menuSetting.setGraphic(imageViewSetting);

		MenuBar leftBar = new MenuBar();
		leftBar.setMinHeight(40);
		leftBar.setMaxHeight(40);
		leftBar.getMenus().addAll(menuAktualnyPouzivatel, vyhladavanie);
		MenuBar rightBar = new MenuBar();
		rightBar.getMenus().addAll(menuLogout);
		rightBar.setMinHeight(40);
		rightBar.setMaxHeight(40);
		MenuBar rightBarSetting = new MenuBar();
		rightBarSetting.getMenus().addAll(menuSetting);
		rightBarSetting.setMinHeight(40);
		rightBarSetting.setMaxHeight(40);
		Region spacer = new Region();
		spacer.getStyleClass().add("menu-bar");
		rightBar.setMinHeight(40);
		HBox.setHgrow(spacer, Priority.SOMETIMES);
		menuBar = new HBox(leftBar, spacer, rightBarSetting, rightBar);

		vytvorInformaciu();
	}

	/** Logout-ne pouzivatela. */
	protected void logout() {
		mojManazer.logout();
	}

	/**
	 * Vygeneruje informaciu ktora bude mozna zobrazit po zadanie vyhladavaneho
	 * pouzivatela.
	 */
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

	/** Zavrie okno s informaciou. */
	private void zavriInformaciu() {
		informacia.setVisible(false);
	}

	/**
	 * Otvori okno s informaciou o pouzivatelovi.
	 * 
	 * @param menoInput Meno zobrazovaneho pouzivatela.
	 */
	private void otvorInformaciu(String menoInput) {
		Pouzivatel pouzivatel = mojManazer.vratPouzivatelaPodlaMena(menoInput);

		if (pouzivatel == null)
			;
		else {
			meno.setText(pouzivatel.vratCeleMeno() + ", " + pouzivatel.vratMojTyp());
			email.setText(pouzivatel.getEmail());

			informacia.setVisible(true);
		}

	}
}
