package cs.tilgungsplan.outputformat;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Offers a method for formating a given BigDecimal value into the german
 * financial amount format x.xxx.xxx,xx
 * 
 * @author Christoph
 * 
 * 
 */
public class GermanFinancialBetragFormater implements FinancialBetragFormater {

	public GermanFinancialBetragFormater() {
		super();
	}

	/**
	 * Formats a given BigDecimal value into the german financial amount format
	 * x.xxx.xxx,xx
	 * 
	 * @param betrag
	 * @return
	 * @throws IllegalArgumentException
	 *             if scale of betrag is bigger than 2
	 */
	public String formatBetrag(BigDecimal betrag) throws IllegalArgumentException {
		if (betrag.scale() > 2) {
			throw new IllegalArgumentException("Scale of betrag must not be bigger than 2");
		}
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.setMaximumFractionDigits(2);
		decimalFormat.setMinimumFractionDigits(2);
		decimalFormat.setGroupingSize(3);
		decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(java.util.Locale.GERMANY));

		return decimalFormat.format(betrag);
	}

}
