package net.darkmorford.jas.proxy;

import net.darkmorford.jas.JustAnotherSnad;
import net.darkmorford.jas.init.ModBlocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Level;

@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        JustAnotherSnad.logger.log(Level.INFO, "registerModels");
        Item itemBlockSnad = Item.getItemFromBlock(ModBlocks.snad);
        Item itemBlockSoulSnad = Item.getItemFromBlock(ModBlocks.soulSnad);
        ModelLoader.setCustomModelResourceLocation(itemBlockSnad, 0, new ModelResourceLocation("justanothersnad:snad", "variant=sand"));
        ModelLoader.setCustomModelResourceLocation(itemBlockSnad, 1, new ModelResourceLocation("justanothersnad:red_snad", "variant=red_sand"));
        ModelLoader.setCustomModelResourceLocation(itemBlockSoulSnad, 0, new ModelResourceLocation("justanothersnad:soul_snad", "inventory"));
    }
}
