package RotLA;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger implements Observers {
    private int turn;
    private String fileName;

    private static Logger logger;

    private Logger() {
    }

    // Lazy Instantiation of Singleton Logger class
    public static Logger getInstance(int turn) {
        if (logger == null) {
            logger = new Logger();
        }
        logger.turn = turn;
        logger.fileName = logger.createNewLoggerFile();
        return logger;
    }

    private String createNewLoggerFile() {
        String filename = "./Logger outputs/Logger-" + this.turn + ".txt";
        try {
            File myObj = new File(filename);
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        return filename;
    }

    public void update(String event) {
        try {
            FileWriter myWriter = new FileWriter(this.fileName, true);
            myWriter.write(event + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
