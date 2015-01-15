package hs_mannheim.ws14.tpe_uib_05.ueb5;

import java.util.ArrayList;

/**
 * Die Klasse Wasserkreislauf ist eine Datenstruktur zur Darstellung eines
 * Wasserkreislaufes, diesbezueglich entspricht diese einem Ringspeicher, der
 * wiederum zur Folge hat, dass er weder einen richtigen Anfang, noch ein
 * richtiges Ende hat.
 * 
 * @author Dennis Keßler 1326697
 *
 */

public class Wasserkreislauf {

	/**
	 * Beinhaltet den Wasserkreislauf (12 Wasserelemente)
	 * 
	 */

	private ArrayList<Wasserelement> wasserkreislauf = new ArrayList<>(12);

	/**
	 * Beinhaltet die Position des Reaktors im Wasserkreislauf 
	 * 
	 */

	private int reaktorPosition = 0;

	/**
	 * Beinhaltet die Position des Flusses im Wasserkreislauf
	 */
	
	private int flussPosition = 6;

	/**
	 * Konstruktor zum erzeugen eines Wasserkreislaufs
	 */

	public Wasserkreislauf() {
		for (int zaehler = 0; zaehler < 12; zaehler++) {
			wasserkreislauf.add(new Wasserelement());
		}
	}

	/**
	 * Liefert den Wasserkreislauf
	 * 
	 * @return Gibt den Wasserkreislauf zurueck
	 */

	public ArrayList<Wasserelement> getWasserkreislauf() {
		return wasserkreislauf;
	}

	/**
	 * Liefert die aktuelle Position des Reaktors.
	 * 
	 * @return Gibt die Position des Reaktors zurueck
	 */
	public int getReaktorPosition() {
		return reaktorPosition;
	}

	/**
	 * Setzt die Position des Reaktors.
	 * 
	 * @param reaktorPosition
	 *            Neue Position des Reaktors
	 */
	public void setReaktorPosition(int reaktorPosition) {
		this.reaktorPosition = reaktorPosition;
	}

	/**
	 * Liefert die Position des Flusswassers
	 * 
	 * @return Gibt die Position des Flusswassers zurueck
	 */
	public int getFlussPosition() {
		return flussPosition;
	}

	/**
	 * Setzt die Position des Flusswassers
	 * 
	 * @param flussPosition
	 *            Neue Position des FLusswassers
	 */
	public void setFlusswasserPosition(int flussPosition) {
		this.flussPosition = flussPosition;
	}

	/**
	 * Setzt einen Pumpvorgang in Gang
	 */
	
	public void pumpen() {
		synchronized (Sperre.positionsLock) {
			reaktorPosition++;
			flussPosition++;
			reaktorPosition = reaktorPosition % 12;
			flussPosition = flussPosition % 12;
		}
	}
}
