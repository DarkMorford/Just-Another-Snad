package net.darkmorford.jas.block

import net.darkmorford.jas.JustAnotherSnad
import net.darkmorford.jas.item.IMetaBlockSnad
import net.minecraft.block.BlockCactus
import net.minecraft.block.BlockReed
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.EnumPlantType
import net.minecraftforge.common.IPlantable
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

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

	override fun getPickBlock(state: IBlockState?, target: RayTraceResult?, world: World, position: BlockPos?, player: EntityPlayer?): ItemStack {
		return ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(position)))
	}

	@SideOnly(Side.CLIENT)
	override fun getDustColor(state: IBlockState): Int {
		return state.getValue(VARIANT).dustColor
	}

	override fun getMapColor(state: IBlockState, world: IBlockAccess?, postion: BlockPos?): MapColor {
		return state.getValue(VARIANT).mapColor
	}

	override fun updateTick(world: World, position: BlockPos, state: IBlockState, random: Random) {
		super.updateTick(world, position, state, random)

		val blockAbove = world.getBlockState(position.up()).block
		if (blockAbove is BlockReed || blockAbove is BlockCactus) {
			var isSameBlockType = true
			var height = 1

			while (isSameBlockType) {
				if (world.getBlockState(position.up(height)).block != null) {
					val nextPlantBlock = world.getBlockState(position.up(height)).block
					if (nextPlantBlock::class.java == blockAbove::class.java) {
						for (growthAttempts in 0..2) { //TODO: Make Configurable
							if (growthAttempts == 0 || canSustainPlant(world.getBlockState(position), world, position, null, blockAbove as IPlantable)) {
								nextPlantBlock.updateTick(world, position.up(height), world.getBlockState(position.up(height)), random)
							}
						}
						height++
					} else {
						isSameBlockType = false
					}
				} else {
					isSameBlockType = false
				}
			}
		} else if (blockAbove is IPlantable) {
			blockAbove.updateTick(world, position.up(), world.getBlockState(position.up()), random)
		}
	}

	override fun canSustainPlant(state: IBlockState, world: IBlockAccess, position: BlockPos, facing: EnumFacing?, plantable: IPlantable): Boolean {
		val plantPosition = BlockPos(position.x, position.y + 1, position.z)
		val plantType = plantable.getPlantType(world, plantPosition)

		return when (plantType) {
			EnumPlantType.Desert -> true
			EnumPlantType.Water -> (world.getBlockState(position).material == Material.WATER) && (world.getBlockState(position) == defaultState)
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
		private val VARIANT: PropertyEnum<AbsBlockSnad.EnumType> = PropertyEnum.create("variant", EnumType::class.java)
	}


}
