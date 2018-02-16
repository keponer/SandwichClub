package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /**
     * Given a json in String format, convert it in a JSONObject and create a Sandiwch Object
     * From it.
     * @param json JSONObject in String format
     * @return Sandwich
     * @throws JSONException Problems with JSON
     */
    public static Sandwich parseSandwichJson(String json) throws JSONException {

        // Convert json in a JSONObject
        JSONObject sandwichJSon = new JSONObject(json);

        // Get the parts about names and create a new JSONObject
        JSONObject sandwichName = sandwichJSon.getJSONObject("name");

        String mainName = sandwichName.getString("mainName");

        // Get the list of sandwich names and add it to a JSONArray
        JSONArray sandwichAKA = sandwichName.getJSONArray("alsoKnownAs");

        // Convert the sandwich names JSONArray in an List<String>
        List<String> alsoKnowAs = getStringListFromJsonArray(sandwichAKA);

        String placeOfOrigin = sandwichJSon.getString("placeOfOrigin");

        String description = sandwichJSon.getString("description");

        String image = sandwichJSon.getString("image");

        // Get the list of the sandwich ingredients and add it to a JSONArray
        JSONArray sandwichIngredients = sandwichJSon.getJSONArray("ingredients");

        // Convert the sandwich ingredients in a List<String>
        List<String> ingredients = getStringListFromJsonArray(sandwichIngredients);

        // Create the new sandwich
        Sandwich sandwich = new Sandwich(mainName, alsoKnowAs, placeOfOrigin, description, image, ingredients);

        return sandwich;
    }


    /**
     * Given an JSONArray return a List<String>
     * @param jArray JSONArray to convert
     * @return new List<String>
     * @throws JSONException Problems with JSON
     */
    public static List<String> getStringListFromJsonArray(JSONArray jArray) throws JSONException {

        List<String> returnList = new ArrayList<String>();

        for (int i = 0; i < jArray.length(); i++) {
            String val = jArray.getString(i);
            returnList.add(val);
        }

        return returnList;
    }
}
