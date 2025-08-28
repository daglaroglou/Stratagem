package org.stratagem.utils;

/**
 * Utility class for ANSI color codes
 */
public class Colors {
    // Reset
    public static final String RESET = "\u001B[0m";

    // Regular colors
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // Bright colors
    public static final String BRIGHT_BLACK = "\u001B[90m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_BLUE = "\u001B[94m";
    public static final String BRIGHT_MAGENTA = "\u001B[95m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_WHITE = "\u001B[97m";

    // Background colors
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_MAGENTA = "\u001B[45m";
    public static final String BG_CYAN = "\u001B[46m";

    // Text styles
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";
    public static final String REVERSED = "\u001B[7m";

    /**
     * Colorize text with the specified color
     * @param text The text to colorize
     * @param color The ANSI color code
     * @return The colorized text
     */
    public static String colorize(String text, String color) {
        return color + text + RESET;
    }

    /**
     * Print colored text to console
     * @param text The text to print
     * @param color The ANSI color code
     */
    public static void print(String text, String color) {
        System.out.print(colorize(text, color));
    }

    /**
     * Print colored text with newline to console
     * @param text The text to print
     * @param color The ANSI color code
     */
    public static void println(String text, String color) {
        System.out.println(colorize(text, color));
    }

    /**
     * Check if ANSI colors are supported in the current environment
     * @return true if colors are supported
     */
    public static boolean isColorSupported() {
        String term = System.getenv("TERM");
        String os = System.getProperty("os.name").toLowerCase();

        // Windows 10 version 1511+ supports ANSI
        if (os.contains("windows")) {
            return true; // Most modern Windows terminals support ANSI
        }

        // Unix-like systems
        return term != null && !term.equals("dumb");
    }
}
