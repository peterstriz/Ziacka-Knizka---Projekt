package ZiackaKnizka;

import java.io.*;

public class Serial {
	private ZiackaKnizka zapis;

	public Serial() {
		this.zapis = new ZiackaKnizka();
	}

	public Serial(ZiackaKnizka ziackaKnizka) {
		this.zapis = ziackaKnizka;
	}

	public void zapamatajData() throws ClassNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("d.out"));
		out.writeObject(this.zapis);
		out.close();
	}

	public ZiackaKnizka vratData() throws ClassNotFoundException, IOException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("d.out"));
		this.zapis = (ZiackaKnizka) in.readObject();
		in.close();
		return this.zapis;
	}

}