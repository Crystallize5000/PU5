package hs_mannheim.ws14.tpe_uib_05.ueb5;

/**
 * Diese Klasse repraesentiert ein Kernkraftwerk, dieses besteht wiederum aus
 * einer Pumpe, einem Wasserkreislauf, einem Reaktor, Waermetauschern (Reaktor +
 * Fluss) und letztlich einer Leitware.
 * 
 * @author Dennis Keßler 1326697
 *
 */

public class Kernkraftwerk implements Runnable {

	/**
	 * Reaktor des Kernkraftwerks
	 */

	private final Reaktor reaktor;

	/**
	 * Pumpe des Kernkraftwerks
	 */

	private final Pumpe pumpe;

	/**
	 * Leitware des Kernkraftwerks
	 */

	private final Leitware leitware;

	/**
	 * Wasserkreislauf des Kernkraftwerks
	 */

	private final Wasserkreislauf wasserkreislauf;

	/**
	 * Waermetauscher des Reaktors
	 */

	private final Waermetauscher waermetauschReaktor;

	/**
	 * Waermetauscher des Flusses
	 */

	private final Waermetauscher waermetauschFluss;

	/**
	 * Konstruktor zum erzeugen eines Kernkraftwerks
	 * 
	 * @param reaktor
	 *            Reaktor des Kraftwerks
	 * @param pumpe
	 *            Pumpe des Kraftwerks
	 * @param leitware
	 *            Leitware des Kraftwerks
	 * @param wasserkreislauf
	 *            Wasserkreislauf des Kraftwerks
	 * @param waermetauschReaktor
	 *            Waermetauscher des Reaktors
	 * @param waermetauschFluss
	 *            Waermetauscher des Flusses
	 * 
	 */

	public Kernkraftwerk(Reaktor reaktor, Pumpe pumpe, Leitware leitware,
			Wasserkreislauf wasserkreislauf,
			Waermetauscher waermetauschReaktor, Waermetauscher waermetauschFluss) {
		this.reaktor = reaktor;
		this.pumpe = pumpe;
		this.leitware = leitware;
		this.wasserkreislauf = wasserkreislauf;
		this.waermetauschReaktor = waermetauschReaktor;
		this.waermetauschFluss = waermetauschFluss;

	}

	/**
	 * Umfasst den Reaktor des Kraftwerks.
	 * 
	 * @return Gibt den Reaktors zurueck.
	 */
	public Reaktor getReaktor() {
		return reaktor;
	}

	/**
	 * Umfasst die Pumpe des Kraftwerks.
	 * 
	 * @return Gibt die Pumpe zurueck.
	 */

	public Pumpe getPumpe() {
		return pumpe;
	}

	/**
	 * Umfasst die Leitware des Kraftwerks.
	 * 
	 * @return Gibt die Leitware zurueck.
	 */

	public Leitware getLeitware() {
		return leitware;
	}

	/**
	 * Umfasst den Wasserkreislauf des Kraftwerks.
	 * 
	 * @return Gibt den Wasserkreislaufs zurueck.
	 */
	public Wasserkreislauf getKreislauf() {
		return wasserkreislauf;
	}

	/**
	 * Umfasst den Waermetauscher fuer den Reaktor.
	 * 
	 * @return Gibt den Waermetauscher bezueglich des Reaktors zurueck.
	 */

	public Waermetauscher getReaktorWaermetauscher() {
		return waermetauschReaktor;
	}

	/**
	 * Umfasst den Waermetauscher fuer den Fluss.
	 * 
	 * @return Gibt den Waermetauscher bezueglich des Flusses zurueck.
	 */

	public Waermetauscher getFlussWaermetauscher() {
		return waermetauschFluss;
	}

	/**
	 * Umfasst die Steuerung (Waerme) eines Kernkraftwerks, das in diesem Fall
	 * anhand von zwei Threads (Reaktor + Pumpe) gesteuert wird (runnable
	 * interface implementiert)
	 * 
	 */

	public synchronized void run() {

		Thread threadReaktor = new Thread(reaktor);
		Thread threadPumpe = new Thread(pumpe);

		threadReaktor.start();
		threadPumpe.start();

		// Zaehler für die 20 sec Laufzeit
		int zaehler = 0;

		// Laufzeit des Reaktors beinhaltet 20 sec, solange keine Kernschmerlez
		// stattfindet
		while (zaehler < 20 && !reaktor.getKernschmelze()) {

			// Lock fuer die Waermetauscher
			
			synchronized (Sperre.positionsLock) {
				waermetauschReaktor.waermetausch(
						wasserkreislauf.getWasserkreislauf().get(
								wasserkreislauf.getReaktorPosition()),
						reaktor.getWasserelement());
				waermetauschFluss.waermetausch(
						wasserkreislauf.getWasserkreislauf().get(
								wasserkreislauf.getFlussPosition()),
						new Wasserelement());
			}

			leitware.temperaturAusgeben();
			zaehler++;
			try {
				wait(1000);
			} catch (InterruptedException e) {
				break;
			}
		}
		// Falls Kernschmelze auftritt
		if (reaktor.getKernschmelze()) {
			pumpe.setKernschmelze(true);

			// Insofern keine Kernschmelze aufgetreten ist

		} else {
			threadReaktor.interrupt();
			threadPumpe.interrupt();
		}
	}
}
