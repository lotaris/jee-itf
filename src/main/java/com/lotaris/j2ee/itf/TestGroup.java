package com.lotaris.j2ee.itf;

/**
 * An interface that acts as a marker to allow the integration tests
 * retrieval and run.
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public interface TestGroup { 
	/**
	 * Enforce each SessionBean to return itself to run the tests
	 * correctly.
	 * @return Itself
	 */
	TestGroup getTestGroup();
}
