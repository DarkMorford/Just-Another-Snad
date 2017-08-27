package net.darkmorford.jas.block;

import net.darkmorford.jas.JustAnotherSnad;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class BlockSnad extends BlockSand {

	public BlockSnad() {
		super();
		setCreativeTab(CreativeTabs.MISC);
		setHardness(0.5f);
		setSoundType(SoundType.SAND);
		setTickRandomly(true);
		setUnlocalizedName("snad");
		setRegistryName(JustAnotherSnad.MODID, "snad");
	}

	@Override
	public void updateTick(World world, BlockPos position, IBlockState state, Random random) {
		super.updateTick(world, position, state, random);
		Block blockAbove = world.getBlockState(position.up()).getBlock();
		if (blockAbove instanceof BlockReed || blockAbove instanceof BlockCactus) {
			boolean isSameBlockType = true;
			int height = 1;
			while (isSameBlockType) {
				Block nextPlantBlock = world.getBlockState(position.up(height)).getBlock();
				if (nextPlantBlock.getClass() == blockAbove.getClass()) {
					for (int growthAttempts = 0; growthAttempts < 2; growthAttempts++) {
						if (growthAttempts == 0 || canSustainPlant(world.getBlockState(position), world, position, null, (IPlantable) blockAbove)) {
							nextPlantBlock.updateTick(world, position.up(height), world.getBlockState(position.up(height)), random);
						}
					}
					height++;
				} else {
					isSameBlockType = false;
				}
			}
		} else if (blockAbove instanceof IPlantable) {
			blockAbove.updateTick(world, position.up(), world.getBlockState(position.up()), random);
		}
	}

	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos position, EnumFacing facing, IPlantable plantable) {
		BlockPos plantPosition = new BlockPos(position.getX(), position.getY() + 1, position.getZ());
		EnumPlantType plantType = plantable.getPlantType(world, plantPosition);
		switch (plantType) {
			case Desert:
				return true;
			case Beach:
				return ((world.getBlockState(new BlockPos(position.getX() - 1, position.getY(), position.getZ())).getMaterial() == Material.WATER) ||
						(world.getBlockState(new BlockPos(position.getX() + 1, position.getY(), position.getZ())).getMaterial() == Material.WATER) ||
						(world.getBlockState(new BlockPos(position.getX(), position.getY(), position.getZ() + 1)).getMaterial() == Material.WATER) ||
						(world.getBlockState(new BlockPos(position.getX(), position.getY(), position.getZ() - 1)).getMaterial() == Material.WATER));

			case Water:
				return (world.getBlockState(position).getMaterial() == Material.WATER) && (world.getBlockState(position) == getDefaultState());
			default: {
				return false;
			}
		}
	}
}
