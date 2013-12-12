package com.lotaris.j2ee.itf.test.utils.controllers;

import com.lotaris.j2ee.itf.AbstractTestController;
import com.lotaris.j2ee.itf.TestGroup;
import com.lotaris.j2ee.itf.annotations.Test;
import com.lotaris.j2ee.itf.model.Description;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 * Dummy test group to use in the unit tests
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
@TransactionManagement(TransactionManagementType.BEAN)
public class DummyTestControllerWithTestException extends AbstractTestController {
	@EJB
	public TestGroup testGroup = new DummyTestGroup();

	public class DummyTestGroup implements TestGroup {
		@Test
		public Description testMethod(Description description) {
			throw new RuntimeException("This an exception");
		}

		@Override
		public TestGroup getTestGroup() {
			return this;
		}
	}
}