package gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Pouzivatelia.Predmet;
import Pouzivatelia.Znamka;

public class ManazerUcitelHlavnaScena {
	public Boolean novaZnamkaSubmit(Predmet predmet, String novaHodnotaS, String novaMaxHodnotaS, String novyDatumS) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			LocalDate.parse(novyDatumS, formatter);
			Double.parseDouble(novaMaxHodnotaS);
			Double.parseDouble(novaHodnotaS);
			predmet.pridajNovuZnamku(novaHodnotaS, novaMaxHodnotaS, novyDatumS);
			return false;
		} catch (DateTimeParseException | NumberFormatException exc) {
			return true;
		}
	}

	public Boolean setMaxHodnota(Znamka znamka, String newMaxHodnotaS) {
		try {
			Double.parseDouble(newMaxHodnotaS);
			znamka.setMaxHodnotaS(newMaxHodnotaS);
			return false;
		} catch (NumberFormatException exc) {
			return true;
		}
	}

	public Boolean setHodnota(Znamka znamka, String newHodnotaS) {
		try {
			Double.parseDouble(newHodnotaS);
			znamka.setHodnotaS(newHodnotaS);
			return false;
		} catch (NumberFormatException exc) {
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
}
