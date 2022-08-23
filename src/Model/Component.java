package Model;

import Persistence.Writable;

public abstract class Component implements Writable {
    protected String name;
    protected Double rating;

    public Component(String name) {
        this.name = name;
        this.rating = 0.0;
    }

    public String getName() {
        return name;
    }

    public Double getRating() {
        return rating;
    }

    public abstract void update();
}
