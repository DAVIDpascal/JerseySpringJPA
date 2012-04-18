package com.selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;

public class SeleniumTest {
	private DefaultSelenium selenium;
	
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/");
		selenium.start();
	}

	@Test
	public void testSelenium () throws Exception {
	/*	selenium.open("/jerseySpringJPA/");
		selenium.type("id=ident", "0");
		selenium.type("id=nom", "Durand");
		selenium.type("id=age", "45");
		selenium.click("css=input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
	*/}
	
	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}

}
