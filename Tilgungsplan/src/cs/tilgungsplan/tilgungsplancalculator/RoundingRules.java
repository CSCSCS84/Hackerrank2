package cs.tilgungsplan.tilgungsplancalculator;

import java.math.MathContext;
import java.math.RoundingMode;

import cs.tilgungsplan.roundingrule.BigDecimalRoundingRule;


/**
 * Class that contains information how the values for the Tilgungsplan should be
 * rounded (scale, precision and RoundingMode)
 * 
 * @author Christoph
 * 
 */
public class RoundingRules {

	public BigDecimalRoundingRule tilgungsbetragRoundingRule = new BigDecimalRoundingRule(2, new MathContext(128,
			RoundingMode.DOWN));

	public BigDecimalRoundingRule rateRoundingRule = new BigDecimalRoundingRule(2, new MathContext(128,
			RoundingMode.HALF_DOWN));

	public BigDecimalRoundingRule zinsRoundingRule = new BigDecimalRoundingRule(2, new MathContext(128,
			RoundingMode.HALF_DOWN));

	public BigDecimalRoundingRule restschuldRoundingRule = new BigDecimalRoundingRule(2, new MathContext(128,
			RoundingMode.HALF_DOWN));

}
