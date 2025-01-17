package juuxel.woodsandmires.item;

import juuxel.woodsandmires.entity.WamBoat;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class WamBoatItem extends BoatItem {
    private final boolean chest;
    private final WamBoat boatData;

    public WamBoatItem(boolean chest, WamBoat boatData, Settings settings) {
        super(EntityType.ACACIA_BOAT, settings);
        this.chest = chest;
        this.boatData = boatData;
    }

}
