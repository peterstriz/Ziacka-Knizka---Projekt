package Pouzivatelia;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Informacie o jednotlivych znamkach.
 * 
 * @author Peter Striz
 */
public class Znamka implements Serializable, Comparable<Znamka> {
	private static final long serialVersionUID = 1L;
	/** Body z pisomky. */
	private String hodnotaS;
	/** Maximalny pocet bodov z pisomky. */
	private String maxHodnotaS;
	/** Datum pisania pisomky (String). */
	private String datumS;
	/** Datum pisania pisomky (LocalDate). */
	private LocalDate datum;

	/**
	 * Vytvorenie novej znamky a pridanie udajov.
	 * 
	 * @param hodnotaS    Kolko bodov bolo ziskanych.
	 * @param maxHodnotaS Maximalny pocet bodov.
	 * @param datumS      Datum pisania testu.
	 */
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
		konvertujDatumS(datumS);
		this.datumS = datumS;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate date) {
		this.datum = date;
	}

	/**
	 * Umozni nam sortovat list podla datumu.
	 */
	public int compareTo(Znamka z) {
		return getDatum().compareTo(z.getDatum());
	}

	/**
	 * Konvetruje datum (String) -&gt; datum (LocalDate).
	 * 
	 * @param datumS Vstupny datum (String).
	 */
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
