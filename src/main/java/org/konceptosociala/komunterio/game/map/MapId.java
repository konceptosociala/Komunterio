package org.konceptosociala.komunterio.game.map;

import java.util.regex.*;

public class MapId {
    public static final Pattern MAP_ID_REGEX = Pattern.compile("kom_(?<chapter>[a-z]+)_(?<number>[0-9]+)");

    private String chapter;
    private int number;

    public MapId(String id) throws InvalidMapIdException {
        var matcher = MAP_ID_REGEX.matcher(id);

        if (matcher.matches()) {
            chapter = matcher.group("chapter");
            number = Integer.parseInt(matcher.group("number"));
        } else {
            throw new InvalidMapIdException(id);
        }
    }

    @Override
    public String toString() {
        return "kom_"+chapter+"_"+number;
    }

    public class InvalidMapIdException extends RuntimeException {
        public InvalidMapIdException(String id) {
            super("Invalid map id `"+id+"`");
        }
    }
}
