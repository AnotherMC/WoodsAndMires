package juuxel.woodsandmires.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record FrozenTreasureFeatureConfig(
    BlockStateProvider ice,
    IntProvider height,
    IntProvider radius,
    RegistryKey<LootTable> lootTable
) implements FeatureConfig {
    public static final Codec<FrozenTreasureFeatureConfig> CODEC = RecordCodecBuilder.create(builder ->
        builder.group(
            BlockStateProvider.TYPE_CODEC.fieldOf("ice").forGetter(FrozenTreasureFeatureConfig::ice),
            IntProvider.POSITIVE_CODEC.fieldOf("height").forGetter(FrozenTreasureFeatureConfig::height),
            IntProvider.POSITIVE_CODEC.fieldOf("radius").forGetter(FrozenTreasureFeatureConfig::radius),
            RegistryKey.createCodec(RegistryKeys.LOOT_TABLE).fieldOf("loot_table").forGetter(FrozenTreasureFeatureConfig::lootTable)
        ).apply(builder, FrozenTreasureFeatureConfig::new)
    );
}
