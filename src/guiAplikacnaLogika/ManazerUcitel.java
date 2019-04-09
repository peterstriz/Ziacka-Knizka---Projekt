package guiAplikacnaLogika;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javafx.collections.ObservableList;
import pouzivatelia.Pouzivatel;
import pouzivatelia.Ucitel;
import pouzivatelia.Ziak;
import udaje.Predmet;
import udaje.Trieda;
import udaje.Znamka;

public class ManazerUcitel {
	private Trieda trieda;
	private Ziak ziak;
	private Predmet predmet;

	@SuppressWarnings("serial")
	public class PrazdneSlovoException extends Exception {
		public PrazdneSlovoException() {
			super();
		}
	}

	public void otestujDouble(String s) throws PrazdneSlovoException, NumberFormatException {
		if (s.equals(""))
			throw new PrazdneSlovoException();
		else {
			try {
				Double.parseDouble(s);
			} catch (NumberFormatException e) {
				throw new NumberFormatException();
			}
		}
	}

	public Boolean novaZnamkaSubmit(String novaHodnotaS, String novaMaxHodnotaS, String novyDatumS) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			LocalDate.parse(novyDatumS, formatter);
			otestujDouble(novaMaxHodnotaS);
			otestujDouble(novaHodnotaS);
			predmet.pridajNovuZnamku(novaHodnotaS, novaMaxHodnotaS, novyDatumS);
			return false;
		} catch (DateTimeParseException | NumberFormatException | PrazdneSlovoException exc) {
			return true;
		}
	}

	public Boolean setMaxHodnota(Znamka znamka, String newMaxHodnotaS) {
		try {
			otestujDouble(newMaxHodnotaS);
			znamka.setMaxHodnotaS(newMaxHodnotaS);
			return false;
		} catch (NumberFormatException | PrazdneSlovoException exc) {
			return true;
		}
	}

	public Boolean setHodnota(Znamka znamka, String newHodnotaS) {
		try {
			otestujDouble(newHodnotaS);
			znamka.setHodnotaS(newHodnotaS);
			return false;
		} catch (NumberFormatException | PrazdneSlovoException exc) {
			return true;
		}
	}

	public Boolean setDatum(Znamka znamka, String newDatumS) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			LocalDate.parse(newDatumS, formatter);
			znamka.setDatumS(newDatumS);
			return false;
		} catch (DateTimeParseException exc) {
			return true;
		}
	}

	public String vratDnesnyDatum() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate localDate = LocalDate.now();
		return dtf.format(localDate);
	}

	public void setTrieda(Pouzivatel aktualnyPouzivatel, int i) {
		try {
			this.trieda = ((Ucitel) aktualnyPouzivatel).vratTriedu(i);
		} catch (Exception e) {

		}
	}

	public void setZiak(int i) {
		try {
			this.ziak = trieda.getZiak(i);
		} catch (Exception e) {

		}
	}

	public void setPredmet(int i) {
		try {
			this.predmet = ziak.vratPredmet(i);
		} catch (Exception e) {

		}
	}

	public ObservableList<String> getMenoZiakov() {
		return trieda.getMenoZiakov();
	}

	public ObservableList<String> vratMenoPredmetov() {
		return ziak.vratMenoPredmetov();
	}

	public ObservableList<Znamka> vratZnamku() {
		return predmet.vratZnamku();
	}

	public ObservableList<String> vratMenoTried(Pouzivatel p) {
		return ((Ucitel) p).vratMenoTried();
	}
}
