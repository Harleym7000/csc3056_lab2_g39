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
		testValues.addValue(-7, 0, 2);
		testValues.addValue(-8, 1, 2);
		testValues.addValue(0, 0, 3);
		testValues.addValue(0, 1, 3);
		testValues.addValue(0, 2, 0);
		testValues.addValue(0, 2, 1);
		testValues.addValue(0, 2, 2);
		testValues.addValue(0, 2, 3);
		testValues.addValue(6, 2, 4);
		testValues.addValue(0, 3, 0);
		testValues.addValue(0, 3, 1);
		testValues.addValue(0, 3, 2);
		testValues.addValue(0, 3, 3);
		testValues.addValue(0, 3, 4);
		testValues.addValue(-6, 3, 5);
		testValues.addValue(0, 4, 0);
		testValues.addValue(0, 4, 1);
	}

	@After
	public void tearDown() throws Exception {
		values2D = null;
	}

	//Example Tests from Lab Instructions
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
	
	//Tests for the CalculateColumnTotal() method
	@Test
	public void testPositiveSumForCalculateColumnTotal() {
		assertEquals("Sum of column index 1 should be 15", 15.0, DataUtilities.calculateColumnTotal(values2D, 1), 0.0000001d);
	}
	
	@Test
	public void testNegativeSumForCalculateColumnTotal() {
		assertEquals("Sum of column index 2 should be -15", -15.0, DataUtilities.calculateColumnTotal(values2D, 2), 0.0000001d);
	}
	
	@Test
	public void testSumEqualsZeroForCalculateColumnTotal() {
		assertEquals("Sum of column index 3 should be 0", 0.0, DataUtilities.calculateColumnTotal(values2D, 3), 0.0000001d);
	}
	
	@Test 
	public void testNegativeColumnIndexForCalculateColumnTotal() {
		try {
			assertEquals("Invalid column index input should return 0", 0.0, DataUtilities.calculateColumnTotal(values2D, -1), 0.0000001d);
			fail("Exception thrown - expected outcome was : invalid column input should return 0");
		} catch (Exception e) {
			assertTrue("0 is not returned and instead an exception is thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}
	
	@Test
	public void testValidPositiveColumnIndexForCalculateColumnTotal() {
		assertEquals("Sum of column index 1 should equal 15", 15.0, DataUtilities.calculateColumnTotal(values2D, 1), 0.0000001d);
	}
	
	@Test
	public void testGreaterThanMaxColumnIndexForCalculateColumnTotal() {
		try {
			assertEquals("Invalid column index input should return 0", 0.0, DataUtilities.calculateColumnTotal(values2D, 2), 0.0000001d);
			fail("Exception thrown - expected outcome was: invalid column input should return 0");
		} catch (Exception e) {
			assertTrue("0 is not returned and instead an exception is thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}
	
	//Tests for the CalculateRowColumn() method
	@Test
	public void testNullDataRowTotal() {
		try {
			DataUtilities.calculateRowTotal(null, 0);
			fail("No exception thrown - expected outcome was: a thrown exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
	}
}
	@Test
	public void testValidDataAndCalculateRowTotal() {
		assertEquals("Sum of row index 0 should be 1.", 1.0, DataUtilities.calculateRowTotal(values2D, 0), 0.0000001d);
	}
	
	@Test
	public void testPositiveSumForCalculateRowTotal() {
		assertEquals("Sum of row index 1 should be 4", 4.0, DataUtilities.calculateRowTotal(values2D, 1), 0.0000001d);
	}
	
	@Test
	public void testNegativeSumForCalculateRowTotal() {
		assertEquals("Sum of row index 3 should be -6", -6.0, DataUtilities.calculateColumnTotal(values2D, 3), 0.0000001d);
	}
	
	public void testSumEqualsZeroForCalculateRowTotal() {
		assertEquals("Sum of row index 4 should be 0", 0.0, DataUtilities.calculateRowTotal(values2D, 0), 0.0000001d);
	}
	
	@Test
	public void testNegativeColumnIndexForCalculateRowTotal() {
		try {
			assertEquals("Invalid row index input should return 0", 0.0, DataUtilities.calculateRowTotal(values2D, 0), 0.0000001d);
			fail("Exception thrown - expected outcome was: invalid row input should return 0");
		} catch (Exception e) {
			assertTrue("0 is not returned and instead an exception is thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}
	
	@Test
	public void testValidPositiveRowIndexForCalculateRowTotal() {
			assertEquals("Sum of row index 1 should be equal to 12", 12.0, DataUtilities.calculateRowTotal(values2D, 1), 0.0000001d);
	}
	
	@Test
	public void testGreaterThanMaxRowIndexForCalculateRowTotal() {
		try {
			assertEquals("Invalid column index input should return 0", 0.0, DataUtilities.calculateRowTotal(values2D, 4), 0.0000001d);
			fail("Exception thrown - expected outcome was: invalid column input should return 0");
		} catch (Exception e) {
			assertTrue("0 is not returned and instead an exception is thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}
}
