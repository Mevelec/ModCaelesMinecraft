package com.elros.elrosmod.blocks.derived;


import com.elros.elrosmod.Main;
import com.elros.elrosmod.init.ModBlocks;
import com.elros.elrosmod.init.ModItems;
import com.elros.elrosmod.util.IHasModel;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class StairBlock extends BlockStairs implements IHasModel
{

	public StairBlock(String name, IBlockState modelState) {
		super(modelState);
		setUnlocalizedName(name);
		setRegistryName(name);
		
		setCreativeTab(Main.tabCaelesMod);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
}