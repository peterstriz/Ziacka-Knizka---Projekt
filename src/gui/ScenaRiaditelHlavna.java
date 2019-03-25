package gui;

import Pouzivatelia.Pouzivatel;
import Pouzivatelia.Trieda;
import Pouzivatelia.Ziak;
import ZiackaKnizka.ZiackaKnizkaSingleton;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ScenaRiaditelHlavna implements ScenaInterface {
	private int width = 800;
	private int height = 600;
	private Pouzivatel aktualnyPouzivatel;
	private Scene mojaScena;
	private StackPane mojPane = new StackPane();
	private HlavnyStage singleton = HlavnyStage.getInstance();
	private ZiackaKnizkaSingleton ziackaKnizka = ZiackaKnizkaSingleton.getInstance();
	private ManazerRiaditel mojManazer = new ManazerRiaditel();

	private Button logout = new Button("Logout");
	private Text vypisMenoPouzivatela = new Text();
	private TableView<Ziak> tabulka = new TableView<>();
	private ChoiceBox<String> vyberTriedu = new ChoiceBox<String>();
	private TextField noveMeno = new TextField("");
	private TextField novePriezvisko = new TextField("");
	private Button novyZiakSubmit = new Button("Prida");
	private Button novyPouzivatel = new Button("Nový používate¾");
	private Text hlaska = new Text();

	private Trieda trieda = null;

	public Scene nastavScene(Pouzivatel aktualnyPouzivatel) {
		this.aktualnyPouzivatel = aktualnyPouzivatel;
		mojaScena = new Scene(mojPane, width, height);

		nastav();
		funkcie();
		pridajPane();

		return mojaScena;
	}

	public void nastav() {
		int velkostTabulky = 150;
		int stredTabulky = 0;
		int velkostPolickaX = 100;
		int velkostPolickaY = 30;
		int medzera = 10;

		logout.setTranslateY(30 - (mojaScena.getHeight() / 2));
		logout.setTranslateX(mojaScena.getWidth() / 2 - 50);

		novyPouzivatel.setTranslateY(stredTabulky + velkostTabulky + medzera * 5 / 2 + velkostPolickaY);
		novyPouzivatel.setMaxWidth(velkostPolickaX * 2);

		vypisMenoPouzivatela.setWrappingWidth(200);
		vypisMenoPouzivatela.setTextAlignment(TextAlignment.RIGHT);
		vypisMenoPouzivatela.setTranslateY(70 - (mojaScena.getHeight() / 2));
		vypisMenoPouzivatela.setTranslateX(mojaScena.getWidth() / 2 - 110);

		vyberTriedu.setTranslateY(-(velkostTabulky + velkostPolickaY));

		tabulka.setMaxSize(velkostPolickaX * 2 + 2, velkostTabulky * 2);
		tabulka.setTranslateY(stredTabulky);
		tabulka.setPlaceholder(new Label("Žiadny žiaci."));

		noveMeno.setPromptText("Meno");
		noveMeno.setTranslateY(stredTabulky + velkostTabulky + medzera * 2);
		noveMeno.setTranslateX(stredTabulky - velkostPolickaX / 2);
		noveMeno.setMaxSize(velkostPolickaX - 1, velkostPolickaY);

		novePriezvisko.setPromptText("Priezvisko");
		novePriezvisko.setTranslateY(stredTabulky + velkostTabulky + medzera * 2);
		novePriezvisko.setTranslateX(stredTabulky + velkostPolickaX / 2);
		novePriezvisko.setMaxSize(velkostPolickaX - 1, velkostPolickaY);

		novyZiakSubmit.setTranslateY(stredTabulky + velkostTabulky + medzera * 2);
		novyZiakSubmit.setTranslateX(stredTabulky + velkostPolickaX * 3 / 2 - medzera);
		novyZiakSubmit.setMaxSize(velkostPolickaX - 31, velkostPolickaY);

		hlaska.setTranslateY(stredTabulky + velkostTabulky + velkostPolickaY * 3);
		hlaska.setFont(new Font(14));
		hlaska.setWrappingWidth(velkostPolickaX * 3);
		hlaska.setTextAlignment(TextAlignment.CENTER);
		hlaska.setFill(Color.RED);
		hlaska.setVisible(false);
	}

	@SuppressWarnings("unchecked")
	public void funkcie() {
		hlaska.setText("Daný žiak nieje v databáze, ak ho chcete prida musíte ho najprv vytvori.");

		vypisMenoPouzivatela.setText(aktualnyPouzivatel.vratCeleMeno());

		logout.setOnAction(e -> {
			logout();
		});

		TableColumn<Ziak, String> menoColumn = new TableColumn<>("Meno");
		menoColumn.setMinWidth(100);
		menoColumn.setCellValueFactory(new PropertyValueFactory<>("meno"));

		TableColumn<Ziak, String> priezviskoColumn = new TableColumn<>("Priezvisko");
		priezviskoColumn.setMinWidth(100);
		priezviskoColumn.setCellValueFactory(new PropertyValueFactory<>("priezvisko"));

		tabulka.getColumns().addAll(menoColumn, priezviskoColumn);

		vyberTriedu.setItems(ziackaKnizka.getZiackaKnizka().vratMenoTried());
		vyberTriedu.getSelectionModel().selectedIndexProperty()
				.addListener((ChangeListener<Number>) (ov, value, new_value) -> {
					trieda = ziackaKnizka.getZiackaKnizka().vratTriedu((int) new_value);
					tabulka.setItems(trieda.vratZiakov());

				});
		vyberTriedu.getSelectionModel().selectFirst();

		novyPouzivatel.setOnAction(e -> {
			mojManazer.pridajNovehoPouzivatela();
		});
		
		novyZiakSubmit.setOnAction(e -> {
			String meno = noveMeno.getText();
			String priezvisko = novePriezvisko.getText();
			Boolean pridane = mojManazer.pridajNovehoZiaka(trieda, meno, priezvisko);
			hlaska.setVisible(pridane);

		});
	}

	public void pridajPane() {
		mojPane.getChildren().addAll(vypisMenoPouzivatela, logout, tabulka, vyberTriedu, noveMeno, novePriezvisko,
				novyZiakSubmit, hlaska, novyPouzivatel);
	}

	public void logout() {
		Scena scena = new Scena(new ScenaLogin());
		singleton.getStage().setScene(scena.nastavScene(null));
	}

}
