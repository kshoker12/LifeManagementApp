package Model;

import Persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelfImage implements Writable {
    private List<Trait> traitList;
    private String name;

    public SelfImage(String name) {
        this.name = name;
        traitList = new ArrayList<>();
    }

    public void addTrait(Trait trait) {
        this.traitList.add(trait);
    }

    public void removeTrait(Trait trait) {
        this.traitList.remove(trait);
    }

    public String getName() {
        return name;
    }

    public List<Trait> getTraitList() {
        return traitList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("traitList", traitListToJson());
        return json;
    }

    private JSONArray traitListToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Trait trait: traitList) {
            jsonArray.put(trait.toJson());
        }
        return jsonArray;
    }
}
