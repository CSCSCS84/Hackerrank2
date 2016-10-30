package cs.tilgungsplan.tilgungsplancalculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Timestamp;

import cs.tilgungsplan.calendar.CalenderUtils;
import cs.tilgungsplan.outputformat.GermanDateFormater;
import cs.tilgungsplan.outputformat.GermanFinancialBetragFormater;
import cs.tilgungsplan.outputformat.OutputFormatTilgungsplaneintrag;
import cs.tilgungsplan.zinsen.ZinsCalculator;

public class TilgungsplanCalculator {
	private static int scaleForInput = 128;
	static MathContext mathContextForInput = new MathContext(scaleForInput, RoundingMode.HALF_DOWN);

	public static void main(String[] args) {

		TilgungsplanCalculator tc = new TilgungsplanCalculator();
		Tilgungsplangroessen tilgungsplangroessen = new Tilgungsplangroessen(new BigDecimal("-100000",
				mathContextForInput), new BigDecimal("0.0212", mathContextForInput), new BigDecimal("0.02",
				mathContextForInput), (short) 10, Tilgungsrhythmus.ONEMONTH, new RoundingRules());
		Tilgungsplan tp = tc.calcTilgungsplan(tilgungsplangroessen);
		OutputFormatTilgungsplaneintrag outputFormater = new OutputFormatTilgungsplaneintrag(new GermanDateFormater(),
				new GermanFinancialBetragFormater());
		System.out.println(tp.toString(outputFormater));

	}

	private Tilgungsplan calcTilgungsplan(Tilgungsplangroessen tilgungsplangroessen) {

		Tilgungsplan tilgungsplan = new Tilgungsplan();
		TilgungsplanWithFixedRateGroessenCalculator fixedTilgungsplangroessenCalculator = new TilgungsplanWithFixedRateGroessenCalculator();
		TilgungsplanWithFixedRateGroessen tfg = fixedTilgungsplangroessenCalculator
				.calculateFixedTilgungsplangroessen(tilgungsplangroessen);

		Tilgungsplaneintrag te = calcFirstTilgungsplaneintrag(tilgungsplangroessen);
		tilgungsplan.addTilgungsplaneintrag(te);

		for (int i = 1; i <= tfg.getLaufzeit(); i++) {
			te = calcTilgungsplanEintrag(te, tfg);
			tilgungsplan.addTilgungsplaneintrag(te);
		}

		return tilgungsplan;
	}

	private Tilgungsplaneintrag calcFirstTilgungsplaneintrag(Tilgungsplangroessen tilgungsplangroessen) {

		Tilgungsplaneintrag tp = new Tilgungsplaneintrag(calcNextDate(System.currentTimeMillis(),
				tilgungsplangroessen.getTilgungsrhythmus()), tilgungsplangroessen.getDarlehensbetrag(), new BigDecimal(
				"0"), tilgungsplangroessen.getDarlehensbetrag(), tilgungsplangroessen.getDarlehensbetrag());
		return tp;
	}

	private Tilgungsplaneintrag calcTilgungsplanEintrag(Tilgungsplaneintrag te, TilgungsplanWithFixedRateGroessen tfg) {

		ZinsCalculator zinssatzCalculator = new ZinsCalculator(tfg.getRoundingRules().zinsRoundingRule);
		BigDecimal zinsBetrag = zinssatzCalculator.calculateZinsBetrag(te.getRestschuld(), tfg.getZinsen());
		BigDecimal tilgungsBetrag = tfg.getRate().subtract(zinsBetrag);

		BigDecimal restSchuld = te.getRestschuld().setScale(tfg.getRoundingRules().restschuldRoundingRule.getScale(),
				tfg.getRoundingRules().restschuldRoundingRule.getRoundingMode());
		restSchuld = restSchuld.add(tilgungsBetrag, tfg.getRoundingRules().restschuldRoundingRule.getMathContext());
		tilgungsBetrag = tilgungsBetrag.setScale(tfg.getRoundingRules().tilgungsbetragRoundingRule.getScale(),
				tfg.getRoundingRules().tilgungsbetragRoundingRule.getScale());
		Timestamp nextDate = calcNextDate(te.getDate().getTime(), tfg.getTilgungsrhythmus());
		Tilgungsplaneintrag tp = new Tilgungsplaneintrag(nextDate, restSchuld, zinsBetrag, tilgungsBetrag,
				tfg.getRate());
		return tp;
	}

	private Timestamp calcNextDate(long time, Tilgungsrhythmus tilgungsrhythmus) {
		Timestamp date = null;
		switch (tilgungsrhythmus) {

		case ONEYEAR:
			date = CalenderUtils.calculateEndOfNextYear(time);
			break;

		case SIXMONTH:
			date = CalenderUtils.calculateEndOfNextHalfYear(time);
			break;

		case THREEMONTH:
			date = CalenderUtils.calculateEndOfNextQuartal(time);
			break;

		case ONEMONTH:
			date = CalenderUtils.calculateEndOfNextMonth(time);
			break;
		}
		return date;
	}
}
