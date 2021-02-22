package skyblock;

import net.minecraft.recipe.BrewingRecipeRegistry;
import quickcarpet.api.settings.ChangeListener;
import quickcarpet.api.settings.ParsedRule;
import skyblock.mixin.BrewingRecipeRegistryAccessor;

public class BetterPotionListener implements ChangeListener<Boolean>
{
    @Override
    public void onChange(ParsedRule<Boolean> rule, Boolean previous)
    {
        BrewingRecipeRegistryAccessor.getPotionRecipeList().clear();
        BrewingRecipeRegistry.registerDefaults();
    }
}
