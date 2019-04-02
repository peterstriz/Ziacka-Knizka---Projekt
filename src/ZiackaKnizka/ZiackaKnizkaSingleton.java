package ZiackaKnizka;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ZiackaKnizkaSingleton implements Serializable {
	private static final long serialVersionUID = 1L;

	private static ZiackaKnizkaSingleton single_instance = null;

	private ZiackaKnizka ziackaKnizka;

	private ZiackaKnizkaSingleton() {
		setZiackaKnizka(new ZiackaKnizka());
	}

	public static ZiackaKnizkaSingleton getInstance() {
		if (single_instance == null)
//			single_instance = new ZiackaKnizkaSingleton();
			deSerializuj();
		return single_instance;
	}

	public ZiackaKnizka getZiackaKnizka() {
		return ziackaKnizka;
	}

	public void setZiackaKnizka(ZiackaKnizka ziackaKnizka) {
		this.ziackaKnizka = ziackaKnizka;
	}

	public void serializuj() {
		try {
			FileOutputStream fileOut = new FileOutputStream("udaje.out");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(single_instance);
			out.close();
			fileOut.close();
			System.out.printf("Serializovanie udajov do udaje.out");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static void deSerializuj() {
		try {
			FileInputStream fileIn = new FileInputStream("udaje.out");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			single_instance = (ZiackaKnizkaSingleton) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			System.out.print("Ioexception");
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("ZiackaKnizkaSingleton class not found");
			c.printStackTrace();
			return;
		} catch (Exception e) {
			System.out.println("Big problem");
			e.printStackTrace();
		}

	}

}