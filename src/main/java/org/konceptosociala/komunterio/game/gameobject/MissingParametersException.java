package org.konceptosociala.komunterio.game.gameobject;

import java.util.List;

public class MissingParametersException extends RuntimeException {
    public MissingParametersException(List<String> params) {
        super("Missing game object parameters: "+params.toString());
    }
}
