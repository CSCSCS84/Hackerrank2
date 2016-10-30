package cs.tilgungsplan.outputformat;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class GermanDateFormaterTest {

	GermanDateFormater germanDateFormater;

	@Before
	public void setUp() throws Exception {
		germanDateFormater = new GermanDateFormater();
	}

	@Test
	public void dateTest1() {
		Calendar calendar = new GregorianCalendar(2016, Calendar.OCTOBER, 28, 0, 0);
		Timestamp date = new Timestamp(calendar.getTimeInMillis());
		String formatedDate = germanDateFormater.formatDate(date);
		assertEquals("28.10.2016", formatedDate);
	}

	@Test
	public void dateTest2() {
		Calendar calendar = new GregorianCalendar(2054, Calendar.JANUARY, 1, 0, 0);
		Timestamp date = new Timestamp(calendar.getTimeInMillis());
		String formatedDate = germanDateFormater.formatDate(date);
		assertEquals("01.01.2054", formatedDate);
	}

}
