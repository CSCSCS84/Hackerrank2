package cs.tilgungsplan.tilgungsplancalculator;

import cs.tilgungsplan.inputreader.InputReader;
import cs.tilgungsplan.outputformat.Currency;
import cs.tilgungsplan.outputformat.GermanDateFormater;
import cs.tilgungsplan.outputformat.GermanFinancialBetragFormater;
import cs.tilgungsplan.outputformat.OutputFormatTilgungsplaneintrag;

/**
 * Class for calculating a Tilgungsplan with the console
 * 
 * @author christoph
 * 
 */
public class TilgungsplanTester {

	public static void main(String[] args) {

		InputReader inputReader = new InputReader();
		Tilgungsplangroessen tilgungsplangroessen = inputReader.readInputFromConsole();
		if (tilgungsplangroessen == null) {
			return;
		}
		TilgungsplanCalculator tilgungsplanCalculator = new TilgungsplanCalculator();
		Tilgungsplan tilgungsplan = tilgungsplanCalculator.calculateTilgungsplan(tilgungsplangroessen);
		OutputFormatTilgungsplaneintrag outputFormat = new OutputFormatTilgungsplaneintrag(new GermanDateFormater(),
				new GermanFinancialBetragFormater(), Currency.euro);

		printOutput(tilgungsplan, outputFormat);

	}

	private static void printOutput(Tilgungsplan tilgungsplan, OutputFormatTilgungsplaneintrag outputFormat) {
		System.out.println();
		System.out.println("Datum " + "Restschuld " + "Zinsen " + "Tilgung(+) / Auszahlung(-)" + "Restschuld");
		System.out.println();
		System.out.println(tilgungsplan.toString(outputFormat));
		Tilgungsplaneintrag lastTilgungsplaneintrag = tilgungsplan.getTilgungsplaneintraege().getLast();
		System.out.println("Zinsbindungsende " + lastTilgungsplaneintrag.getRestschuld() + " " + Currency.euro + "  "
				+ lastTilgungsplaneintrag.getZinsbetrag() + " " + Currency.euro + "  "
				+ lastTilgungsplaneintrag.getTilgungsbetrag() + " " + Currency.euro + "  "
				+ lastTilgungsplaneintrag.getRestschuld() + " " + Currency.euro + "  ");
	}
}
