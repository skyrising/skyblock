package skyblock;

import carpet.settings.ParsedRule;
import carpet.settings.Rule;
import carpet.settings.Validator;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.server.ServerTask;
import net.minecraft.server.command.ServerCommandSource;
import skyblock.mixin.BrewingRecipeRegistryAccessor;

import static carpet.settings.RuleCategory.*;

public class SkyBlockSettings
{
    public static final String SKYBLOCK = "skyblock";
    
    @Rule(desc = "Better potions", category = {SKYBLOCK, EXPERIMENTAL, FEATURE}, validate = BetterPotionListener.class)
    public static boolean betterPotions = false;
    
    public static class BetterPotionListener extends Validator<Boolean> {
        @Override
        public Boolean validate(ServerCommandSource source, ParsedRule<Boolean> currentRule, Boolean newValue, String string)
        {
            if (source != null)
            {
                source.getMinecraftServer().send(new ServerTask(source.getMinecraftServer().getTicks(), () -> {
                    BrewingRecipeRegistryAccessor.getPotionRecipeList().clear();
                    BrewingRecipeRegistry.registerDefaults();
                }));
            }
            return newValue;
        }
    }
    
    @Rule(desc = "Add trades to the wandering trader for Skyblock", category = {SKYBLOCK, EXPERIMENTAL, FEATURE}, validate = WanderingTraderSkyblockTradesChange.class)
    public static boolean wanderingTraderSkyblockTrades = false;
    
    public static class WanderingTraderSkyblockTradesChange extends Validator<Boolean> {
        @Override
        public Boolean validate(ServerCommandSource source, ParsedRule<Boolean> currentRule, Boolean newValue, String string) {
            if (newValue) {
                Trades.mergeWanderingTraderOffers(Trades.getSkyblockWanderingTraderOffers());
            } else {
                Trades.mergeWanderingTraderOffers(new Int2ObjectOpenHashMap<>());
            }
            return newValue;
        }
    }
    
    @Rule(
            desc = "Block light detector.",
            extra = {
            "Right click a daylight sensor with a light source to toggle.",
            "No visual indicator besides redstone power is given."
            },
            category = {SKYBLOCK, FEATURE, EXPERIMENTAL}
    )
    public static boolean blockLightDetector = false;
    
    @Rule(desc = "Fixes exit end portal generating too low", extra = "Fixes MC-93185", category = {SKYBLOCK, BUGFIX})
    public static boolean endPortalFix = true;
    
}
