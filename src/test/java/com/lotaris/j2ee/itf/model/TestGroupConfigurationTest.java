package com.lotaris.j2ee.itf.model;

import com.lotaris.j2ee.itf.TestGroup;
import com.lotaris.j2ee.itf.test.utils.ItfTestHelper;
import com.lotaris.rox.annotations.RoxableTest;
import com.lotaris.rox.annotations.RoxableTestClass;
import java.lang.reflect.Method;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for {@link TestGroupConfiguration}
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
@RoxableTestClass(tags = "test-group-configuration")
public class TestGroupConfigurationTest {

	Description description;
	TestGroup testGroup;
	TestGroupDefinition testGroupDefinition;
	
	@Before
	public void setup() {
		testGroup = ItfTestHelper.createDefaultTestGroup();
		testGroupDefinition = new TestGroupDefinition(testGroup, new Random());

		try {
			Method m = testGroup.getClass().getMethod("testMethod", Description.class);
			com.lotaris.j2ee.itf.annotations.Test testAnnotation = m.getAnnotation(com.lotaris.j2ee.itf.annotations.Test.class);
			description = new Description(testGroup.getClass().getName(), testAnnotation, m);
		}
		catch (NoSuchMethodException nme) {}
		catch (SecurityException se) {}
	}
	
	@Test
	@RoxableTest(key = "09d8e9c18177")
	public void theTestGroupNameShouldBeTheClassName() {
		assertEquals("The name of the group should be the complete class name itself", testGroupDefinition.getName(), testGroup.getClass().getCanonicalName());
	}
	
	@Test
	@RoxableTest(key = "0256374e537e")
	public void gettingTheTestGroupFromTheGroupShouldReturnItself() {
		assertEquals("Getting the test group should return itself", testGroupDefinition.getTestGroup(), testGroup);
	}
	
	@Test
	@RoxableTest(key = "282de281a828")
	public void twoTestsMethodShouldBeReturnedWhenTwoArePresent() {
		assertEquals("Two test methods should be present", testGroupDefinition.getTestMethods().size(), 2);
	}
	
	@Test
	@RoxableTest(key = "9f918f86ae0e")
	public void oneBeforeAllMethodShouldBeReturnedWhenOnlyOneIsPresent() {
		assertEquals("One before all method should be present", testGroupDefinition.getBeforeAll().size(), 1);
	}

	@Test
	@RoxableTest(key = "46b35f45dc80")
	public void oneAfterAllMethodShouldBeReturnedWhenOnlyOneIsPresent() {
		assertEquals("One after all method should be present", testGroupDefinition.getAfterAll().size(), 1);
	}

	@Test
	@RoxableTest(key = "08fde925b7b7")
	public void oneBeforeEachOutTxMethodShouldBeReturnedWhenOnlyOneIsPresent() {
		assertEquals("One before each (out tx) method should be present", testGroupDefinition.getBeforeEachOutMainTx().size(), 1);
	}

	@Test
	@RoxableTest(key = "9b7ce662ea92")
	public void oneAfterEachOutTxMethodShouldBeReturnedWhenOnlyOneIsPresent() {
		assertEquals("One after each (out tx) method should be present", testGroupDefinition.getAfterEachOutMainTx().size(), 1);
	}

	@Test
	@RoxableTest(key = "03b4d52a62bc")
	public void oneBeforeEachInTxMethodShouldBeReturnedWhenOnlyOneIsPresent() {
		assertEquals("One before each (in tx) method should be present", testGroupDefinition.getBeforeEachInMainTx().size(), 1);
	}

	@Test
	@RoxableTest(key = "5b424873353c")
	public void oneAfterEachInTxMethodShouldBeReturnedWhenOnlyOneIsPresent() {
		assertEquals("One after each (in tx) method should be present", testGroupDefinition.getAfterEachInMainTx().size(), 1);
	}

	@Test
	@RoxableTest(key = "cd249a568606")
	public void oneBeforeOutTxMethodShouldBeReturnedWhenOnlyOneIsPresent() {
		assertEquals("One before (out tx) method should be present", testGroupDefinition.getBeforeOutMainTx().size(), 1);
	}

	@Test
	@RoxableTest(key = "d26f56325b27")
	public void oneAfterOutTxMethodShouldBeReturnedWhenOnlyOneIsPresent() {
		assertEquals("One after (out tx) method should be present", testGroupDefinition.getAfterOutMainTx().size(), 1);
	}

	@Test
	@RoxableTest(key = "a0dc1ad1a945")
	public void oneBeforeInTxMethodShouldBeReturnedWhenOnlyOneIsPresent() {
		assertEquals("One before (in tx) method should be present", testGroupDefinition.getBeforeInMainTx().size(), 1);
	}

	@Test
	@RoxableTest(key = "df0430b2a632")
	public void oneAfterInTxMethodShouldBeReturnedWhenOnlyOneIsPresent() {
		assertEquals("One after (in tx) method should be present", testGroupDefinition.getAfterInMainTx().size(), 1);
	}	
	
	@Test
	@RoxableTest(key = "e1118e5e00b9")
	public void oneBeforeOutTxMethodShouldBeReturnedWhenOnlyOneIsPresentForSpecificMethod() {
		assertEquals("One before (out tx) method should be present for specific method", testGroupDefinition.getBeforeOutMainTx(description).size(), 1);
	}

	@Test
	@RoxableTest(key = "1fb0f4c5bd21")
	public void oneAfterOutTxMethodShouldBeReturnedWhenOnlyOneIsPresentForSpecificMethod() {
		assertEquals("One after (out tx) method should be present for specific method", testGroupDefinition.getAfterOutMainTx(description).size(), 1);
	}

	@Test
	@RoxableTest(key = "c43abad93409")
	public void oneBeforeInTxMethodShouldBeReturnedWhenOnlyOneIsPresentForSpecificMethod() {
		assertEquals("One before (in tx) method should be present for specific method", testGroupDefinition.getBeforeInMainTx(description).size(), 1);
	}

	@Test
	@RoxableTest(key = "3d1446ac4ca5")
	public void oneAfterInTxMethodShouldBeReturnedWhenOnlyOneIsPresentForSpecificMethod() {
		assertEquals("One after (in tx) method should be present for specific method", testGroupDefinition.getAfterInMainTx(description).size(), 1);
	}
}
