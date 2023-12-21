package juuxel.woodsandmires.block;

import juuxel.woodsandmires.block.entity.WamSignBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.SignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class WamSignBlock extends SignBlock {
    public WamSignBlock(Settings settings, WoodType woodType) {
        super(woodType, settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WamSignBlockEntity(pos, state);
    }
}
