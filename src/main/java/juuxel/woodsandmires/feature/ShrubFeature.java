package juuxel.woodsandmires.feature;

import com.mojang.serialization.Codec;
import juuxel.woodsandmires.block.ShrubLogBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class ShrubFeature extends Feature<ShrubFeatureConfig> {
    public ShrubFeature(Codec<ShrubFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<ShrubFeatureConfig> context) {
        var world = context.getWorld();
        var pos = context.getOrigin();
        var config = context.getConfig();
        var random = context.getRandom();

        BlockPos below = pos.down();
        if (!isSoil(world, below) || !world.getBlockState(below).isSideSolidFullSquare(world, below, Direction.UP)) {
            return false;
        }

        BlockPos.Mutable mut = new BlockPos.Mutable();
        mut.set(pos);

        BlockState log = config.log;
        BlockState logWithLeaves = log.getBlock() instanceof ShrubLogBlock ? log.withIfExists(ShrubLogBlock.HAS_LEAVES, true) : log;
        BlockState leaves = config.leaves.withIfExists(LeavesBlock.DISTANCE, 1);
        int extraHeight = random.nextFloat() < config.extraHeightChance ? random.nextInt(config.extraHeight + 1) : 0;
        int height = config.baseHeight + extraHeight;

        for (int y = 1; y <= height; y++) {
            if (y > 1 || height == 1) {
                setBlockState(world, mut, logWithLeaves);

                for (Direction direction : Direction.Type.HORIZONTAL) {
                    mut.move(direction);
                    setBlockState(world, mut, leaves);
                    mut.move(direction.getOpposite());
                }
            } else {
                setBlockState(world, mut, log);
            }

            mut.move(Direction.UP);
        }

        setBlockState(world, mut, leaves);
        return false;
    }
}
