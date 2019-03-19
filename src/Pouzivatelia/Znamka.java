package Pouzivatelia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Znamka implements Comparable<Znamka> {
	private String hodnotaS;
	private String maxHodnotaS;
	private String datumS;
	private LocalDate datum;

	public Znamka(String hodnotaS, String maxHodnotaS, String datumS) {
		setHodnotaS(hodnotaS);
		setMaxHodnotaS(maxHodnotaS);
		setDatumS(datumS);
	}

	public String getHodnotaS() {
		return hodnotaS;
	}

	public void setHodnotaS(String hodnotaS) {
		this.hodnotaS = hodnotaS;
	}

	public String getMaxHodnotaS() {
		return maxHodnotaS;
	}

	public void setMaxHodnotaS(String maxHodnotaS) {
		this.maxHodnotaS = maxHodnotaS;
	}

	public String getDatumS() {

		return datumS;
	}

	public void setDatumS(String datumS) {
		if (datumS != null)
			konvertujDatumS(datumS);
		this.datumS = datumS;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate date) {
		this.datum = date;
	}

	public int compareTo(Znamka z) {
		return getDatum().compareTo(z.getDatum());
	}

	public void konvertujDatumS(String datumS) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			LocalDate date = LocalDate.parse(datumS, formatter);
			setDatum(date);
		} catch (DateTimeParseException exc) {
			System.out.printf("Error pri prevadzani String -> Date\n", datumS);
		}
	}

}
