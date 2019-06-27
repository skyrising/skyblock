package skyblock;

import net.fabricmc.api.ModInitializer;
import quickcarpet.QuickCarpet;
import quickcarpet.module.QuickCarpetModule;

public class SkyBlockModule implements ModInitializer, QuickCarpetModule {
    @Override
    public void onInitialize() {
        QuickCarpet.getInstance().registerModule(this);
    }

    @Override
    public String getName() {
        return Build.NAME;
    }

    @Override
    public String getVersion() {
        return Build.VERSION;
    }

    @Override
    public String getId() {
        return "skyblock";
    }

    @Override
    public Class<?> getSettingsClass() {
        return SkyBlockSettings.class;
    }
}
