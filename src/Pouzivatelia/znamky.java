package Pouzivatelia;

import java.util.*;

public class Znamky {
	private String meno;
	private double hodnota;
	private double maxHodnota;
	private Date datum;

	public Znamky(String meno, double hodnota, double maxHodnota, Date datum) {
		this.meno = meno;
		this.hodnota = hodnota;
		this.maxHodnota = maxHodnota;
		this.datum = datum;
	}

	public String vratMeno() {
		return meno;
	}

	public double vrathodnota() {
		return hodnota;
	}

	public double vratmaxHodnota() {
		return maxHodnota;
	}

	public Date vratDatum() {
		return datum;
	}
}
