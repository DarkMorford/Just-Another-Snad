package net.darkmorford.jas.item

import net.minecraft.item.ItemStack

interface IMetaBlockSnad {
	fun getSpecialName(stack: ItemStack): String
}
