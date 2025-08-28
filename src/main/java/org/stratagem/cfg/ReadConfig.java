package org.stratagem.cfg;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadConfig {
    public static Config loadConfig(String path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(path), Config.class);
        } catch (IOException e) {
            return null;
        }
    }
}