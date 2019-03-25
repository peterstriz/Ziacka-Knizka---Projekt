package gui;

import Pouzivatelia.Ucitel;
import Pouzivatelia.Ziak;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ScenaRiaditelPridaj {
	private ManazerRiaditel mojManazer = new ManazerRiaditel();
	private Stage subStage = new Stage();
	private VBox root = new VBox();

	private Button submit = new Button("Pridaù");
	private TextField meno = new TextField("");
	private TextField priezvisko = new TextField("");
	private TextField username = new TextField("");
	private TextField password = new TextField("");
	private ChoiceBox<String> typ = new ChoiceBox<String>();
	private Text hlaska = new Text();

	private ObservableList<String> typyPouzivatelov = FXCollections.observableArrayList();
	private String[] typPouzivatela = { Ziak.class.getSimpleName(), Ucitel.class.getSimpleName() };
	private String typNovehoPouzivatela;

	public ScenaRiaditelPridaj() {
		typyPouzivatelov.addAll(typPouzivatela);

		meno.setPromptText("Meno");
		priezvisko.setPromptText("Priezvisko");
		username.setPromptText("username");
		password.setPromptText("password");

		meno.setMaxWidth(100);
		priezvisko.setMaxWidth(100);
		username.setMaxWidth(100);
		password.setMaxWidth(100);
		typ.setMaxWidth(100);
		submit.setMaxWidth(100);

		hlaska.setFont(new Font(14));
		hlaska.setWrappingWidth(150);
		hlaska.setTextAlignment(TextAlignment.CENTER);
		hlaska.setFill(Color.GREEN);
		hlaska.setVisible(false);
		hlaska.setText("PouûÌvateæ uspeöne pridan˝, mÙûete zavrieù okno.");

		typ.setItems(typyPouzivatelov);
		typ.getSelectionModel().selectedIndexProperty().addListener((ChangeListener<Number>) (ov, value, new_value) -> {
			setTypNovehoPouzivatela(typPouzivatela[(int) new_value]);
		});
		typ.getSelectionModel().selectFirst();

		submit.setOnAction(e -> {
			String meno = this.meno.getText();
			String priezvisko = this.priezvisko.getText();
			String username = this.username.getText();
			String password = this.password.getText();
			Boolean vidno = mojManazer.pridajNovehoPouzivatela(getTypNovehoPouzivatela(), meno, priezvisko, username,
					password);
			hlaska.setVisible(vidno);
		});

		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(meno, priezvisko, username, password, typ, submit, hlaska);

		Scene scene = new Scene(root, 300, 350);

		subStage.setTitle("Nov˝ pouûÌvateæ");
		subStage.setScene(scene);
		subStage.show();
	}

	public String getTypNovehoPouzivatela() {
		return typNovehoPouzivatela;
	}

	public void setTypNovehoPouzivatela(String typNovehoPouzivatela) {
		this.typNovehoPouzivatela = typNovehoPouzivatela;
	}
}