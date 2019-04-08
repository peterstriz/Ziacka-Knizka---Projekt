package gui;

import guiAplikacnaLogika.ManazerLogin;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import pouzivatelia.Pouzivatel;

public class ScenaLogin extends DefaultHodnoty implements ScenaInterface {
	private ManazerLogin mojManazer = new ManazerLogin();

	private TextField loginUsername = new TextField("");
	private PasswordField loginPassword = new PasswordField();
	private Button loginSubmit = new Button("Login");
	private Text loginHlaska = new Text();
	private Text nazov = new Text();

	private Scene mojaScena;
	private GridPane mojPane = new GridPane();

	public Scene nastavScene(Pouzivatel aktualnyPouzivatel) {
		nastav();
		funkcie();
		pridajPane();
		mojaScena = new Scene(mojPane, width, height);
		return mojaScena;
	}

	public void nastav() {
		velkostPolickaX = velkostPolickaX * 3 / 2 + 5;

		loginUsername.setPromptText("Username");

		loginPassword.setPromptText("Password");

		loginSubmit.setMaxWidth(velkostPolickaX);

		mojPane.getColumnConstraints().add(new ColumnConstraints(velkostPolickaX));
		mojPane.setHgap(5);
		mojPane.setVgap(5);
		mojPane.setAlignment(Pos.CENTER);

		loginHlaska.setFont(new Font(14));
		loginHlaska.setWrappingWidth(velkostPolickaX);
		loginHlaska.setTextAlignment(TextAlignment.CENTER);
		loginHlaska.setFill(Color.RED);
		loginHlaska.setVisible(false);

		nazov.setFont(new Font(26));
		nazov.setWrappingWidth(velkostPolickaX);
		nazov.setTextAlignment(TextAlignment.CENTER);
		nazov.setFill(Color.ORANGE);
		nazov.setVisible(true);
		nazov.setText("Žiacka knižka");
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

		loginHlaska.setText("Nesprávne prihlasovacie meno alebo heslo, skúste to znova.\n");
	}

	public void pridajPane() {
		mojPane.add(nazov, 0, 0, 1, 1);
		mojPane.add(loginUsername, 0, 1, 1, 1);
		mojPane.add(loginPassword, 0, 2, 1, 1);
		mojPane.add(loginSubmit, 0, 3, 1, 1);
		mojPane.add(loginHlaska, 0, 4, 1, 1);
	}

	public void loginSubmit() {
		Boolean vipis = mojManazer.loginSubmit(loginUsername.getText(), loginPassword.getText());
		loginHlaska.setVisible(vipis);
	}

}
