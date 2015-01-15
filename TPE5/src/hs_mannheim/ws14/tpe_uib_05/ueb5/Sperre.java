package hs_mannheim.ws14.tpe_uib_05.ueb5;

/**
 * Klasse fuer die Positionssperre des Wasserkreislaufs und Temperatursperre der
 * Wassertemperatur 
 * ( "Lock" Signalisiert an der kritischen Stelle, ob diese belegt ist
 * oder nicht).
 * 
 * @author Dennis Keßler 1326697
 *
 */

public class Sperre {

	public static Sperre positionsLock = new Sperre();
	public static Sperre temperaturLock = new Sperre();

}
