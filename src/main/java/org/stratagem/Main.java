package org.stratagem;

import org.stratagem.cfg.Config;
import org.stratagem.cfg.ReadConfig;
import org.stratagem.utils.Colors;

public class Main {
    public static void main(String[] args) {
        // Print title in cyan with ASCII box
        Colors.println("========================================", Colors.CYAN);
        Colors.println("              STRATAGEM                 ", Colors.CYAN);
        Colors.println("========================================", Colors.CYAN);

        Config cfg = ReadConfig.loadConfig("config.json");
        if (cfg != null && cfg.app != null) {
            Colors.println("[OK] Configuration loaded successfully", Colors.GREEN);
            Colors.print("Author: ", Colors.BLUE);
            Colors.println(cfg.app.author, Colors.BRIGHT_BLUE);
            Colors.print("Version: ", Colors.YELLOW);
            Colors.println(cfg.app.version, Colors.BRIGHT_YELLOW);
            Colors.print("Name: ", Colors.MAGENTA);
            Colors.println(cfg.app.name, Colors.BRIGHT_MAGENTA);
        } else {
            Colors.println("[ERROR] Failed to load configuration", Colors.RED);
        }

        // Example of using colorize method
        String successMessage = Colors.colorize("Application started successfully!", Colors.BRIGHT_GREEN);
        System.out.println(successMessage);
    }
}