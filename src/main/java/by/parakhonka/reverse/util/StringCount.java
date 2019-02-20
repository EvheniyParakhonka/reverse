package by.parakhonka.reverse.util;

public class StringCount {
    public static int countLines(String str) {
        String[] lines = str.split("\r\n|\r|\n");
        return lines.length;
    }
}
