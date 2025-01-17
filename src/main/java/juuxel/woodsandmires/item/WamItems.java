package juuxel.woodsandmires.item;

import juuxel.woodsandmires.WoodsAndMires;
import juuxel.woodsandmires.entity.WamBoat;
import net.minecraft.block.DispenserBlock;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;

public final class WamItems {
    public static final Item PINE_CONE = register("pine_cone", new Item.Settings());
    public static final Item PINE_CONE_JAM = register("pine_cone_jam", new Item.Settings()
            .recipeRemainder(Items.GLASS_BOTTLE)
            .useRemainder(Items.GLASS_BOTTLE)
            .food(new FoodComponent.Builder().nutrition(3).saturationModifier(0.25f).build())
    );

    public static void init() {
        for (WamBoat boat : WamBoat.values()) {
            //DispenserBlock.registerBehavior(boat.boat(), new WamBoatDispenserBehavior(boat, false));
            //DispenserBlock.registerBehavior(boat.chestBoat(), new WamBoatDispenserBehavior(boat, true));
        }
    }

    private static Item register(String id, Item.Settings settings) {
        return Registry.register(Registries.ITEM, WoodsAndMires.id(id), new Item(settings.registryKey(RegistryKey.of(RegistryKeys.ITEM, WoodsAndMires.id(id)))));
    }
}
