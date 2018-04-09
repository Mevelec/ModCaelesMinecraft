package com.elros.elrosmod.init;

import java.util.ArrayList;
import java.util.List;

import com.elros.elrosmod.items.ItemBase;
import com.elros.elrosmod.items.ColdLighter;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;

public class ModItems 
{

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item COLD_LIGHTER_ITEM = new ColdLighter("cold_lighter_item");
	
	public static final Item HIEROS_STONE_DUST_ITEM = new ItemBase("hieros_stone_dust_item");
	
	public static final Item METEORITE_INGOT_ITEM = new ItemBase("meteorite_ingot_item");
	
}
