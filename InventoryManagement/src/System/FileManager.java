package System;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
	public void writeToFile(String file, String content) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void deleteFromFile(String file, String contentDelete) {
        List<String> fileContents = new ArrayList<>();

        
        try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
	        String Line;
	        
            while ((Line = reader.readLine()) != null) {
                String[] parts = Line.split("\\|");
                
                boolean matchFound = false;
                
                // Check if any part of the array matches contentDelete
                for (String part : parts) {
                    if (part.equals(contentDelete)) {
                        matchFound = true;
                        break;
                    }
                }
                
                // If no match is found, add the line to fileContents
                if (!matchFound) {
                    fileContents.add(Line);
                }
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String line : fileContents) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
