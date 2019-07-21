package skyblock;

import net.fabricmc.api.ModInitializer;
import quickcarpet.QuickCarpet;

import java.lang.reflect.Method;

public class SkyBlockLoader implements ModInitializer {
    @Override
    public void onInitialize() {
        try {
            Class<?> qcModuleClass = Class.forName("quickcarpet.module.QuickCarpetModule");
            Method register = QuickCarpet.class.getDeclaredMethod("registerModule", qcModuleClass);
            register.invoke(QuickCarpet.getInstance(), new SkyBlockModule());
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("QuickCarpet module system not found. Requires at least QuickCarpet version 1.12.0");
        }
    }
}
