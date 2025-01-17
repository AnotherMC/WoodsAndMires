package juuxel.woodsandmires.data.mixin;

import juuxel.woodsandmires.data.builtin.CommonItemTags;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RecipeGenerator.class)
abstract class RecipeProviderMixin {

}
