package net.darkmorford.jas

import net.darkmorford.jas.proxy.CommonProxy
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.Logger

@Mod(modid = JustAnotherSnad.MODID, name = JustAnotherSnad.MODNAME, version = JustAnotherSnad.VERSION, acceptedMinecraftVersions = "[1.12,1.13)", useMetadata = true, modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter")
object JustAnotherSnad {

	const val MODID = "justanothersnad"
	const val MODNAME = "Just Another Snad"
	const val VERSION = "1.12-0.0.0.0"

	@Mod.Instance
	var instance: JustAnotherSnad? = null

	@SidedProxy(clientSide = "net.darkmorford.jas.proxy.ClientProxy", serverSide = "net.darkmorford.jas.proxy.ServerProxy")
	var proxy: CommonProxy? = null
	var logger: Logger? = null

    @EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        logger = event.modLog
		logger!!.log(Level.INFO, "preInit")
        proxy!!.preInit(event)
    }

    @EventHandler
    fun init(event: FMLInitializationEvent) {
        proxy!!.init(event)
		logger!!.log(Level.INFO, "init")
    }

    @EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        proxy!!.postInit(event)
		logger!!.log(Level.INFO, "postInit")
    }
}
