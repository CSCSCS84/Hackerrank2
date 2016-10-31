package cs.tilgungsplan.inputreader;

import java.math.BigDecimal;
import java.util.Scanner;

import cs.tilgungsplan.tilgungsplancalculator.RoundingRules;
import cs.tilgungsplan.tilgungsplancalculator.Tilgungsplangroessen;
import cs.tilgungsplan.tilgungsplancalculator.Tilgungsrhythmus;

/**
 * Reads the Tilgungsplangroessen from the console.
 * 
 * @author Christoph
 * 
 */
//TODO Reader should handle incorrect input
public class InputReader {
	
	private Scanner sc;

	public Tilgungsplangroessen readInputFromConsole() {

		sc = new Scanner(System.in);

		BigDecimal darlehensbetrag = readDarlehensbetrag();

		BigDecimal sollzins = readSollzins();
		BigDecimal tilgungAnfang = readTilgungsanfang();
		short zinsbindung = readZinsbindung();

		sc.close();

		return new Tilgungsplangroessen(darlehensbetrag, sollzins, tilgungAnfang, zinsbindung,
				Tilgungsrhythmus.ONEMONTH, new RoundingRules());
	}

	private BigDecimal readDarlehensbetrag() {
		System.out.println("Bitte den Darlehensbetrag eingeben.");
		String lineDarlehensbetrag = sc.nextLine();
		BigDecimal darlehensbetrag = new BigDecimal(lineDarlehensbetrag).negate();
		if (darlehensbetrag.compareTo(BigDecimal.ZERO) > 0) {
			System.out.println("Darlehensbetrag muss positiv sein.");
			sc.close();
			return null;
		}
		return darlehensbetrag;
	}

	private short readZinsbindung() {
		System.out.println("Bitte die Zinsbindung in Jahren eingeben.");
		short zinsbindung = sc.nextShort();
		return zinsbindung;
	}

	private BigDecimal readTilgungsanfang() {
		System.out.println("Bitte die anfängliche Tilgung in Prozent eingeben.");
		String linetilgungAnfang = sc.nextLine();
		BigDecimal tilgungAnfang = new BigDecimal(linetilgungAnfang);
		return tilgungAnfang;
	}

	private BigDecimal readSollzins() {
		System.out.println("Bitte den Sollzins in Prozent eingeben.");
		String lineSollzins = sc.nextLine();
		BigDecimal sollzins = new BigDecimal(lineSollzins);
		return sollzins;
	}

}
