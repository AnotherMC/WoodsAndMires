package juuxel.woodsandmires.client;

import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import com.terraformersmc.terraform.sign.TerraformSign;
import juuxel.woodsandmires.block.WamBlocks;
import juuxel.woodsandmires.client.renderer.WamBoatEntityRenderer;
import juuxel.woodsandmires.entity.WamBoat;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.util.SpriteIdentifier;

@Environment(EnvType.CLIENT)
public final class WoodsAndMiresClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        WamBlocksClient.init();
        registerSign(WamBlocks.PINE_SIGN);

        for (WamBoat boat : WamBoat.values()) {
            registerBoatModel(boat);
        }
    }

    private static void registerSign(Block sign) {
        TerraformSign t = (TerraformSign) sign;
        SpriteIdentifier sprite = new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, t.getTexture());
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(sprite);
    }

    private static void registerBoatModel(WamBoat boat) {
        var type = boat.entityType();
        EntityRendererRegistry.register(type, context -> new WamBoatEntityRenderer(context, boat));
        EntityModelLayerRegistry.registerModelLayer(WamBoatEntityRenderer.getModelLayer(boat),
            BoatEntityModel::getTexturedModelData);
    }
}
