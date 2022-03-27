package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.Values2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities {
	
	private Values2D values2D;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		values2D = testValues;
		testValues.addValue(1, 0, 0);
		testValues.addValue(4, 1, 0);
		testValues.addValue(7, 0, 1);
		testValues.addValue(8, 1, 1);
	}

	@After
	public void tearDown() throws Exception {
		values2D = null;
	}

	@Test
	public void testValidDataAndColumnColumnTotal() {
		assertEquals("Wrong sum returned. It should be 5.0", 5.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
	}
	
	@Test
	public void testNullDataColumnTotal() {
		try {
			DataUtilities.calculateColumnTotal(null, 0);
			fail("No exception thrown- Expected outcome was: a thrown exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}
	
	@Test 
	public void testNegativeColumnIndexForCalculateColumnTotal() {
		try {
			assertEquals("Invalid column index input should return 0", 0.0, DataUtilities.calculateColumnTotal(values2D, -1), 0.0000001d);
			fail("Exception thrown - expected outcome was : invalid column index should return 0");
		} catch (Exception e) {
			assertTrue("0 is not returned and instead an exception is thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}
	
	@Test
	public void testPositiveColumnIndexForCalculateColumnTotal() {
		assertEquals("Sum of column index 1 should equal 15", 15.0, DataUtilities.calculateColumnTotal(values2D, 1), 0.0000001d);
	}
	
	@Test
	public void testGreaterThanMaxColumnIndexForCalculateColumnTotal() {
		try {
			DataUtilities.calculateColumnTotal(values2D, 2);
		} catch (Exception e) {
			assertTrue("error", e.getClass().equals(InvalidParameterException.class));
		}
	}
}
