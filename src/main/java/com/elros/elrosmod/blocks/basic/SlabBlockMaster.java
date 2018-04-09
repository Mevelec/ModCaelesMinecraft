package com.elros.elrosmod.blocks.basic;

import java.util.Random;

import com.elros.elrosmod.Main;
import com.elros.elrosmod.blocks.ModBlocks;
import com.elros.elrosmod.blocks.derived.SlabBlock;
import com.elros.elrosmod.blocks.types.EnumType;
import com.elros.elrosmod.init.ModItems;
import com.elros.elrosmod.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPurpurSlab;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public abstract class SlabBlockMaster extends BlockSlab implements IHasModel
{
    public static final PropertyEnum<SlabBlockMaster.Variant> VARIANT = PropertyEnum.<SlabBlockMaster.Variant>create("variant", SlabBlockMaster.Variant.class);

    public SlabBlockMaster(String name, Material modelState)
    {
        super(Material.ROCK, MapColor.MAGENTA);
        
        IBlockState iblockstate = this.blockState.getBaseState();
        
        setUnlocalizedName(name);
		setRegistryName(name);
		
    	//ModBlocks.BLOCKS.add(this);
    	//ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    	
    	IBlockState state = this.blockState.getBaseState();
        if (!this.isDouble())
        {
            iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        }
        
        setDefaultState(state);
        //this.setDefaultState(iblockstate.withProperty(VARIANT, SlabBlock.Variant.DEFAULT));
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        
	}
	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(SlabBlock.NUBE_BRICK_SLAB_BLOCK);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(SlabBlock.NUBE_BRICK_SLAB_BLOCK);
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, SlabBlockMaster.Variant.DEFAULT);

        if (!this.isDouble())
        {
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }

        return iblockstate;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
        {
            i |= 8;
        }

        return i;
    }

    protected BlockStateContainer createBlockState()
    {
        return this.isDouble() ? new BlockStateContainer(this, new IProperty[] {VARIANT}) : new BlockStateContainer(this, new IProperty[] {HALF, VARIANT});
    }

    /**
     * Returns the slab block name with the type associated with it
     */
    public String getUnlocalizedName(int meta)
    {
        return super.getUnlocalizedName();
    }

    public IProperty<?> getVariantProperty()
    {
        return VARIANT;
    }

    public Comparable<?> getTypeForItem(ItemStack stack)
    {
        return SlabBlockMaster.Variant.DEFAULT;
    }

    public static class Double extends BlockPurpurSlab
    {	

		public boolean isDouble()
        {
            return true;
        }
    }

    public static class Half extends BlockPurpurSlab
        {
			public boolean isDouble()
            {
                return false;
            }
        }

    public static enum Variant implements IStringSerializable
    {
        DEFAULT;
        public String getName()
        {
            return "default";
        }
    }
}
