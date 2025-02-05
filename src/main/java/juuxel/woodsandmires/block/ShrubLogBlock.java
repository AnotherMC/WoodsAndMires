package juuxel.woodsandmires.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

public class ShrubLogBlock extends PillarBlock implements Waterloggable {
    public static final BooleanProperty HAS_LEAVES = BooleanProperty.of("has_leaves");
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    private static final VoxelShape X_SHAPE = createCuboidShape(0, 2, 2, 16, 14, 14);
    private static final VoxelShape Y_SHAPE = createCuboidShape(2, 0, 2, 14, 16, 14);
    private static final VoxelShape Z_SHAPE = createCuboidShape(2, 2, 0, 14, 14, 16);
    private final Block leaves;

    public ShrubLogBlock(Settings settings, Block leaves) {
        super(settings);
        this.leaves = leaves;
        setDefaultState(getDefaultState().with(HAS_LEAVES, false).with(WATERLOGGED, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        // noinspection ConstantConditions
        return super.getPlacementState(ctx)
            .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER);
    }

    @SuppressWarnings("deprecation")
    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }

        return super.getFluidState(state);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(HAS_LEAVES)) {
            return VoxelShapes.fullCube();
        }

        switch (state.get(AXIS)) {
            case X:
                return X_SHAPE;
            case Y:
                return Y_SHAPE;
            case Z:
            default:
                return Z_SHAPE;
        }
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if (state.get(WATERLOGGED)) {
            tickView.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        var stack = player.getStackInHand(Hand.MAIN_HAND);
        if (!state.get(HAS_LEAVES) && stack.isOf(leaves.asItem())) {
            if (!world.isClient()) {
                world.setBlockState(pos, state.with(HAS_LEAVES, true));
                var soundGroup = leaves.getDefaultState().getSoundGroup();
                player.playSoundToPlayer(soundGroup.getPlaceSound(), SoundCategory.BLOCKS,
                    (soundGroup.getVolume() + 1f) / 2f,
                    soundGroup.getPitch() * 0.8f);
                return ActionResult.CONSUME;
            }

            return ActionResult.SUCCESS;
        }

        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(HAS_LEAVES, WATERLOGGED);
    }
}
