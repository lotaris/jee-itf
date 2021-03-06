package com.lotaris.j2ee.itf.model;

import com.lotaris.j2ee.itf.test.utils.ItfTestHelper;
import com.lotaris.rox.annotations.RoxableTest;
import com.lotaris.rox.annotations.RoxableTestClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for {@link Description}
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
@RoxableTestClass(tags = "description")
public class DescriptionTest {
	Description description;
	
	@Before
	public void createDescription() {
		description = ItfTestHelper.createDefaultDescription();
	}
	
	@Test
	@RoxableTest(key = "933d8b46f246")
	public void byDefaultTheDescriptionShouldBeRunnable() {
		assertTrue("The description must be runnable by default", description.isRunnable());
	}
	
	@Test
	@RoxableTest(key = "cc125c2ed888")
	public void byDefaultTheDescriptionShouldBeNotPassed() {
		assertFalse("The description should be not passed by default", description.isPassed());
	}
	
	@Test
	@RoxableTest(key = "7d8244fa359b")
	public void whenNoRollbackAnnotationIsPresentOnTestMethodTheDescriptionShouldNotBeRollbackable() {
		assertFalse("The description should not be rollbackable when there is NoRollback annotation", description.isRollbackable());
	}

	@Test
	@RoxableTest(key = "81413891f1c0")
	public void theDescriptionSimpleNameMustBeTheSameAsTheMethodName() {
		assertTrue("The description simple name should be the same as the method name", description.getSimpleName().equals(description.getMethod().getName()));
	}

	@Test
	@RoxableTest(key = "e8c4ec34a625")
	public void theDescriptionNameIsTheGroupNameDotSimpleName() {
		assertTrue("The description name should be the name.simple-name", description.getName().equals("groupName." + description.getMethod().getName()));
	}
	
	@Test
	@RoxableTest(key = "d0929a86a0cc")
	public void callingPassMethodOnDescriptionShouldKeepPassedTrueAndMessageNull() {
		description.pass();
		assertTrue("Calling pass method must keep passed=true", description.isPassed());
		assertNull("Calling pass method must keep message=null", description.getMessage());
	}

	@Test
	@RoxableTest(key = "8bbeeb47cde8")
	public void callingPassMethodWithMessageOnDescriptionShouldKeepPassedTrueAndMessageNotNull() {
		description.pass("A message");
		assertTrue("Calling pass method with message must keep passed=true", description.isPassed());
		assertNotNull("Calling pass method with message must keep message=null", description.getMessage());
		assertTrue("Calling pass method with message must keep the same message", description.getMessage().equals("A message"));
	}
	
	@Test
	@RoxableTest(key = "c7040e93e38f")
	public void callingFailMethodWithMessageOnDescriptionShouldKeepPassedAsTrueAndMessageAsNotNull() {
		description.fail("A message");
		assertFalse("Calling fail method with message must keep passed=false", description.isPassed());
		assertNotNull("Calling fail method with message must keep message=null", description.getMessage());
		assertTrue("Calling fail method with message must keep the same message", description.getMessage().equals("A message"));
	}
	
	@Test
	@RoxableTest(key = "9f3af7b31e38") 
	public void dataShouldBePresentWhenAddedManually() {
		description.addData("DummyKey", "DummyValue");
		assertEquals("The data should be present in the description", description.getData().size(), 1);
		assertNotNull("The data must be present in the description", description.getData().get("DummyKey"));
		assertTrue("The data retrived must be the same as the data added", description.getData().get("DummyKey").equals("DummyValue"));
	}
}
