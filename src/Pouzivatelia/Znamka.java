package Pouzivatelia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Znamka implements Comparable<Znamka> {
	private String hodnota;
	private String maxHodnota;
	private String datumS;
	private LocalDate datum;

	public Znamka(String hodnota, String maxHodnota, String datumS) {
		setHodnota(hodnota);
		setMaxHodnota(maxHodnota);
		setDatumS(datumS);
	}

	public String getHodnota() {
		return hodnota;
	}

	public void setHodnota(String hodnota) {
		this.hodnota = hodnota;
	}

	public String getMaxHodnota() {
		return maxHodnota;
	}

	public void setMaxHodnota(String maxHodnota) {
		this.maxHodnota = maxHodnota;
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
		System.out.print(date);
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
			System.out.printf("%s is not parsable!\n", datumS);
			// throw exc;
		}
	}

}
