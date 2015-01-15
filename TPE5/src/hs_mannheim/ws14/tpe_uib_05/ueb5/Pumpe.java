package hs_mannheim.ws14.tpe_uib_05.ueb5;

/**
 * Diese Klasse beinhaltet die Funktion einer Pumpe.
 * 
 * @author Dennis Keßler 1326697
 *
 */

public class Pumpe extends Thread {

	/**
	 * Beinhaltet die Pumpleistung der Pumpe (in Litern)
	 */

	private double pumpleistung;

	/**
	 * Beinhaltet einen Wasserkreislauf, der durch die Pumpe gepumpt wird
	 */
	private Wasserkreislauf wasserkreislauf;

	/**
	 * Liefert die Aussage, ob eine Kernschmelze eingetreten ist
	 */

	private boolean kernschmelze = false;

	/**
	 * Konstruktor zum erzeugen der Pumpe
	 * 
	 * @param pumpleistung
	 *            Pumpleistung der Pumpe
	 * @param wasserkreislauf
	 *            Wasserkreislauf, der durch die Pumpe gepumpt wird
	 */

	public Pumpe(double pumpleistung, Wasserkreislauf wasserkreislauf) {
		this.pumpleistung = pumpleistung;
		this.wasserkreislauf = wasserkreislauf;
	}

	/**
	 * Liefert die Pumpleistung
	 * 
	 * @return Gibt die Pumpleistung der Pumpe zurueck
	 */

	public double getPumpleistung() {
		return pumpleistung;
	}

	/**
	 * Setzt die Pumpleistung fest
	 * 
	 * @param pumpleistung
	 *            Gibt Pumpleistung an, die gesetzt werden soll
	 */

	public void setPumpleistung(double pumpleistung) {
		this.pumpleistung = pumpleistung;
	}

	/**
	 * Liefert den Wasserkreislauf des Kernkraftwerks
	 * 
	 * @return Gibt den Wasserkreislauf zurueck.
	 */

	public Wasserkreislauf getWasserkreislauf() {
		return wasserkreislauf;
	}

	/**
	 * Liefert die Aussage, ob eine Kernschmelze stattgefunden hat
	 * 
	 * @return Gibt die Aussage ueber eine Kernschmelze zurueck
	 */

	public boolean getKernschmelze() {
		return kernschmelze;
	}

	/**
	 * Liefert die Aussage ueber eine moegliche Kernschmelze
	 * 
	 * @param kernschmelze
	 *            Aussage ueber eine moegliche Kernschmelze
	 */

	public void setKernschmelze(boolean kernschmelze) {
		this.kernschmelze = kernschmelze;
	}

	/**
	 * Setzt die Pumpe in Gang
	 */

	public void pumpen() {
		wasserkreislauf.pumpen();
	}

	/**
	 * Aktiviert die Pumpfunktion
	 * 
	 */

	public synchronized void run() {
		while (true) {
			if (!this.getKernschmelze()) {
				pumpen();
				try {
					wait(Math.round(1000 / pumpleistung));
				} catch (InterruptedException e) {
					System.out.println("Pumpe wird heruntergefahren");
					break;
				}
			} else {
				break;
			}
		}
	}
}
