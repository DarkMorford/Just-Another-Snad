package net.darkmorford.jas.proxy;

import net.darkmorford.jas.JustAnotherSnad;
import net.darkmorford.jas.init.ModBlocks;
import net.darkmorford.jas.item.ItemBlockSnadMeta;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Level;

@Mod.EventBusSubscriber
public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {}

	public void init(FMLInitializationEvent event) {}

	public void postInit(FMLPostInitializationEvent event) {}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		JustAnotherSnad.logger.log(Level.INFO, "Registering blocks");
		event.getRegistry().registerAll(ModBlocks.snad);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		JustAnotherSnad.logger.log(Level.INFO, "Registering items");
		IForgeRegistry<Item> registry = event.getRegistry();

		// Special Items
		Item snadBlock = new ItemMultiTexture(ModBlocks.snad, ModBlocks.snad, new ItemMultiTexture.Mapper() {
			@Override
			public String apply(ItemStack stack) {
				return BlockSand.EnumType.byMetadata(stack.getMetadata()).getUnlocalizedName();
			}
		}).setRegistryName(ModBlocks.snad.getRegistryName());

		// ItemBlocks
		registry.register(snadBlock);
	}
}
