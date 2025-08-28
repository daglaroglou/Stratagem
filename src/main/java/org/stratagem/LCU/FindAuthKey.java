package org.stratagem.LCU;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class FindAuthKey {
    public static String findAuthKey() {
        try (BufferedReader lockfile = new BufferedReader(
                new FileReader(FindPaths.getLeaguePath() + "/lockfile"))) {
            String[] parts = lockfile.readLine().split(":");
            String password = parts[3];
            String combination = "riot:" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(combination.getBytes(StandardCharsets.US_ASCII));
            return "Basic " + encodedAuth;
        } catch (Exception e) {
            return null;
        }
    }
}