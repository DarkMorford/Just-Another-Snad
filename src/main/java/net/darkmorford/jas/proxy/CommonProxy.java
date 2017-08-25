package net.darkmorford.jas.proxy;

import net.darkmorford.jas.JustAnotherSnad;
import net.darkmorford.jas.block.BlockSnad;
import net.darkmorford.jas.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.Level;

@Mod.EventBusSubscriber
public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
    }

    public void init(FMLInitializationEvent event)
    {
    }

    public void postInit(FMLPostInitializationEvent event)
    {
    }

    @SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		JustAnotherSnad.logger.log(Level.INFO, "Registering blocks");
		IForgeRegistry<Block> registry = event.getRegistry();

		registry.register(new BlockSnad());
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		JustAnotherSnad.logger.log(Level.INFO, "Registering items");
		IForgeRegistry<Item> registry = event.getRegistry();

		// ItemBlocks
		registry.register(new ItemBlock(ModBlocks.snad).setRegistryName(ModBlocks.snad.getRegistryName()));
	}
}
