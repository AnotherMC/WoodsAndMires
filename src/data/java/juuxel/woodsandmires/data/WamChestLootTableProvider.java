package juuxel.woodsandmires.data;

import juuxel.woodsandmires.data.builtin.WamConfiguredFeatures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.TagEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public final class WamChestLootTableProvider extends SimpleFabricLootTableProvider {

    public WamChestLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.CHEST);
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> sink) {
        sink.accept(WamConfiguredFeatures.FROZEN_TREASURE_LOOT_TABLE,
            new LootTable.Builder()
                .pool(
                    LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(2, 4))
                        .with(TagEntry.expandBuilder(ConventionalItemTags.IRON_INGOTS))
                        .with(TagEntry.expandBuilder(ConventionalItemTags.GOLD_INGOTS))
                        .with(TagEntry.expandBuilder(ConventionalItemTags.COPPER_INGOTS))
                )
                .pool(
                    LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1, 2))
                        .with(TagEntry.expandBuilder(ConventionalItemTags.EMERALD_GEMS))
                        .with(ItemEntry.builder(Items.NAME_TAG))
                )
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(4))
                        .with(ItemEntry.builder(Items.STRING))
                        .with(ItemEntry.builder(Items.SPIDER_EYE))
                        .with(ItemEntry.builder(Items.ROTTEN_FLESH))
                        .with(ItemEntry.builder(Items.BONE))
                )
        );
    }
}
