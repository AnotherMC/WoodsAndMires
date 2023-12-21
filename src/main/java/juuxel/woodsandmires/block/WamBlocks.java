package juuxel.woodsandmires.block;

import com.google.common.base.Suppliers;
import juuxel.woodsandmires.WoodsAndMires;
import juuxel.woodsandmires.feature.WamConfiguredFeatureKeys;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.item.TallBlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

public final class WamBlocks {
    public static final Block PINE_LOG = new PillarBlock(copyWoodSettings(Blocks.OAK_LOG));
    public static final Block AGED_PINE_LOG = new AgedLogBlock(PINE_LOG, AbstractBlock.Settings.copy(PINE_LOG));
    // Supplier for same reason as above
    public static final Block PINE_LEAVES = Blocks.createLeavesBlock(BlockSoundGroup.GRASS);
    public static final Block PINE_SAPLING = new SaplingBlock(new SaplingGenerator("pine", Optional.empty(), Optional.of(WamConfiguredFeatureKeys.PINE_FROM_SAPLING), Optional.empty()), AbstractBlock.Settings.copy(Blocks.OAK_SAPLING));
    public static final Block POTTED_PINE_SAPLING = Blocks.createFlowerPotBlock(PINE_SAPLING);
    public static final Block PINE_WOOD = new PillarBlock(copyWoodSettings(Blocks.OAK_WOOD));
    public static final Block AGED_PINE_WOOD = new WoodVariantBlock(PINE_WOOD, AbstractBlock.Settings.copy(PINE_WOOD));
    public static final Block STRIPPED_PINE_LOG = new PillarBlock(copyWoodSettings(Blocks.STRIPPED_OAK_LOG));
    public static final Block STRIPPED_PINE_WOOD = new PillarBlock(copyWoodSettings(Blocks.STRIPPED_OAK_WOOD));
    public static final Block PINE_SNAG_LOG = new PillarBlock(copyWoodSettings(Blocks.STRIPPED_OAK_LOG));
    public static final Block PINE_SNAG_WOOD = new PillarBlock(copyWoodSettings(Blocks.STRIPPED_OAK_WOOD));
    public static final Block FIREWEED = new TallFlowerBlock(createFlowerSettings());
    public static final Block TANSY = new BigFlowerBlock(StatusEffects.SLOW_FALLING, 10, createFlowerSettings());
    public static final Block POTTED_TANSY = Blocks.createFlowerPotBlock(TANSY);
    public static final Block FELL_LICHEN = new LichenBlock(createFlowerSettings().mapColor(MapColor.OFF_WHITE).offset(AbstractBlock.OffsetType.XZ));
    public static final Block POTTED_FELL_LICHEN = Blocks.createFlowerPotBlock(FELL_LICHEN);
    public static final Block HEATHER = new HeatherBlock(StatusEffects.REGENERATION, 8, createFlowerSettings());
    public static final Block POTTED_HEATHER = Blocks.createFlowerPotBlock(HEATHER);

    private WamBlocks() {
    }

    public static void init() {
        register("pine_log", PINE_LOG);
        register("aged_pine_log", AGED_PINE_LOG);
        register("pine_leaves", PINE_LEAVES);
        register("pine_sapling", PINE_SAPLING);
        register("potted_pine_sapling", POTTED_PINE_SAPLING, (Item) null);
        register("pine_wood", PINE_WOOD);
        register("aged_pine_wood", AGED_PINE_WOOD);
        register("stripped_pine_log", STRIPPED_PINE_LOG);
        register("stripped_pine_wood", STRIPPED_PINE_WOOD);
        register("pine_snag_log", PINE_SNAG_LOG);
        register("pine_snag_wood", PINE_SNAG_WOOD);
        register("fireweed", FIREWEED, new TallBlockItem(FIREWEED, new Item.Settings()));
        register("tansy", TANSY);
        register("potted_tansy", POTTED_TANSY, (Item) null);
        register("fell_lichen", FELL_LICHEN);
        register("potted_fell_lichen", POTTED_FELL_LICHEN, (Item) null);
        register("heather", HEATHER);
        register("potted_heather", POTTED_HEATHER, (Item) null);

        FlammableBlockRegistry fbr = FlammableBlockRegistry.getDefaultInstance();
        fbr.add(PINE_LOG, 5, 5);
        fbr.add(AGED_PINE_LOG, 5, 5);
        fbr.add(PINE_WOOD, 5, 5);
        fbr.add(AGED_PINE_WOOD, 5, 5);
        fbr.add(STRIPPED_PINE_LOG, 5, 5);
        fbr.add(STRIPPED_PINE_WOOD, 5, 5);
        fbr.add(PINE_SNAG_LOG, 5, 5);
        fbr.add(PINE_SNAG_WOOD, 5, 5);
        fbr.add(PINE_LEAVES, 5, 20);

        FuelRegistry fr = FuelRegistry.INSTANCE;

        StrippableBlockRegistry.register(PINE_LOG, STRIPPED_PINE_LOG);
        StrippableBlockRegistry.register(AGED_PINE_LOG, STRIPPED_PINE_LOG);
        StrippableBlockRegistry.register(PINE_WOOD, STRIPPED_PINE_WOOD);
        StrippableBlockRegistry.register(AGED_PINE_WOOD, STRIPPED_PINE_WOOD);
    }

    private static void register(String id, Block block) {
        register(id, block, new BlockItem(block, new Item.Settings()));
    }

    private static void register(String id, Block block, @Nullable Item item) {
        Registry.register(Registries.BLOCK, WoodsAndMires.id(id), block);

        if (item != null) {
            Registry.register(Registries.ITEM, WoodsAndMires.id(id), item);
        }
    }

    private static void register(String id, Block block, Supplier<@Nullable Item> itemSupplier) {
        Registry.register(Registries.BLOCK, WoodsAndMires.id(id), block);

        @Nullable Item item = itemSupplier.get();
        if (item != null) {
            Registry.register(Registries.ITEM, WoodsAndMires.id(id), item);
        }
    }

    private static AbstractBlock.Settings copyWoodSettings(Block block) {
        return AbstractBlock.Settings.copy(block);
    }

    private static AbstractBlock.Settings createFlowerSettings() {
        return AbstractBlock.Settings.create()
            .mapColor(MapColor.DARK_GREEN)
            .noCollision()
            .breakInstantly()
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.GRASS);
    }
}
