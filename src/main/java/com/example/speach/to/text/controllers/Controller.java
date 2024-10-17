package com.example.speach.to.text.controllers;
import org.springframework.web.bind.annotation.*;
import service.ServiceInsert;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController()
@RequestMapping("/api/words")
public class Controller {
    List<String> listNotFound;

    @PostMapping
    String newWords(@RequestBody String words) {
        ServiceInsert serviceInsert = new ServiceInsert();
        listNotFound = new ArrayList<>();
        System.out.println(words.replaceAll("[\\t\\n\\r]+", " "));
        String towords = words.replaceAll("[\\t\\n\\r]+", " ");
        List<String> lis = List.of(towords.split(" "));
        for (String word : lis) {
            String description = "";
            System.err.println("word :" + word);
            if (!(serviceInsert.searchWordInCSV(word))) {
                description = serviceInsert.rechercherWordsFromList(word);
                serviceInsert.insertDataFile(word, description);
                listNotFound.add(word);

            }


        }
        if (listNotFound.isEmpty()) {
            return "";
        } else {
            return String.join(" ", listNotFound);
        }


    }
}





