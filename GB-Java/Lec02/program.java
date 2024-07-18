package Lec02;

import java.io.File;
import java.util.Formatter;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.XMLFormatter;

import javax.sound.midi.Soundbank;

public class program
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("Hello World");
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath.concat("/file01.txt");
        File fileNew = new File(filePath);
         
        if(fileNew.createNewFile()) System.out.println("File was created");
            else System.out.println("File existed");
        System.out.println(fileNew.getAbsolutePath());

        System.out.println(fileNew.length());

        Logger logger = Logger.getLogger("logger");

        FileHandler info = new FileHandler("log.txt");
        logger.addHandler((info));
        XMLFormatter xml = new XMLFormatter();

        info.setFormatter(xml);
        
        logger.log(Level.WARNING, "Test Logger 01");
        logger.info("Logger Test 02");

        
        
    }
}