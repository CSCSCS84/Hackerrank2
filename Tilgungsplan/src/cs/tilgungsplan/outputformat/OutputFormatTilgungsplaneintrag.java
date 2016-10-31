package cs.tilgungsplan.outputformat;

/**
 * Class provides information how the Tilgungsplaneintrag should be formatted
 * when used in an output.
 * 
 * @author Christoph
 * 
 */
public class OutputFormatTilgungsplaneintrag {

	DateFormater dateFormater;
	FinancialBetragFormater financialBetragFormater;
	String currency;

	public OutputFormatTilgungsplaneintrag(DateFormater dateFormater, FinancialBetragFormater financialBetragFormater,
			String currency) {
		super();
		this.dateFormater = dateFormater;
		this.financialBetragFormater = financialBetragFormater;
		this.currency = currency;
	}

	public DateFormater getDateFormater() {
		return dateFormater;
	}

	public void setDateFormater(DateFormater dateFormater) {
		this.dateFormater = dateFormater;
	}

	public FinancialBetragFormater getFinancialBetragFormater() {
		return financialBetragFormater;
	}

	public void setFinancialBetragFormater(FinancialBetragFormater financialBetragFormater) {
		this.financialBetragFormater = financialBetragFormater;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
