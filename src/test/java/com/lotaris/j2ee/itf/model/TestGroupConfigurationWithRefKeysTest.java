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
@RoxableTestClass(tags = "test-group-configuration-with-refkeys")
public class TestGroupConfigurationWithRefKeysTest {

	Description descriptionTestOne;
	Description descriptionTestTwo;
	Description descriptionTestThree;
	TestGroup testGroup;
	TestGroupDefinition testGroupDefinition;
	
	@Before
	public void setup() {
		testGroup = ItfTestHelper.createDefaultTestGoupWithRefKeys();
		testGroupDefinition = new TestGroupDefinition(testGroup, new Random());

		try {
			Method m = testGroup.getClass().getMethod("testMethodOne", Description.class);
			com.lotaris.j2ee.itf.annotations.Test testAnnotation = m.getAnnotation(com.lotaris.j2ee.itf.annotations.Test.class);
			descriptionTestOne = new Description(testGroup.getClass().getName(), testAnnotation, m);

			m = testGroup.getClass().getMethod("testMethodTwo", Description.class);
			testAnnotation = m.getAnnotation(com.lotaris.j2ee.itf.annotations.Test.class);
			descriptionTestTwo = new Description(testGroup.getClass().getName(), testAnnotation, m);
		
			m = testGroup.getClass().getMethod("testMethodThree", Description.class);
			testAnnotation = m.getAnnotation(com.lotaris.j2ee.itf.annotations.Test.class);
			descriptionTestThree = new Description(testGroup.getClass().getName(), testAnnotation, m);
		}
		catch (NoSuchMethodException nme) {}
		catch (SecurityException se) {}
	}
	
	@Test
	@RoxableTest(key = "f7b38d3b8e49")
	public void threeBeforeOutTxMethodShouldBeReturnedWhenOnlyOneIsPresentByRefKey() {
		assertEquals("One before (out tx) method should be present", 3, testGroupDefinition.getBeforeOutMainTx().size());
	}

	@Test
	@RoxableTest(key = "50398ca62b16")
	public void threeAfterOutTxMethodShouldBeReturnedWhenOnlyOneIsPresentByRefKey() {
		assertEquals("One after (out tx) method should be present", 3, testGroupDefinition.getAfterOutMainTx().size());
	}

	@Test
	@RoxableTest(key = "2e9969a91780")
	public void threeBeforeInTxMethodShouldBeReturnedWhenOnlyOneIsPresentByRefKey() {
		assertEquals("One before (in tx) method should be present", 3, testGroupDefinition.getBeforeInMainTx().size());
	}

	@Test
	@RoxableTest(key = "747154260795")
	public void threeAfterInTxMethodShouldBeReturnedWhenOnlyOneIsPresentByRefKey() {
		assertEquals("One after (in tx) method should be present", 3, testGroupDefinition.getAfterInMainTx().size());
	}	
	
	@Test
	@RoxableTest(key = "97ae4cda44e4")
	public void oneBeforeOutTxMethodShouldBeReturnedWhenOnlyOneIsPresentForSpecificMethodByRefKey() {
		assertEquals("One before (out tx) method should be present for specific method", 1, testGroupDefinition.getBeforeOutMainTx(descriptionTestOne).size());
	}

	@Test
	@RoxableTest(key = "8785b8898d45")
	public void oneAfterOutTxMethodShouldBeReturnedWhenOnlyOneIsPresentForSpecificMethodByRefKey() {
		assertEquals("One after (out tx) method should be present for specific method", 1, testGroupDefinition.getAfterOutMainTx(descriptionTestOne).size());
	}

	@Test
	@RoxableTest(key = "8daffe661fb6")
	public void oneBeforeInTxMethodShouldBeReturnedWhenOnlyOneIsPresentForSpecificMethodByRefKey() {
		assertEquals("One before (in tx) method should be present for specific method", 1, testGroupDefinition.getBeforeInMainTx(descriptionTestOne).size());
	}

	@Test
	@RoxableTest(key = "cc5c81404851")
	public void oneAfterInTxMethodShouldBeReturnedWhenOnlyOneIsPresentForSpecificMethodByRefKey() {
		assertEquals("One after (in tx) method should be present for specific method", 1, testGroupDefinition.getAfterInMainTx(descriptionTestOne).size());
	}

	@Test
	@RoxableTest(key = "1ad55a21ed72")
	public void sameMethodShouldBeFoundForBeforeOutWhenTwoRefKeysAreConfiguredOnOneSetupMethod() {
		assertEquals(
			"Test methods should be the same", 
			testGroupDefinition.getBeforeOutMainTx(descriptionTestTwo).get(0).getMethod(), 
			testGroupDefinition.getBeforeOutMainTx(descriptionTestThree).get(0).getMethod()
		);
	}

	@Test
	@RoxableTest(key = "95d6434b3dfa")
	public void sameMethodShouldBeFoundForAfterOutWhenTwoRefKeysAreConfiguredOnOneSetupMethod() {
		assertEquals(
			"Test methods should be the same", 
			testGroupDefinition.getAfterOutMainTx(descriptionTestTwo).get(0).getMethod(), 
			testGroupDefinition.getAfterOutMainTx(descriptionTestThree).get(0).getMethod()
		);
	}

	@Test
	@RoxableTest(key = "bee2a9d589d4")
	public void sameMethodShouldBeFoundForBeforeInWhenTwoRefKeysAreConfiguredOnOneSetupMethod() {
		assertEquals(
			"Test methods should be the same", 
			testGroupDefinition.getBeforeInMainTx(descriptionTestTwo).get(0).getMethod(), 
			testGroupDefinition.getBeforeInMainTx(descriptionTestThree).get(0).getMethod()
		);
	}

	@Test
	@RoxableTest(key = "fd4ae2ed4a7f")
	public void sameMethodShouldBeFoundForAfterInWhenTwoRefKeysAreConfiguredOnOneSetupMethod() {
		assertEquals(
			"Test methods should be the same", 
			testGroupDefinition.getAfterInMainTx(descriptionTestTwo).get(0).getMethod(), 
			testGroupDefinition.getAfterInMainTx(descriptionTestThree).get(0).getMethod()
		);
	}
}
