package skyblock;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.settings.SettingsManager;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SkyBlockExtension implements CarpetExtension, ModInitializer
{
    private static SettingsManager mySettingManager;
    
    public static void noop() {}
    
    public SkyBlockExtension() {
    }

    static {
        mySettingManager = new SettingsManager(Build.VERSION, Build.ID, Build.NAME);
        CarpetServer.manageExtension(new SkyBlockExtension());
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
        return "carpet-skyblock";
    }
    
    public static boolean isIgnoredForRegistrySync(Identifier registry, Identifier entry)
    {
        if (!Registry.POTION_KEY.getValue().equals(registry)) return false;
        return entry.toString().startsWith("minecraft:super_");
    }
}
