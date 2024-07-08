package org.konceptosociala.komunterio.utils.load_game;

import org.konceptosociala.komunterio.game.map.MapId;

public record NewGame(MapId mapId) implements GameLoadType {}