package DataStructure;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class KartenKomparatorTest {
	private KartenKomparator kk = new KartenKomparator();
	private Spieler s1;
	private ArrayList<Karte> kartendeck = Karte.erstelleKartendeck();
	

	@Before
	public void setUp()  {
		s1 = new Spieler();
		s1.Hand.add(kartendeck.get(0)); // Herz Bube 2
		s1.Hand.add(kartendeck.get(5)); // Karo Bube 2
		s1.Hand.add(kartendeck.get(4)); // Herz Ass 11
		s1.Hand.add(kartendeck.get(16)); // Kreuz Dame 3
		s1.Hand.add(kartendeck.get(12)); // Pik Königer 4
		
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
		assertEquals("Kreuz Dame ist kleiner als Herz Bube", -1, (kk.compare((s1.Hand.get(0)),(kartendeck.get(0)))));
	}
	
	@Test
	public void testComparePikkoenig_Karobube() {
		assertEquals("Pik König ist kleiner als Karo Bube",  -1, (kk.compare((s1.Hand.get(1)),(kartendeck.get(5)))));
	}
	
	@Test
	public void testCompareKarobube_Herzass() {
		assertEquals("Karo Bube ist kleiner als Herz Ass",  -1, (kk.compare((s1.Hand.get(2)),(kartendeck.get(4)))));
	}
	
	@Test
	public void testCompareHerzBube_Kreuzdame() {
		assertEquals("Herz Bube ist größer als Kreuz Dame",  1, (kk.compare((s1.Hand.get(3)),(kartendeck.get(16)))));
	}
	
	@Test
	public void testCompareHerzass_Pikkoenig() {
		assertEquals("Herz Ass ist größer als Pik König",  1, (kk.compare((s1.Hand.get(4)),(kartendeck.get(2)))));
	}

}
