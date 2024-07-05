package juuxel.woodsandmires.data.builtin;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class CommonItemTags {
    public static final TagKey<Item> CHAINS = of("chains");
    public static final TagKey<Item> HONEY = of("honey");
    public static final TagKey<Item> JAMS = of("jams");
    public static final TagKey<Item> PINE_CONES = of("pine_cones");
    public static final TagKey<Item> SUGAR = of("sugar");
    public static final TagKey<Item> WOODEN_CHESTS = of("wooden_chests");
    public static final TagKey<Item> WOODEN_RODS = of("wooden_rods");

    private static TagKey<Item> of(String path) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of("c", path));
    }
}
