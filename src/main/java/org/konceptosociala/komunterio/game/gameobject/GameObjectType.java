package org.konceptosociala.komunterio.game.gameobject;

public enum GameObjectType {
    Player,
    Npc,
    Enemy,
    Item,
    Interactable;

    public static GameObjectType parse(String type) throws InvalidGameObjectTypeException {
        return switch (type) {
            case "player" -> Player;
            case "npc" -> Npc;
            case "enemy" -> Enemy;
            case "item" -> Item;
            case "interactable" -> Interactable;
            default -> throw new InvalidGameObjectTypeException(type);
        };
    }
}
