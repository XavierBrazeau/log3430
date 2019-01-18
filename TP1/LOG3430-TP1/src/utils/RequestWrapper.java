package utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RequestWrapper {
	
	private CloseableHttpClient httpClient;
	private String apiKey, apiToken;
	
	public RequestWrapper(String apiKey, String apiToken) {
		this.apiKey = apiKey;
		this.apiToken = apiToken;
	}

	public void setHttpClient(CloseableHttpClient httpClient){
		 this.httpClient = httpClient;
	}
	
	public CloseableHttpResponse get(String url, String key, String token) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = getHttpClient();
		HttpGet httpGet = new HttpGet(url + "?key=" + key + "&token=" + token);
		return httpClient.execute(httpGet);
	}
	
	public String getJson(String url) throws TrelloException, ParseException, IOException {
		try (CloseableHttpResponse response = get(url, this.apiKey, this.apiToken)) {
			if (response.getStatusLine().getStatusCode() >= HttpStatus.SC_BAD_REQUEST) {
				String responseContent = EntityUtils.toString(response.getEntity());
				throw new TrelloException(String.valueOf(response.getStatusLine().getStatusCode()), response.getStatusLine().getReasonPhrase(),
						responseContent);
			}
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, "UTF-8");
		}
	}
	
	private CloseableHttpClient getHttpClient() {
		if (httpClient == null) {
			httpClient = HttpClients.custom()
					.setDefaultRequestConfig(
							RequestConfig.custom()
							.setCookieSpec(CookieSpecs.STANDARD)
							.build())
					.build();
		}
		return httpClient;
	}

}
