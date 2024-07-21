package org.dhbw.advancewars.util;

import org.dhbw.advancewars.MainApplication;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DirectoryReader {

    public static List<String> getFolderItemNames(String path) throws IOException, URISyntaxException {
        URI uri = Objects.requireNonNull(MainApplication.class.getResource(path)).toURI();
        Path dirPath = Paths.get(uri);
        return Files.list(dirPath).map(p -> p.getFileName().toString()).toList();
    }
    
}