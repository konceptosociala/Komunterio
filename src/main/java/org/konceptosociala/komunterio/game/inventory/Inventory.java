package org.konceptosociala.komunterio.game.inventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.Savable;

import lombok.Setter;

public class Inventory implements Savable {
    @Setter
    private ArrayList<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public Inventory(List<Integer> ids) {
        this();

        for (int id : ids) {
            items.add(Item.load(id));
        }
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        var capsule = ex.getCapsule(this);
        capsule.writeSavableArrayList(items, "items", new ArrayList<>());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void read(JmeImporter im) throws IOException {
        var capsule = im.getCapsule(this);
        this.items = capsule.readSavableArrayList("items", new ArrayList<>()); 
    }

    @Override
    public String toString() {
        return "Item IDs: " + items.stream()
            .map((item) -> String.valueOf(item.getId()))
            .map((id) -> id.equals("0")
                ? "X"
                : id
            )
            .collect(Collectors.joining(", ", "[", "]"));
    }
}
