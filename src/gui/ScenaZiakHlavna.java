package gui;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pouzivatelia.*;
import udaje.Znamka;

/**
 * Trieda na scenu pre Ziaka.
 * 
 * @author Peter Striz
 * @see ScenaInterface
 * @see DefaultHodnoty
 */
public class ScenaZiakHlavna extends DefaultHodnoty implements ScenaInterface {
	private VBox mojPane = new VBox();
	private BorderPane hlavnyPane = new BorderPane();

	private TableView<Znamka> tabulkaZiak = new TableView<>();
	private ChoiceBox<String> vyberPredmetov = new ChoiceBox<String>();

	/**
	 * @return Vrati aktualnu scenu na zobrazenie, v ktorej bude pouzivatel
	 *         prihlaseny.
	 */
	public Scene nastavScene(Pouzivatel aktualnyPouzivatel) {
		super.aktualnyPouzivatel = aktualnyPouzivatel;

		menu();
		nastav();
		funkcie();
		pridajPane();
		hlavnyPane.setTop(menuBar);
		hlavnyPane.setCenter(mojPane);
		Scene mojaScena = new Scene(hlavnyPane, width, height);
		return mojaScena;
	}

	/**
	 * Nastavi funkcie jednotlivych elementov.
	 */
	@SuppressWarnings("unchecked")
	public void funkcie() {
		TableColumn<Znamka, String> datumColumn = new TableColumn<>("Datum pisomky");
		datumColumn.setMinWidth(velkostPolickaX - 1);
		datumColumn.setCellValueFactory(new PropertyValueFactory<>("datumS"));

		TableColumn<Znamka, Double> hodnotaColumn = new TableColumn<Znamka, Double>("Hodnota");
		hodnotaColumn.setMinWidth(velkostPolickaX - 1);
		hodnotaColumn.setCellValueFactory(new PropertyValueFactory<>("hodnotaS"));

		TableColumn<Znamka, Double> maxHodnotaColumn = new TableColumn<Znamka, Double>("Max. Hodnota");
		maxHodnotaColumn.setMinWidth(velkostPolickaX - 1);
		maxHodnotaColumn.setCellValueFactory(new PropertyValueFactory<>("maxHodnotaS"));

		tabulkaZiak.getColumns().addAll(hodnotaColumn, maxHodnotaColumn, datumColumn);

		vyberPredmetov.setItems(((Ziak) aktualnyPouzivatel).vratMenoPredmetov());
		vyberPredmetov.getSelectionModel().selectedIndexProperty()
				.addListener((ChangeListener<Number>) (ov, value, new_value) -> {
					tabulkaZiak.setItems(((Ziak) aktualnyPouzivatel).vratZnamkyPredmetu((int) new_value));
				});
		vyberPredmetov.getSelectionModel().selectFirst();
	}

	/**
	 * Prida elementy do pane-u.
	 */
	public void pridajPane() {
		mojPane.setAlignment(Pos.CENTER);

		mojPane.getChildren().addAll(informacia, vyberPredmetov, tabulkaZiak);
	}

	/**
	 * Nastavi pozicie elementov v pane.
	 */
	public void nastav() {
		tabulkaZiak.setMaxWidth(velkostPolickaX * 3 + 2);
		tabulkaZiak.setPlaceholder(new Label("Žiadne známky."));

	}
}
