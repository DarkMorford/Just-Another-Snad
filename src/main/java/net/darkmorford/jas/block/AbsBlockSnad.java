package net.darkmorford.jas.block;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class AbsBlockSnad extends BlockFalling {
	enum EnumType implements IStringSerializable {
		SNAD(0, "snad", "default", MapColor.SAND, -2370656),
		RED_SNAD(1, "red_snad", "red", MapColor.ADOBE, -5679071);

		private static final EnumType[] META_LOOKUP = new EnumType[values().length];

		static {
			for (EnumType blockType : values()) {
				META_LOOKUP[blockType.getMetadata()] = blockType;
			}
		}

		private final int meta;
		private final String name;
		private final MapColor mapColor;
		private final String unlocalizedName;
		private final int dustColor;

		EnumType(int meta, String name, String unlocalizedName, MapColor mapColor, int dustColor) {
			this.meta = meta;
			this.name = name;
			this.unlocalizedName = unlocalizedName;
			this.mapColor = mapColor;
			this.dustColor = dustColor;
		}

		public static EnumType byMetadata(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}

			return META_LOOKUP[meta];
		}

		@SideOnly(Side.CLIENT)
		public int getDustColor() {
			return this.dustColor;
		}

		public int getMetadata() {
			return this.meta;
		}

		public String toString() {
			return this.name;
		}

		public MapColor getMapColor() {
			return this.mapColor;
		}

		@Override
		public String getName() {
			return name;
		}

		public String getUnlocalizedName() {
			return unlocalizedName;
		}
	}
}
