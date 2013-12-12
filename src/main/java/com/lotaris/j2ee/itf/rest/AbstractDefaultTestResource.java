package com.lotaris.j2ee.itf.rest;

import com.lotaris.j2ee.itf.TestController;
import com.lotaris.j2ee.itf.filters.DefaultFilter;
import com.lotaris.j2ee.itf.filters.Filter;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Expose the method to start the integration tests through
 * a REST service.
 * 
 * @author Laurent Prévost, laurent.prevost@lotaris.com
 */
public abstract class AbstractDefaultTestResource {
	private static final Log LOGGER = LogFactory.getLog(AbstractDefaultTestResource.class);
	
	/**
	 * Start the test through the integration test controller
	 * 
	 * @param filters Filters to apply if necessary
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response run(@QueryParam("filters") String filters, @QueryParam("seed") Long seed) {

		// Logging
		if (LOGGER.isDebugEnabled()) {
			StringBuilder message = new StringBuilder();
			
			if (filters != null && !filters.isEmpty()) {
				message.append("Filters [").append(filters).append("]");
			}
			
			if (seed != null) {
				message.append("Seed [").append(seed).append("]");
			}

			LOGGER.debug(message.toString());
		}

		// Get the controller
		TestController testController = getController();
		
		Map<String, Filter> itfFilters = new HashMap<>();
		itfFilters.put("nameFilter", new DefaultFilter(filters == null || filters.isEmpty() ? new String[] {} : filters.split(",")));
		
		// Run the integration tests
		LOGGER.info("Generator seed: " + testController.run(itfFilters, null, seed));
		
		return Response.ok().build();
	}
	
	/**
	 * @return Retrieve the integration test controller
	 */
	public abstract TestController getController();
}
