package flashcards;

import java.io.*;
import java.nio.file.Files;
import java.util.stream.Collectors;

class FileIO {

    private File file;

    void write(String str) {
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(str);
        } catch (IOException e) {
            SystemIO.println("Error: " + e.getMessage());
        }
    }

    String read() {
        try {
            return Files.lines(file.toPath()).collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            SystemIO.println("Error: " + e.getMessage());
            return "-1";
        }
    }

    void setFile(String filePath) {
        if (filePath != null) {
            file = new File(filePath);
        } else {
            SystemIO.println("File name:");
            file = new File(SystemIO.scan());
        }
    }
}
