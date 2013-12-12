package com.lotaris.j2ee.itf.filters;

import com.lotaris.j2ee.itf.model.Description;
import com.lotaris.j2ee.itf.test.utils.ItfTestHelper;
import com.lotaris.rox.annotations.RoxableTest;
import com.lotaris.rox.annotations.RoxableTestClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for {@link DefaultFilter}
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
@RoxableTestClass(tags = "default-filter")
public class DefaultFilterTest {
	
	Description description;
	
	@Before
	public void createDescription() {
		description = ItfTestHelper.createDefaultDescription();
	}
	
	@Test
	@RoxableTest(key = "2ec6ce2c860b")
	public void descriptionShouldBeRunnableWhenNoFilterIsSpecified() {
		DefaultFilter df = new DefaultFilter(null);
		assertTrue("The test is not runnable when it must be (null filters)", df.isRunnable(description));

		df = new DefaultFilter(new String[]{});
		assertTrue("The test is not runnable when it must be (empty filters)", df.isRunnable(description));
	}
	
	@Test
	@RoxableTest(key = "7472c092073a")
	public void descriptionShouldBeRunnableWhenValidFilterNameIsSpecified() {
		DefaultFilter df = new DefaultFilter(new String[] {"dummyMethod"});
		assertTrue("The test is not runnable when it must be", df.isRunnable(description));
	}
	
	@Test
	@RoxableTest(key = "08ed5b8338ec")
	public void descriptionShouldBeRunnableWhenInvalidFilterNameIsSpecified() {
		DefaultFilter df = new DefaultFilter(new String[] {"noMethod"});
		assertFalse("The test is runnable when it should not", df.isRunnable(description));
	}	
}
