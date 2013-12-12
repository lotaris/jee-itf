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
public class DummyTestControllerWithTestBeforeAfterInTx extends AbstractTestController {
	@EJB
	public TestGroup testGroup = new DummyTestGroup();

	public class DummyTestGroup implements TestGroup {
		@TestSetup(value = TestSetupType.BEFORE_IN_MAIN_TX, refSetupKey = "testMethodOne")
		public void before() {}
		
		@TestSetup(value = TestSetupType.AFTER_IN_MAIN_TX, refSetupKey = "testMethodOne")
		public void after() {}
		
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