package juuxel.woodsandmires.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public final class WamBoatEntity extends BoatEntity implements BoatWithWamData {
    private final WamBoat boatData;

    public WamBoatEntity(EntityType<? extends BoatEntity> type, World world, WamBoat boatData) {
        super(type, world, () -> Items.AIR);
        this.boatData = boatData;
    }

    @Override
    public WamBoat getBoatData() {
        return boatData;
    }

}
