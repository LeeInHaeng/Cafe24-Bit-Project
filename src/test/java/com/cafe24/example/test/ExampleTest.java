package com.cafe24.example.test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class ExampleTest {
	
	// 테스트 케이스끼리 공유해야 할 변수가 있으면 static
	private static StringBuilder output = new StringBuilder();
	
	// 테스트 케이스의 순서가 알파벳 순서

	@BeforeClass
	public static void setUpBefore() {
		System.out.println("@BeforeClass");
	}
	
	@AfterClass
	public static void tearDownAfter() {
		System.out.println("@@AfterClass : " + output.toString());
	}
	
	@Before
	public void setUp() {
		System.out.println("@Before");
	}
	
	@After
	public void tearDown() {
		System.out.println("@After");
	}
	
	@Test
	public void myKTest() {
		System.out.println("@Test:K");
		output.append("K");
	}
	
	@Test
	public void myBTest() {
		System.out.println("@Test:B");
		output.append("B");
	}
	
	@Test
	public void myCTest() {
		System.out.println("@Test:C");
		output.append("C");
	}
	
	// test 무시
	@Ignore
	@Test
	public void ignoreTest() {
		assertTrue(false);
	}
	
	@Test
	public void testAssert() {
		assertTrue(true);
		assertFalse(false);
		
		assertNull(null);
		assertNotNull(new Object());
		
		// 값 비교
		assertEquals(1+2, 3);
		assertEquals(new String("hello"), "hello");
		assertNotEquals(true, false);
		
		// 객체 자체가 같은지 비교
		assertSame("Hello", "Hello");
		assertNotSame(new Integer(1), new Integer(1));
		
		Object[] a = {"Java", "JUnit", "Spring"};
		Object[] b = {"Java", "JUnit", "Spring"};
		
		assertArrayEquals(a, b);
		
		// assertThat : is
		assertThat(1+2, is(3));
		assertThat(5, is(not(3)));
		
		// assertThat : allOf
		assertThat("Hello World", allOf(startsWith("Hell"), containsString("lo")));
		
		// assertThat : anyOf
		assertThat("Hello World", anyOf(startsWith("Hell"), containsString("asdf")));
		
		// assertThat : both
		assertThat("ABC", both(containsString("A")).and(containsString("B")));
		
		// assertThat : everyItem
		assertThat(Arrays.asList("Apple", "Application", "Apolosize"), everyItem(startsWith("Ap")));
		
		// assertThat : hasItem
		assertThat(Arrays.asList("Apple", "Application", "Banana", "Apolosize"), hasItem("Banana"));
		
		// fail();
	}

}
