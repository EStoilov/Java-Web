package chushka.util;

import java.io.*;
public class HtmlReader {

    public String readHtmlFile(String htmlFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(htmlFilePath)
                        )
                )
        );
        
        StringBuilder htmlContent = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            htmlContent.append(line).append(System.lineSeparator());
        }
        
        return htmlContent.toString().trim();
    }
}
