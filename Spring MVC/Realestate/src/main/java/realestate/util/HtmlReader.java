package realestate.util;

import java.io.*;
public class HtmlReader {
    public String readHtmlFile(String htmlFilePath) throws IOException {
        
        BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(htmlFilePath)
                        )
                )
        );

        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = fileReader.readLine()) != null){
            sb.append(line)
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
    
}
