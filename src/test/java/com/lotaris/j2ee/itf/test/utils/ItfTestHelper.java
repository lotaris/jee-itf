package com.lotaris.j2ee.itf.test.utils;

import com.lotaris.j2ee.itf.test.utils.groups.DummyTestGroup;
import com.lotaris.j2ee.itf.TestGroup;
import com.lotaris.j2ee.itf.annotations.Test;
import com.lotaris.j2ee.itf.annotations.TestSetup;
import com.lotaris.j2ee.itf.model.Description;
import com.lotaris.j2ee.itf.test.utils.groups.DummyTestGroupOrderedSetup;
import com.lotaris.j2ee.itf.test.utils.groups.DummyTestGroupWithRefKeys;
import com.lotaris.rox.annotations.RoxableTest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Method utilities to test the Integration Test Framework
 * 
 * @author Laurent Prevost, laurent.prevost@lotaris.com
 */
public class ItfTestHelper {
	/**
	 * Create a default test group
	 * @return The test group created
	 */
	public static TestGroup createDefaultTestGroup() {
		return new DummyTestGroup();
	}
	
	/**
	 * Create a default test group that has ref keys
	 * @return The test group created
	 */
	public static TestGroup createDefaultTestGoupWithRefKeys() {
		return new DummyTestGroupWithRefKeys();
	}
	
	/**
	 * Create a default test group that has ordered setup
	 * @return The test group created
	 */
	public static TestGroup createDefaultTestGroupOrderedSetup() {
		return new DummyTestGroupOrderedSetup();
	}
	
	/**
	 * Create a default description
	 * @return The description created
	 */
	public static Description createDefaultDescription() {
		try {
			Method m = ItfTestHelper.class.getMethod("dummyMethod", Description.class);
			return new Description("groupName", m.getAnnotation(Test.class), m);
		}
		catch (NoSuchMethodException nme) {}
		catch (SecurityException se) {}

		return null;
	}
	
	/**
	 * Allow to set a static attribute with a custom value
	 * @param field The attribute to modify
	 * @param newValue The new value to set
	 * @throws Exception Any exception that occurs
	 */
	public static void setFinalStatic(Field field, Object newValue) throws Exception {
		field.setAccessible(true);

		// remove final modifier from field
		Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

		field.set(null, newValue);
	}		
	
	/**
	 * This method is never run. It is used only to create
	 * the description object to test the rox Filter that 
	 * allows to run test by key, tag, ticket or name.
	 */
	@RoxableTest(key = "dummyKey", tags = "dummyTag", tickets = "dummyTicket")
	@com.lotaris.j2ee.itf.annotations.Test
	public Description dummyMethod(Description description) {
		return description;
	}
}
