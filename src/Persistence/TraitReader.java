package Persistence;

import Model.SelfImage;
import Model.Trait;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TraitReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public TraitReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public SelfImage read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSelfImage(jsonObject);
    }

    private SelfImage parseSelfImage(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        SelfImage selfImage = new SelfImage(name);
        addTraits(selfImage, jsonObject);
        return selfImage;
    }

    private void addTraits(SelfImage selfImage, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("traitList");
        for (Object obj: jsonArray) {
            JSONObject next = (JSONObject) obj;
            selfImage.addTrait(readTrait(next));
        }
    }

    private Trait readTrait(JSONObject next) {
        String name = next.getString("name");
        String image = next.getString("image");
        Trait trait = new Trait(name, image);
        try {
            String description = next.getString("description");
            trait.setDescription(description);
        } catch (JSONException e) {

        }
        return trait;
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }
}
