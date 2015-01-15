package hs_mannheim.ws14.tpe_uib_05.ueb5;

/**
 * Diese Klasse umfasst die Funktion eines Reaktors.
 * 
 * @author Dennis Keßler 1326697
 *
 */

public class Reaktor implements Runnable {

	/**
	 * Umfasst ein Wasserelement
	 */

	private Wasserelement wasserelement;

	/**
	 * Beinhaltet die maximal-zulaessige Temperatur bevor die Kernschmelze
	 * eintritt
	 */

	final static int MAXIMALTEMPERATUR = 2878;

	/**
	 * Beinhaltet den Waermekoeffizienten (Abwaerme)
	 */

	final int koeffizient;

	/**
	 * Beinhaltet die Temperatur des Flusswassers
	 */

	private int tempFlusswasser;

	/**
	 * Liefert die Aussage ueber eine Kernschmelze
	 */

	private volatile boolean kernschmelze = false;

	/**
	 * Konstruktor zum Instanziieren eines Reaktors
	 * 
	 * @param wasserelement
	 *            Wasserelement im Reaktor
	 * @param koeffizient
	 *            Abwaerme, die im Reaktor entsteht
	 * @param tempFlusswasser
	 *            Temperatur des Flusswassers
	 */

	public Reaktor(Wasserelement wasserelement, int koeffizient,
			int tempFlusswasser) {
		this.wasserelement = wasserelement;
		this.koeffizient = koeffizient;
		this.tempFlusswasser = tempFlusswasser;
	}

	/**
	 * Liefert das Wasserelement des Reaktors
	 * 
	 * @return Gibt das Wasserelement zurueck
	 */

	public Wasserelement getWasserelement() {
		return wasserelement;
	}

	/**
	 * Setzt das Wasserelement des Reaktors fest
	 * 
	 * @param wasserelement
	 *            des Reaktors
	 */

	public void setWasserelement(Wasserelement wasserelement) {
		this.wasserelement = wasserelement;
	}

	/**
	 * Liefert die Temperatur des Flusswassers zurueck.
	 * 
	 * @return Temperatur des Flusswassers
	 */

	public int getFlusswasser() {
		return tempFlusswasser;
	}

	/**
	 * Setzt den Waermekoeffizienten fest
	 * 
	 * @param tempFlusswasser
	 *            Temperatur des Flusswassers
	 */

	public void setFlusswasser(int tempFlusswasser) {
		this.tempFlusswasser = tempFlusswasser;
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
	 * Reaktor wird in Gang gesetzt
	 */

	public synchronized void run() {
		while (!kernschmelze) {
			wasserelement.setWasserTemperatur(wasserelement
					.getWassertemperatur() + tempFlusswasser);
			if (wasserelement.getWassertemperatur() >= MAXIMALTEMPERATUR) {
				System.out
						.println("Kernschmelze eingetreten (EXPLOSIONEN!!!!!111)");
				this.kernschmelze = true;

			}

			if (Thread.currentThread().isInterrupted()) {
				break;
			}

			try {
				wait(Math.round(1000.0 / koeffizient));
			} catch (InterruptedException e) {
				System.out.println("Reaktor wird heruntergefahren");
				break;
			}
		}

	}
}
