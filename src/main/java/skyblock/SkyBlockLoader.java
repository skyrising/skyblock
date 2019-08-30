package skyblock;

import net.fabricmc.api.ModInitializer;
import quickcarpet.QuickCarpet;

import java.lang.reflect.Method;

public class SkyBlockLoader implements ModInitializer {
    @Override
    public void onInitialize() {
        try {
            Class.forName("quickcarpet.module.QuickCarpetModule");
            QuickCarpet.getInstance().registerModule(new SkyBlockModule());
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("QuickCarpet module system not found. Requires at least QuickCarpet version 1.12.0", e);
        } catch (LinkageError e) {
            throw new IllegalStateException("Incompatible QuickCarpet version (" + quickcarpet.Build.VERSION + ")", e);
        } catch (Throwable e) {
            throw new IllegalStateException("Error initializing SkyBlock", e);
        }
    }
}
