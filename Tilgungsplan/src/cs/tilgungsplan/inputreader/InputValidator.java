package cs.tilgungsplan.inputreader;

import java.math.BigDecimal;
import java.util.Scanner;

import cs.tilgungsplan.tilgungsplancalculator.Tilgungsplangroessen;
import cs.tilgungsplan.tilgungsplancalculator.Tilgungsrhythmus;


public class InputValidator {

	public Tilgungsplangroessen readInput(String[] input) {
		Scanner sc = new Scanner(System.in);
        System.out.println("Printing the file passed in:");
        String line=sc.nextLine();
        
		BigDecimal darlehensbetrag = new BigDecimal(input[0]);
		BigDecimal sollzins = new BigDecimal(input[1]);
		BigDecimal tilgungAnfang = new BigDecimal(input[2]);
		Short zinsbindung = Short.parseShort(input[3]);
		return new Tilgungsplangroessen(darlehensbetrag, sollzins, tilgungAnfang, zinsbindung, Tilgungsrhythmus.ONEMONTH, null);
	}
}
