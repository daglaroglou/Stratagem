package org.stratagem.LCU;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FindPort {
    public static String findPort() {
        String installPath = FindPaths.getLeaguePath();
        if (installPath == null) return null;
        String lockfilePath = installPath + "/lockfile";
        try (BufferedReader lockfile = new BufferedReader(new FileReader(lockfilePath))) {
            String line = lockfile.readLine();
            if (line == null) return null;
            String[] parts = line.split(":");
            if (parts.length < 3) return null;
            return parts[2];
        } catch (IOException e) {
            return null;
        }
    }
}