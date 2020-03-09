package ru.skillbench.tasks.basics.entity;

import java.util.LinkedList;
import java.util.List;

public class LocationImpl implements Location {

    private String name;
    private Type type;
    private Location parent;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public void setParent(Location parent) {
        this.parent = parent;
    }

    @Override
    public String getParentName() {
        return (this.parent == null) ?
                "--" :
                this.parent.getName();
    }

    @Override
    public Location getTopLocation() {
        if (this.parent == null) {
            return this;
        }

        return this.parent.getTopLocation();
    }

    @Override
    public boolean isCorrect() {
        if (this.parent == null) {
            return true;
        }

        Type type = this.parent.getType();
        switch (this.type) {
            case APARTMENT:
                if (type != Type.APARTMENT) {
                    return this.parent.isCorrect();
                } else return false;
            case BUILDING:
                if (type != Type.APARTMENT && type != Type.BUILDING) {
                    return this.parent.isCorrect();
                } else return false;
            case STREET:
                if (type != Type.APARTMENT &&
                    type != Type.BUILDING &&
                    type != Type.STREET) {
                    return this.parent.isCorrect();
                } else return false;
            case DISTRICT:
                if (type != Type.STREET &&
                    type != Type.BUILDING &&
                    type != Type.APARTMENT &&
                    type != Type.DISTRICT) {
                    return this.parent.isCorrect();
                } else return false;
            case CITY:
                if (type != Type.STREET &&
                    type != Type.BUILDING &&
                    type != Type.APARTMENT &&
                    type != Type.DISTRICT &&
                    type != Type.CITY) {
                    return this.parent.isCorrect();
                } else return false;
            case REGION:
                if (type != Type.STREET &&
                    type != Type.BUILDING &&
                    type != Type.APARTMENT &&
                    type != Type.DISTRICT &&
                    type != Type.CITY &&
                    type != Type.REGION) {
                    return this.parent.isCorrect();
                } else return false;
            case COUNTRY:
                return type != Type.COUNTRY;
            default: return false;
        }
    }

    @Override
    public String getAddress() {
        String res;
        if (this.name.endsWith(".") || (this.name.contains(" ") &&
            this.name.substring(0,this.name.indexOf(" ")).contains("."))) {
            res = this.name;
        } else {
            res = this.type.getNameForAddress() + this.name;
        }

        if (this.parent != null) {
            return res + ", " + this.parent.getAddress();
        }

        return res;
    }

    public String toString() {
        return this.name + " (" + this.type.toString() + ")";
    }
}
