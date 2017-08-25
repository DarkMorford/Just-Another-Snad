package net.darkmorford.jas.proxy

import net.darkmorford.jas.JustAnotherSnad
import net.darkmorford.jas.init.ModBlocks
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import org.apache.logging.log4j.Level

@Mod.EventBusSubscriber(Side.CLIENT)
class ClientProxy : CommonProxy() {

	override fun preInit(event: FMLPreInitializationEvent) {
		super.preInit(event)
	}

	@Mod.EventBusSubscriber(Side.CLIENT)
	object ModelRegistrationHandler {

		@JvmStatic
		@SubscribeEvent
		fun registerModels(event: ModelRegistryEvent) {
			JustAnotherSnad.logger!!.log(Level.INFO, "registerModels")
			val itemBlockSnad = Item.getItemFromBlock(ModBlocks.snad)
			ModelLoader.registerItemVariants(itemBlockSnad, ResourceLocation("justanothersnad:snad"), ResourceLocation("justanothersnad:red_snad"))
			ModelLoader.setCustomModelResourceLocation(itemBlockSnad, 0, ModelResourceLocation("justanothersnad:snad", "inventory"))
			ModelLoader.setCustomModelResourceLocation(itemBlockSnad, 1, ModelResourceLocation("justanothersnad:red_snad", "inventory"))
		}
	}
}
