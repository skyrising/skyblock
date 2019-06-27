package skyblock;

import net.minecraft.recipe.BrewingRecipeRegistry;
import quickcarpet.settings.ChangeListener;
import quickcarpet.settings.ParsedRule;
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
