package juuxel.woodsandmires.data;

import juuxel.woodsandmires.WoodsAndMires;
import juuxel.woodsandmires.block.WamBlocks;
import juuxel.woodsandmires.data.builtin.CommonItemTags;
import juuxel.woodsandmires.item.WamItemTags;
import juuxel.woodsandmires.item.WamItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public final class WamRecipeProvider extends FabricRecipeProvider {


    public WamRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter exporter) {
        return new RecipeGenerator(wrapperLookup, exporter) {
            public void offerShapelessRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input, @Nullable String group, int outputCount) {
               createShapeless(RecipeCategory.MISC, output, outputCount)
                    .input(input)
                    .group(group)
                    .criterion(hasItem(input), conditionsFromItem(input))
                    .offerTo(exporter, WoodsAndMires.id(convertBetween(output, input)).toString());
            }

            @Override
            public void generate() {
                // Wooden

                createShapeless(RecipeCategory.BUILDING_BLOCKS, Items.BIRCH_PLANKS, 4)
                    .input(WamItemTags.THICK_PINE_LOGS).group("planks").criterion("has_logs", conditionsFromTag(WamItemTags.THICK_PINE_LOGS))
                    .offerTo(exporter, Identifier.of("terrestria", "pine_to_planks").toString());

                offerBarkBlockRecipe(WamBlocks.PINE_WOOD, WamBlocks.PINE_LOG);
                offerBarkBlockRecipe(WamBlocks.AGED_PINE_WOOD, WamBlocks.AGED_PINE_LOG);
                offerBarkBlockRecipe(WamBlocks.PINE_SNAG_WOOD, WamBlocks.PINE_SNAG_LOG);
                offerBarkBlockRecipe(WamBlocks.STRIPPED_PINE_WOOD, WamBlocks.STRIPPED_PINE_LOG);

                // Dyes
                offerShapelessRecipe(exporter, Items.MAGENTA_DYE, WamBlocks.FIREWEED, "magenta_dye", 2);
                offerShapelessRecipe(exporter, Items.PINK_DYE, WamBlocks.HEATHER, "pink_dye", 1);
                offerShapelessRecipe(exporter, Items.YELLOW_DYE, WamBlocks.TANSY, "yellow_dye", 1);

                // Other
                createShapeless(RecipeCategory.FOOD, WamItems.PINE_CONE_JAM)
                    .input(Items.GLASS_BOTTLE)
                    .input(ingredientFromTag(CommonItemTags.PINE_CONES), 2)
                    .input(CommonItemTags.SUGAR)
                    .criterion(hasItem(WamItems.PINE_CONE), conditionsFromItem(WamItems.PINE_CONE))
                    .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "recipegen";
    }
}
