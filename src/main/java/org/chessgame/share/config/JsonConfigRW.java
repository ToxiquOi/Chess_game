package org.chessgame.share.config;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonConfigRW {

    private static final String PATH = System.getProperty("user.dir") + "/src/main/java/org/chessgame/share/config/config.json";
    private static Logger logger = Logger.getLogger(JsonConfigRW.class.getSimpleName());
    private JSONParser parser = new JSONParser();
    private JSONObject confJson;

    public JsonConfigRW() {
        this.readConfig();
    }

    private void readConfig() {
        try
        {
            FileReader reader = new FileReader(PATH);

            Object data = parser.parse(reader);
            this.confJson = (JSONObject) data;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeConfig() {
        try {
            FileWriter writer = new FileWriter(PATH);
            writer.write(this.confJson.toJSONString());
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDimension(String nameCategory, String nameSubCategory)  {
        if (nameCategory.equals("board") || nameCategory.equals("window")) {
            JSONObject category = (JSONObject) this.confJson.get(nameCategory);

            if(nameSubCategory.equals("small") || nameSubCategory.equals("standard")) {
                JSONArray dataToSet = (JSONArray) category.get(nameSubCategory);
                category.put("current", dataToSet);
                this.confJson.put(nameCategory, category);
                System.out.println(this.confJson.toJSONString());
                this.writeConfig();
            }
            else {
                logger.log(Level.WARNING, "sub category name invalid");
            }
        } else {
            logger.log(Level.WARNING, "category name invalid");
        }
    }

    public Dimension getDimension(String nameCategory, String nameSubCategory) {

        if (nameCategory.equals("board") || nameCategory.equals("window")) {
            JSONObject category = (JSONObject) this.confJson.get(nameCategory);

            if(nameSubCategory.equals("small") || nameSubCategory.equals("standard") || nameSubCategory.equals("current")) {
                JSONArray size = (JSONArray) category.get(nameSubCategory);
                int width = (int) (long) size.get(0);
                int height = (int) (long) size.get(1);
                return new Dimension(width, height);
            }
            else {
                logger.log(Level.WARNING, "sub category name invalid");
                return null;
            }
        } else {
            logger.log(Level.SEVERE, "No category with this name");
            return null;
        }
    }
}
