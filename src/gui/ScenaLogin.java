package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Pouzivatelia.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ScenaLogin extends DefaultHodnoty implements ScenaInterface {
	private ManagerLogin mojManazer = new ManagerLogin();

	private TextField loginUsername = new TextField("");
	private PasswordField loginPassword = new PasswordField();
	private Button loginSubmit = new Button("Login");
	private Text loginHlaska = new Text();
	private Text nazov = new Text();
	private MenuBar menuBar = new MenuBar();

	private Scene mojaScena;
	private StackPane mojPane = new StackPane();

	public Scene nastavScene(Pouzivatel aktualnyPouzivatel) {
		mojaScena = new Scene(mojPane, width, height);
		nastav();
		funkcie();
		pridajPane();
		return mojaScena;
	}

	public void nastav() {
		menuBar.setTranslateY(-mojaScena.getHeight() / 2 + 29);

		loginUsername.setPromptText("Username");
		loginUsername.setTranslateY(-(velkostPolickaY + medzera));
		loginUsername.setMaxSize(velkostPolickaX * 3 / 2, velkostPolickaY);

		loginPassword.setPromptText("Password");
		loginPassword.setTranslateY(stredTabulky);
		loginPassword.setMaxSize(velkostPolickaX * 3 / 2, velkostPolickaY);

		loginSubmit.setTranslateY(velkostPolickaY + medzera);
		loginSubmit.setMaxSize(velkostPolickaX * 3 / 2, velkostPolickaY);

		loginHlaska.setTranslateY(velkostPolickaY * 3);
		loginHlaska.setFont(new Font(14));
		loginHlaska.setWrappingWidth(velkostPolickaX * 2);
		loginHlaska.setTextAlignment(TextAlignment.CENTER);
		loginHlaska.setFill(Color.RED);
		loginHlaska.setVisible(false);

		nazov.setTranslateY(-velkostPolickaY * 4);
		nazov.setFont(new Font(26));
		nazov.setWrappingWidth(velkostPolickaX * 2);
		nazov.setTextAlignment(TextAlignment.CENTER);
		nazov.setFill(Color.ORANGE);
		nazov.setVisible(true);
		nazov.setText("�iacka kni�ka");
	}

	public void funkcie() {
		loginUsername.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER)
				loginSubmit();
		});

		loginPassword.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER)
				loginSubmit();
		});

		loginSubmit.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER)
				loginSubmit();
		});

		loginSubmit.setOnAction(e -> {
			loginSubmit();
		});

		loginHlaska.setText("Nespr�vne prihlasovacie meno alebo heslo, sk�ste to znova.\n");

		Menu menuLogout = new Menu();
		Image image = null;
		try {
			image = new Image(new FileInputStream("obrazky/exit.png"));
		} catch (FileNotFoundException e1) {
		}
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(50);
		imageView.setFitHeight(50);
		menuLogout.setGraphic(imageView);

		Menu menuExit = new Menu();
		Image image1 = null;
		try {
			image1 = new Image(new FileInputStream("obrazky/power.png"));
		} catch (FileNotFoundException e1) {
		}
		ImageView imageView1 = new ImageView(image1);
		imageView1.setFitWidth(45);
		imageView1.setFitHeight(45);
		menuExit.setGraphic(imageView1);

		menuBar.getMenus().addAll(menuLogout, menuExit);

	}

	public void pridajPane() {
		mojPane.getChildren().addAll(loginUsername, loginPassword, loginSubmit, loginHlaska, nazov);
//		mojPane.getChildren().add(menuBar);
	}

	public void loginSubmit() {
		Boolean vipis = mojManazer.loginSubmit(loginUsername.getText(), loginPassword.getText());
		loginHlaska.setVisible(vipis);
	}

}
