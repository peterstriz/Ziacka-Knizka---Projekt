package Pouzivatelia;

import java.util.*;

public class Znamka {
	public double hodnota;
	public double maxHodnota;
	public Date datum;

	public Znamka(double hodnota, double maxHodnota, Date datum) {
		this.hodnota = hodnota;
		this.maxHodnota = maxHodnota;
		this.datum = datum;
	}

	public double getHodnota() {
		return hodnota;
	}

	public void setHodnota(double hodnota) {
		this.hodnota = hodnota;
	}

	public double getMaxHodnota() {
		return maxHodnota;
	}

	public void setMaxHodnota(double maxHodnota) {
		this.maxHodnota = maxHodnota;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}
}
