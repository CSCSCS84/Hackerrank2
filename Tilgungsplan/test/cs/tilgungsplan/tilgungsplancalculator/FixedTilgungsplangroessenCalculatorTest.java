package cs.tilgungsplan.tilgungsplancalculator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class FixedTilgungsplangroessenCalculatorTest {
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
		TilgungsplanWithFixedRateGroessenCalculator fixedTilgungsplangroessenCalculator = new TilgungsplanWithFixedRateGroessenCalculator();

		TilgungsplanWithFixedRateGroessen tilgungsplanWithFixedRateGroessen = fixedTilgungsplangroessenCalculator
				.calculateFixedTilgungsplangroessen(tilgungsplangroessen);

		assertEquals("343.33", tilgungsplanWithFixedRateGroessen.getRate().toPlainString());
		assertEquals(12, tilgungsplanWithFixedRateGroessen.getLaufzeit());
		BigDecimal zinsen=sollzins.divide(new BigDecimal("12"),roundingRules.zinsRoundingRule.getMathContext());
		zinsen=zinsen.setScale(roundingRules.zinsRoundingRule.getScale(),roundingRules.zinsRoundingRule.getRoundingMode());
		assertEquals(zinsen.toPlainString(), tilgungsplanWithFixedRateGroessen.getZinsen().toPlainString());

	}
	
	@Test
	public void calcTilgungsplanFixedGroessenTest2() {
		BigDecimal darlehensbetrag = new BigDecimal("-100000");
		BigDecimal sollzins = new BigDecimal("0.0212");
		BigDecimal percentFirstTilgung = new BigDecimal("0.02");
		short zinsbindungInYears = 1;
		Tilgungsrhythmus tilgungsrhythmus = Tilgungsrhythmus.ONEMONTH;

		Tilgungsplangroessen tilgungsplangroessen = new Tilgungsplangroessen(darlehensbetrag, sollzins,
				percentFirstTilgung, zinsbindungInYears, tilgungsrhythmus, roundingRules);
		TilgungsplanWithFixedRateGroessenCalculator fixedTilgungsplangroessenCalculator = new TilgungsplanWithFixedRateGroessenCalculator();

		TilgungsplanWithFixedRateGroessen tilgungsplanWithFixedRateGroessen = fixedTilgungsplangroessenCalculator
				.calculateFixedTilgungsplangroessen(tilgungsplangroessen);

		assertEquals("343.33", tilgungsplanWithFixedRateGroessen.getRate().toPlainString());
		assertEquals(12, tilgungsplanWithFixedRateGroessen.getLaufzeit());
		BigDecimal zinsen=sollzins.divide(new BigDecimal("12"),roundingRules.zinsRoundingRule.getMathContext());
		zinsen=zinsen.setScale(roundingRules.zinsRoundingRule.getScale(),roundingRules.zinsRoundingRule.getRoundingMode());
		assertEquals(zinsen.toPlainString(), tilgungsplanWithFixedRateGroessen.getZinsen().toPlainString());

	}
	
	@Test
	public void calcTilgungsplanFixedGroessenTest3() {
		BigDecimal darlehensbetrag = new BigDecimal("-100000");
		BigDecimal sollzins = new BigDecimal("0.0212");
		BigDecimal percentFirstTilgung = new BigDecimal("0.02");
		short zinsbindungInYears = 1;
		Tilgungsrhythmus tilgungsrhythmus = Tilgungsrhythmus.ONEMONTH;

		Tilgungsplangroessen tilgungsplangroessen = new Tilgungsplangroessen(darlehensbetrag, sollzins,
				percentFirstTilgung, zinsbindungInYears, tilgungsrhythmus, roundingRules);
		TilgungsplanWithFixedRateGroessenCalculator fixedTilgungsplangroessenCalculator = new TilgungsplanWithFixedRateGroessenCalculator();

		TilgungsplanWithFixedRateGroessen tilgungsplanWithFixedRateGroessen = fixedTilgungsplangroessenCalculator
				.calculateFixedTilgungsplangroessen(tilgungsplangroessen);

		assertEquals("343.33", tilgungsplanWithFixedRateGroessen.getRate().toPlainString());
		assertEquals(12, tilgungsplanWithFixedRateGroessen.getLaufzeit());
		BigDecimal zinsen=sollzins.divide(new BigDecimal("12"),roundingRules.zinsRoundingRule.getMathContext());
		zinsen=zinsen.setScale(roundingRules.zinsRoundingRule.getScale(),roundingRules.zinsRoundingRule.getRoundingMode());
		assertEquals(zinsen.toPlainString(), tilgungsplanWithFixedRateGroessen.getZinsen().toPlainString());

	}
	
	@Test
	public void calcTilgungsplanFixedGroessenTest4() {
		BigDecimal darlehensbetrag = new BigDecimal("-100000");
		BigDecimal sollzins = new BigDecimal("0.0212");
		BigDecimal percentFirstTilgung = new BigDecimal("0.02");
		short zinsbindungInYears = 1;
		Tilgungsrhythmus tilgungsrhythmus = Tilgungsrhythmus.ONEMONTH;

		Tilgungsplangroessen tilgungsplangroessen = new Tilgungsplangroessen(darlehensbetrag, sollzins,
				percentFirstTilgung, zinsbindungInYears, tilgungsrhythmus, roundingRules);
		TilgungsplanWithFixedRateGroessenCalculator fixedTilgungsplangroessenCalculator = new TilgungsplanWithFixedRateGroessenCalculator();

		TilgungsplanWithFixedRateGroessen tilgungsplanWithFixedRateGroessen = fixedTilgungsplangroessenCalculator
				.calculateFixedTilgungsplangroessen(tilgungsplangroessen);

		assertEquals("343.33", tilgungsplanWithFixedRateGroessen.getRate().toPlainString());
		assertEquals(12, tilgungsplanWithFixedRateGroessen.getLaufzeit());
		BigDecimal zinsen=sollzins.divide(new BigDecimal("12"),roundingRules.zinsRoundingRule.getMathContext());
		zinsen=zinsen.setScale(roundingRules.zinsRoundingRule.getScale(),roundingRules.zinsRoundingRule.getRoundingMode());
		assertEquals(zinsen.toPlainString(), tilgungsplanWithFixedRateGroessen.getZinsen().toPlainString());

	}
}
