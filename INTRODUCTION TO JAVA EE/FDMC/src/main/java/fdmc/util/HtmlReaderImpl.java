package fdmc.util;

import java.io.*;
public class HtmlReaderImpl implements HtmlReader {
    @Override
    public String readHtmlFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(path)
                        )
                )
        );
        
        StringBuilder fileContent = new StringBuilder();
        
        String line;
        while ((line = reader.readLine()) != null){
            fileContent.append(line).append(System.lineSeparator());
        }
        
        return fileContent.toString().trim();
    }
}
