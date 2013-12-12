package com.lotaris.j2ee.itf.listeners;

import com.lotaris.j2ee.itf.model.Description;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A default implementation of {@link Listener}
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class DefaultListener implements Listener {

	private static final Log LOGGER = LogFactory.getLog(DefaultListener.class);
	
	/**
	 * Start and end dates of the test run
	 */
	protected long startDate;
	protected long endDate;
	
	@Override
	public void testRunStart() {
		startDate = System.currentTimeMillis();
		LOGGER.info("Test run started.");
	}

	@Override
	public void testRunEnd() {
		endDate = System.currentTimeMillis();
		LOGGER.info("Test run ended in " + (endDate - startDate) + "ms.");
	}

	@Override
	public void testStart(Description description) {
		description.setStartDate(System.currentTimeMillis());
		LOGGER.info("Test " + description.getName() + " started.");
	}

	@Override
	public void testEnd(Description description) {
		description.setEndDate(System.currentTimeMillis());
		description.setDuration(description.getEndDate() - description.getStartDate());
		LOGGER.info("Test " + description.getName() + " ended in " + description.getDuration() + "ms.");
	}

	@Override
	public void fail(Description description) {
		LOGGER.info("Test " + description.getName() + " failed with message: " + description.getMessage());
	}

	@Override
	public void success(Description description) {
		if (description.getMessage() != null) {
			LOGGER.info("Test " + description.getName() + " succeed with message: " + description.getMessage());
		}
		else {
			LOGGER.info("Test " + description.getName() + " succeed.");
		}
	}
}
