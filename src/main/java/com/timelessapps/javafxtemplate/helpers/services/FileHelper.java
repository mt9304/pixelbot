package main.java.com.timelessapps.javafxtemplate.helpers.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper 
{
        public String getTextFromFile(String path) 
        throws IOException 
        {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, StandardCharsets.UTF_8);
        }
}
