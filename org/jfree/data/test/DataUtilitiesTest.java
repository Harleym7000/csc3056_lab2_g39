package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.List;

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
	private List valuesList;

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
//		testValues.addValue(1, 0, 0);
//		testValues.addValue(4, 1, 0);
		testValues.addValue(-1, 0, 0);
		testValues.addValue(-5, 1, 0);
	}

	@After
	public void tearDown() throws Exception {
		values2D = null;
	}

	@Test
	public void testValidDataAndColumnTotal() {
		assertEquals("Wrong sum returned. It should be 5.0", 5.0, DataUtilities.calculateColumnTotal(values2D,  0), 0.0000001d);
	}
	
	@Test
	public void testNullDataColumnTotal() {
		try {
			DataUtilities.calculateColumnTotal(null, 0);
			fail("No exception thrown-Expected outcome was a thrown exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}
	
	// Test Code per Test Cases Designed by Harley
	@Test
	public void testNegativeValuesForCalculateColumnTotal() {
		try {
			DataUtilities.calculateColumnTotal(values2D, -1);
		} catch(IndexOutOfBoundsException e) {
			assertTrue("A negative value for the column index should throw an IndexOutOfBoundsException", e.getClass().equals(IndexOutOfBoundsException.class));
		}
	}
}
