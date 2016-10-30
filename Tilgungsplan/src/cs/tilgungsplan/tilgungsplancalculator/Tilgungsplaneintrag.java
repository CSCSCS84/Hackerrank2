package cs.tilgungsplan.tilgungsplancalculator;

import java.math.BigDecimal;
import java.sql.Timestamp;

import cs.tilgungsplan.outputformat.FinancialBetragFormater;
import cs.tilgungsplan.outputformat.DateFormater;
import cs.tilgungsplan.outputformat.OutputFormatTilgungsplaneintrag;

/**
 * Class that provides all relevant information for a Tilgungsplaneintrag
 * 
 * @author Christoph
 * 
 */
public class Tilgungsplaneintrag {

	private Timestamp date;
	private BigDecimal restschuld;
	private BigDecimal zinsbetrag;
	private BigDecimal tilgungsbetrag;
	private BigDecimal rate;

	public Tilgungsplaneintrag(Timestamp date, BigDecimal restschuld, BigDecimal zinsbetrag, BigDecimal tilgungsbetrag,
			BigDecimal rate) {
		super();
		this.date = date;
		this.restschuld = restschuld;
		this.zinsbetrag = zinsbetrag;
		this.tilgungsbetrag = tilgungsbetrag;
		this.rate = rate;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public BigDecimal getRestschuld() {
		return restschuld;
	}

	public void setRestschuld(BigDecimal restschuld) {
		this.restschuld = restschuld;
	}

	public BigDecimal getZinsbetrag() {
		return zinsbetrag;
	}

	public void setZinsbetrag(BigDecimal zinsbetrag) {
		this.zinsbetrag = zinsbetrag;
	}

	public BigDecimal getTilgungsbetrag() {
		return tilgungsbetrag;
	}

	public void setTilgungsbetrag(BigDecimal tilgungsbetrag) {
		this.tilgungsbetrag = tilgungsbetrag;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	/**
	 * Returns a string in the given format outputFormat
	 * 
	 * @param outputFormat
	 * @return a string in the given format
	 */
	public String toString(OutputFormatTilgungsplaneintrag outputFormat) {
		DateFormater dateFormater = outputFormat.getDateFormater();
		FinancialBetragFormater financialBetragFormater = outputFormat.getFinancialBetragFormater();
		return dateFormater.formatDate(date) + " " + financialBetragFormater.formatBetrag(restschuld) + " " + financialBetragFormater.formatBetrag(zinsbetrag) + " "
				+ financialBetragFormater.formatBetrag(tilgungsbetrag) + " " + financialBetragFormater.formatBetrag(rate);
	}

}
