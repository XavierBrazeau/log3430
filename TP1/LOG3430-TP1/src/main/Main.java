package main;

import trello.TrelloJsonClient;

//Ce Main vous permettra de vous familiariser avec le projet
public class Main {
	
	//TODO: Il faut changer la valeur avec votre key (suivre les étapes de l'énoncé pour l'obtenir)
	private final static String KEY = "d3b35a4fecdfc0ebd653b6e15bc416d9";
	//TODO: Il faut changer la valeur avec votre token (suivre les étapes de l'énoncé pour l'obtenir)
	private final static String TOKEN = "7eabc81ac1359be1bebb364c43fb1bad900f8239204587eedce6143e74301e54";
	
	private final static String ENDPOINT = "https://api.trello.com/1/";
	private static TrelloJsonClient jsonClient = new TrelloJsonClient(ENDPOINT, KEY, TOKEN);

	public static void main(String[] args) {
		try {
			//TODO: Il faut spécifier un BOARD ID pour cette méthode
			String result = jsonClient.getBoardCards("5c41de91317c6b28c2db0a6e");
			System.out.println(result);
		}
		catch(Exception e) {}
	}

}
