package ru.senkinay.client.messagehistory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MessageHistory implements AutoCloseable{
    private File messageHistoryFile;
    private PrintWriter printWriter;
    private final String userName;

    public MessageHistory(String userName) {
        this.userName = userName;
    }

    public void createHistoryFile() {
        this.messageHistoryFile = new File(String.format("./History/message_history_%s.txt", userName));
        if (!this.messageHistoryFile.exists()) {
            this.messageHistoryFile.getParentFile().mkdirs();
           try {
               this.messageHistoryFile.createNewFile();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }

    public void addMessageToHistoryFile(String message) {
        if (printWriter == null) {
            try {
                this.printWriter = new PrintWriter(new BufferedWriter(new FileWriter(messageHistoryFile, StandardCharsets.UTF_8,true)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.printWriter.println(message);
        this.printWriter.flush();
    }

    @Override
    public void close(){
        if (printWriter != null) {
            printWriter.close();
        }
    }

    public String loadMessagesFromHistoryFile(int rows) {
        List<String> rowsFromFile =  new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(messageHistoryFile));
            String row = bufferedReader.readLine();
            while (row != null) {
                rowsFromFile.add(row);
                row = bufferedReader.readLine();
            }
            List<String> result =  rowsFromFile.subList(Math.max(0,rowsFromFile.size() - rows),rowsFromFile.size());
            return String.join(System.lineSeparator(),result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
