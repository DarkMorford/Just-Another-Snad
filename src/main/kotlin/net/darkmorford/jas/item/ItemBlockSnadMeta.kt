package net.darkmorford.jas.item

import net.minecraft.block.Block
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack

class ItemBlockSnadMeta(block: Block) : ItemBlock(block) {
	init {
		if (block !is IMetaBlockSnad) {
			throw IllegalArgumentException(String.format(
					"The given Block %s is not an instance of IMetaBlockSnad!",
					block.unlocalizedName))
		}
		maxDamage = 0
		hasSubtypes = true
	}

	override fun getMetadata(damage: Int): Int {
		return damage
	}

	override fun getUnlocalizedName(stack: ItemStack): String {
		return "${super.getUnlocalizedName(stack)}.${(block as IMetaBlockSnad).getSpecialName(stack)}"
	}
}


