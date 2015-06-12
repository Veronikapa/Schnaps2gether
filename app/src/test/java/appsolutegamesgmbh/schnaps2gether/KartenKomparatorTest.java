package appsolutegamesgmbh.schnaps2gether;

import appsolutegamesgmbh.schnaps2gether.DataStructure.Spieler;
import appsolutegamesgmbh.schnaps2gether.DataStructure.Karte;
import appsolutegamesgmbh.schnaps2gether.DataStructure.KartenKomparator;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class KartenKomparatorTest {
	private KartenKomparator kk = new KartenKomparator();
	private Spieler s1;
	private ArrayList<Karte> kartendeck = Karte.erstelleKartendeck();


	@Before
	public void setUp()  {
		s1 = new Spieler();
		s1.Hand.add(new Karte ("Herz", "Bube", 2)); // Herz Bube 2
		s1.Hand.add(new Karte ("Karo", "Bube", 2)); // Karo Bube 2
		s1.Hand.add(new Karte ("Herz", "Ass", 11)); // Herz Ass 11
		s1.Hand.add(new Karte ("Kreuz", "Dame", 3)); // Kreuz Dame 3
		s1.Hand.add(new Karte ("Pik", "König", 4)); // Pik Königer 4

		Collections.sort(s1.Hand, new KartenKomparator());

/** Sortiert
		 * Kreuz Dame 3
		 * Pik König 4
		 * Karo Bube 2
		 * Herz Bube 2
		 * Herz Ass 11
		 */

	}

	@Test
	public void testCompareKreuzDame_HerzBube() {
		assertEquals("Kreuz Dame ist kleiner als Herz Bube", -1, (kk.compare((s1.Hand.get(0)), (new Karte ("Herz", "Bube", 2)))));
	
	}

	
	@Test
	public void testComparePikkoenig_Karobube() {
		assertEquals("Pik König ist kleiner als Karo Bube",  -1, (kk.compare((s1.Hand.get(1)),(new Karte ("Karo", "Bube", 2)))));
	}
	
	@Test
	public void testCompareKarobube_Herzass() {
		assertEquals("Karo Bube ist kleiner als Herz Ass",  -1, (kk.compare((s1.Hand.get(2)),(new Karte ("Herz", "Ass", 11)))));
	}
	
	@Test
	public void testCompareHerzBube_Kreuzdame() {
		assertEquals("Herz Bube ist größer als Kreuz Dame",  1, (kk.compare((s1.Hand.get(3)),(new Karte ("Kreuz", "Dame", 3)))));
	}
	
	@Test
	public void testCompareHerzass_Pikkoenig() {
		assertEquals("Herz Ass ist größer als Pik König",  1, (kk.compare((s1.Hand.get(4)),(new Karte ("Pik", "König", 4)))));
	}

}

