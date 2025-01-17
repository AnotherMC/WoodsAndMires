package juuxel.woodsandmires.data;

import com.google.common.hash.HashCode;
import eu.pb4.polymer.resourcepack.api.AssetPaths;
import eu.pb4.polymer.resourcepack.extras.api.format.item.ItemAsset;
import eu.pb4.polymer.resourcepack.extras.api.format.item.model.BasicItemModel;
import eu.pb4.polymer.resourcepack.extras.api.format.item.tint.ConstantTintSource;
import eu.pb4.polymer.resourcepack.extras.api.format.item.tint.GrassTintSource;
import juuxel.woodsandmires.WoodsAndMires;
import juuxel.woodsandmires.block.WamBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public record WamItemModelProvider(FabricDataOutput output) implements DataProvider {

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        BiConsumer<String, byte[]> assetWriter = (path, data) -> {
            try {
                writer.write(this.output.getPath().resolve(path), data, HashCode.fromBytes(data));
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        return CompletableFuture.runAsync(() -> {
            for (var item : Registries.ITEM) {
                var id = Registries.ITEM.getId(item);
                if (!id.getNamespace().equals(WoodsAndMires.ID)) {
                    continue;
                }

                if (item instanceof BlockItem blockItem && blockItem.getBlock() == WamBlocks.PINE_LEAVES) {
                    assetWriter.accept(AssetPaths.itemAsset(id), new ItemAsset(new BasicItemModel(id.withPrefixedPath("item/"),
                        List.of(new ConstantTintSource(-12012264))), ItemAsset.Properties.DEFAULT).toJson().getBytes(StandardCharsets.UTF_8));
                } else if (item instanceof BlockItem blockItem && (blockItem.getBlock() == WamBlocks.FIREWEED || blockItem.getBlock() == WamBlocks.TANSY)) {
                    assetWriter.accept(AssetPaths.itemAsset(id), new ItemAsset(new BasicItemModel(id.withPrefixedPath("item/"),
                        List.of(new GrassTintSource())), ItemAsset.Properties.DEFAULT).toJson().getBytes(StandardCharsets.UTF_8));
                } else {
                    assetWriter.accept(AssetPaths.itemAsset(id), new ItemAsset(new BasicItemModel(id.withPrefixedPath("item/")), ItemAsset.Properties.DEFAULT).toJson().getBytes(StandardCharsets.UTF_8));
                }
            }
        }, Util.getMainWorkerExecutor());
    }

    @Override
    public String getName() {
        return "model";
    }
}
