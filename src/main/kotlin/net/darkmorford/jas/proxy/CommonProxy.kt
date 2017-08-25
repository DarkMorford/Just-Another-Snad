package net.darkmorford.jas.proxy

import net.darkmorford.jas.JustAnotherSnad
import net.darkmorford.jas.block.BlockSnad
import net.darkmorford.jas.init.ModBlocks
import net.darkmorford.jas.item.ItemBlockSnadMeta
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.apache.logging.log4j.Level

@Mod.EventBusSubscriber
open class CommonProxy {
	open fun preInit(event: FMLPreInitializationEvent) {
		JustAnotherSnad.logger!!.log(Level.INFO, "common proxy preInit")
	}

	fun init(event: FMLInitializationEvent) {}

	fun postInit(event: FMLPostInitializationEvent) {}

	@Mod.EventBusSubscriber
	object RegistrationHandler {

		@JvmStatic
		@SubscribeEvent
		fun registerBlocks(event: RegistryEvent.Register<Block>) {

			JustAnotherSnad.logger!!.log(Level.INFO, "Registering blocks")
			val registry = event.registry

			registry.register(ModBlocks.snad)
		}

		@JvmStatic
		@SubscribeEvent
		fun registerItems(event: RegistryEvent.Register<Item>) {
			JustAnotherSnad.logger!!.log(Level.INFO, "Registering items")
			val registry = event.registry

			// ItemBlocks
			registry.register(ItemBlockSnadMeta(ModBlocks.snad).setRegistryName(ModBlocks.snad.registryName!!))
		}
	}
}


