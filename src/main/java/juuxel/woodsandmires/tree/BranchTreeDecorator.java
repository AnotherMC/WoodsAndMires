package juuxel.woodsandmires.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import juuxel.woodsandmires.block.BranchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public final class BranchTreeDecorator extends TreeDecorator {
    public static final MapCodec<BranchTreeDecorator> CODEC = RecordCodecBuilder.mapCodec(
        instance -> instance.group(
            Registries.BLOCK.getCodec().fieldOf("block").forGetter(BranchTreeDecorator::getBlock),
            Codec.FLOAT.fieldOf("chance").forGetter(BranchTreeDecorator::getChance)
        ).apply(instance, BranchTreeDecorator::new)
    );

    private final Block block;
    private final float chance;

    public BranchTreeDecorator(Block block, float chance) {
        this.block = block;
        this.chance = chance;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return WamTreeDecorators.BRANCH;
    }

    public Block getBlock() {
        return block;
    }

    public float getChance() {
        return chance;
    }

    @Override
    public void generate(Generator generator) {
        BlockPos.Mutable mut = new BlockPos.Mutable();
        Random random = generator.getRandom();

        for (BlockPos pos : generator.getLogPositions()) {
            // Don't replace the dirt underneath the trunk
            if (generator.getWorld().testBlockState(pos, Feature::isSoil)) continue;

            mut.set(pos);

            for (Direction side : Direction.Type.HORIZONTAL) {
                if (random.nextFloat() < chance) {
                    BlockState state = block.getDefaultState()
                        .withIfExists(BranchBlock.AXIS, side.getAxis())
                        .withIfExists(BranchBlock.STYLE, random.nextBoolean() ? BranchBlock.Style.THICK : BranchBlock.Style.THIN);

                    mut.move(side);
                    if (generator.isAir(mut)) {
                        generator.replace(mut, state);
                    }
                    mut.move(side.getOpposite());
                }
            }
        }
    }
}
