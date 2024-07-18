package Sem02.task04;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class program {

    static Logger logger = Logger.getAnonymousLogger();


    static void createLogger(String logPath)
    {
        
        SimpleFormatter formatter = new SimpleFormatter();
        try {
            FileHandler handler = new FileHandler("log.txt", true);
            handler.setFormatter(formatter);
            logger.addHandler(handler);

        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static void closeLogger()
    {
        Handler[] handlers = logger.getHandlers();
        for(Handler h : handlers)
        {
            h.close();
        }
    }
    


    public static void main(String[] args) {
        String s = "test";
        int n = 3;
        repeat(s, n);
        readFile("text.txt");
    }

    static public void repeat(String s, int n)
    {

        createLogger("log.txt");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
        {
            sb.append(s);
            sb.append(" ");
        }
        sb.append('\n');
        String filepath = "text.txt";
        try (FileWriter writer = new FileWriter(filepath, true);){
            writer.write(sb.toString());
            logger.info("Запись прошла успешно");
            return;
        } 
        catch(IOException e){
        System.out.println("Something went wrong"); 
        logger.warning("ЗАпист не прошла");
        return;
    }
    finally{System.out.println("Finally"); closeLogger();}
        

    }

    static void readFile(String filePath)
    {
        createLogger("log.txt");
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)){
        
        while (scanner.hasNextLine()){
            System.out.println((scanner.nextLine()));
        }
        logger.info("Read successful");
        }
        catch(FileNotFoundException e){
            logger.warning("File not found");
        }
        finally{closeLogger();}
    }


}
