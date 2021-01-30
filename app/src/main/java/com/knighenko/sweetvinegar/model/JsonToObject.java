package com.knighenko.sweetvinegar.model;


import com.knighenko.sweetvinegar.entity.Advertisement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.pushy.sdk.lib.jackson.databind.ObjectMapper;

public class JsonToObject {


    /**
     * This method returns ArrayList of Advertisements from OlX
     */
    public static ArrayList<Advertisement> getAdvertisements(String jsonString) {
        ArrayList<Advertisement> advertisements = new ArrayList<>();
        String regEx = "\\{[^{}]*\\}";
        Pattern logEntry = Pattern.compile(regEx);
        Matcher matchPattern = logEntry.matcher(jsonString.replaceAll("\\s+"," "));

        while (matchPattern.find()) {
            ObjectMapper mapper = new ObjectMapper();
            Advertisement adv = null;
            try {
                adv = mapper.readValue(matchPattern.group(), Advertisement.class);
                advertisements.add(adv);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //  System.out.println(adv);
        }
        return advertisements;
    }


}
