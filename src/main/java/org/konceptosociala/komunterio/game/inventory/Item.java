package org.konceptosociala.komunterio.game.inventory;

import java.io.File;
import java.io.IOException;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.Savable;
import com.moandjiezana.toml.Toml;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item implements Savable {
    public static final Item EMPTY = new Item(0, "empty", "empty", "Interface/UI/transparent.png");

    private int id;
    private String name;
    private String description;
    private String iconPath;

    public static Item load(int id) {
        if (id <= 0)
            return Item.EMPTY;

        var toml = new Toml()
            .read(new File("data/Items/"+id+".toml"))
            .getTable("item");

        if (!new File("data/Items/"+id+".png").exists()) 
            throw new RuntimeException("Image `"+id+".png` not found");

        return new Item(
            id, 
            toml.getString("name"),
            toml.getString("description"),
            "data/Items/"+id+".png"
        );
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        var capsule = ex.getCapsule(this);
        capsule.write(id, "id", 0);
        capsule.write(name, "name", "empty");
        capsule.write(description, "description", "empty");
        capsule.write(iconPath, "iconPath", "Interface/UI/transparent.png");
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        var capsule = im.getCapsule(this);
        id          = capsule.readInt("id", 0);
        name        = capsule.readString("name", "empty");
        description = capsule.readString("description", "empty");
        iconPath    = capsule.readString("iconPath", "Interface/UI/transparent.png");
    }
}
