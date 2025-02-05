package juuxel.woodsandmires.item;

import juuxel.woodsandmires.block.WamBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public final class WamItemGroups {
    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(Items.WARPED_BUTTON,
                WamBlocks.PINE_LOG,
                WamBlocks.AGED_PINE_LOG,
                WamBlocks.PINE_WOOD,
                WamBlocks.AGED_PINE_WOOD,
                WamBlocks.STRIPPED_PINE_LOG,
                WamBlocks.STRIPPED_PINE_WOOD,
                WamBlocks.PINE_SNAG_LOG,
                WamBlocks.PINE_SNAG_WOOD
            );
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Items.WARPED_STEM,
                WamBlocks.PINE_LOG,
                WamBlocks.AGED_PINE_LOG,
                WamBlocks.PINE_SNAG_LOG);
            entries.addAfter(Items.FLOWERING_AZALEA_LEAVES,
                WamBlocks.PINE_LEAVES);
            entries.addAfter(Items.FLOWERING_AZALEA,
                WamBlocks.PINE_SAPLING);
            entries.addAfter(Items.LILY_OF_THE_VALLEY,
                WamBlocks.TANSY,
                WamBlocks.HEATHER);
            entries.addAfter(Items.PEONY,
                WamBlocks.FIREWEED);
            entries.addBefore(Items.GLOW_LICHEN,
                WamBlocks.FELL_LICHEN);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            addBefore(entries, stack -> stack.isOf(Items.ENCHANTED_BOOK),
                WamItems.PINE_CONE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(WamItems.PINE_CONE_JAM);
        });
    }

    private static void addBefore(FabricItemGroupEntries entries, Predicate<ItemStack> predicate, ItemConvertible... items) {
        var stacks = Arrays.stream(items).map(ItemStack::new).toList();
        entries.addBefore(predicate, stacks, ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS);
    }

    private static void addAfterFirstEnabled(FabricItemGroupEntries entries, List<Item> after, ItemConvertible... items) {
        Item start = after.stream()
            .filter(item -> item.getRequiredFeatures().isSubsetOf(entries.getEnabledFeatures()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Could not find any of the items " + after));
        entries.addAfter(start, items);
    }
}
