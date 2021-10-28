package com.example.app.controller;

import com.example.app.FivemServerDataHandler;
import com.example.app.model.FivemPlayers;
import com.example.app.model.FivemServer;
import com.example.app.model.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/fivem")
public class DashboardController {

    private List<FivemServer> fivemServerList = new ArrayList<>();

    @PostConstruct
    private void loadData() {}

    @GetMapping("/search")
    private String displayServerInfo() {
        return "search";
    }

    @GetMapping("{endpoint}")
    private String searchDashboard(@PathVariable String endpoint, @ModelAttribute(name = "query") Query query, Model theModel) {

        try {

            String q = query.getQuery();

            fivemServerList = FivemServerDataHandler.prepareServerData(endpoint);

            List<FivemPlayers> playersList = null;

            for (FivemServer server : fivemServerList) {
                if (server.getEndpoint().equals(endpoint)) {
                    playersList = server.getFivemPlayers();
                }
            }

            List<FivemPlayers> foundPlayers = Objects.requireNonNull(playersList).stream().filter(player -> player.getName().contains(q))
                    .collect(Collectors.toList());

            theModel.addAttribute("foundPlayers", foundPlayers);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return "dashboard-find";
    }

    @GetMapping("/dashboard")
    private String showDashboard(@ModelAttribute("fivemServer") FivemServer theServer, Model theModel) {

        try {

            String endpoint = theServer.getEndpoint();

            fivemServerList = FivemServerDataHandler.prepareServerData(endpoint);

            List<FivemPlayers> playersList = null;

            String serverName = "none";

            int totalPlayers = 0;
            int maxPlayers = 0;

            for (FivemServer server : fivemServerList) {
                if (server.getEndpoint().equals(endpoint)) {
                    totalPlayers = server.getOnline();
                    maxPlayers = server.getMaxOnline();
                    serverName = server.getHostName();
                    playersList = server.getFivemPlayers();
                }
            }

            theModel.addAttribute("endpoint", endpoint);
            theModel.addAttribute("totalPlayers", totalPlayers);
            theModel.addAttribute("maxPlayers", maxPlayers);
            theModel.addAttribute("serverName", serverName);
            theModel.addAttribute("players", playersList);


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return "dashboard";
    }

}
