// real bypass!!!!
package dev.client.rose.module.impl.render;

import dev.client.rose.module.Category;
import dev.client.rose.module.Module;

public class FullBright extends Module {

    public FullBright() {
        super("FullBright", "see stuff", 0, Category.RENDER);
    }

    protected float flPreGamma = mc.gameSettings.gammaSetting;

    @Override
    public void onEnable() {
        flPreGamma = mc.gameSettings.gammaSetting;
        mc.gameSettings.gammaSetting = 100.0 F;
    }

    @Override
    public void onDisable() {
        mc.gameSettings.gammaSetting = flPreGamma;
    }

}
