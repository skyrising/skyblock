package skyblock;

import quickcarpet.module.QuickCarpetModule;

public class SkyBlockModule implements QuickCarpetModule {
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
