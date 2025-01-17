package juuxel.woodsandmires.data;

import juuxel.woodsandmires.block.WamBlocks;
import juuxel.woodsandmires.data.builtin.CommonItemTags;
import juuxel.woodsandmires.item.WamItemTags;
import juuxel.woodsandmires.item.WamItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public final class WamItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public WamItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture, null);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        // Minecraft tags
        builder(ItemTags.LEAVES)
            .add(WamBlocks.PINE_LEAVES);
        builder(ItemTags.LOGS_THAT_BURN)
            .addTag(WamItemTags.PINE_LOGS);
        builder(ItemTags.SAPLINGS)
            .add(WamBlocks.PINE_SAPLING);
        builder(ItemTags.SMALL_FLOWERS)
            .add(WamBlocks.HEATHER, WamBlocks.TANSY);
        builder(ItemTags.BEE_FOOD)
            .add(WamBlocks.FIREWEED);

        // WaM tags
        builder(WamItemTags.PINE_LOGS)
            .addTag(WamItemTags.THICK_PINE_LOGS);
        builder(WamItemTags.THICK_PINE_LOGS)
            .add(WamBlocks.PINE_LOG, WamBlocks.AGED_PINE_LOG)
            .add(WamBlocks.PINE_WOOD, WamBlocks.AGED_PINE_WOOD)
            .add(WamBlocks.STRIPPED_PINE_LOG, WamBlocks.STRIPPED_PINE_WOOD)
            .add(WamBlocks.PINE_SNAG_LOG, WamBlocks.PINE_SNAG_WOOD);

        // Common tags
        builder(CommonItemTags.CHAINS)
            .add(Items.CHAIN);
        builder(CommonItemTags.HONEY)
            .add(WamItems.PINE_CONE_JAM);
        builder(CommonItemTags.JAMS)
            .add(WamItems.PINE_CONE_JAM);
        builder(CommonItemTags.PINE_CONES)
            .add(WamItems.PINE_CONE);
        builder(CommonItemTags.SUGAR)
            .add(Items.SUGAR);
        builder(CommonItemTags.WOODEN_CHESTS)
            .add(Items.CHEST, Items.TRAPPED_CHEST);
        builder(CommonItemTags.WOODEN_RODS)
            .add(Items.STICK);
    }

    private Builder builder(TagKey<Item> tag) {
        return new Builder(getOrCreateTagBuilder(tag));
    }

    private static final class Builder {
        private final FabricTagProvider<Item>.FabricTagBuilder parent;

        private Builder(FabricTagProvider<Item>.FabricTagBuilder parent) {
            this.parent = parent;
        }

        public Builder add(ItemConvertible... items) {
            for (ItemConvertible item : items) {
                parent.add(item.asItem());
            }

            return this;
        }

        public Builder addTag(TagKey<Item> tag) {
            parent.addTag(tag);
            return this;
        }
    }
}
