package net.darkmorford.jas.block;

import net.darkmorford.jas.JustAnotherSnad;
import net.darkmorford.jas.configuration.ConfigurationData;
import net.minecraft.block.Block;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.BlockSoulSand;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

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
    public void randomTick(World world, BlockPos position, IBlockState state, Random random) {
        super.randomTick(world, position, state, random);
        Block blockAbove = world.getBlockState(position.up()).getBlock();
        if (blockAbove instanceof BlockNetherWart) {
            for (int growthAttempts = 0; growthAttempts < ConfigurationData.SOUL_SNAD_SPEED_INCREASE_VALUE; growthAttempts++) {
                if (growthAttempts == 0 || canSustainPlant(world.getBlockState(position), world, position, null, (IPlantable) blockAbove)) {
                    blockAbove.randomTick(world, position.up(), world.getBlockState(position.up()), random);
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
