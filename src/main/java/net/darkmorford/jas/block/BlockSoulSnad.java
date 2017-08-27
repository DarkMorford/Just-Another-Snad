package net.darkmorford.jas.block;

import net.darkmorford.jas.JustAnotherSnad;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import org.apache.logging.log4j.Level;

import java.util.Random;

public class BlockSoulSnad extends BlockSoulSand {

	public BlockSoulSnad() {
		super();
		setCreativeTab(CreativeTabs.MISC);
		setHardness(0.5f);
		setSoundType(SoundType.SAND);
		setTickRandomly(true);
		setUnlocalizedName("soul_snad");
		setRegistryName(JustAnotherSnad.MODID, "soul_snad");
	}


	@Override
	public void updateTick(World world, BlockPos position, IBlockState state, Random random) {
		JustAnotherSnad.logger.log(Level.INFO, String.format("Update Tick Soul Snad: %s", position.toString()));
		super.updateTick(world, position, state, random);
		Block blockAbove = world.getBlockState(position.up()).getBlock();
		if (blockAbove instanceof BlockNetherWart) {
			boolean isSameBlockType = true;
			for (int growthAttempts = 0; growthAttempts < 5; growthAttempts++) { // TODO: Configurable
				if (growthAttempts == 0 || canSustainPlant(world.getBlockState(position), world, position, null, (IPlantable) blockAbove)) {
					blockAbove.updateTick(world, position.up(), world.getBlockState(position.up()), random);
				}
			}
		}
	}



	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos position, EnumFacing facing, IPlantable plantable) {
		BlockPos plantPosition = new BlockPos(position.getX(), position.getY() + 1, position.getZ());
		EnumPlantType plantType = plantable.getPlantType(world, plantPosition);
		return plantType == EnumPlantType.Nether;
	}
}
