package trello;

import java.io.IOException;

import org.apache.http.ParseException;

import utils.RequestWrapper;
import utils.TrelloException;

public class TrelloJsonClient {
	
	private String apiKey, apiToken, apiEndpoint;
	private RequestWrapper requestWrapper;
	
	public TrelloJsonClient(String endpoint, String key, String token) {
		this.apiKey = key;
		this.apiToken = token;
		this.apiEndpoint = endpoint;
		this.requestWrapper = new RequestWrapper(apiKey, apiToken);
	}
	
	public void setRequestWrapper(RequestWrapper requestWrapper) {
		this.requestWrapper = requestWrapper;
	}
	
	public String getBoardCards(String boardId) throws ParseException, TrelloException, IOException {
		String url  = apiEndpoint + "boards/" + boardId + "/cards/";
		return requestWrapper.getJson(url);
	}

}
