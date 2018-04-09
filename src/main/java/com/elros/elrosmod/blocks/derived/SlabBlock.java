package com.elros.elrosmod.blocks.derived;

import com.elros.elrosmod.blocks.ModBlocks;
import com.elros.elrosmod.blocks.basic.SlabBlockMaster;
import com.elros.elrosmod.init.ModItems;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SlabBlock 
{
	public static final SlabBlockMaster NUBE_BRICK_SLAB_BLOCK          = (SlabBlockMaster) new HalfSlabBlock("nube_brick_slab_block", Material.GROUND).setUnlocalizedName("nube_brick_slab_block");
	public static final SlabBlockMaster NUBE_BRICK_DOUBLESLAB_BLOCK    = (SlabBlockMaster) new DoubleSlabBlock("nube_brick_doubleslab_block", Material.GROUND).setUnlocalizedName("nube_brick_slab_block");

	public SlabBlock()
	{
		ModBlocks.BLOCKS.add(NUBE_BRICK_SLAB_BLOCK);
		ModBlocks.BLOCKS.add(NUBE_BRICK_DOUBLESLAB_BLOCK);
		
		ItemBlock item = new ItemSlab(NUBE_BRICK_SLAB_BLOCK, NUBE_BRICK_SLAB_BLOCK, NUBE_BRICK_DOUBLESLAB_BLOCK);
		item.setRegistryName(NUBE_BRICK_SLAB_BLOCK.getRegistryName());
		ModItems.ITEMS.add(item);
	}
}
