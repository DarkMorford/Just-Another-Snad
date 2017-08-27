package net.darkmorford.jas;

import net.darkmorford.jas.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

@Mod(modid = JustAnotherSnad.MODID, name = JustAnotherSnad.MODNAME, version = JustAnotherSnad.VERSION, acceptedMinecraftVersions = "[1.12,1.13)", useMetadata = true)
public class JustAnotherSnad {
	public static final String MODID = "justanothersnad";
	public static final String MODNAME = "Just Another Snad";
	public static final String VERSION = "1.12-0.0.0.0";

	@Mod.Instance
	public static JustAnotherSnad instance;

	@SidedProxy(clientSide = "net.darkmorford.jas.proxy.ClientProxy", serverSide = "net.darkmorford.jas.proxy.ServerProxy")
	private static CommonProxy proxy;

	public static Logger logger;

	@Mod.EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		logger.log(Level.INFO,"preInit");
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public static void init(FMLInitializationEvent event) {
		logger.log(Level.INFO, "init");
		proxy.init(event);
	}

	@Mod.EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		logger.log(Level.INFO, "postInit");
		proxy.postInit(event);
	}
}
