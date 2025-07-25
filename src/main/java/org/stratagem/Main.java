package org.stratagem;

import org.stratagem.utils.FindLoLPath;

public class Main {
    public static void main(String[] args) {
        String path = FindLoLPath.findLeagueClientPath();
        if (path != null) {
            System.out.println("League of Legends client found at: " + path);
        } else {
            System.out.println("League of Legends client not found.");
        }
    }
}