package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities {
	
	private Values2D values2D;
	private DefaultKeyedValues2D positiveValuesData;
	private DefaultKeyedValues2D onePositiveColumnValue;
	private DefaultKeyedValues2D oneNegativeColumnValue;
	private DefaultKeyedValues2D negativeValuesData;
	private DefaultKeyedValues2D allZeroValuesData;
	private DefaultKeyedValues2D negativeColumnIndexData;
	private DefaultKeyedValues2D positiveColumnIndexData;
	private DefaultKeyedValues2D negativeRowIndexData;
	private DefaultKeyedValues2D positiveRowIndexData;
	private double[] doublesArray;
	private double[] negativeDoublesArray;
	private double[][] doublesArray2D;
	private DefaultKeyedValues keyedValuesData;

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
		
		positiveValuesData = new DefaultKeyedValues2D();
		positiveValuesData.addValue(1, 0, 0);
		positiveValuesData.addValue(4, 1, 0);
		positiveValuesData.addValue(0, 2, 0);
		positiveValuesData.addValue(12, 2, 1);
		positiveValuesData.addValue(81, 2, 2);
		
		onePositiveColumnValue = new DefaultKeyedValues2D();
		onePositiveColumnValue.addValue(56, 0, 0);
		
		oneNegativeColumnValue = new DefaultKeyedValues2D();
		oneNegativeColumnValue.addValue(-8, 0, 0);
		
		negativeValuesData = new DefaultKeyedValues2D();
		negativeValuesData.addValue(-3, 0, 0);
		negativeValuesData.addValue(-54, 1, 0);
		negativeValuesData.addValue(-4, 2, 0);
		negativeValuesData.addValue(-17, 2, 1);
		negativeValuesData.addValue(-43, 2, 2);
		
		allZeroValuesData = new DefaultKeyedValues2D();
		allZeroValuesData.addValue(0, 0, 0);
		allZeroValuesData.addValue(0, 1, 0);
		allZeroValuesData.addValue(0, 0, 1);
		allZeroValuesData.addValue(0, 1, 1);
		
		negativeColumnIndexData = new DefaultKeyedValues2D();
		negativeColumnIndexData.addValue(43, 0, -1);
		negativeColumnIndexData.addValue(65, 1, -1);
		negativeColumnIndexData.addValue(89, 2, -1);
		
		positiveColumnIndexData = new DefaultKeyedValues2D();
		positiveColumnIndexData.addValue(54, 0, 0);
		positiveColumnIndexData.addValue(47, 1, 0);
		positiveColumnIndexData.addValue(12, 2, 0);
		positiveColumnIndexData.addValue(43, 0, 1);
		positiveColumnIndexData.addValue(87, 1, 1);
		positiveColumnIndexData.addValue(65, 2, 1);
		
		negativeRowIndexData = new DefaultKeyedValues2D();
		negativeRowIndexData.addValue(34, -1, 0);
		negativeRowIndexData.addValue(11, -1, 1);
		
		positiveRowIndexData = new DefaultKeyedValues2D();
		positiveRowIndexData.addValue(12, 0, 0);
		positiveRowIndexData.addValue(43, 0, 1);
		positiveRowIndexData.addValue(23, 1, 0);
		positiveRowIndexData.addValue(48, 1, 1);
		
		doublesArray = new double[] {1.0, 2.5, 4.7, 8.9, 89.2};
		negativeDoublesArray = new double[] {-5.8, -9.2, -9.3, -86.3};
		doublesArray2D = new double[][] {doublesArray, negativeDoublesArray};
		
		keyedValuesData = new DefaultKeyedValues();
		keyedValuesData.addValue((Comparable) 0.0, 5.0);
		keyedValuesData.addValue((Comparable) 1.0, 9.0);
		keyedValuesData.addValue((Comparable) 2.0, 2.0);
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
	public void testPositiveTotalForCalculateColumnTotal() {
		values2D = positiveValuesData;
		assertEquals("Sum of column index 1 should be 5", 5.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
	}
	
	@Test
	public void testNegativeTotalForCalculateColumnTotal() {
		values2D = negativeValuesData;
		assertEquals("Sum of column index 2 should be -61", -61.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
	}
	
	@Test
	public void testSinglePositiveValueInColumnForCalculateColumnTotal() {
		values2D = onePositiveColumnValue;
		assertEquals("Sum of column index 0 should be 56", 56.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
	}
	
	@Test
	public void testSingleNegativeValueInColumnForCalculateColumnTotal() {
		values2D = oneNegativeColumnValue;
		assertEquals("Sum of column idex 0 should be -8", -8, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
	}
	
	@Test
	public void testTotalEqualsZeroForCalculateColumnTotal() {
		values2D = allZeroValuesData;
		assertEquals("Sum of column index 0 should be 0", 0.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
	}
	
	@Test 
	public void testNegativeColumnIndexForCalculateColumnTotal() {
		values2D = negativeColumnIndexData;
		try {
			assertEquals("Column indexes are 0 based, therefore negative column indexes should be out of bounds", 197.0, DataUtilities.calculateColumnTotal(values2D, -1), 0.0000001d);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(IndexOutOfBoundsException.class, e.getClass());
		}
	}
	
	@Test
	public void testValidPositiveColumnIndexForCalculateColumnTotal() {
		values2D = positiveColumnIndexData;
		assertEquals("Sum of column index 1 should equal 195", 195.0, DataUtilities.calculateColumnTotal(values2D, 1), 0.0000001d);
	}
	
	@Test
	public void testGreaterThanMaxColumnIndexForCalculateColumnTotal() {
		values2D = positiveValuesData;
		try {
			assertEquals("Column indexes greater than the number of columns in the table should result in an IndexOutOfBoundsException", 0.0, DataUtilities.calculateColumnTotal(values2D, 3), 0.0000001d);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Wrong Exception type thrown: ", IndexOutOfBoundsException.class, e.getClass());
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
	public void testPositiveSumForCalculateRowTotal() {
		values2D = positiveValuesData;
		assertEquals("Sum of row index 1 should be 93", 93.0, DataUtilities.calculateRowTotal(values2D, 2), 0.0000001d);
	}
	
	@Test
	public void testNegativeSumForCalculateRowTotal() {
		values2D = negativeValuesData;
		assertEquals("Sum of row index 3 should be -64", -64.0, DataUtilities.calculateColumnTotal(values2D, 2), 0.0000001d);
	}
	
	@Test
	public void testSumEqualsZeroForCalculateRowTotal() {
		values2D = allZeroValuesData;
		assertEquals("Sum of row index 4 should be 0", 0.0, DataUtilities.calculateRowTotal(values2D, 0), 0.0000001d);
	}
	
	@Test
	public void testNegativeRowIndexForCalculateRowTotal() {
		values2D = negativeRowIndexData;
		try {
			DataUtilities.calculateRowTotal(values2D, -1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Row indexes are 0 based, therefore row index -1 should throw an IndexOutOfBoundsException", IndexOutOfBoundsException.class, e.getClass());
		}
	}
	
	@Test
	public void testValidPositiveRowIndexForCalculateRowTotal() {
		values2D = positiveRowIndexData;
			assertEquals("Sum of row index 1 should be equal to 71", 71.0, DataUtilities.calculateRowTotal(values2D, 1), 0.0000001d);
	}
	
	@Test
	public void testGreaterThanMaxRowIndexForCalculateRowTotal() {
		values2D = positiveRowIndexData;
		try {
			DataUtilities.calculateRowTotal(values2D, 2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Row indexes greater than the number of rows in the table should result in an IndexOutOfBoundsException", IndexOutOfBoundsException.class, e.getClass());
		}
	}
	
	@Test
	public void testNullDoublesArrayForCreateNumberArray() {
		double[] nullDoublesArray = null;
		try  {
			DataUtilities.createNumberArray(nullDoublesArray);
			fail("Wrong Exception thrown, should be InvalidParameterException however throws a Java.lang.AssertionError instead");
		} catch (Exception e) {
			fail(e.getMessage());
			assertTrue("Null values are not permitted and an exception is thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}
	
	@Test
	public void testNumberArrayCreatedWithPositiveDoublesArrayForCreateNumberArray() {
		Number[] createdArray = DataUtilities.createNumberArray(doublesArray);
		try {
			assertNotNull(createdArray);
		} catch (Exception e){
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testNumberArrayCreatedWithNegativeDoublesArrayForCreateNumberArray() {
		Number[] createdArray = DataUtilities.createNumberArray(negativeDoublesArray);
		try {
			assertNotNull(createdArray);
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testArrayOfNumberArraysNotCreatedWithNullForCreateNumberArray2D() {
		try {
			DataUtilities.createNumberArray2D(null);
			fail("Wrong Exception thrown: Expected output was InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect Exception type thrown: Expected InvalidParamaterException but got Assertion Error", e.getClass().equals(InvalidParameterException.class));
		}
	}
	
	@Test
	public void testArrayofNumberArraysCreatedForCreateNumberArray2D() {
		Number[][] createdArray = DataUtilities.createNumberArray2D(doublesArray2D);
		try {
			assertNotNull(createdArray);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testNullDataForGetCumulativePercentages() {
		try {
			DataUtilities.getCumulativePercentages(null);
			fail("Wrong Exception thrown: Expected output was InvalidParameterException");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetCumulativePercentagesWithValidData() {
		KeyedValues valuesToTest = DataUtilities.getCumulativePercentages((KeyedValues) keyedValuesData);
		assertEquals((double) valuesToTest.getValue(2), 1.0, .000000001d);
	}
}
