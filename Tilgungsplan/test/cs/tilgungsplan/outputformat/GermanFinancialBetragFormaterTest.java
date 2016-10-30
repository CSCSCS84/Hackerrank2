package cs.tilgungsplan.outputformat;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class GermanFinancialBetragFormaterTest {

	GermanFinancialBetragFormater germanFinancialBetragFormater;

	@Before
	public void setUp() throws Exception {
		germanFinancialBetragFormater = new GermanFinancialBetragFormater();
	}

	@Test
	public void bigBetragTest() {
		BigDecimal betrag = new BigDecimal("76111789.76");
		String formatedBetrag = germanFinancialBetragFormater.formatBetrag(betrag);
		assertEquals("76.111.789,76", formatedBetrag);
	}

	@Test
	public void negativeBetragTest() {
		BigDecimal betrag = new BigDecimal("-0.02");
		String formatedBetrag = germanFinancialBetragFormater.formatBetrag(betrag);
		assertEquals("-0,02", formatedBetrag);
	}

	@Test
	public void integerBetragTest() {
		BigDecimal betrag = new BigDecimal("2");
		String formatedBetrag = germanFinancialBetragFormater.formatBetrag(betrag);
		assertEquals("2,00", formatedBetrag);
	}

	@Test
	public void scaleIsThreeTest() {
		try {
			BigDecimal betrag = new BigDecimal("2.002");
			germanFinancialBetragFormater.formatBetrag(betrag);
			fail("Expected an IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException exception) {
			assertEquals(exception.getMessage(), "Scale of betrag must not be bigger than 2");
		}
	}

}
