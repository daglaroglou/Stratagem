package org.stratagem.utils;

import java.util.Optional;

public class FindLoLPath {

    public static String findLeagueClientPath() {
        return ProcessHandle.allProcesses()
                .map(ProcessHandle::info)
                .map(ProcessHandle.Info::command)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(cmd -> cmd.toLowerCase().endsWith("leagueclient.exe"))
                .findFirst()
                .orElse(null);
    }
}
