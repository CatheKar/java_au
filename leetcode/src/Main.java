import java.io.IOException;

class Main {
    static String txtFilePath = "source_leetcode_data.txt";

    public static void main(String[] args) throws IOException {
        String firstChar = args[0].substring(0, 1).toUpperCase();
        String themeName = firstChar + args[0].substring(1);

        MdGenerator mdBuilder = new MdGenerator(themeName, txtFilePath);
        mdBuilder.write(String.format("%s.md", args[0]));
    }
}
