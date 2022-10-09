package RotLA;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public interface Observers {
    void update(String event);
}

class Logger implements Observers{
    int turn;
    String fileName;
    Logger(int i){
        this.turn = i;
        this.fileName = this.createNewLoggerFile();
    }

    private String createNewLoggerFile(){
        String filename = "./Logger outputs/Logger-" + this.turn + ".txt";
        try {
            File myObj = new File(filename);
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        return filename;
    }

    public void update(String event){
        try {
            FileWriter myWriter = new FileWriter(this.fileName,true);
            myWriter.write(event+"\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}