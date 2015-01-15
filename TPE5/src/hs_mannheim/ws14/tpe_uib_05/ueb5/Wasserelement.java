package hs_mannheim.ws14.tpe_uib_05.ueb5;

/**
 * Diese Klasse beinhaltet eine innere Klasse zur Darstellung eines
 * Wasserelements.
 * 
 * @author Dennis Keßler 1326697
 *
 */
public class Wasserelement {

	/**
	 * Beinhaltet die Normaltemperatur des Wassers
	 */

	private double wassertemperatur = 10.0;

	/**
	 * Konstruktor zum erzeugen eines Wasserelementes
	 */

	public Wasserelement() {
	}

	/**
	 * Liefert die Normaltemperatur des Wassers
	 * 
	 * @return Gibt die Temperatur des Wassers zurueck
	 */

	public double getWassertemperatur() {
		synchronized (Sperre.temperaturLock) {
			return wassertemperatur;
		}
	}

	/**
	 * Setzt die Temperatur des Wassers fest
	 * 
	 * @param wassertemperatur
	 *            Temperatur des Wassers
	 */

	public void setWasserTemperatur(double wassertemperatur) {
		synchronized (Sperre.temperaturLock) {
			this.wassertemperatur = wassertemperatur;
		}
	}
}
