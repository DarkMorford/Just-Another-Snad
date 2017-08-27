package net.darkmorford.jas.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockSoulSnadMeta extends ItemBlock {
	public ItemBlockSoulSnadMeta(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(false);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return String.format("%s.%s", super.getUnlocalizedName(stack), (block.getUnlocalizedName()));
	}
}
