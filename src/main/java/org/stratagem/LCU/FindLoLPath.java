package org.stratagem.LCU;

import java.util.Optional;

public class FindLoLPath {

    public static String findLeagueInstallationPath() {
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
}