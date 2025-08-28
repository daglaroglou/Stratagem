package org.stratagem;

import org.stratagem.cfg.Config;
import org.stratagem.cfg.ReadConfig;
import org.stratagem.utils.Colors;

public class Main {
    public static void main(String[] args) {
        // Print title in cyan with ASCII box
        Colors.println(" ____  _             _                              ", Colors.CYAN);
        Colors.println("/ ___|| |_ _ __ __ _| |_ __ _  __ _  ___ _ __ ___  ", Colors.CYAN);
        Colors.println("\\___ \\| __| '__/ _` | __/ _` |/ _` |/ _ | '_ ` _ \\ ", Colors.CYAN);
        Colors.println(" ___) | |_| | | (_| | || (_| | (_| |  __| | | | | |", Colors.CYAN);
        Colors.println("|____/ \\__|_|  \\__,_|\\__\\__,_|\\__, |\\___|_| |_| |_|", Colors.CYAN);
        Colors.println("[>] github.com/daglaroglou    |___/                ", Colors.CYAN);

        Config cfg = ReadConfig.loadConfig("config.json");
        if (cfg != null) {
            Colors.println("[-] Reading configuration...", Colors.YELLOW);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            Colors.println("[+] Configuration loaded successfully", Colors.GREEN);
        } else {
            Colors.println("[x] Failed to load configuration", Colors.RED);
        }
    }
}