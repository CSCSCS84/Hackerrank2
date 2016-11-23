package cs.tilgungsplan.roundingrule;

import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Class that provides informations how a BigDecimal is rounded. This
 * information are scale and MathContext
 * 
 * @author Christoph
 * 
 */
public class BigDecimalRoundingRule {

	private int scale;
	private MathContext mathContext;

	public BigDecimalRoundingRule(int scale, MathContext mathContext) {
		super();
		this.scale = scale;
		this.mathContext = mathContext;
	}

	public RoundingMode getRoundingMode() {
		return mathContext.getRoundingMode();
	}

	public int getPrecision() {
		return mathContext.getPrecision();
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public MathContext getMathContext() {
		return mathContext;
	}

	public void setMathContext(MathContext mathContext) {
		this.mathContext = mathContext;
	}

}
