package org.konceptosociala.komunterio.game.gameobject;

public class InvalidGameObjectTypeException extends RuntimeException {
    public InvalidGameObjectTypeException() {
        super("Game object type property must be specified: player, npc, enemy, item, interactable");
    }
    
    public InvalidGameObjectTypeException(String type) {
        super("Invalid game object type: `"+type+"`. Available are: player, npc, enemy, item, interactable");
    }
}