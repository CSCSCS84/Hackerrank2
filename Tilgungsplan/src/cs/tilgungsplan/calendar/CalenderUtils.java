package cs.tilgungsplan.calendar;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 
 * @author Christoph This Class consists of methods for calculating end of next
 *         month/quartal/half year or year.
 * 
 */
public class CalenderUtils {

	// TODO method is not working correct
	/**
	 * Calculates the last Timestamp of the next month for a given time.
	 * 
	 * @param currentTime
	 *            current time in millis
	 * 
	 * @return Timestamp
	 * @deprecated Method is not working correctly and has not been tested.
	 */
	public static Timestamp calculateEndOfNextMonth(long currentTime) {
		Calendar calender = new GregorianCalendar();
		calender.setTimeInMillis(currentTime + 1);
		calender.add(Calendar.DAY_OF_MONTH, 1);
		int actualMaximumOfCurrentMonth = calender.getActualMaximum(Calendar.DAY_OF_MONTH);
		calender.set(Calendar.DAY_OF_MONTH, actualMaximumOfCurrentMonth);
		return new Timestamp(calender.getTimeInMillis());
	}

	/**
	 * Not yet implemented.
	 * 
	 * @param currentTime
	 *            currentTime in millis
	 * @return Timestamp
	 */
	public static Timestamp calculateEndOfNextQuartal(long currentTime) {
		// TODO
		return new Timestamp(currentTime);
	}

	/**
	 * Not yet implemented.
	 * 
	 * @param currentTime
	 *            currentTime in millis
	 * @return Timestamp
	 */
	public static Timestamp calculateEndOfNextHalfYear(long currentTime) {
		// TODO
		return new Timestamp(currentTime);
	}

	/**
	 * Not yet implemented.
	 * 
	 * @param currentTime
	 *            currentTime in millis
	 * @return Timestamp
	 */
	public static Timestamp calculateEndOfNextYear(long currentTime) {
		// TODO
		return new Timestamp(currentTime);
	}
}
