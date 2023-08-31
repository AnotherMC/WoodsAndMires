package juuxel.woodsandmires.data;

import juuxel.woodsandmires.block.WamBlockTags;
import juuxel.woodsandmires.block.WamBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public final class WamBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public WamBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        // Minecraft tags
        getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
            .add(WamBlocks.POTTED_PINE_SAPLING)
            .add(WamBlocks.POTTED_TANSY)
            .add(WamBlocks.POTTED_FELL_LICHEN)
            .add(WamBlocks.POTTED_HEATHER);
        getOrCreateTagBuilder(BlockTags.LEAVES)
            .add(WamBlocks.PINE_LEAVES);
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
            .addTag(WamBlockTags.PINE_LOGS);
        getOrCreateTagBuilder(BlockTags.MUSHROOM_GROW_BLOCK)
            .addTag(WamBlockTags.THICK_PINE_LOGS);
        getOrCreateTagBuilder(BlockTags.SAPLINGS)
            .add(WamBlocks.PINE_SAPLING);
        getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS)
            .add(WamBlocks.HEATHER, WamBlocks.TANSY);
        getOrCreateTagBuilder(BlockTags.TALL_FLOWERS)
            .add(WamBlocks.FIREWEED);

        // WaM tags
        getOrCreateTagBuilder(WamBlockTags.LICHEN_PLANTABLE_ON)
            .forceAddTag(BlockTags.DIRT)
            .add(Blocks.FARMLAND)
            .forceAddTag(BlockTags.BASE_STONE_OVERWORLD);
        getOrCreateTagBuilder(WamBlockTags.PINE_LOGS)
            .addTag(WamBlockTags.THICK_PINE_LOGS);
        getOrCreateTagBuilder(WamBlockTags.THICK_PINE_LOGS)
            .add(WamBlocks.PINE_LOG, WamBlocks.AGED_PINE_LOG)
            .add(WamBlocks.PINE_WOOD, WamBlocks.AGED_PINE_WOOD)
            .add(WamBlocks.STRIPPED_PINE_LOG, WamBlocks.STRIPPED_PINE_WOOD)
            .add(WamBlocks.PINE_SNAG_LOG, WamBlocks.PINE_SNAG_WOOD);
    }
}
