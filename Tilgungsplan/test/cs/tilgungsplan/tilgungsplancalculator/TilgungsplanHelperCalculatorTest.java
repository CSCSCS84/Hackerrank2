package cs.tilgungsplan.tilgungsplancalculator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class TilgungsplanHelperCalculatorTest {
	RoundingRules roundingRules;

	@Before
	public void setUp() throws Exception {
		roundingRules = new RoundingRules();
	}

	@Test
	public void calcTilgungsplanFixedGroessenTest1() {

		BigDecimal darlehensbetrag = new BigDecimal("-100000");
		BigDecimal sollzins = new BigDecimal("0.0212");
		BigDecimal percentFirstTilgung = new BigDecimal("0.02");
		short zinsbindungInYears = 1;
		Tilgungsrhythmus tilgungsrhythmus = Tilgungsrhythmus.ONEMONTH;

		Tilgungsplangroessen tilgungsplangroessen = new Tilgungsplangroessen(darlehensbetrag, sollzins,
				percentFirstTilgung, zinsbindungInYears, tilgungsrhythmus, roundingRules);
		TilgungsplangroessenHelperCalculator fixedTilgungsplangroessenCalculator = new TilgungsplangroessenHelperCalculator();

		TilgungsplangroessenHelper tilgungsplanWithFixedRateGroessen = fixedTilgungsplangroessenCalculator
				.calculateFixedTilgungsplangroessen(tilgungsplangroessen);

		assertEquals("343.33", tilgungsplanWithFixedRateGroessen.getRate().toPlainString());
		assertEquals(12, tilgungsplanWithFixedRateGroessen.getLaufzeit());

		BigDecimal zinssatz = sollzins.divide(new BigDecimal("12"), roundingRules.genericRoundingRule.getMathContext());
		zinssatz = zinssatz.setScale(roundingRules.genericRoundingRule.getScale(),
				roundingRules.genericRoundingRule.getRoundingMode());
		assertEquals(zinssatz.toPlainString(), tilgungsplanWithFixedRateGroessen.getSollzinsPerPeriod().toPlainString());

	}

	@Test
	public void calcTilgungsplanFixedGroessenTest2() {
		BigDecimal darlehensbetrag = new BigDecimal("-0");
		BigDecimal sollzins = new BigDecimal("0.0212");
		BigDecimal percentFirstTilgung = new BigDecimal("2.00");
		short zinsbindungInYears = 2;
		Tilgungsrhythmus tilgungsrhythmus = Tilgungsrhythmus.THREEMONTH;

		Tilgungsplangroessen tilgungsplangroessen = new Tilgungsplangroessen(darlehensbetrag, sollzins,
				percentFirstTilgung, zinsbindungInYears, tilgungsrhythmus, roundingRules);
		TilgungsplangroessenHelperCalculator fixedTilgungsplangroessenCalculator = new TilgungsplangroessenHelperCalculator();

		TilgungsplangroessenHelper tilgungsplanWithFixedRateGroessen = fixedTilgungsplangroessenCalculator
				.calculateFixedTilgungsplangroessen(tilgungsplangroessen);

		assertEquals("0.00", tilgungsplanWithFixedRateGroessen.getRate().toPlainString());
		assertEquals(8, tilgungsplanWithFixedRateGroessen.getLaufzeit());

		BigDecimal zinsen = sollzins.divide(new BigDecimal("4"), roundingRules.genericRoundingRule.getMathContext());
		zinsen = zinsen.setScale(roundingRules.genericRoundingRule.getScale(),
				roundingRules.genericRoundingRule.getRoundingMode());
		assertEquals(zinsen.toPlainString(), tilgungsplanWithFixedRateGroessen.getSollzinsPerPeriod().toPlainString());

	}
	
	@Test
	public void calcTilgungsplanFixedGroessenTest3() {
		BigDecimal darlehensbetrag = new BigDecimal("-1000");
		BigDecimal sollzins = new BigDecimal("-0.2132");
		BigDecimal percentFirstTilgung = new BigDecimal("0.01");
		short zinsbindungInYears = 5;
		Tilgungsrhythmus tilgungsrhythmus = Tilgungsrhythmus.SIXMONTH;

		Tilgungsplangroessen tilgungsplangroessen = new Tilgungsplangroessen(darlehensbetrag, sollzins,
				percentFirstTilgung, zinsbindungInYears, tilgungsrhythmus, roundingRules);
		TilgungsplangroessenHelperCalculator fixedTilgungsplangroessenCalculator = new TilgungsplangroessenHelperCalculator();

		TilgungsplangroessenHelper tilgungsplanWithFixedRateGroessen = fixedTilgungsplangroessenCalculator
				.calculateFixedTilgungsplangroessen(tilgungsplangroessen);

		assertEquals("-101.60", tilgungsplanWithFixedRateGroessen.getRate().toPlainString());
		assertEquals(10, tilgungsplanWithFixedRateGroessen.getLaufzeit());

		BigDecimal zinsen = sollzins.divide(new BigDecimal("2"), roundingRules.genericRoundingRule.getMathContext());
		zinsen = zinsen.setScale(roundingRules.genericRoundingRule.getScale(),
				roundingRules.genericRoundingRule.getRoundingMode());
		assertEquals(zinsen.toPlainString(), tilgungsplanWithFixedRateGroessen.getSollzinsPerPeriod().toPlainString());

	}

	@Test
	public void calcTilgungsplanFixedGroessenTest4() {
		BigDecimal darlehensbetrag = new BigDecimal("-10000000000");
		BigDecimal sollzins = new BigDecimal("0.05");
		BigDecimal percentFirstTilgung = new BigDecimal("0.02");
		short zinsbindungInYears = 10;
		Tilgungsrhythmus tilgungsrhythmus = Tilgungsrhythmus.ONEYEAR;

		Tilgungsplangroessen tilgungsplangroessen = new Tilgungsplangroessen(darlehensbetrag, sollzins,
				percentFirstTilgung, zinsbindungInYears, tilgungsrhythmus, roundingRules);
		TilgungsplangroessenHelperCalculator fixedTilgungsplangroessenCalculator = new TilgungsplangroessenHelperCalculator();

		TilgungsplangroessenHelper tilgungsplanWithFixedRateGroessen = fixedTilgungsplangroessenCalculator
				.calculateFixedTilgungsplangroessen(tilgungsplangroessen);

		assertEquals("700000000.00", tilgungsplanWithFixedRateGroessen.getRate().toPlainString());
		assertEquals(10, tilgungsplanWithFixedRateGroessen.getLaufzeit());

		BigDecimal zinsen = sollzins.divide(new BigDecimal("1"), roundingRules.genericRoundingRule.getMathContext());
		zinsen = zinsen.setScale(roundingRules.genericRoundingRule.getScale(),
				roundingRules.genericRoundingRule.getRoundingMode());
		assertEquals(zinsen.toPlainString(), tilgungsplanWithFixedRateGroessen.getSollzinsPerPeriod().toPlainString());

	}


}
