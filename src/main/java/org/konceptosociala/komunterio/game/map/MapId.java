package org.konceptosociala.komunterio.game.map;

import java.util.regex.*;

public class MapId {
    public static final Pattern MAP_ID_REGEX = Pattern.compile("kom_([a-z]+)_([0-9]+)");

    private String id;

    public MapId(String id) throws InvalidMapIdException {
        if (!MAP_ID_REGEX.matcher(id).matches())
            throw new InvalidMapIdException(id);
        else
            this.id = id;
    }

    public class InvalidMapIdException extends Exception {
        public InvalidMapIdException(String id) {
            super("Invalid map id `"+id+"`");
        }
    }

    @Override
    public String toString() {
        return id;
    }
}
