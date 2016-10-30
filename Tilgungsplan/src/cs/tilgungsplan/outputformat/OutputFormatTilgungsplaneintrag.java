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

	public OutputFormatTilgungsplaneintrag(DateFormater dateFormater, FinancialBetragFormater betragFormater) {
		super();
		this.dateFormater = dateFormater;
		this.financialBetragFormater = betragFormater;
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

}
