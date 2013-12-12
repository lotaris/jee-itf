package com.lotaris.j2ee.itf.test.utils.controllers;

import com.lotaris.j2ee.itf.AbstractTestController;
import com.lotaris.j2ee.itf.TestGroup;
import com.lotaris.j2ee.itf.annotations.Test;
import com.lotaris.j2ee.itf.annotations.TestSetup;
import com.lotaris.j2ee.itf.annotations.TestSetupType;
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
public class DummyTestControllerWithTestBeforeAfterEachInTx extends AbstractTestController {
	@EJB
	public TestGroup testGroup = new DummyTestGroup();

	public class DummyTestGroup implements TestGroup {
		@TestSetup(TestSetupType.BEFORE_EACH_IN_MAIN_TX)
		public void beforeEach() {}
		
		@TestSetup(TestSetupType.AFTER_EACH_IN_MAIN_TX)
		public void afterEach() {}
		
		@Test
		public Description testMethodOne(Description description) {
			return description.pass();
		}

		@Test
		public Description testMethodTwo(Description description) {
			return description.pass();
		}

		@Override
		public TestGroup getTestGroup() {
			return this;
		}
	}
}