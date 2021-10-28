package com.example.app;

/*
    Sends API request to collect data and places them inside the list
    its all based on server endpoint which will be specified in html form
 */

import com.example.app.model.FivemPlayers;
import com.example.app.model.FivemServer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FivemServerDataHandler {

    private static final OkHttpClient client = new OkHttpClient();

    private static final String URL = "https://servers-frontend.fivem.net/api/servers/single/";

    public static List<FivemServer> prepareServerData(String endpoint) throws Exception {

        URL website = new URL(URL + endpoint);

        Request request = new Request.Builder()
                .url(website)
                .get()
                .build();

        String content = "";

        JSONObject json ;

        try (Response response = client.newCall(request).execute()) {

            content = Objects.requireNonNull(response.body()).string();

            json = new JSONObject(content);

        }

        JSONObject data = json.getJSONObject("Data");

        String hostname = data.getString("hostname")
                .replaceAll("[0-9]","")
                .replaceAll("\\^", "")
                .replaceAll("https://discord.gg/[A-Z]", "")
                .replace("[", "").replace("]", "");

        String queue = data.getString("hostname").substring(0, 3)
                .replace("[", "").replace("]", "");

        int clients = data.getInt("clients");
        int maxClients = data.getInt("sv_maxclients");

        int online = data.getJSONArray("players").length();

        JSONArray players = data.getJSONArray("players");

        List<FivemPlayers> playersList = new ArrayList<>();

        FivemPlayers fivemPlayers = null;

        for (int i = 0; i < online; i++) {

            fivemPlayers = new FivemPlayers(
                    players.getJSONObject(i).getInt("id"),
                    players.getJSONObject(i).getString("name"),
                    players.getJSONObject(i).getInt("ping")
            );

            playersList.add(fivemPlayers);

        }

        FivemServer fivemServer = null;

        if (isQueue(data)) {

            fivemServer = new FivemServer(
                    endpoint,
                    clients, maxClients,
                    Integer.parseInt(queue),
                    hostname,
                    playersList
            );

        } else {

            fivemServer = new FivemServer(
                    endpoint,
                    clients, maxClients,
                    0,
                    hostname,
                    playersList
            );

        }

        List<FivemServer> fivemServerList = new ArrayList<>();

        fivemServerList.add(fivemServer);

        return fivemServerList;
    }

    private static boolean isQueue(JSONObject data) {
        return data.getString("hostname").startsWith("[");
    }

}
