package skyblock;

import net.fabricmc.api.ModInitializer;
import quickcarpet.api.QuickCarpetAPI;

public class SkyBlockLoader implements ModInitializer {
    @Override
    public void onInitialize() {
        QuickCarpetAPI.getInstance().registerModule(new SkyBlockModule());
    }
}
