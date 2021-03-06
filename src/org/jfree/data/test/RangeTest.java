package org.jfree.data.test;

import java.security.InvalidParameterException;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

public class RangeTest extends TestCase {
	
	private Range rangeObjectUnderTest;
	private Range uninitialisedRange;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	protected void setUp() throws Exception {
		rangeObjectUnderTest = new Range(0, 10);
	}

	@After
	protected void tearDown() throws Exception {
	}

	@Test
	public void testCentralValueShouldBeZero() {
		assertEquals("The central value of 0 and 10 should be 5", 5, rangeObjectUnderTest.getCentralValue(), 0.000000001d);
	}
	
	@Test
	public void testGetLength() {
		Range r1 = new Range(2, 2);
		assertEquals("getLength: Did not return the expected output", 0.0, r1.getLength());
		
		Range r2 = new Range(4, 9);
		assertEquals("getLength: Did not return the expected output", 5.0, r2.getLength());
		
		Range r3 = new Range(-99, -99);
		assertEquals("getLength: Did not return the expected output", 0.0, r3.getLength());
		
		Range r4 = new Range(-11, -4);
		assertEquals("getLength: Did not return the expected output", 7.0, r4.getLength());
		
		Range r5 = new Range(-5, 3);
		assertEquals("getLength: Did not return the expected output", 8.0, r5.getLength());
	}
	
	//Testing the Contains Method
	
	@Test
	public void testContainsTrue() {
		//Act
		boolean result = rangeObjectUnderTest.contains(5);
		//Assert
		assertEquals("testContainsTrue: Should find 5 in range 0-10",true, result);
	}
	
	@Test
	public void testContainsFalse() {
		//Act
		boolean result = rangeObjectUnderTest.contains(50);
		//Assert
		assertEquals("testContainsFalse: Shouldn't find 50 in range 0-10",false, result);
	}
	
	@Test
	public void testContainsMax() {
		//Act
		boolean result = rangeObjectUnderTest.contains(Double.POSITIVE_INFINITY);
		//Assert
		assertEquals("testContainsMax: Shouldnt find max double in range 0-10",false, result);
	}
	
	@Test
	public void testContainsTooBig() {
		//Act
		boolean result = rangeObjectUnderTest.contains(Double.POSITIVE_INFINITY+1);
		//Assert
		assertEquals("testContainsTooBig: Shouldnt find max double+1 in range 0-10",false, result);
	}
	
	// Testing the Intersect method
	
	//This method results in an error - should fail instead
	//This method should fail as it doesn't give the expected result
	@Test
	public void testIntersectTrue() {
		//Act
		boolean result = rangeObjectUnderTest.intersects(5, 15);
		//Assert
		assertEquals("testIntersectTrue: Expected range 5-15 to overlap range 0-10",true, result);
	}
	
	@Test
	public void testIntersectFalse() {
		//Act
		boolean result = rangeObjectUnderTest.intersects(11, 20);
		//Assert
		assertEquals("testIntersectFalse: Not expecting range 11-20 to overlap range 0-10",false, result);
	}
	
	@Test
	public void testIntersectLargerLowerBound() {
		//Act
		boolean result = rangeObjectUnderTest.intersects(20, 11);
		//Assert
		assertEquals("testIntersectFalse: Not expecting range 20-11 to overlap range 0-10",false, result);
	}
	
	@Test
	public void testIntersectEntireRange() {
		//Act
		boolean result = rangeObjectUnderTest.intersects(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
		//Assert
		assertEquals("testIntersectFalse: Expecting range 4.9E-324 - 1.7976931348623157E308 to overlap range 0-10",true, result);
	}
	//Testing the GetLowerBound Method
	
	@Test
	public void testGetLowerBound() {
		//Act
		double result = rangeObjectUnderTest.getLowerBound();
		//Assert
		assertEquals("testGetLowerBound: expected lower bound to be the same as initialised",0.0, result);
	}
	
	// Testing the GetUpperBound Method
	
	//This method results in an error - should fail instead
	//This method should fail as the actual result is different from the expected result
	@Test
	public void testGetUpperBound() {
		//Act
		double result = rangeObjectUnderTest.getUpperBound();
		//Assert
		assertEquals("testGetUpperBound: expected upper bound of range 0-10 to be 10",10.0, result);
	}
	
	//Testing the Expand Method
	
	@Test
	public void testExpandValid() {
		Range expanded = Range.expand(new Range(2, 6), 0.25, 0.5);
		Range expectedExpanded = new Range(1, 8);
		assertEquals("testExpandValid: Expected expanded range 2 - 6 by factor of 0.2 lower and 0.5 higher to be 1 - 8",expectedExpanded, expanded);
	}
	
	//This method results in an error - should fail instead
	//Error seems to be due to throwing the wrong exception 
	@Test
	public void testExpandNullRange() {
		try {
		Range expanded = Range.expand(null, 0.2, 0.5);
		fail("Expected InvalidParameterException to be thrown");
		}catch(Exception ex) {
			assertEquals("testExpandNullRange: Calling expand with a null range to expand throws InvalidParameterException", InvalidParameterException.class,ex.getClass());
		}
		
	}
	
	//This method results in an error - should fail instead
	//Error seems to be due to throwing the wrong exception
	@Test
	public void testExpandUninitialisedRange() {
		Range rangeToTest = null;
		try {
			Range.expand(rangeToTest, 0.2, 0.5);
//			fail("Expected InvalidParameterException to be thrown");
			} catch(Exception ex) {
				System.out.println(ex.getClass());
				assertEquals("Should be invalid paramater exception", InvalidParameterException.class, ex.getClass());
	}
}
}
