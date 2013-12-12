package com.lotaris.j2ee.itf;

import com.lotaris.j2ee.itf.filters.DefaultFilter;
import com.lotaris.j2ee.itf.filters.Filter;
import com.lotaris.j2ee.itf.listeners.DefaultListener;
import com.lotaris.j2ee.itf.listeners.Listener;
import com.lotaris.j2ee.itf.model.TestGroupDefinition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Manage the configuration to run the integration tests into the
 * {@link TestController}
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public final class TestControllerConfiguration {
	/**
	 * Store the test groups definitions
	 */
	private List<TestGroupDefinition> testGroupDefinitions = new ArrayList<TestGroupDefinition>();

	/**
	 * Listeners and filters
	 */
	private Map<String, Listener> listeners = new HashMap<String, Listener>();
	private Map<String, Filter> filters = new HashMap<String, Filter>();
	
	/**
	 * Random generator
	 */
	private Random rand;
	
	/**
	 * Enforce the creation of the object from the test controller
	 */
	protected TestControllerConfiguration() {}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		if (rand == null) {
			throw new IllegalArgumentException("You must provide a valid Random instance");
		}
		
		this.rand = rand;
	}
	
	/**
	 * Register a listener to manage the result of the tests
	 * 
	 * @param name The name of the listener to avoid adding a listener twice
	 * @param listener The listener to register
	 */
	public void register(String name, Listener listener) {
		if (listener != null && !listeners.containsKey(name)) {
			listeners.put(name, listener);
		}
	}
	
	/**
	 * Register a filter to define if a test should run
	 * or not
	 * 
	 * @param name The name of the filter to avoid adding a filter twice
	 * @param filter The filter to register
	 */
	public void register(String name, Filter filter) {
		if (filter != null && !filters.containsKey(name)) {
			filters.put(name, filter);
		}
	}
	
	/**
	 * Ensure that the configuration is valid to run the tests
	 */
	protected void ensure() {
		if (listeners.isEmpty()) {
			listeners.put("defaultListener", new DefaultListener());
		}
		
		if (filters.isEmpty()) {
			filters.put("defaultFilter", new DefaultFilter(new String[]{}));
		}		
	}
	
	/**
	 * Add a new test group configuration
	 * 
	 * @param testGroupDefinition The test group definition to add
	 */
	protected void addTestGroupDefinition(TestGroupDefinition testGroupDefinition) {
		testGroupDefinitions.add(testGroupDefinition);
	}
	
	/**
	 * @return The list of the test group definitions
	 */
	protected List<TestGroupDefinition> getTestGroupDefinitions() {
		return testGroupDefinitions;
	}
	
	/**
	 * Randomize the test group configuration order
	 */
	protected void shuffleTestGroupDefinitions() {
		Collections.shuffle(testGroupDefinitions, rand);
	}
	
	/**
	 * @return The list of listeners
	 */
	protected Collection<Listener> getListeners() {
		return listeners.values();
	}
	
	/**
	 * @return The list of filters
	 */
	protected Collection<Filter> getFilters() {
		return filters.values();
	}
}
