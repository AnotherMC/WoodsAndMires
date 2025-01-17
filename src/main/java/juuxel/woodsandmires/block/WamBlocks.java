package juuxel.woodsandmires.block;

import com.google.common.base.Suppliers;
import juuxel.woodsandmires.WoodsAndMires;
import juuxel.woodsandmires.feature.WamConfiguredFeatureKeys;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.TallBlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public final class WamBlocks {
    public static final Block PINE_LOG = register("pine_log", copyWoodSettings(Blocks.OAK_LOG), PillarBlock::new);
    public static final Block AGED_PINE_LOG = register("aged_pine_log", AbstractBlock.Settings.copy(PINE_LOG), s -> new AgedLogBlock(PINE_LOG, s));
    // Supplier for same reason as above
    public static final Block PINE_LEAVES = register("pine_leaves", Blocks.createLeavesSettings(BlockSoundGroup.GRASS), LeavesBlock::new);
    public static final Block PINE_SAPLING = register("pine_sapling", AbstractBlock.Settings.copy(Blocks.OAK_SAPLING), settings -> new SaplingBlock(new SaplingGenerator("pine", Optional.empty(), Optional.of(WamConfiguredFeatureKeys.PINE_FROM_SAPLING), Optional.empty()), settings));
    public static final Block POTTED_PINE_SAPLING = register("potted_pine_sapling", Blocks.createFlowerPotSettings(), settings -> new FlowerPotBlock(PINE_SAPLING, settings), null);
    public static final Block PINE_WOOD = register("pine_wood", copyWoodSettings(Blocks.OAK_WOOD), PillarBlock::new);
    public static final Block AGED_PINE_WOOD = register("aged_pine_wood", AbstractBlock.Settings.copy(PINE_WOOD)
        .overrideTranslationKey(PINE_WOOD.getTranslationKey()), PillarBlock::new);
    public static final Block STRIPPED_PINE_LOG = register("stripped_pine_log", copyWoodSettings(Blocks.STRIPPED_OAK_LOG), PillarBlock::new);
    public static final Block STRIPPED_PINE_WOOD = register("stripped_pine_wood", copyWoodSettings(Blocks.STRIPPED_OAK_WOOD), PillarBlock::new);
    public static final Block PINE_SNAG_LOG = register("pine_snag_log", copyWoodSettings(Blocks.STRIPPED_OAK_LOG), PillarBlock::new);
    public static final Block PINE_SNAG_WOOD = register("pine_snag_wood", copyWoodSettings(Blocks.STRIPPED_OAK_WOOD), PillarBlock::new);;
    public static final Block FIREWEED = register("fireweed", createFlowerSettings(), TallFlowerBlock::new, TallBlockItem::new);
    public static final Block TANSY = register("tansy", createFlowerSettings(), settings -> new BigFlowerBlock(StatusEffects.SLOW_FALLING, 10, settings));
    public static final Block POTTED_TANSY = register("potted_tansy", Blocks.createFlowerPotSettings(), settings -> new FlowerPotBlock(TANSY, settings), null);
    public static final Block FELL_LICHEN = register("fell_lichen", createFlowerSettings().mapColor(MapColor.OFF_WHITE).offset(AbstractBlock.OffsetType.XZ), LichenBlock::new);
    public static final Block POTTED_FELL_LICHEN = register("potted_fell_lichen", Blocks.createFlowerPotSettings(), settings -> new FlowerPotBlock(FELL_LICHEN, settings), null);
    public static final Block HEATHER = register("heather", createFlowerSettings(), settings -> new HeatherBlock(StatusEffects.REGENERATION, 8, settings));
    public static final Block POTTED_HEATHER = register("potted_fell_heather", Blocks.createFlowerPotSettings(), settings -> new FlowerPotBlock(HEATHER, settings), null);

    private WamBlocks() {
    }

    public static void init() {
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

        StrippableBlockRegistry.register(PINE_LOG, STRIPPED_PINE_LOG);
        StrippableBlockRegistry.register(AGED_PINE_LOG, STRIPPED_PINE_LOG);
        StrippableBlockRegistry.register(PINE_WOOD, STRIPPED_PINE_WOOD);
        StrippableBlockRegistry.register(AGED_PINE_WOOD, STRIPPED_PINE_WOOD);
    }

    private static Block register(String id, Function<AbstractBlock.Settings, Block> block) {
        return register(id, block, BlockItem::new);
    }

    private static Block register(String id, Function<AbstractBlock.Settings, Block> block, @Nullable BiFunction<Block, Item.Settings, Item> item) {
        return register(id, AbstractBlock.Settings.create(), block, item);
    }

    private static Block register(String id, AbstractBlock.Settings settings, Function<AbstractBlock.Settings, Block> block) {
        return register(id, settings, block, BlockItem::new);
    }

    private static Block register(String id, AbstractBlock.Settings settings, Function<AbstractBlock.Settings, Block> block, @Nullable BiFunction<Block, Item.Settings, Item> item) {
        var val = block.apply(settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, WoodsAndMires.id(id))));
        Registry.register(Registries.BLOCK, WoodsAndMires.id(id), val);

        if (item != null) {
            Registry.register(Registries.ITEM, WoodsAndMires.id(id), item.apply(val, new Item.Settings()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, WoodsAndMires.id(id)))
                .translationKey(val.getTranslationKey())
            ));
        }

        return val;
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
