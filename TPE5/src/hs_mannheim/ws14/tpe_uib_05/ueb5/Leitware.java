package hs_mannheim.ws14.tpe_uib_05.ueb5;

/**
 * Diese Klasse beinhaltet die Leitware, die fuer die Ausgabe der verschiedenen
 * Temperaturdaten zustaendig ist.
 * 
 * @author Dennis Keßler 1326697
 *
 */

public class Leitware {

	/**
	 * Beinhaltet die Temperatur des Reaktors
	 */

	private Waermetauscher tempReaktor;

	/**
	 * Beinhaltet die Temperatur des Flusses
	 */

	private Waermetauscher tempFluss;

	/**
	 * Konstruktor zum erzeugen einer Leitware
	 * 
	 * @param tempReaktor
	 *            Temperatur des Reaktors
	 * @param tempFluss
	 *            Temperatur des Flusses
	 */

	public Leitware(Waermetauscher tempReaktor, Waermetauscher tempFluss) {
		this.tempReaktor = tempReaktor;
		this.tempFluss = tempFluss;
	}

	/**
	 * Methode zur Ausgabe der Temperaturen (Reaktor + Fluss)
	 */

	public void temperaturAusgeben() {
		System.out.println("Temperatur Reaktor: "
				+ Math.round(tempReaktor.getWaermetausch()) + ", "
				+ "Temperatur Rückfluss: " + Math.round(tempFluss.getWaermetausch()));

	}
}
