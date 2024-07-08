package org.konceptosociala.komunterio.game;

import java.io.IOException;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;

public class PlayerControl extends AbstractControl {
    @Override
    protected void controlUpdate(float tpf) {
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }

    @Override
    public Control cloneForSpatial(Spatial spatial){
        final PlayerControl control = new PlayerControl();
        /* Optional: use setters to copy userdata into the cloned control */
        // control.setIndex(i); // example
        control.setSpatial(spatial);
        return control;
    }

    @Override
    public void read(JmeImporter im) throws IOException {
        super.read(im);
        var capsule = im.getCapsule(this);
        // im.getCapsule(this).read(...);
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        super.write(ex);
        var capsule = ex.getCapsule(this);
        // ex.getCapsule(this).write(...);
    }
}
