import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

class MdGenerator {
    private final Charset utf_8 = StandardCharsets.UTF_8;
    private final String themeName;
    private FieldsData data;

    MdGenerator(String _themeName, String _fileName) {
        themeName = _themeName;

        Scanner Reader;
        try {
            Reader = new Scanner(new File(_fileName));
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
            return;
        }

        String title = Reader.nextLine().replaceAll("^\\d+. ", "");
        String link = Reader.nextLine();
        Reader.nextLine();  // skip beginning of class Solution
        String sourceCode = Reader.useDelimiter("\\Z").next();
        int classSolutionEndIndex = sourceCode.length() - 2;
        sourceCode = sourceCode.substring(0, classSolutionEndIndex);

        data = new FieldsData(title, link, sourceCode);
        Reader.close();
    }

    void write(String fileName) throws IOException {
        Path filePath = Paths.get(fileName);
        List<String> lines = Files.readAllLines(filePath, utf_8);

        String themeEntry = generateThemeEntry();
        String themeSection = generateThemeSection();
        if (lines.isEmpty()) {
            Object[] formatData = {themeName, themeEntry, themeSection};
            String writeString = String.format("# %s\n\n%s\n\n%s", formatData);
            Files.write(filePath, writeString.getBytes());
        } else {
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).startsWith("##")) {
                    lines.add(i - 1, themeEntry);
                    break;
                }
            }
            lines.add("\n" + themeSection);
            Files.write(filePath, lines, utf_8);
        }
    }

    private String generateThemeEntry() {
        Object[] formatData = {
                data.title,
                data.title.toLowerCase().replace(' ', '-')
        };
        return String.format("+ [%s](#%s)", formatData);
    }

    private String generateThemeSection() {
        Object[] formatData = {data.title, data.link, data.sourceCode};
        return String.format("## %s\n\n%s\n\n```java\n%s\n```", formatData);
    }
}