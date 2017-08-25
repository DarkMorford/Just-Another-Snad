package net.darkmorford.jas;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
		modid = JustAnotherSnad.MODID,
		name = JustAnotherSnad.MODNAME,
		version = JustAnotherSnad.VERSION,
		acceptedMinecraftVersions = "[1.12,1.13)",
		useMetadata = true
)
public class JustAnotherSnad
{
	public static final String MODID = "justanothersnad";
	public static final String MODNAME = "Just Another Snad";
	public static final String VERSION = "1.12-0.0.0.0";
    
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
	}
}
