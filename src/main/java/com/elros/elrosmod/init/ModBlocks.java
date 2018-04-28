package com.elros.elrosmod.init;

import java.util.ArrayList;
import java.util.List;

import com.elros.elrosmod.blocks.basic.BlockBase;
import com.elros.elrosmod.blocks.basic.ColdIce;
import com.elros.elrosmod.blocks.basic.HierosStone;
import com.elros.elrosmod.blocks.basic.SlabBlockMaster;
import com.elros.elrosmod.blocks.derived.DoubleSlabBlock;
import com.elros.elrosmod.blocks.derived.HalfSlabBlock;
import com.elros.elrosmod.blocks.derived.StairBlock;
import com.elros.elrosmod.blocks.fire.ColdFire;
import com.elros.elrosmod.blocks.machines.SinteringFurnace;
import com.elros.elrosmod.blocks.natural.SaplingBlock;
import com.elros.elrosmod.blocks.derived.SlabBlock;

import com.elros.elrosmod.blocks.natural.StarGrassBlock;
import com.elros.elrosmod.blocks.portal.CaelesPortalBlock;
import com.elros.elrosmod.blocks.types.EnumType;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;

public class ModBlocks 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();

	
	public static final EnumType TYPES = new EnumType();

	public static final Block TREE_SAPLING_BLOCK  = new SaplingBlock("tree_sapling_block", Material.PLANTS);
	
	public static final Block METEOR_ORE_BLOCK   = new BlockBase("meteor_ore_block", Material.GROUND).setHardness(1.5F).setResistance(10.0F);

	public static final Block COLD_STONE_BLOCK   = new ColdIce("cold_stone_block", Material.PACKED_ICE).setHardness(1.5F).setResistance(10.0F);

	public static final Block HIEROS_STONE_BLOCK   = new HierosStone("hieros_stone_block", Material.GLASS).setHardness(0.3F);
	
	public static final Block NUBE_STONE_BLOCK   = new BlockBase("nube_stone_block", Material.GROUND).setHardness(1.5F).setResistance(10.0F);
	public static final Block NUBE_BRICK_BLOCK    = new BlockBase("nube_brick_block", Material.GROUND).setHardness(1.5F).setResistance(10.0F);
	public static final Block NUBE_BRICK_STAIR_BLOCK    = new StairBlock("nube_brick_stairs_block", NUBE_BRICK_BLOCK.getDefaultState()).setHardness(1.5F).setResistance(10.0F);
	public static com.elros.elrosmod.blocks.derived.SlabBlock  SlabBlock = new SlabBlock();
				
	public static final Block STAR_GRASS_BLOCK    = new StarGrassBlock("star_grass_block", Material.GRASS).setHardness(1.5F).setResistance(10.0F);

	public static final CaelesPortalBlock CAELES_PORTAL_BLOCK = new CaelesPortalBlock("caeles_portal_block", Material.GRASS);
	
	public static final ColdFire COLD_FIRE_BLOCK = new ColdFire("cold_fire_block", Material.GRASS);
	
	///////////////////////////////////////////////////////////
	
	public static final Block SINTERING_FURNACE = new SinteringFurnace("sintering_furnace", Material.GRASS);
		
}
