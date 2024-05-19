package juuxel.woodsandmires.data;

import juuxel.woodsandmires.block.WamBlocks;
import juuxel.woodsandmires.item.WamItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public final class WamBlockLootTableProvider extends FabricBlockLootTableProvider {


    protected WamBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(WamBlocks.PINE_LOG);
        addDrop(WamBlocks.AGED_PINE_LOG);
        addDrop(WamBlocks.PINE_LEAVES,
            block -> leavesDrops(block, WamBlocks.PINE_SAPLING, SAPLING_DROP_CHANCE)
                .pool(addSurvivesExplosionCondition(WamItems.PINE_CONE, LootPool.builder()
                    .conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                    .with(ItemEntry.builder(WamItems.PINE_CONE)
                        .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(3, 0.04f)))
                    )
                ))
        );
        addDrop(WamBlocks.PINE_SAPLING);
        addPottedPlantDrops(WamBlocks.POTTED_PINE_SAPLING);
        addDrop(WamBlocks.PINE_WOOD);
        addDrop(WamBlocks.AGED_PINE_WOOD);
        addDrop(WamBlocks.STRIPPED_PINE_LOG);
        addDrop(WamBlocks.STRIPPED_PINE_WOOD);
        addDrop(WamBlocks.PINE_SNAG_LOG);
        addDrop(WamBlocks.PINE_SNAG_WOOD);
        addDrop(WamBlocks.FIREWEED, block -> dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
        addDrop(WamBlocks.TANSY);
        addPottedPlantDrops(WamBlocks.POTTED_TANSY);
        addDrop(WamBlocks.FELL_LICHEN);
        addPottedPlantDrops(WamBlocks.POTTED_FELL_LICHEN);
        addDrop(WamBlocks.HEATHER);
        addPottedPlantDrops(WamBlocks.POTTED_HEATHER);
    }
}
