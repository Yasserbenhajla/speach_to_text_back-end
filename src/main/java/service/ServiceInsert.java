package service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Service
public class ServiceInsert {


    public final List<String> bestWords = Arrays.asList(
            "Happy", "Excited", "Joyful", "Content", "Friendly",
            "Kind", "Generous", "Optimistic", "Enthusiastic", "Curious"
    );
    public final List<String> badWords = Arrays.asList(
            "dump", "useless", "shitty", "bull shit ", "foolish",
            "stupid", "silly", "bankers", "blockhead", "clod"
    );

    public String getFilePath() {
        return System.getProperty("user.dir") + "\\src\\main\\resources\\mots.csv";
    }


    public void insertDataFile(String mots, String decription) {
        String[] newColumns = {mots, decription};
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(getFilePath(), true))) {
            // Append the new row to the CSV file
            csvWriter.writeNext(newColumns);
            System.out.println("New row inserted at the end of the CSV file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean searchWordInCSV(String searchWord) {
        try (CSVReader reader = new CSVReader(new FileReader(getFilePath()))) {
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {

                for (String column : nextLine) {
                    if (column.contains(searchWord)) {
                        return true;
                    }
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return false;
    }


    public  String rechercherWordsFromList(String motRecherche) {
        motRecherche = motRecherche.toLowerCase();
        String word = "not found";
        if (searchList(bestWords, motRecherche)) {
            return "accepted";
        }
        if (searchList(badWords, motRecherche)) {
            return "not accepted";
        }
        return word;
    }


    public Boolean searchList(List<String> list, String word) {
        for (String mot : list) {

            String motEnMinuscules = mot.toLowerCase();

            if (motEnMinuscules.equals(word)) {
                return true;
            }
        }
        return false;
    }

}


