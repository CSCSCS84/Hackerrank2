package cs.tilgungsplan.tilgungsplancalculator;

import java.math.BigDecimal;
import java.math.MathContext;

import cs.tilgungsplan.roundingrule.BigDecimalRoundingRule;
import cs.tilgungsplan.zinsen.ZinsCalculator;

/**
 * 
 * @author Christoph
 * 
 */
public class TilgungsplanWithFixedRateGroessenCalculator {

	public TilgungsplanWithFixedRateGroessen calculateFixedTilgungsplangroessen(
			Tilgungsplangroessen tilgungsplangroessen) {
		int numberOfPeriods = calculateNumberOfPeriods(tilgungsplangroessen);
		BigDecimal zinssatzPerPeriod = calculateZinssatzPerPeriod(tilgungsplangroessen.getSollzins(),
				tilgungsplangroessen.getTilgungsrhythmus(), tilgungsplangroessen.getRoundingRules().zinsRoundingRule);
		BigDecimal firstTilgung = calculateFirstTilgungsbetrag(tilgungsplangroessen);
		BigDecimal rate = calculateRate(tilgungsplangroessen.getDarlehensbetrag(), zinssatzPerPeriod, firstTilgung,
				tilgungsplangroessen);

		return new TilgungsplanWithFixedRateGroessen(rate, zinssatzPerPeriod, numberOfPeriods,
				tilgungsplangroessen.getTilgungsrhythmus(), tilgungsplangroessen.getRoundingRules());
	}

	private BigDecimal calculateFirstTilgungsbetrag(Tilgungsplangroessen tilgungsplangroessen) {

		BigDecimal zinssatzFirstTilgung = calculateZinssatzFirstTilgung(tilgungsplangroessen);
		ZinsCalculator zinsbetragCalculator = new ZinsCalculator(
				tilgungsplangroessen.getRoundingRules().tilgungsbetragRoundingRule);
		BigDecimal tilgungsBetrag = zinsbetragCalculator.calculateZinsBetrag(tilgungsplangroessen.getDarlehensbetrag(),
				zinssatzFirstTilgung);

		return tilgungsBetrag;
	}

	private BigDecimal calculateRate(BigDecimal betrag, BigDecimal zinsen, BigDecimal firstTilgung,
			Tilgungsplangroessen tilgungsplangroessen) {
		ZinsCalculator zinssatzcalculator = new ZinsCalculator(tilgungsplangroessen.getRoundingRules().zinsRoundingRule);
		BigDecimal zinsBetrag = zinssatzcalculator.calculateZinsBetrag(betrag, zinsen);
		BigDecimal rate = firstTilgung.add(zinsBetrag);
		rate = rate.setScale(tilgungsplangroessen.getRoundingRules().tilgungsbetragRoundingRule.getScale(),
				tilgungsplangroessen.getRoundingRules().tilgungsbetragRoundingRule.getRoundingMode());

		return rate;
	}

	private BigDecimal calculateZinssatzFirstTilgung(Tilgungsplangroessen tilgungsplangroessen) {
		BigDecimal numberOfPeriods = null;
		MathContext mathContext = tilgungsplangroessen.getRoundingRules().zinsRoundingRule.getMathContext();
		switch (tilgungsplangroessen.getTilgungsrhythmus()) {
		case ONEYEAR:
			numberOfPeriods = tilgungsplangroessen.getPercentFirstTilgung();
			break;
		case SIXMONTH:
			numberOfPeriods = tilgungsplangroessen.getPercentFirstTilgung().divide(new BigDecimal("2"), mathContext);
			break;
		case THREEMONTH:
			numberOfPeriods = tilgungsplangroessen.getPercentFirstTilgung().divide(new BigDecimal("4"), mathContext);
			break;
		case ONEMONTH:
			numberOfPeriods = tilgungsplangroessen.getPercentFirstTilgung().divide(new BigDecimal("12"), mathContext);

			break;
		}
		return numberOfPeriods;

	}

	private int calculateNumberOfPeriods(Tilgungsplangroessen tilgungsplangroessen) {
		int numberOfPeriods = 0;
		switch (tilgungsplangroessen.getTilgungsrhythmus()) {
		case ONEYEAR:
			numberOfPeriods = tilgungsplangroessen.getZinsbindungInYears();
			break;
		case SIXMONTH:
			numberOfPeriods = tilgungsplangroessen.getZinsbindungInYears() * 2;
			break;
		case THREEMONTH:
			numberOfPeriods = tilgungsplangroessen.getZinsbindungInYears() * 4;
			break;
		case ONEMONTH:
			numberOfPeriods = tilgungsplangroessen.getZinsbindungInYears() * 12;
			break;
		}

		return numberOfPeriods;
	}

	public BigDecimal calculateZinssatzPerPeriod(BigDecimal zinssatzProJahr, Tilgungsrhythmus tilgungsrhytmus,
			BigDecimalRoundingRule bigDecimalRoundingRule) {
		BigDecimal zinssatzProTilgungsrhytmus = null;

		switch (tilgungsrhytmus) {

		case ONEYEAR:
			zinssatzProTilgungsrhytmus = zinssatzProJahr;
			break;

		case SIXMONTH:
			zinssatzProTilgungsrhytmus = zinssatzProJahr.divide(new BigDecimal("2"),
					bigDecimalRoundingRule.getMathContext());
			break;

		case THREEMONTH:
			zinssatzProTilgungsrhytmus = zinssatzProJahr.divide(new BigDecimal("4"),
					bigDecimalRoundingRule.getMathContext());
			break;

		case ONEMONTH:
			zinssatzProTilgungsrhytmus = zinssatzProJahr.divide(new BigDecimal("12"),
					bigDecimalRoundingRule.getMathContext());
			break;
		}

		return zinssatzProTilgungsrhytmus;

	}

}
