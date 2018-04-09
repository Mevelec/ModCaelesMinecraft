package com.elros.elrosmod.blocks.basic;

import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.google.common.collect.Lists;

import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class ColdIce extends BlockBase
{

	public ColdIce(String name, Material material) {
		super(name, material);
		this.slipperiness = 0.98F;
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}    

	/**
	 * Called after the block is set in the Chunk data, but before the Tile Entity is set
	 */
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		System.out.println("added"	);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Queue<Tuple<BlockPos, Integer>> queue = Lists.<Tuple<BlockPos, Integer>>newLinkedList();
		        List<BlockPos> list = Lists.<BlockPos>newArrayList();
		        queue.add(new Tuple(pos, Integer.valueOf(0)));
		        int i = 0;

		        while (!queue.isEmpty())
		        {
		            Tuple<BlockPos, Integer> tuple = (Tuple)queue.poll();
		            BlockPos blockpos = tuple.getFirst();
		            int j = ((Integer)tuple.getSecond()).intValue();

		            for (EnumFacing enumfacing : EnumFacing.values())
		            {
		                BlockPos blockpos1 = blockpos.offset(enumfacing);

		                if (worldIn.getBlockState(blockpos1).getMaterial() == Material.WATER)
		                {
		                    worldIn.setBlockState(blockpos1, Blocks.ICE.getDefaultState(), 2);
		                    list.add(blockpos1);
		                    ++i;

		                    if (j < 6)
		                    {
		                        queue.add(new Tuple(blockpos1, j + 1));
		                    }
		                }
		            }

		            if (i > 64)
		            {
		                break;
		            }
		        }
				
				i++;
			}
		}, 1000);
	}

	public boolean freezBlock(World worldIn, BlockPos pos)
	{
		boolean r = false;
		if(worldIn.getBlockState(pos).getMaterial() == Material.WATER)
		{
			worldIn.setBlockState(pos, Blocks.ICE.getDefaultState(), 2);
		}
		return r;
		
	}
}
