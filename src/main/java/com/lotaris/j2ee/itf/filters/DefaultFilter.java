package com.lotaris.j2ee.itf.filters;

import com.lotaris.j2ee.itf.model.Description;

/**
 * A default implementation of {@link Filter}
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class DefaultFilter implements Filter {
	/**
	 * Define the filters to apply
	 */
	protected String[] filters;
	
	public DefaultFilter(String[] filters) {
		this.filters = filters;
	}
	
	@Override
	public boolean isRunnable(Description description) {
		// The test is deactivated
		if (!description.isRunnable()) {
			return false;
		}
		
		// No filters defined
		else if (filters == null || filters.length == 0) {
			return true;
		}

		// Check filters
		else {
			for (String filter : filters) {
				if (description.getSimpleName().contains(filter)) {
					return true;
				}
			}

			return false;
		}
	}
}
