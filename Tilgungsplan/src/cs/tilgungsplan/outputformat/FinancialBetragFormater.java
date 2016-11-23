package cs.tilgungsplan.outputformat;

import java.math.BigDecimal;

/**
 * Offers a method for formating a given BigDecimal value into a financial
 * amount format, such as 10.000,00
 * 
 * @author Christoph
 * 
 * 
 */
public interface FinancialBetragFormater {

	/**
	 * Formats a given BigDecimal value into a financial amount format, such
	 * as 10.000,00
	 * 
	 * @param betrag
	 * @return
	 * @throws IllegalArgumentException if scale of betrag is bigger than 2
	 */
	String formatBetrag(BigDecimal betrag) throws IllegalArgumentException;
}
