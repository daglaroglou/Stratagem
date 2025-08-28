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

            process.waitFor();
            
            return found;

        } catch (IOException | InterruptedException e) {
            System.err.println("Error checking process status: " + e.getMessage());
            return false;
        }
    }

    /**
     * Start a process with the given command
     * @param command The command to execute
     * @return the started Process, or null if failed
     */
    public static java.lang.Process startProcess(String command) {
        if (command == null || command.trim().isEmpty()) {
            return null;
        }

        try {
            // Use a more robust approach to handle paths with spaces
            String[] commandArray = parseCommand(command);
            ProcessBuilder processBuilder = new ProcessBuilder(commandArray);
            return processBuilder.start();
        } catch (IOException e) {
            System.err.println("Error starting process: " + e.getMessage());
            return null;
        }
    }

    /**
     * Parse command string into array, properly handling quoted arguments
     * @param command The command string to parse
     * @return Array of command and arguments
     */
    private static String[] parseCommand(String command) {
        java.util.List<String> tokens = new java.util.ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        
        for (int i = 0; i < command.length(); i++) {
            char c = command.charAt(i);
            
            if (c == '"' || c == '\'') {
                inQuotes = !inQuotes;
            } else if (c == ' ' && !inQuotes) {
                if (current.length() > 0) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
            } else {
                current.append(c);
            }
        }
        
        if (current.length() > 0) {
            tokens.add(current.toString());
        }
        
        return tokens.toArray(String[]::new);
    }

    /**
     * Start a process with the given command and arguments
     * @param command The command to execute
     * @param args Additional arguments for the command
     * @return the started Process, or null if failed
     */
    public static java.lang.Process startProcess(String command, String... args) {
        if (command == null || command.trim().isEmpty()) {
            return null;
        }

        try {
            String[] commandArray = new String[args.length + 1];
            commandArray[0] = command;
            System.arraycopy(args, 0, commandArray, 1, args.length);
            
            ProcessBuilder processBuilder = new ProcessBuilder(commandArray);
            return processBuilder.start();
        } catch (IOException e) {
            System.err.println("Error starting process: " + e.getMessage());
            return null;
        }
    }
}
