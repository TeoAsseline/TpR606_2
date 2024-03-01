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
	public void testHorsEteUniquement() {
		_subject.addReading(new Reading (10, new Date ("1 Jan 2023")));
		_subject.addReading(new Reading (50, new Date ("1 Feb 2023")));
		assertEquals(3.67d, _subject.charge().amount());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testDansEteUniquement() {
		_subject.addReading(new Reading (10, new Date ("16 May 2023")));
		_subject.addReading(new Reading (50, new Date ("9 Sep 2023")));
		assertEquals(3.25d, _subject.charge().amount());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSurDateEte() {
		_subject.addReading(new Reading (10, new Date ("15 May 2023")));
		_subject.addReading(new Reading (50, new Date ("10 Sep 2023")));
		assertEquals(3.25d, _subject.charge().amount());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testFinisseDansEte() {
		_subject.addReading(new Reading (10, new Date ("12 May 2023")));
		_subject.addReading(new Reading (50, new Date ("8 Sep 2023")));
		assertEquals(3.26d, _subject.charge().amount());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCommenceDansEte() {
		_subject.addReading(new Reading (10, new Date ("21 May 2023")));
		_subject.addReading(new Reading (50, new Date ("26 Sep 2023")));
		assertEquals(3.3d, _subject.charge().amount());
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMax() {
		_subject.addReading(new Reading (0, new Date ("1 Jan 2023")));
		_subject.addReading(new Reading (Integer.MAX_VALUE, new Date ("1 Feb 2023")));
		assertEquals (1.7738215102E8, _subject.charge().amount());
	}
}
