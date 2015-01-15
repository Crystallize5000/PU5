package hs_mannheim.ws14.tpe_uib_05.ueb5;

/**
 * Diese Klasse umfasst die Funktion eines Waermetauschers
 * 
 * @author Dennis Keßler 1326697
 */

public class Waermetauscher {

	/**
	 * Beinhaltet den Waermeaustausch der Wassertemperatur ((Te+Tk)/2)
	 */

	private double waermetausch;

	/**
	 * Konstruktor zum erzeugen eines Waermetauschers
	 */

	public Waermetauscher() {
	}

	/**
	 * Austausch der Waerme
	 * 
	 * @param wasserelementA
	 *            (heißes Wasser)
	 * @param wasserelementB
	 *            (Kühlwasser)
	 * 
	 */

	public void waermetausch(Wasserelement wasserelementA,
			Wasserelement wasserelementB) {
		synchronized (Sperre.temperaturLock) {
			double waerme = (wasserelementA.getWassertemperatur() + wasserelementB
					.getWassertemperatur()) / 2;
			wasserelementA.setWasserTemperatur(waerme);
			wasserelementB.setWasserTemperatur(waerme);
			this.waermetausch = waerme;
		}
	}

	/**
	 * Liefert den Mittelwert der Wassertemperatur
	 * 
	 * @return Gibt den Mittelwert der Wassertemperatur zurueck
	 */

	public double getWaermetausch() {
		return this.waermetausch;
	}
}
