package de.szut.zuul.model.items;

public class Item {

    private String name;
    private String description;
    private double weight;

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public Item(String name, String description, double weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %.2fkg", this.name, this.description, this.weight);
    }
}
