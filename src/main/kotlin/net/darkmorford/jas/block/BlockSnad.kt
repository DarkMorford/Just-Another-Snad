package net.darkmorford.jas.block

import net.darkmorford.jas.JustAnotherSnad
import net.darkmorford.jas.item.IMetaBlockSnad
import net.minecraft.block.BlockSand
import net.minecraft.block.SoundType
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack

class BlockSnad : BlockSand(), IMetaBlockSnad {

	override fun getSpecialName(stack: ItemStack): String {
		return if (stack.itemDamage == 0) "default" else "red"
	}

	init {
        setCreativeTab(CreativeTabs.MISC)
        setHardness(0.5f)
        soundType = SoundType.SAND
        tickRandomly = true

        unlocalizedName = "snad"
        setRegistryName(JustAnotherSnad.MODID, "snad")
    }
}
