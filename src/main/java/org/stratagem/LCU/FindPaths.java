package org.stratagem.LCU;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindPaths {

    public static String getLeaguePath() {
        String clientPath = ProcessHandle.allProcesses()
                .map(ProcessHandle::info)
                .map(ProcessHandle.Info::command)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(cmd -> cmd.toLowerCase().endsWith("leagueclient.exe"))
                .findFirst()
                .orElse(null);

        if (clientPath == null) return null;
        int idx = clientPath.toLowerCase().lastIndexOf("leagueclient.exe");
        if (idx == -1) return null;
        return clientPath.substring(0, idx);
    }

    public static String getRiotClientPath() {
        // Find the RiotClientInstalls file
        String appDataPath = System.getenv("PROGRAMDATA");
        if (appDataPath == null) {
            appDataPath = System.getProperty("java.io.tmpdir").replace("Temp", "").replace("temp", "");
        }
        
        Path installPath = Paths.get(appDataPath, "Riot Games", "RiotClientInstalls.json");
        if (!Files.exists(installPath)) {
            return null;
        }

        try {
            // Read and parse the JSON file
            String content = Files.readString(installPath);
            // Simple JSON parsing - extract paths manually since we don't have a full JSON library
            List<String> rcPaths = new ArrayList<>();
            
            // Extract rc_default
            String rcDefault = extractJsonValue(content, "rc_default");
            if (rcDefault != null) rcPaths.add(rcDefault);
            
            // Extract rc_live  
            String rcLive = extractJsonValue(content, "rc_live");
            if (rcLive != null) rcPaths.add(rcLive);
            
            // Extract rc_beta
            String rcBeta = extractJsonValue(content, "rc_beta");
            if (rcBeta != null) rcPaths.add(rcBeta);

            // Return first existing path
            for (String path : rcPaths) {
                if (Files.exists(Paths.get(path))) {
                    return path;
                }
            }
            
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    private static String extractJsonValue(String json, String key) {
        String searchKey = "\"" + key + "\":";
        int keyIndex = json.indexOf(searchKey);
        if (keyIndex == -1) return null;
        
        int valueStart = json.indexOf("\"", keyIndex + searchKey.length());
        if (valueStart == -1) return null;
        
        int valueEnd = json.indexOf("\"", valueStart + 1);
        if (valueEnd == -1) return null;
        
        return json.substring(valueStart + 1, valueEnd);
    }
}