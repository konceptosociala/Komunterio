package org.konceptosociala.komunterio.utils;

import java.util.logging.Logger;
import java.util.Optional;
import org.konceptosociala.komunterio.Komunterio;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.render.NiftyImage;
import lombok.NonNull;

public class Utils {
    public static final Logger LOG = Logger.getLogger(Komunterio.class.getName());

    public static Optional<NiftyImage> createUiImage(
        @NonNull Nifty nifty, 
        @NonNull String screenName, 
        @NonNull String path, 
        boolean filterLinear
    ) {
        var screen = nifty.getScreen(screenName);
        
        if (screen != null) {
            return Optional.of(nifty.getRenderEngine().createImage(
                screen, 
                path, 
                filterLinear
            ));
        } else {            
            LOG.severe(String.format("Screen %s is not found", screenName));
            return Optional.empty();
        }
    }
}
