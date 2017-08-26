package net.darkmorford.jas.block

import net.darkmorford.jas.JustAnotherSnad
import net.darkmorford.jas.item.IMetaBlockSnad
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.common.EnumPlantType
import net.minecraftforge.common.IPlantable

class BlockSnad : AbsBlockSnad(), IMetaBlockSnad {

	override fun getSpecialName(stack: ItemStack): String {
		return if (stack.itemDamage == 0) "default" else "red"
	}

	override fun createBlockState(): BlockStateContainer {
		return BlockStateContainer(this, VARIANT)
	}

	override fun getStateFromMeta(meta: Int): IBlockState {
		return defaultState.withProperty(VARIANT, EnumType.byMetadata(meta))
	}

	override fun getMetaFromState(state: IBlockState): Int {
		return state.getValue(VARIANT).metadata
	}

	override fun canSustainPlant(state: IBlockState, world: IBlockAccess, position: BlockPos, facing : EnumFacing, plantable: IPlantable): Boolean {
		val plantPosition = BlockPos(position.x, position.y + 1, position.z)
		val plantType = plantable.getPlantType(world, plantPosition)

		return when (plantType) {
			EnumPlantType.Desert -> true
			EnumPlantType.Water -> (world.getBlockState(position).material  == Material.WATER) && (world.getBlockState(position) == defaultState)
			EnumPlantType.Beach -> (
					(world.getBlockState(BlockPos(position.x - 1, position.y, position.z)).material == Material.WATER) ||
							(world.getBlockState(BlockPos(position.x + 1, position.y, position.z)).material == Material.WATER) ||
							(world.getBlockState(BlockPos(position.x, position.y, position.z + 1)).material == Material.WATER) ||
							(world.getBlockState(BlockPos(position.x, position.y, position.z - 1)).material == Material.WATER)
					)
			else -> false
		}
	}

	init {
		setCreativeTab(CreativeTabs.MISC)
		setHardness(0.5f)
		soundType = SoundType.SAND
		tickRandomly = true

		unlocalizedName = "snad"
		defaultState = blockState.baseState.withProperty(VARIANT, EnumType.SNAD)
		setRegistryName(JustAnotherSnad.MODID, "snad")
	}

	companion object {
		private val snadClass = EnumType::class
		private val snadJavaClass = snadClass.java
		private val VARIANT: PropertyEnum<AbsBlockSnad.EnumType> = PropertyEnum.create("variant", snadJavaClass)
	}


}
