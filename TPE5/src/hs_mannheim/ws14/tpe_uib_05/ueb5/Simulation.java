package hs_mannheim.ws14.tpe_uib_05.ueb5;

/**
 * Diese Klasse dient zu einer Simulation eines Kernkraftwerks
 * 
 * @author Dennis Keßler 1326697
 *
 */

public class Simulation {

	public static void main(String[] args) {

		Waermetauscher waermetauscherA = new Waermetauscher();
		Waermetauscher waermetauscherB = new Waermetauscher();
		
		Wasserkreislauf wasserkreislauf = new Wasserkreislauf();
		
		Leitware leitwarte = new Leitware(waermetauscherA, waermetauscherB);
		
		Pumpe pumpe = new Pumpe(0, wasserkreislauf);
		
		Reaktor reaktor = new Reaktor(new Wasserelement(), 42, 15);
		
		Kernkraftwerk kernkraftwerk = new Kernkraftwerk(reaktor, pumpe,
				leitwarte, wasserkreislauf, waermetauscherA, waermetauscherB);

		Thread thread = new Thread(kernkraftwerk);

		thread.start();
	}

}
