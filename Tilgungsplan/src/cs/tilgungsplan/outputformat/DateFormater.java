package cs.tilgungsplan.outputformat;

import java.sql.Timestamp;

/**
 * Offers a method for formating a given Timestamp into a specific date format
 * 
 * @author Christoph
 * 
 * 
 */
public interface DateFormater {

	/**
	 * Formats a given Timestamp into a specific date format.
	 * 
	 * @param date
	 * @return output string in the specific date format
	 */
	String formatDate(Timestamp date);

}
