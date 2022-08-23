package Persistence;

import org.json.JSONObject;


//Interface used to support JsonReader and JsonWriter to support design
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
