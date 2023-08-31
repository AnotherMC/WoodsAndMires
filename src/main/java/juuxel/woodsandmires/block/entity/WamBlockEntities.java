package juuxel.woodsandmires.block.entity;

import juuxel.woodsandmires.WoodsAndMires;
import juuxel.woodsandmires.block.WamBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public final class WamBlockEntities {
    public static void register() {;
    }

    private static void register(String id, BlockEntityType<?> type) {
        Registry.register(Registries.BLOCK_ENTITY_TYPE, WoodsAndMires.id(id), type);
    }
}
