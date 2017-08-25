package net.darkmorford.jas.block

import net.darkmorford.jas.JustAnotherSnad
import net.minecraft.block.BlockSand
import net.minecraft.block.SoundType
import net.minecraft.creativetab.CreativeTabs

class BlockSnad : BlockSand() {
    init {
        setCreativeTab(CreativeTabs.MISC)
        setHardness(0.5f)
        soundType = SoundType.SAND
        tickRandomly = true

        unlocalizedName = "snad"
        setRegistryName(JustAnotherSnad.MODID, "snad")
    }
}
