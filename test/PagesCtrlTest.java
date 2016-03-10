import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;

import static play.test.Helpers.*;
import static org.junit.Assert.*;

public class PagesCtrlTest {

	@Test 
	public void renderHomeTemplate() {
		Content html = views.html.home.render();
		assertEquals("text/html", html.contentType());
		assertTrue(html.body().contains("Home"));
	}

	@Test 
	public void renderAboutTemplate() {
		Content html = views.html.about.render();
		assertEquals("text/html", html.contentType());
		assertTrue(html.body().contains("About"));
	}

	@Test 
	public void renderContactTemplate() {
		Content html = views.html.contact.render();
		assertEquals("text/html", html.contentType());
		assertTrue(html.body().contains("Contact"));
	}
}