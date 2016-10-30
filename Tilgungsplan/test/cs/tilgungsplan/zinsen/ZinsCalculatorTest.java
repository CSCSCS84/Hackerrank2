package cs.tilgungsplan.zinsen;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.Test;

import cs.tilgungsplan.roundingrule.BigDecimalRoundingRule;

public class ZinsCalculatorTest {

	@Test
	public void calculateZinsBetragTest() {
		MathContext mathContext = new MathContext(128, RoundingMode.DOWN);
		BigDecimalRoundingRule bigDecimalRoundingRule = new BigDecimalRoundingRule(2, mathContext);
		ZinsCalculator zinsCalculator = new ZinsCalculator(bigDecimalRoundingRule);

		BigDecimal auszahlung = new BigDecimal("100000");
		BigDecimal zinssatz = new BigDecimal("0.02");
		BigDecimal zinsBetrag = zinsCalculator.calculateZinsBetrag(auszahlung, zinssatz);

		assertEquals("-2000.00", zinsBetrag.toPlainString());
	}

	@Test
	public void calculateZinsBetragTest2() {
		MathContext mathContext = new MathContext(128, RoundingMode.DOWN);
		BigDecimalRoundingRule bigDecimalRoundingRule = new BigDecimalRoundingRule(1, mathContext);
		ZinsCalculator zinsCalculator = new ZinsCalculator(bigDecimalRoundingRule);

		BigDecimal auszahlung = new BigDecimal("1");
		BigDecimal zinssatz = new BigDecimal("0.02");
		BigDecimal zinsBetrag = zinsCalculator.calculateZinsBetrag(auszahlung, zinssatz);

		assertEquals("0.0", zinsBetrag.toPlainString());
	}

	@Test
	public void calculateZinsBetragTest3() {
		MathContext mathContext = new MathContext(128, RoundingMode.UP);
		BigDecimalRoundingRule bigDecimalRoundingRule = new BigDecimalRoundingRule(1, mathContext);
		ZinsCalculator zinsCalculator = new ZinsCalculator(bigDecimalRoundingRule);

		BigDecimal auszahlung = new BigDecimal("1");
		BigDecimal zinssatz = new BigDecimal("0.02");
		BigDecimal zinsBetrag = zinsCalculator.calculateZinsBetrag(auszahlung, zinssatz);

		assertEquals("-0.1", zinsBetrag.toPlainString());
	}

	@Test
	public void calculateZinsBetragTest4() {
		MathContext mathContext = new MathContext(128, RoundingMode.UP);
		BigDecimalRoundingRule bigDecimalRoundingRule = new BigDecimalRoundingRule(1, mathContext);
		ZinsCalculator zinsCalculator = new ZinsCalculator(bigDecimalRoundingRule);

		BigDecimal auszahlung = new BigDecimal("-100");
		BigDecimal zinssatz = new BigDecimal("0.5");
		BigDecimal zinsBetrag = zinsCalculator.calculateZinsBetrag(auszahlung, zinssatz);

		assertEquals("50.0", zinsBetrag.toPlainString());
	}

	@Test
	public void calculateZinsBetragTest5() {
		MathContext mathContext = new MathContext(128, RoundingMode.UP);
		BigDecimalRoundingRule bigDecimalRoundingRule = new BigDecimalRoundingRule(2, mathContext);
		ZinsCalculator zinsCalculator = new ZinsCalculator(bigDecimalRoundingRule);

		BigDecimal auszahlung = new BigDecimal("100.00");
		BigDecimal zinssatz = new BigDecimal("-5.0");
		BigDecimal zinsBetrag = zinsCalculator.calculateZinsBetrag(auszahlung, zinssatz);

		assertEquals("500.00", zinsBetrag.toPlainString());
	}

}
