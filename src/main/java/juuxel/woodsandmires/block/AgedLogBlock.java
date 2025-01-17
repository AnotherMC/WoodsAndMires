package juuxel.woodsandmires.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;

public class AgedLogBlock extends PillarBlock {
    public static final BooleanProperty MID = BooleanProperty.of("mid");
    public AgedLogBlock(Block main, Settings settings) {
        super(settings.overrideTranslationKey(main.getTranslationKey()));
        setDefaultState(getDefaultState().with(MID, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(MID);
    }
}
