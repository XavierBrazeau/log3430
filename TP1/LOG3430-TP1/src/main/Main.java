package main;

import trello.TrelloJsonClient;

//Ce Main vous permettra de vous familiariser avec le projet
public class Main {
	
	//TODO: Il faut changer la valeur avec votre key (suivre les étapes de l'énoncé pour l'obtenir)
	private final static String KEY = "";
	//TODO: Il faut changer la valeur avec votre token (suivre les étapes de l'énoncé pour l'obtenir)
	private final static String TOKEN = "";
	
	private final static String ENDPOINT = "https://api.trello.com/1/";
	private static TrelloJsonClient jsonClient = new TrelloJsonClient(ENDPOINT, KEY, TOKEN);

	public static void main(String[] args) {
		try {
			//TODO: Il faut spécifier un BOARD ID pour cette méthode
			String result = jsonClient.getBoardCards("À REMPLACER PAR LE ID DU BOARD");
			System.out.println(result);
		}
		catch(Exception e) {}
	}

}
