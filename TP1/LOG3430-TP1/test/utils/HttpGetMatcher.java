package utils;
import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.hamcrest.Description;
import org.mockito.ArgumentMatcher;


/**
 * Class HttpGetMatcher should be used to match HTTP GET, POST, PATCH and DELETE requests with a specific URL.
 * Source : https://stackoverflow.com/a/39493690
 */
public class HttpGetMatcher extends ArgumentMatcher<HttpGet> {

	private final URI expected;

	public HttpGetMatcher(URI expected) {
		this.expected = expected;
	}

	@Override
	public boolean matches(Object actual) {
		//Actuellement, cette méthode supporte seulement les requêtes GET
		return ((HttpGet) actual).getURI().equals(expected);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(expected == null ? null : expected.toString());
	}

}
