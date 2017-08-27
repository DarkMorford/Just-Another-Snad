package net.darkmorford.jas.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockSnadMeta extends ItemBlock {

	public ItemBlockSnadMeta(Block block) {
		super(block);
		if (!(block instanceof IMetaBlockSnad)) {
			throw new IllegalArgumentException(String.format("The given Block %s is not an instance of IMetaBlockSnad!",
					block.getUnlocalizedName()));
		}
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return String.format("%s.%s", super.getUnlocalizedName(stack), ((IMetaBlockSnad) block).getSpecialName(stack));
	}
}
