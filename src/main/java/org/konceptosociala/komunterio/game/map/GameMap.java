package org.konceptosociala.komunterio.game.map;

import java.io.*;

import org.konceptosociala.komunterio.game.gameobject.GameObjectExtrasLoader;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.scene.plugins.gltf.GltfModelKey;

import lombok.Getter;

public class GameMap {
    @Getter
    private MapId mapId;
    @Getter
    private Node scene;

    public GameMap(MapId mapId, AssetManager assetManager) throws RuntimeException {
        var mapPath = "data/Maps/"+mapId+".glb";
        var mapFile = new File(mapPath);
        if (!mapFile.exists())
            throw new RuntimeException("Map file not found: `"+mapPath+"`");

        this.mapId = mapId;

        var mapKey = new GltfModelKey(mapPath);
        mapKey.setExtrasLoader(new GameObjectExtrasLoader());

        scene = (Node) assetManager.loadModel(mapKey);
    }
}
