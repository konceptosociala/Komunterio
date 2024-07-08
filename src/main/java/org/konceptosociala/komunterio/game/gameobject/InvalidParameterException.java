package org.konceptosociala.komunterio.game.gameobject;

public class InvalidParameterException  extends RuntimeException {
    public InvalidParameterException(String param) {
        super("Invalid parameter `"+param+"`");
    }
}
