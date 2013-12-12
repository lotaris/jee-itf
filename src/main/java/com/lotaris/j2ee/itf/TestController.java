package com.lotaris.j2ee.itf;

import com.lotaris.j2ee.itf.filters.Filter;
import com.lotaris.j2ee.itf.listeners.Listener;
import java.util.Map;

/**
 * The test controller interface is used to create Local/Stateless session
 * beans to define the entry point to run the integration tests
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public interface TestController { 
	/**
	 * Launch the master controller to run every integration test
	 * 
	 * @param filters Map of wanted filters, if null or empty, default filter is used
	 * @param listeners Map of wanted listeners, if null or empty, default listener is used
	 * @param seed The seed to create the random generator, if null or negative, a seed == to System.currentTime() is used
	 * @return The seed used for the test run
   */
	Long run(Map<String, Filter> filters, Map<String, Listener> listeners, Long seed);
}
