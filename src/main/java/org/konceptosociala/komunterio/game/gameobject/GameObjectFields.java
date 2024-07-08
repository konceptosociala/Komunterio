package org.konceptosociala.komunterio.game.gameobject;

import java.util.HashMap;

import org.konceptosociala.komunterio.game.gameobject.GameObjectFields.Type;

public class GameObjectFields extends HashMap<String, Type> {
    public enum Type {
        String,
        Bool,
        Int,
        IntArray,
    }
}
