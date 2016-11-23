package cs.tilgungsplan.calendar;

import java.sql.Timestamp;

import org.junit.Test;

public class CalendarUtilsTest {

	@Test
	public void test() {
		Timestamp date=CalenderUtils.calculateEndOfNextMonth(System.currentTimeMillis());
	}

}
