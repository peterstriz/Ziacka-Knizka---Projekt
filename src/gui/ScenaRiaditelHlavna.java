package gui;

import Pouzivatelia.Pouzivatel;
import Pouzivatelia.Trieda;
import Pouzivatelia.Ucitel;
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

public class ScenaRiaditelHlavna extends DefaultHodnoty implements ScenaInterface {
	private Pouzivatel aktualnyPouzivatel;
	private Scene mojaScena;
	private StackPane mojPane = new StackPane();
	private ZiackaKnizkaSingleton ziackaKnizka = ZiackaKnizkaSingleton.getInstance();
	private ManazerRiaditel mojManazer = new ManazerRiaditel();

	private Text vypisMenoPouzivatela = new Text();
	private Text vypisMenoUcitela = new Text();
	private Text hlaska = new Text();
	private TextField noveMeno = new TextField("");
	private TextField novePriezvisko = new TextField("");
	private Button novyZiakSubmit = new Button("Prida�");
	private Button novyPouzivatel = new Button("Nov� pou��vate�");
	private Button novyUcitel = new Button("Zmeni�");
	private Button logout = new Button("Logout");
	private ChoiceBox<String> vyberUcitela = new ChoiceBox<String>();
	private ChoiceBox<String> vyberTriedu = new ChoiceBox<String>();
	private TableView<Ziak> tabulka = new TableView<>();

	private Trieda trieda = null;
	private Ucitel ucitel = null;

	public Scene nastavScene(Pouzivatel aktualnyPouzivatel) {
		this.aktualnyPouzivatel = aktualnyPouzivatel;
		mojaScena = new Scene(mojPane, width, height);

		nastav();
		funkcie();
		pridajPane();

		return mojaScena;
	}

	public void nastav() {
		logout.setTranslateY(30 - (mojaScena.getHeight() / 2));
		logout.setTranslateX(mojaScena.getWidth() / 2 - 50);

		novyPouzivatel.setTranslateY(stredTabulky + velkostTabulky + medzera * 5 / 2 + velkostPolickaY);
		novyPouzivatel.setMaxWidth(velkostPolickaX * 2);

		vypisMenoPouzivatela.setWrappingWidth(200);
		vypisMenoPouzivatela.setTextAlignment(TextAlignment.RIGHT);
		vypisMenoPouzivatela.setTranslateY(70 - (mojaScena.getHeight() / 2));
		vypisMenoPouzivatela.setTranslateX(mojaScena.getWidth() / 2 - 110);

		vypisMenoUcitela.setWrappingWidth(200);
		vypisMenoUcitela.setTextAlignment(TextAlignment.CENTER);
		vypisMenoUcitela.setTranslateY(-(velkostTabulky + velkostPolickaY));
		vypisMenoUcitela.setTranslateX(stredTabulky);

		vyberTriedu.setTranslateY(-(velkostTabulky + velkostPolickaY * 2 + medzera));

		vyberUcitela.setTranslateY(-(velkostTabulky + velkostPolickaY));
		vyberUcitela.setTranslateX(stredTabulky + velkostPolickaX * 5 / 2 - 2 * medzera);
		vyberUcitela.setMaxWidth(velkostPolickaX);

		tabulka.setMaxSize(velkostPolickaX * 2 + 2, velkostTabulky * 2);
		tabulka.setTranslateY(stredTabulky);
		tabulka.setPlaceholder(new Label("�iadny �iaci."));

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

		novyUcitel.setTranslateY(-(velkostTabulky + velkostPolickaY));
		novyUcitel.setTranslateX(stredTabulky + velkostPolickaX * 3 / 2 - medzera);
		novyUcitel.setMaxSize(velkostPolickaX - 31, velkostPolickaY);

		hlaska.setTranslateY(stredTabulky + velkostTabulky + velkostPolickaY * 3);
		hlaska.setFont(new Font(14));
		hlaska.setWrappingWidth(velkostPolickaX * 3);
		hlaska.setTextAlignment(TextAlignment.CENTER);
		hlaska.setFill(Color.RED);
		hlaska.setVisible(false);
	}

	@SuppressWarnings("unchecked")
	public void funkcie() {
		hlaska.setText("Dan� �iak nieje v datab�ze, ak ho chcete prida� mus�te ho najprv vytvori�.");

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
					vyberUcitela.setVisible(false);
					novyUcitel.setVisible(false);
					update();
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

		novyUcitel.setVisible(true);
		novyUcitel.setOnAction(e -> {
			vyberUcitela.setItems(ziackaKnizka.getZiackaKnizka().vratMenoUcitelov());
			vyberUcitela.setVisible(true);
		});

		vyberUcitela.setVisible(false);
		vyberUcitela.setItems(ziackaKnizka.getZiackaKnizka().vratMenoUcitelov());
		vyberUcitela.getSelectionModel().selectedIndexProperty()
				.addListener((ChangeListener<Number>) (ov, value, new_value) -> {
					try {
						ziackaKnizka.getZiackaKnizka().vratUcitelov().get((int) new_value).pridajTriedu(trieda);
					} catch (Exception e) {
					}
					vyberUcitela.setVisible(false);
					novyUcitel.setVisible(false);
					update();
				});
	}

	public void pridajPane() {
		mojPane.getChildren().addAll(vypisMenoPouzivatela, logout, tabulka, vyberTriedu, noveMeno, novePriezvisko,
				novyZiakSubmit, hlaska, novyPouzivatel, vypisMenoUcitela, novyUcitel, vyberUcitela);
	}

	public void update() {
		tabulka.setItems(trieda.getZiak());
		ucitel = ziackaKnizka.getZiackaKnizka().vratUcitelaPodlaTriedy(trieda);

		try {
			vypisMenoUcitela.setText("U�ite�: " + ucitel.vratCeleMeno());
		} catch (NullPointerException e) {
			vypisMenoUcitela.setText("�iadny u�ite�.");
			novyUcitel.setVisible(true);
		}

	}
}
