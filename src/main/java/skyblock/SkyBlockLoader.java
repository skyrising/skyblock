package skyblock;

import net.fabricmc.api.ModInitializer;
import quickcarpet.QuickCarpet;

public class SkyBlockLoader implements ModInitializer {
    @Override
    public void onInitialize() {
        try {
            Class.forName("quickcarpet.module.QuickCarpetModule");
            QuickCarpet.getInstance().registerModule(new SkyBlockModule());
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("QuickCarpet module system not found. Requires at least QuickCarpet version 1.12.0");
        }
    }
}
