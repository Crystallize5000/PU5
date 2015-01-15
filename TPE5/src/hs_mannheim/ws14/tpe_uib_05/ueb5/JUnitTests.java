package hs_mannheim.ws14.tpe_uib_05.ueb5;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit Test-Cases
 * 
 * @author Dennis Keßler
 * 
 */

public class JUnitTests {

	@Test
	public void test() throws InterruptedException {

		Reaktor reaktor = new Reaktor(new Wasserelement(), 42, 1);

		Wasserkreislauf wasserkreislauf = new Wasserkreislauf();

		Pumpe pumpe = new Pumpe(1, wasserkreislauf);

		Waermetauscher waermetauscherA = new Waermetauscher();
		Waermetauscher waermetauscherB = new Waermetauscher();

		Leitware leitwarte = new Leitware(waermetauscherA, waermetauscherB);

		Kernkraftwerk kernkraftwerk = new Kernkraftwerk(reaktor, pumpe,
				leitwarte, wasserkreislauf, waermetauscherA, waermetauscherB);

		Thread kern = new Thread(kernkraftwerk);

		kern.start();

		assertEquals(2878, Reaktor.MAXIMALTEMPERATUR);

		assertNotEquals(waermetauscherA, waermetauscherB);

		assertTrue(kern.isAlive());

		assertEquals(3, Thread.activeCount());

		kern.interrupt();
		assertFalse(kern.isInterrupted());

		kern.start();
		kern.wait(1000);
		

	}
}
