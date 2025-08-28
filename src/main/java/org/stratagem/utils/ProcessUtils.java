package org.stratagem.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Utility class for checking if processes are running on Windows
 * @author daglaroglou
 */
public class ProcessUtils {

    /**
     * Check if a process with the given name is currently running on Windows
     * @param processName The name of the process to check (case-insensitive, can include .exe extension)
     * @return true if the process is running, false otherwise
     */
    public static boolean isProcessRunning(String processName) {
        if (processName == null || processName.trim().isEmpty()) {
            return false;
        }

        // Ensure .exe extension for Windows processes
        if (!processName.toLowerCase().endsWith(".exe")) {
            processName += ".exe";
        }

        processName = processName.toLowerCase().trim();

        try {
            // Use tasklist command on Windows
            ProcessBuilder processBuilder = new ProcessBuilder("tasklist", "/FI", "IMAGENAME eq " + processName, "/NH");
            java.lang.Process process = processBuilder.start();

            boolean found = false;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.toLowerCase().contains(processName)) {
                        found = true;
                        break;
                    }
                }
            }

            // Wait for the process to complete (important for cleanup)
            process.waitFor();
            
            return found;

        } catch (IOException | InterruptedException e) {
            System.err.println("Error checking process status: " + e.getMessage());
            return false;
        }
    }
}
