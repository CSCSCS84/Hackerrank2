package cs.tilgungsplan.tilgungsplancalculator;

import java.math.BigDecimal;
import java.sql.Timestamp;

import cs.tilgungsplan.calendar.CalenderUtils;
import cs.tilgungsplan.zinsen.ZinsCalculator;

/**
 * Calculates a Tilgungsplan for given Tilgungsplangroessen. Note: There are no unit tests for this class yet. 
 * @author Christoph
 *
 */
public class TilgungsplanCalculator {
	

	public Tilgungsplan calculateTilgungsplan(Tilgungsplangroessen tilgungsplangroessen) {

		Tilgungsplan tilgungsplan = new Tilgungsplan();
		TilgungsplangroessenHelperCalculator fixedTilgungsplangroessenCalculator = new TilgungsplangroessenHelperCalculator();
		TilgungsplangroessenHelper tilgungsplangroessenHelper = fixedTilgungsplangroessenCalculator
				.calculateFixedTilgungsplangroessen(tilgungsplangroessen);

		Tilgungsplaneintrag tilgungsplaneintrag = calculateFirstTilgungsplaneintrag(tilgungsplangroessen);
		tilgungsplan.addTilgungsplaneintrag(tilgungsplaneintrag);

		for (int i = 1; i <= tilgungsplangroessenHelper.getLaufzeit(); i++) {
			tilgungsplaneintrag = calculateTilgungsplanEintrag(tilgungsplaneintrag, tilgungsplangroessenHelper);
			tilgungsplan.addTilgungsplaneintrag(tilgungsplaneintrag);
		}

		return tilgungsplan;
	}

	private Tilgungsplaneintrag calculateFirstTilgungsplaneintrag(Tilgungsplangroessen tilgungsplangroessen) {

		Tilgungsplaneintrag tilgungsplaneintrag = new Tilgungsplaneintrag(calculateNextDate(System.currentTimeMillis(),
				tilgungsplangroessen.getTilgungsrhythmus()), tilgungsplangroessen.getDarlehensbetrag(), new BigDecimal(
				"0"), tilgungsplangroessen.getDarlehensbetrag(), tilgungsplangroessen.getDarlehensbetrag());
		return tilgungsplaneintrag;
	}

	private Tilgungsplaneintrag calculateTilgungsplanEintrag(Tilgungsplaneintrag lastTilgungsplaneintrag,
			TilgungsplangroessenHelper tilgungsplangroessenHelper) {

		BigDecimal zinsbetrag = calculateZinsbetrag(lastTilgungsplaneintrag, tilgungsplangroessenHelper);
		BigDecimal tilgungsbetrag = calculateTilgungsbetrag(lastTilgungsplaneintrag, tilgungsplangroessenHelper,
				zinsbetrag);
		BigDecimal restSchuld = calculateRestschuld(lastTilgungsplaneintrag, tilgungsplangroessenHelper, tilgungsbetrag);
		Timestamp nextDate = calculateNextDate(lastTilgungsplaneintrag.getDate().getTime(),
				tilgungsplangroessenHelper.getTilgungsrhythmus());

		BigDecimal rate=zinsbetrag.add(tilgungsbetrag);
		Tilgungsplaneintrag tilgungsplaneintrag = new Tilgungsplaneintrag(nextDate, restSchuld, zinsbetrag,
				tilgungsbetrag, rate);
		return tilgungsplaneintrag;
	}

	private BigDecimal calculateZinsbetrag(Tilgungsplaneintrag lastTilgungsplaneintrag,
			TilgungsplangroessenHelper tilgungsplangroessenHelper) {
		ZinsCalculator zinssatzCalculator = new ZinsCalculator(
				tilgungsplangroessenHelper.getRoundingRules().zinsbetragRoundingRule);
		BigDecimal zinsbetrag = zinssatzCalculator.calculateZinsBetrag(lastTilgungsplaneintrag.getRestschuld(),
				tilgungsplangroessenHelper.getSollzinsPerPeriod());
		return zinsbetrag;
	}

	private BigDecimal calculateTilgungsbetrag(Tilgungsplaneintrag lastTilgungsplaneintrag,
			TilgungsplangroessenHelper tilgungsplangroessenHelper, BigDecimal zinsbetrag) {
		BigDecimal tilgungsbetrag = tilgungsplangroessenHelper.getRate().subtract(zinsbetrag);
		if (tilgungsbetrag.compareTo(lastTilgungsplaneintrag.getRestschuld().negate()) >= 0) {
			tilgungsbetrag = lastTilgungsplaneintrag.getRestschuld().negate();
		}
		tilgungsbetrag = tilgungsbetrag.setScale(
				tilgungsplangroessenHelper.getRoundingRules().tilgungsbetragRoundingRule.getScale(),
				tilgungsplangroessenHelper.getRoundingRules().tilgungsbetragRoundingRule.getScale());
		return tilgungsbetrag;
	}

	private BigDecimal calculateRestschuld(Tilgungsplaneintrag lastTilgungsplaneintrag,
			TilgungsplangroessenHelper tilgungsplangroessenHelper, BigDecimal tilgungsbetrag) {
		BigDecimal restSchuld = lastTilgungsplaneintrag.getRestschuld().setScale(
				tilgungsplangroessenHelper.getRoundingRules().restschuldRoundingRule.getScale(),
				tilgungsplangroessenHelper.getRoundingRules().restschuldRoundingRule.getRoundingMode());
		restSchuld = restSchuld.add(tilgungsbetrag,
				tilgungsplangroessenHelper.getRoundingRules().restschuldRoundingRule.getMathContext());
		return restSchuld;
	}

	private Timestamp calculateNextDate(long timeInMillis, Tilgungsrhythmus tilgungsrhythmus) {
		Timestamp date = null;
		switch (tilgungsrhythmus) {

		case ONEYEAR:
			date = CalenderUtils.calculateEndOfNextYear(timeInMillis);
			break;

		case SIXMONTH:
			date = CalenderUtils.calculateEndOfNextHalfYear(timeInMillis);
			break;

		case THREEMONTH:
			date = CalenderUtils.calculateEndOfNextQuartal(timeInMillis);
			break;

		case ONEMONTH:
			date = CalenderUtils.calculateEndOfNextMonth(timeInMillis);
			break;
		}
		return date;
	}
}
