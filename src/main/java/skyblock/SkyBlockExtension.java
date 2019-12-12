package skyblock;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.settings.SettingsManager;
import net.fabricmc.api.ModInitializer;

public class SkyBlockExtension implements CarpetExtension, ModInitializer
{
    private static SettingsManager mySettingManager = new SettingsManager(Build.VERSION, Build.ID, Build.NAME);

    public SkyBlockExtension() {
        CarpetServer.manageExtension(this);
    }

    @Override
    public void onInitialize() {
    }

    @Override
    public void onGameStarted()
    {
        // Lets have our own settings class independent from carpet.conf
        mySettingManager.parseSettingsClass(SkyBlockSettings.class);
    }

    @Override
    public SettingsManager customSettingsManager()
    {
        // this will ensure that our settings are loaded properly when world loads
        return mySettingManager;
    }
    
    @Override
    public String version()
    {
        return "carpet-skyblock 1.1.0";
    }
}
