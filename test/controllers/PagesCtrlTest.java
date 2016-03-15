package controllers;

import org.junit.*;

import play.twirl.api.Content;

import static org.junit.Assert.*;

public class PagesCtrlTest {

	@Test 
	public void renderHomeTemplate() {
		Content html = views.html.pages.home.render();
		assertEquals("text/html", html.contentType());
		assertTrue(html.body().contains("Home"));
	}

	@Test 
	public void renderAboutTemplate() {
		Content html = views.html.pages.about.render();
		assertEquals("text/html", html.contentType());
		assertTrue(html.body().contains("About"));
	}

	@Test 
	public void renderContactTemplate() {
		Content html = views.html.pages.contact.render();
		assertEquals("text/html", html.contentType());
		assertTrue(html.body().contains("Contact"));
	}
}