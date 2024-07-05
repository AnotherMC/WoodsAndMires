package juuxel.woodsandmires.data;

import juuxel.woodsandmires.WoodsAndMires;
import juuxel.woodsandmires.block.WamBlocks;
import juuxel.woodsandmires.data.builtin.CommonItemTags;
import juuxel.woodsandmires.item.WamItemTags;
import juuxel.woodsandmires.item.WamItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public final class WamRecipeProvider extends FabricRecipeProvider {


    public WamRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        // Wooden

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Items.BIRCH_PLANKS, 4)
            .input(WamItemTags.THICK_PINE_LOGS).group("planks").criterion("has_logs", conditionsFromTag(WamItemTags.THICK_PINE_LOGS))
            .offerTo(exporter, Identifier.of("terrestria", "pine__to_planks"));

        offerBarkBlockRecipe(exporter, WamBlocks.PINE_WOOD, WamBlocks.PINE_LOG);
        offerBarkBlockRecipe(exporter, WamBlocks.AGED_PINE_WOOD, WamBlocks.AGED_PINE_LOG);
        offerBarkBlockRecipe(exporter, WamBlocks.PINE_SNAG_WOOD, WamBlocks.PINE_SNAG_LOG);
        offerBarkBlockRecipe(exporter, WamBlocks.STRIPPED_PINE_WOOD, WamBlocks.STRIPPED_PINE_LOG);

        // Dyes
        offerShapelessRecipe(exporter, Items.MAGENTA_DYE, WamBlocks.FIREWEED, "magenta_dye", 2);
        offerShapelessRecipe(exporter, Items.PINK_DYE, WamBlocks.HEATHER, "pink_dye", 1);
        offerShapelessRecipe(exporter, Items.YELLOW_DYE, WamBlocks.TANSY, "yellow_dye", 1);

        // Other
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, WamItems.PINE_CONE_JAM)
            .input(Items.GLASS_BOTTLE)
            .input(Ingredient.fromTag(CommonItemTags.PINE_CONES), 2)
            .input(CommonItemTags.SUGAR)
            .criterion(hasItem(WamItems.PINE_CONE), conditionsFromItem(WamItems.PINE_CONE))
            .offerTo(exporter);
    }

    public static void offerShapelessRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input, @Nullable String group, int outputCount) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, output, outputCount)
            .input(input)
            .group(group)
            .criterion(hasItem(input), conditionsFromItem(input))
            .offerTo(exporter, WoodsAndMires.id(convertBetween(output, input)));
    }
}
