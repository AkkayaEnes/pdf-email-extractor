import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class Main {
    public static void main(String[] args) {
        try {
            PDDocument document = PDDocument.load(new File("ornek.pdf"));
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);

            Pattern pattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");
            Matcher matcher = pattern.matcher(text);

            FileWriter writer = new FileWriter("mailler.txt");
            while (matcher.find()) {
                writer.write(matcher.group());
                writer.write("\n");
            }

            writer.close();

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}