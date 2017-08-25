package net.darkmorford.jas.block;

import net.darkmorford.jas.JustAnotherSnad;
import net.minecraft.block.BlockSand;
import net.minecraft.block.SoundType;
import net.minecraft.creativetab.CreativeTabs;

public class BlockSnad extends BlockSand
{
	public BlockSnad()
	{
		super();

		setCreativeTab(CreativeTabs.MISC);
		setHardness(0.5F);
		setSoundType(SoundType.SAND);
		setTickRandomly(true);

		setUnlocalizedName("snad");
		setRegistryName(JustAnotherSnad.MODID, "snad");
	}
}
