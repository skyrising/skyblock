package skyblock;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import quickcarpet.annotation.BugFix;
import quickcarpet.settings.ChangeListener;
import quickcarpet.settings.ParsedRule;
import quickcarpet.settings.Rule;

import static quickcarpet.settings.RuleCategory.*;

public class SkyBlockSettings {
    @Rule(category = {EXPERIMENTAL, FEATURE}, onChange = BetterPotionListener.class)
    public static boolean betterPotions = false;

    @Rule(category = {EXPERIMENTAL, FEATURE}, onChange = WanderingTraderSkyblockTradesChange.class)
    public static boolean wanderingTraderSkyblockTrades = false;

    private static class WanderingTraderSkyblockTradesChange implements ChangeListener<Boolean> {
        @Override
        public void onChange(ParsedRule<Boolean> rule, Boolean previous) {
            if (wanderingTraderSkyblockTrades) {
                Trades.mergeWanderingTraderOffers(Trades.getSkyblockWanderingTraderOffers());
            } else {
                Trades.mergeWanderingTraderOffers(new Int2ObjectOpenHashMap<>());
            }
        }
    }

    @Rule(category = {FEATURE, EXPERIMENTAL})
    public static boolean blockLightDetector = false;

    @Rule(bug = @BugFix("MC-93185"), category = FIX)
    public static boolean endPortalFix = true;
}
