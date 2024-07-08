package org.konceptosociala.komunterio.game.gameobject;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.konceptosociala.komunterio.game.PlayerControl;
import org.konceptosociala.komunterio.game.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.*;
import com.jme3.scene.Spatial;
import com.jme3.scene.plugins.gltf.*;

public class GameObjectExtrasLoader implements ExtrasLoader {         
    public GameObjectExtrasLoader() {}

    @Override
    public Object handleExtras(
        GltfLoader loader, 
        String parentName,     
        JsonElement parent, 
        JsonElement extras, 
        Object input 
    ) {
        if (input instanceof Spatial spatial && extras.isJsonObject()) {
            JsonObject ext = extras.getAsJsonObject();

            var typeExt = ext.entrySet()
                .stream()
                .filter((e) -> e.getKey().equals("type"))
                .findFirst()
                .orElseThrow(() -> new InvalidGameObjectTypeException());

            var type = GameObjectType.parse(typeExt.getValue().getAsString());

            Map<String, JsonElement> map = ext
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));

            switch (type) {
                case Player -> {
                    var userData = getUserData(map,
                        new GameObjectFields(){{
                            put("health", GameObjectFields.Type.Int);
                            put("inventory", GameObjectFields.Type.IntArray);
                        }}
                    );

                    initPlayer(spatial, userData);
                }
                default -> {} // TODO: other game object types
            }
        }

        return input;
    }

    @SuppressWarnings("unchecked")
    private void initPlayer(Spatial spatial, Map<String, Object> userData) {
        for (var entry : userData.entrySet()) {
            var key = entry.getKey();
            var value = switch (key) {
                case "health" -> entry.getValue();
                case "inventory" -> new Inventory((List<Integer>) entry.getValue());
                default -> throw new InvalidParameterException(key);
            };

            spatial.setUserData(key, value);
        }

        spatial.addControl(new PlayerControl());
    }

    private static Map<String, Object> getUserData(
        Map<String, JsonElement> extras, 
        GameObjectFields fields
    ) {
        var userData = new HashMap<String, Object>();
        var missingParameters = new ArrayList<String>();

        for (var extra : extras.entrySet()) {
            if (fields
                .entrySet()
                .stream()
                .anyMatch(
                    (field) -> field.getKey().equals(extra.getKey())
                )) 
            {
                var key = extra.getKey();
                var value = switch (fields.get(extra.getKey())) {
                    case Bool -> extra.getValue().getAsBoolean();
                    case Int -> extra.getValue().getAsInt();
                    case IntArray ->
                        StreamSupport.stream(extra.getValue().getAsJsonArray().spliterator(), false)
                            .map((e) -> e.getAsInt())
                            .toList();
                    case String -> extra.getValue().getAsString();
                };
                userData.put(key, value);
            }
        }

        for (var key : fields.keySet()) {
            if (!userData.keySet().stream().anyMatch((k) -> k.equals(key))) {
                missingParameters.add(key);
            }
        }

        if (!missingParameters.isEmpty())
            throw new MissingParametersException(missingParameters);

        return userData;
    }
}