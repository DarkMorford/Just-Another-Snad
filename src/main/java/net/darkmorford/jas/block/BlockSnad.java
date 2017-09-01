package net.darkmorford.jas.block;

import net.darkmorford.jas.configuration.ConfigurationData;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

import static net.darkmorford.jas.JustAnotherSnad.MODID;

public class BlockSnad extends BlockSand {

    public BlockSnad() {
        super();
        setCreativeTab(CreativeTabs.MISC);
        setHardness(0.5f);
        setSoundType(SoundType.SAND);
        setTickRandomly(true);
        setUnlocalizedName("snad");
        setRegistryName(MODID, "snad");
    }

    @Override
    public void randomTick(World world, BlockPos position, IBlockState state, Random random) {
        super.randomTick(world, position, state, random);

        Block blockAbove = world.getBlockState(position.up()).getBlock();
        if (blockAbove instanceof BlockReed || blockAbove instanceof BlockCactus) {
            checkColumnOfPlant(world, position, random, blockAbove);
        } else if (blockAbove instanceof IPlantable) {
            blockAbove.randomTick(world, position.up(), world.getBlockState(position.up()), random);
        }
    }

    private void checkColumnOfPlant(World world, BlockPos position, Random random, Block blockAbove) {
        boolean isSameBlockType = true;
        int height = 1;
        while (isSameBlockType) {
            Block nextPlantBlock = world.getBlockState(position.up(height)).getBlock();
            if (nextPlantBlock.getClass().equals(blockAbove.getClass())) {
                growthLoop(world, position, random, (IPlantable) blockAbove, height, nextPlantBlock);
                height++;
            } else {
                isSameBlockType = false;
            }
        }
    }

    private void growthLoop(World world, BlockPos position, Random random, IPlantable blockAbove, int height, Block nextPlantBlock) {
        for (int growthAttempts = 0; growthAttempts < ConfigurationData.SNAD_SPEED_INCREASE_VALUE; growthAttempts++) {
            if (growthAttempts == 0 || canSustainPlant(world.getBlockState(position), world, position, null, blockAbove)) {
                nextPlantBlock.randomTick(world, position.up(height), world.getBlockState(position.up(height)), random);
            }
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
