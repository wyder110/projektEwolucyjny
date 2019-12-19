package agh.cs.lab2;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Constants {

    public int startEnergy;
    public int moveEnergy;
    public int plantEnergy;
    public Double jugnleRatio;
    public int energyToBreed;
    public int width;
    public int height;
    public int startingAnimalsCount;
    public Constants(){

        JSONParser parser = new JSONParser();

            try (
        Reader reader = new FileReader("parameters.json")) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            width = Math.toIntExact((long) jsonObject.get("width"));
            height = Math.toIntExact((long) jsonObject.get("height"));
            startEnergy = Math.toIntExact((long) jsonObject.get("startEnergy"));
            moveEnergy = Math.toIntExact((long) jsonObject.get("moveEnergy"));
            plantEnergy = Math.toIntExact((long) jsonObject.get("plantEnergy"));
            jugnleRatio = (Double) jsonObject.get("jugnleRatio");
            energyToBreed = startEnergy/2;

            startingAnimalsCount = Math.toIntExact((long) jsonObject.get("startingAnimalsCount"));

        } catch (
        IOException e) {
            e.printStackTrace();
        } catch (
        ParseException e) {
            e.printStackTrace();
        }
    }
}
