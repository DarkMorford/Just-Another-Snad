package net.darkmorford.jas.proxy;

import net.darkmorford.jas.init.ModBlocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy
{
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event)
	{
		Item itemSnadBlock = Item.getItemFromBlock(ModBlocks.snad);

		ModelLoader.setCustomModelResourceLocation(itemSnadBlock, 0, new ModelResourceLocation("justanothersnad:snad", "variant=sand"));
		ModelLoader.setCustomModelResourceLocation(itemSnadBlock, 1, new ModelResourceLocation("justanothersnad:snad", "variant=red_sand"));
	}
}
