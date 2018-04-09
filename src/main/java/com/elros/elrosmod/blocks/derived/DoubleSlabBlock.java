package com.elros.elrosmod.blocks.derived;

import com.elros.elrosmod.blocks.basic.SlabBlockMaster;

import net.minecraft.block.material.Material;

public class DoubleSlabBlock extends  SlabBlockMaster
{

	
	 public DoubleSlabBlock(String name, Material modelState) {
		super(name, modelState);
		// TODO Auto-generated constructor stub
	}

	@Override
	 public final boolean isDouble() 
	 {
	        return true;
	 }
}
