package trello;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
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

class JsonClientTest {

	//TODO: Déclarer vos variables, mocks, stub ou spies ici
	//La librairie mockito ainsi que d'autres dépendances utiles ont été importé pour vous
	private final static String KEY = "12";
	private final static String TOKEN = "123";
	private final static String ENDPOINT = "localhost";
	private final static String BOARD_ID = "1234";
	
	private static TrelloJsonClient jsonClient;
	TrelloJsonClient trelloJsonClientSpy;
	StatusLine mockStatusLine;
	CloseableHttpResponse mockCloseableHttpResponse;
	String mockurl;
	CloseableHttpClient mockHttpClient;
	
	@BeforeEach
	void setUp() throws Exception {
		//TODO: Initiliser vos objets, mocks, etc ici
		jsonClient = new TrelloJsonClient(ENDPOINT, KEY, TOKEN);
		trelloJsonClientSpy = Mockito.spy(jsonClient);
		mockStatusLine = Mockito.mock(StatusLine.class);
		mockCloseableHttpResponse = Mockito.mock(CloseableHttpResponse.class);
		mockurl = "http://test.com";
		mockHttpClient= Mockito.mock(CloseableHttpClient.class);
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
		
		try 
		{
			HttpEntity expectedEntity= new StringEntity("{json}");
			Mockito.when(mockStatusLine.getStatusCode()).thenReturn(200);
			Mockito.when(mockCloseableHttpResponse.getEntity()).thenReturn(expectedEntity);
			URI uri = new URI(ENDPOINT + "?key=" + KEY + "&token=" + TOKEN);
			doReturn(mockCloseableHttpResponse).when(mockHttpClient).execute(aHttpGetRequestWithUriMatching(uri));
		}
		catch(Exception e)
		{
			
		}
		
		//String expectedString = EntityUtils.toString(expectedEntity, "UTF-8");
		
		String expectedString = "{json}";
		try
		{
			//String result = Mockito.verify(trelloJsonClientSpy).getBoardCards(BOARD_ID);
			String result = jsonClient.getBoardCards(BOARD_ID);
			assertTrue(result.equals(expectedString));
		}
		catch (ParseException | TrelloException | IOException e)
		{
			assertNull(e);
		}
		
		
	}
	
	@Test
	public void testGetBoardCardsError() {
		//TODO: tester l'échec de la requête
		//Vérifier qu'il s'agit bien d'une requête de type GET
		//Vérifier l'URL de la requête
		//Vérifier que la méthode lance une exception
	}
	
	//TODO: Décommenter cette méthode et l'implémenter
	private HttpGet aHttpGetRequestWithUriMatching(URI expected) {
		//TODO: utiliser la classe HttpGetMatcher��
		HttpGetMatcher httpGetMatcher = new HttpGetMatcher(expected);
		
		HttpGet httpGet = new HttpGet(ENDPOINT + "?key=" + KEY + "&token=" + TOKEN);
		
		assertTrue(httpGetMatcher.matches(httpGet));
		return httpGet;
		
	}

}
