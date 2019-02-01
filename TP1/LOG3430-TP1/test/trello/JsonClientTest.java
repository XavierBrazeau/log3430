package trello;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicStatusLine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.when;

import utils.HttpGetMatcher;
import utils.RequestWrapper;
import utils.TrelloException;

import org.apache.http.entity.StringEntity; //import rajouté manuellement

class JsonClientTest {

	//TODO: Déclarer vos variables, mocks, stub ou spies ici
	//La librairie mockito ainsi que d'autres dépendances utiles ont été importé pour vous
	private final static String KEY = "12";
	private final static String TOKEN = "123";
	private final static String ENDPOINT = "https://localhost/";
	private final static String BOARD_ID = "1234";

	TrelloJsonClient client;
	RequestWrapper wrapper;
	StatusLine mockStatusLine;
	CloseableHttpClient mockCloseableHttpClient;
	CloseableHttpResponse mockCloseableHttpResponse;
	String mockURL;
	URI mockURI;
	HttpEntity expectedEntity;
	String expectedString;

	@BeforeEach
	void setUp() throws Exception {
		//TODO: Initiliser vos objets, mocks, etc ici

		mockURL = ENDPOINT + "boards/" + BOARD_ID + "/cards/";
		mockURI = new URI(mockURL + "?key=" + KEY +"&token=" + TOKEN);
		client = new TrelloJsonClient(ENDPOINT, KEY, TOKEN);
		mockStatusLine = Mockito.mock(StatusLine.class);
		mockCloseableHttpResponse = Mockito.mock(CloseableHttpResponse.class);
		mockCloseableHttpClient = Mockito.mock(CloseableHttpClient.class);
		wrapper = new RequestWrapper(KEY, TOKEN);


		expectedString = "{json}";

		expectedEntity = new StringEntity(expectedString);

	}

	@AfterEach
	void tearDown() throws Exception {
		//TODO: Libérer vos ressource ici, si nécessaire
	}

	@Test
	public void testGetBoardCardsSuccess() {
		//TODO: Tester le succés de la requête
		//Vérifier qu'il s'agit bien d'une requête de type GET
		//Vérifier l'URL de la requête
		//Vérifier le résultat retourné par la requête
		try {
			final int OK = 200;
			Mockito.when(mockCloseableHttpResponse.getStatusLine()).thenReturn(mockStatusLine);
			Mockito.when(mockStatusLine.getStatusCode()).thenReturn(OK);
			doReturn(mockCloseableHttpResponse).when(mockCloseableHttpClient).execute(aHttpGetRequestWithUriMatching(mockURI));

			Mockito.when(mockCloseableHttpResponse.getEntity()).thenReturn(expectedEntity);

			client.setRequestWrapper(wrapper);
			wrapper.setHttpClient(mockCloseableHttpClient);

			assertEquals(expectedString, client.getBoardCards(BOARD_ID));
		} catch(Exception e) {}

	}

	@Test
	public void testGetBoardCardsError() {
		//TODO: tester l'échec de la requête
		//Vérifier qu'il s'agit bien d'une requête de type GET
		//Vérifier l'URL de la requête
		//Vérifier que la méthode lance une exception
		final Integer BAD_REQUEST = 400;
		try {
			Mockito.when(mockCloseableHttpResponse.getStatusLine()).thenReturn(mockStatusLine);
			Mockito.when(mockStatusLine.getStatusCode()).thenReturn(BAD_REQUEST);
			doReturn(mockCloseableHttpResponse).when(mockCloseableHttpClient).execute(aHttpGetRequestWithUriMatching(mockURI));
			Mockito.when(mockCloseableHttpResponse.getEntity()).thenReturn(expectedEntity);


			client.setRequestWrapper(wrapper);
			wrapper.setHttpClient(mockCloseableHttpClient);

			client.getBoardCards(BOARD_ID);
		} catch(TrelloException e) {
			assertEquals(e.getCode(), BAD_REQUEST.toString());
			assertEquals(expectedString, e.getResponseContent());
		} catch(Exception e) {}
	}

	//TODO: Décommenter cette méthode et l'implémenter
	private HttpGet aHttpGetRequestWithUriMatching(URI expected) {
		//TODO: utiliser la classe HttpGetMatcher��
		return argThat(new HttpGetMatcher(expected));

	}

}
