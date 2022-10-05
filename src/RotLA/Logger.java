package RotLA;

public interface Logger {
    public void log(String message);
}

class ConsoleLogger implements Logger {
    public void log(String message){
        System.out.println(message);
    }
}