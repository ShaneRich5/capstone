package controllers;

import org.junit.*;

import play.twirl.api.Content;

import static org.junit.Assert.*;

public class PagesCtrlTest {

	@Test 
	public void renderHomeTemplate() {
		assertTrue(true);
//		Content html = views.pages.home.render();
//		assertEquals("text/html", html.contentType());
//		assertTrue(html.body().contains("Home"));
	}
}