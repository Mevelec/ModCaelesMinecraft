package com.elros.elrosmod.blocks.fire;

import com.elros.elrosmod.Main;
import com.elros.elrosmod.blocks.ModBlocks;
import com.elros.elrosmod.init.ModItems;
import com.elros.elrosmod.util.IHasModel;

import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ColdFire extends BlockFire implements IHasModel
{

	public static String name;

	public ColdFire(String name, Material material)
	{
		super();
		this.name = name;
		this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)).withProperty(UPPER, Boolean.valueOf(false)));
		this.setTickRandomly(true);
		setUnlocalizedName(name);
		setRegistryName(name);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");		
	}

	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		if (!ModBlocks.CAELES_PORTAL_BLOCK.trySpawnPortal(worldIn, pos))
		{
			if (!worldIn.getBlockState(pos.down()).isOpaqueCube() && !this.canNeighborCatchFire(worldIn, pos))
			{
				worldIn.setBlockToAir(pos);
			}
			else
			{
				worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn) + worldIn.rand.nextInt(10));
			}
		}
	}

	private boolean canNeighborCatchFire(World worldIn, BlockPos pos)
	{
		for (EnumFacing enumfacing : EnumFacing.values())
		{
			if (this.canCatchFire(worldIn, pos.offset(enumfacing), enumfacing.getOpposite()))
			{
				return true;
			}
		}

		return false;
	}

	public boolean canCatchFire(IBlockAccess world, BlockPos pos, EnumFacing face)
	{
		return world.getBlockState(pos).getBlock().isFlammable(world, pos, face);
	}


}