import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DisabilitySiteTest {

	DisabilitySite _subject;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		_subject = new DisabilitySite(new Zone ("A", 0.06, 0.07, new Date ("15 May 2023"), new Date ("10 Sep 2023")));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testZero() {
		_subject.addReading(new Reading (10, new Date ("1 Jan 2023")));
		_subject.addReading(new Reading (10, new Date ("1 Feb 2023")));
		assertEquals(0d, _subject.charge().amount());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test49() {
		_subject.addReading(new Reading (59, new Date ("1 Jan 2023")));
		_subject.addReading(new Reading (108, new Date ("1 Feb 2023")));
		assertEquals(3.58d, _subject.charge().amount());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test50() {
		_subject.addReading(new Reading (100, new Date ("1 Jan 2023")));
		_subject.addReading(new Reading (150, new Date ("1 Feb 2023")));
		assertEquals(3.66d, _subject.charge().amount());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test51() {
		_subject.addReading(new Reading (1000, new Date ("1 Jan 2023")));
		_subject.addReading(new Reading (1051, new Date ("1 Feb 2023")));
		assertEquals(3.72d, _subject.charge().amount());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test74() {
		_subject.addReading(new Reading (10000, new Date ("1 Jan 2023")));
		_subject.addReading(new Reading (10074, new Date ("1 Feb 2023")));
		assertEquals(5.39d, _subject.charge().amount());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test75() {
		_subject.addReading(new Reading (0, new Date ("1 Jan 2023")));
		_subject.addReading(new Reading (75, new Date ("1 Feb 2023")));
		assertEquals(5.46d, _subject.charge().amount());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test76() {
		_subject.addReading(new Reading (50, new Date ("1 Jan 2023")));
		_subject.addReading(new Reading (126, new Date ("1 Feb 2023")));
		assertEquals(5.53d, _subject.charge().amount());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMax() {
		_subject.addReading(new Reading (0, new Date ("1 Jan 2023")));
		_subject.addReading(new Reading (Integer.MAX_VALUE, new Date ("1 Feb 2023")));
		assertEquals (1.5220290473E8, _subject.charge().amount());
	}
	
	@Test
	public void testNoReadings() {
		try {
			_subject.charge();
			assert(false);
		} catch (NullPointerException e) {}
	}
}
