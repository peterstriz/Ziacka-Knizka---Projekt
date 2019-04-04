package gui;

import Pouzivatelia.Pouzivatel;
import Pouzivatelia.Trieda;
import Pouzivatelia.Ucitel;
import Pouzivatelia.Ziak;
import ZiackaKnizka.ZiackaKnizkaSingleton;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ScenaRiaditelHlavna extends DefaultHodnoty implements ScenaInterface {
	private Scene mojaScena;
	private StackPane mojPane = new StackPane();
	private BorderPane borderPane = new BorderPane();
	private ZiackaKnizkaSingleton ziackaKnizka = ZiackaKnizkaSingleton.getInstance();
	private ManazerRiaditel mojManazer = new ManazerRiaditel();

	private Text vypisMenoPouzivatela = new Text();
	private Text vypisMenoUcitela = new Text();
	private Text hlaska = new Text();
	private TextField noveMeno = new TextField("");
	private TextField novePriezvisko = new TextField("");
	private TextField novaTriedaText = new TextField("");
	private TextField novyPredmetText = new TextField("");
	private Button novyZiakSubmit = new Button("Pridaù ûiaka");
	private Button novyPredmetSubmit = new Button("Pridaù predmet");
	private Button novyPouzivatel = new Button("Nov˝ pouûÌvateæ");
	private Button novaTrieda = new Button("Nov· trieda");
	private Button novaTriedaSubmit = new Button("Pridaù");
	private Button novyUcitel = new Button("Pridaù");
	private Button logout = new Button("Logout");
	private ChoiceBox<String> vyberUcitela = new ChoiceBox<String>();
	private ChoiceBox<String> vyberTriedu = new ChoiceBox<String>();
	private TableView<Ziak> tabulka = new TableView<>();
	private TableView<String> tabulkaPredmetov = new TableView<>();

	private Trieda trieda = null;
	private Ucitel ucitel = null;
	private Boolean novaTriedaToggle = false;

	public Scene nastavScene(Pouzivatel aktualnyPouzivatel) {
		super.aktualnyPouzivatel = aktualnyPouzivatel;

		menu();
		nastav();
		funkcie();
		pridajPane();
		borderPane.setTop(menuBar);
		borderPane.setCenter(mojPane);
		mojaScena = new Scene(borderPane, width, height);
		return mojaScena;
	}

	public void nastav() {
		informacia.setTranslateY(menuBar.getTranslateY() + 70);
		informacia.setTranslateX(-width / 4);

		novyPouzivatel.setTranslateY(-(velkostTabulky + velkostPolickaY * 2 + medzera));
		novyPouzivatel.setTranslateX(stredTabulky + velkostPolickaX * 2 + medzera);
		novyPouzivatel.setMaxWidth(velkostPolickaX * 2);

		novaTrieda.setTranslateY(-(velkostTabulky + velkostPolickaY + medzera / 2));
		novaTrieda.setTranslateX(stredTabulky + velkostPolickaX * 2 + medzera);
		novaTrieda.setMaxWidth(velkostPolickaX * 2);

		novaTriedaText.setTranslateY(stredTabulky + velkostTabulky + medzera * 8 / 2 + 3 * velkostPolickaY);
		novaTriedaText.setMaxWidth(velkostPolickaX * 2);
		novaTriedaText.setVisible(false);
		novaTriedaText.setPromptText("Nov· trieda...");

		novaTriedaSubmit.setTranslateY(stredTabulky + velkostTabulky + medzera * 8 / 2 + 3 * velkostPolickaY);
		novaTriedaSubmit.setTranslateX(stredTabulky + velkostPolickaX * 3 / 2 - medzera);
		novaTriedaSubmit.setMaxSize(velkostPolickaX - 31, velkostPolickaY);
		novaTriedaSubmit.setVisible(false);

		vypisMenoUcitela.setWrappingWidth(200);
		vypisMenoUcitela.setTextAlignment(TextAlignment.CENTER);
		vypisMenoUcitela.setTranslateY(-(velkostTabulky + velkostPolickaY));
		vypisMenoUcitela.setTranslateX(stredTabulky);

		tabulkaPredmetov.setTranslateY(0);
		tabulkaPredmetov.setTranslateX(stredTabulky + velkostPolickaX * 2 + medzera);
		tabulkaPredmetov.setMaxSize(velkostPolickaX * 3 / 2 + 2, velkostTabulky * 2);
		tabulkaPredmetov.setPlaceholder(new Label("éiadny predmet."));

		novyPredmetSubmit.setTranslateY(stredTabulky + velkostTabulky + medzera * 5 / 2 + velkostPolickaY);
		novyPredmetSubmit.setTranslateX(stredTabulky + velkostPolickaX * 2 + medzera);
		novyPredmetSubmit.setMaxWidth(velkostPolickaX * 3 / 2);

		novyPredmetText.setTranslateY(stredTabulky + velkostTabulky + medzera * 2);
		novyPredmetText.setTranslateX(stredTabulky + velkostPolickaX * 2 + medzera);
		novyPredmetText.setMaxWidth(velkostPolickaX * 3 / 2);
		novyPredmetText.setPromptText("Nov˝ predmet...");

		vyberTriedu.setTranslateY(-(velkostTabulky + velkostPolickaY * 2 + medzera));

		vyberUcitela.setTranslateY(-(velkostTabulky + velkostPolickaY));
		vyberUcitela.setTranslateX(-(stredTabulky + velkostPolickaX * 5 / 2 - 2 * medzera));
		vyberUcitela.setMaxWidth(velkostPolickaX);

		tabulka.setMaxSize(velkostPolickaX * 2 + 2, velkostTabulky * 2);
		tabulka.setTranslateY(stredTabulky);
		tabulka.setPlaceholder(new Label("éiadny ûiaci."));

		noveMeno.setPromptText("Meno");
		noveMeno.setTranslateY(stredTabulky + velkostTabulky + medzera * 2);
		noveMeno.setTranslateX(stredTabulky - velkostPolickaX / 2);
		noveMeno.setMaxSize(velkostPolickaX - 1, velkostPolickaY);

		novePriezvisko.setPromptText("Priezvisko");
		novePriezvisko.setTranslateY(stredTabulky + velkostTabulky + medzera * 2);
		novePriezvisko.setTranslateX(stredTabulky + velkostPolickaX / 2);
		novePriezvisko.setMaxSize(velkostPolickaX - 1, velkostPolickaY);

		novyZiakSubmit.setTranslateY(stredTabulky + velkostTabulky + medzera * 5 / 2 + velkostPolickaY);
		novyZiakSubmit.setTranslateX(stredTabulky);
		novyZiakSubmit.setMaxSize(velkostPolickaX * 2, velkostPolickaY);

		novyUcitel.setTranslateY(-(velkostTabulky + velkostPolickaY));
		novyUcitel.setTranslateX(-(stredTabulky + velkostPolickaX * 3 / 2 - medzera));
		novyUcitel.setMaxSize(velkostPolickaX - 31, velkostPolickaY);

		hlaska.setTranslateY(stredTabulky + velkostTabulky + velkostPolickaY * 3 + medzera * 2);
		hlaska.setFont(new Font(14));
		hlaska.setWrappingWidth(velkostPolickaX * 2);
		hlaska.setTextAlignment(TextAlignment.CENTER);
		hlaska.setFill(Color.RED);
		hlaska.setVisible(false);

//		menuBar.setTranslateY(-(height / 2) + 20);
//		menuBar.setMaxHeight(40);
	}

	@SuppressWarnings("unchecked")
	public void funkcie() {
		hlaska.setText("Dan˝ ûiak nieje v datab·ze, ak ho chcete pridaù musÌte ho najprv vytvoriù.");

		vypisMenoPouzivatela.setText(aktualnyPouzivatel.vratCeleMeno());

		logout.setOnAction(e -> {
			logout();
		});

		TableColumn<String, String> menoPredmetuColumn = new TableColumn<>("Predmet");
		menoPredmetuColumn.setMinWidth(velkostPolickaX * 3 / 2);
		menoPredmetuColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		tabulkaPredmetov.getColumns().add(menoPredmetuColumn);

		TableColumn<Ziak, String> menoColumn = new TableColumn<>("Meno");
		menoColumn.setMinWidth(velkostPolickaX);
		menoColumn.setCellValueFactory(new PropertyValueFactory<>("meno"));

		TableColumn<Ziak, String> priezviskoColumn = new TableColumn<>("Priezvisko");
		priezviskoColumn.setMinWidth(velkostPolickaX);
		priezviskoColumn.setCellValueFactory(new PropertyValueFactory<>("priezvisko"));
		tabulka.getColumns().addAll(menoColumn, priezviskoColumn);

		vyberTriedu.setItems(ziackaKnizka.getZiackaKnizka().vratMenoTried());
		vyberTriedu.getSelectionModel().selectedIndexProperty()
				.addListener((ChangeListener<Number>) (ov, value, new_value) -> {
					try {
						trieda = ziackaKnizka.getZiackaKnizka().vratTriedu((int) new_value);

					} catch (Exception exc) {
					}
					vyberUcitela.setVisible(false);
					novyUcitel.setVisible(false);
					update();
				});
		vyberTriedu.getSelectionModel().selectFirst();

		novyPouzivatel.setOnAction(e -> {
			hlaska.setVisible(false);
			mojManazer.pridajNovehoPouzivatela();
		});

		novyZiakSubmit.setOnAction(e -> {
			String meno = noveMeno.getText();
			String priezvisko = novePriezvisko.getText();
			Boolean pridane = mojManazer.pridajNovehoZiaka(trieda, meno, priezvisko);
			hlaska.setVisible(pridane);
			update();
		});

		novyUcitel.setVisible(false);
		novyUcitel.setOnAction(e -> {
			vyberUcitela.setItems(ziackaKnizka.getZiackaKnizka().vratMenoUcitelov());
			vyberUcitela.setVisible(true);
		});

		novaTrieda.setOnAction(e -> {
			if (novaTriedaToggle)
				novaTriedaToggle = false;
			else
				novaTriedaToggle = true;
			novaTriedaText.setVisible(novaTriedaToggle);
			novaTriedaSubmit.setVisible(novaTriedaToggle);
		});

		novaTriedaSubmit.setOnAction(e -> {
			String meno = novaTriedaText.getText();
			mojManazer.pridajNovuTriedu(meno);
			vyberTriedu.setItems(ziackaKnizka.getZiackaKnizka().vratMenoTried());
			vyberTriedu.getSelectionModel().selectFirst();
		});

		novyPredmetSubmit.setOnAction(e -> {
			String meno = novyPredmetText.getText();
			mojManazer.pridajNovuPredmet(trieda, meno);
			update();
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
				novyZiakSubmit, hlaska, novyPouzivatel, vypisMenoUcitela, novyUcitel, vyberUcitela, novaTrieda,
				novaTriedaText, novaTriedaSubmit, tabulkaPredmetov, novyPredmetSubmit, novyPredmetText, informacia);
	}

	public void update() {
		tabulka.setItems(trieda.getZiak());
		ucitel = ziackaKnizka.getZiackaKnizka().vratUcitelaPodlaTriedy(trieda);
		tabulkaPredmetov.setItems(trieda.getMenoPredmetov());
		try {
			vypisMenoUcitela.setText("UËiteæ: " + ucitel.vratCeleMeno());
		} catch (NullPointerException e) {
			vypisMenoUcitela.setText("éiadny uËiteæ.");
			novyUcitel.setVisible(true);
		}

	}

}
