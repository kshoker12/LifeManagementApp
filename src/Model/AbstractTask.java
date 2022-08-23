package Model;

import Persistence.Writable;

import java.util.Objects;

public abstract class AbstractTask implements Writable {
    protected int duration;
    protected String name;
    protected Boolean isCompleted;

    public AbstractTask(String name, int duration) {
        this.duration = duration;
        this.name = name;
        isCompleted = false;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public abstract void complete();

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractTask)) return false;
        AbstractTask that = (AbstractTask) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
