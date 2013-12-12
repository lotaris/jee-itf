package com.lotaris.j2ee.itf.filters;

import com.lotaris.j2ee.itf.model.Description;

/**
 * Defining a filter allows to run only part of the tests.
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public interface Filter {
	/**
	 * Determine if a test should be run or not
	 * @param description The description to get the data
	 * @return True if the test is runnable, false otherwise
	 */
	boolean isRunnable(Description description);
}
