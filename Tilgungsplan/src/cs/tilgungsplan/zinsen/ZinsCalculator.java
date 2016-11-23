package cs.tilgungsplan.zinsen;

import java.math.BigDecimal;

import cs.tilgungsplan.roundingrule.BigDecimalRoundingRule;

/**
 * Class that provides methods for calculating
 * 
 * @author Christoph
 * 
 */
public class ZinsCalculator {

	BigDecimalRoundingRule bigDecimalRoundingRule;

	public ZinsCalculator(BigDecimalRoundingRule bigDecimalRoundingRule) {
		super();
		this.bigDecimalRoundingRule = bigDecimalRoundingRule;
	}

	/**
	 * Calculates the zinsbetrag with using the RoundingRules of this object.
	 * 
	 * @param betrag the given betrag where debts are a negative betrag
	 * @param zins
	 * @return zinsbetrag
	 */
	public BigDecimal calculateZinsBetrag(BigDecimal betrag, BigDecimal zins) {
		BigDecimal zinsBetrag = betrag.multiply(zins);
		zinsBetrag = zinsBetrag.negate(bigDecimalRoundingRule.getMathContext());
		zinsBetrag = zinsBetrag.setScale(bigDecimalRoundingRule.getScale(), bigDecimalRoundingRule.getRoundingMode());
		return zinsBetrag;
	}

	public BigDecimalRoundingRule getBigIntegerPrecision() {
		return bigDecimalRoundingRule;
	}

	public void setBigIntegerPrecision(BigDecimalRoundingRule bigIntegerPrecision) {
		this.bigDecimalRoundingRule = bigIntegerPrecision;
	}
}
