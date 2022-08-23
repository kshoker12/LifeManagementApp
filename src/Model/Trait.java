package Model;

import Persistence.Writable;
import org.json.JSONObject;

public class Trait implements Writable {
    private String description;
    private String name;
    private String image;

    public String getImage() {
        return image;
    }

    public Trait(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("image", image);
        json.put("description", description);
        return json;
    }
}
