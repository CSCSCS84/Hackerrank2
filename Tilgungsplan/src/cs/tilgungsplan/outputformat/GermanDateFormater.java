package cs.tilgungsplan.outputformat;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Offers a method for formating a given Timestamp into the german date format
 * DAY.MONTH.YEAR xx.xx.xxxx
 * 
 * @author Christoph
 * 
 * 
 */
public class GermanDateFormater implements DateFormater {

	/**
	 * Formats a given Timestamp into the german date format DAY.MONTH.YEAR
	 * xx.xx.xxxx
	 * 
	 * @param date
	 * @return output string in the specific date format
	 */
	public String formatDate(Timestamp date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(date.getTime());
		String day = formatDay(calendar);
		String month = formatMonth(calendar);
		String year = formatYear(calendar);

		return day + "." + month + "." + year;
	}

	private String formatDay(Calendar calendar) {
		String day;
		if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
			day = "0" + calendar.get(Calendar.DAY_OF_MONTH);
		} else {
			day = calendar.get(Calendar.DAY_OF_MONTH) + "";
		}
		return day;
	}

	private String formatMonth(Calendar calendar) {
		String month;
		if (calendar.get(Calendar.MONTH) + 1 < 10) {
			month = "0" + (calendar.get(Calendar.MONTH) + 1);
		} else {
			month = (calendar.get(Calendar.MONTH) + 1) + "";
		}
		return month;
	}

	private String formatYear(Calendar calendar) {
		String year = calendar.get(Calendar.YEAR) + "";
		return year;
	}
}
