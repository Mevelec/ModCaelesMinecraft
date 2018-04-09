package com.elros.elrosmod.blocks.portal;

import java.util.Random;

import javax.annotation.Nullable;

import com.elros.elrosmod.Main;
import com.elros.elrosmod.blocks.ModBlocks;
import com.elros.elrosmod.blocks.TileEntityDim;
import com.elros.elrosmod.init.ModItems;
import com.elros.elrosmod.util.IHasModel;
import com.google.common.cache.LoadingCache;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CaelesPortalBlock extends BlockPortal implements IHasModel
{

	public static String name;

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        EnumFacing.Axis enumfacing$axis = (EnumFacing.Axis)state.getValue(AXIS);

        if (enumfacing$axis == EnumFacing.Axis.X)
        {
        	CaelesPortalBlock.Size caelesportalblock$size = new CaelesPortalBlock.Size(worldIn, pos, EnumFacing.Axis.X);

            if (!caelesportalblock$size.isValid() || caelesportalblock$size.portalBlockCount < caelesportalblock$size.getWidth() * caelesportalblock$size.getHeight())
            {
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
        else if (enumfacing$axis == EnumFacing.Axis.Z)
        {
        	CaelesPortalBlock.Size caelesportalblock$size1 = new CaelesPortalBlock.Size(worldIn, pos, EnumFacing.Axis.Z);

            if (!caelesportalblock$size1.isValid() || caelesportalblock$size1.portalBlockCount < caelesportalblock$size1.width * caelesportalblock$size1.height)
            {
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
    }
    
	public CaelesPortalBlock(String name, Material material)
	{	

		super();
		this.name = name;
		this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.X));
		this.setTickRandomly(true);

		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));

	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		super.updateTick(worldIn, pos, state, rand);

		if (worldIn.provider.isSurfaceWorld() && worldIn.getGameRules().getBoolean("doMobSpawning") && rand.nextInt(2000) < worldIn.getDifficulty().getDifficultyId())
		{
			int i = pos.getY();
			BlockPos blockpos;

			for (blockpos = pos; !worldIn.getBlockState(blockpos).isBlockNormalCube() && blockpos.getY() > 0; blockpos = blockpos.down())
			{
				;
			}

//			if (i > 0 && !worldIn.getBlockState(blockpos.up()).isNormalCube())
//			{
//				Entity entity = ItemMonsterPlacer.spawnCreature(worldIn, EntityList.getID(EntityBlaze.class), (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 1.1D, (double)blockpos.getZ() + 0.5D);
//
//				if (entity != null)
//				{
//					entity.timeUntilPortal = entity.getPortalCooldown();
//				}
//			}
		}
	}

	public boolean trySpawnPortal(World worldIn, BlockPos pos)
	{
		CaelesPortalBlock.Size caelesportalblock$size = new CaelesPortalBlock.Size(worldIn, pos, EnumFacing.Axis.X);
		if (caelesportalblock$size.isValid() && caelesportalblock$size.portalBlockCount == 0)
		{
			caelesportalblock$size.placePortalBlocks();
			return true;
		}
		else
		{
			CaelesPortalBlock.Size blockportal$size1 = new CaelesPortalBlock.Size(worldIn, pos, EnumFacing.Axis.Z);

			if (blockportal$size1.isValid() && blockportal$size1.portalBlockCount == 0)
			{
				blockportal$size1.placePortalBlocks();
				return true;
			}
			else
			{
				return false;
			}
		}
	}

	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	{	

		if ((entityIn.getRidingEntity() == null) && ((entityIn instanceof EntityPlayerMP)))
		{

			EntityPlayerMP player1 = (EntityPlayerMP)entityIn;

			TileEntityDim.tele(player1);

		}

	}

	public static class Size
	{
		private final World world;
		private final EnumFacing.Axis axis;
		private final EnumFacing rightDir;
		private final EnumFacing leftDir;
		private int portalBlockCount;
		private BlockPos bottomLeft;
		private int height;
		private int width;

		public Size(World worldIn, BlockPos p_i45694_2_, EnumFacing.Axis p_i45694_3_)
		{
			this.world = worldIn;
			this.axis = p_i45694_3_;

			if (p_i45694_3_ == EnumFacing.Axis.X)
			{
				this.leftDir = EnumFacing.EAST;
				this.rightDir = EnumFacing.WEST;
			}
			else
			{
				this.leftDir = EnumFacing.NORTH;
				this.rightDir = EnumFacing.SOUTH;
			}

			for (BlockPos blockpos = p_i45694_2_; p_i45694_2_.getY() > blockpos.getY() - 21 && p_i45694_2_.getY() > 0 && this.isEmptyBlock(worldIn.getBlockState(p_i45694_2_.down()).getBlock()); p_i45694_2_ = p_i45694_2_.down())
			{
				;
			}

			int i = this.getDistanceUntilEdge(p_i45694_2_, this.leftDir) - 1;

			if (i >= 0)
			{
				this.bottomLeft = p_i45694_2_.offset(this.leftDir, i);
				this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);

				if (this.width < 2 || this.width > 21)
				{
					this.bottomLeft = null;
					this.width = 0;
				}
			}

			if (this.bottomLeft != null)
			{
				this.height = this.calculatePortalHeight();
			}
		}

		protected int getDistanceUntilEdge(BlockPos p_180120_1_, EnumFacing p_180120_2_)
		{
			int i;

			for (i = 0; i < 22; ++i)
			{
				BlockPos blockpos = p_180120_1_.offset(p_180120_2_, i);

				if (!this.isEmptyBlock(this.world.getBlockState(blockpos).getBlock()) || this.world.getBlockState(blockpos.down()).getBlock() != Blocks.QUARTZ_BLOCK)
				{
					break;
				}
			}

			Block block = this.world.getBlockState(p_180120_1_.offset(p_180120_2_, i)).getBlock();
			return block == Blocks.QUARTZ_BLOCK ? i : 0;
		}

		public int getHeight()
		{
			return this.height;
		}

		public int getWidth()
		{
			return this.width;
		}

		protected int calculatePortalHeight()
		{
			label24:

				for (this.height = 0; this.height < 21; ++this.height)
				{
					for (int i = 0; i < this.width; ++i)
					{
						BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
						Block block = this.world.getBlockState(blockpos).getBlock();

						if (!this.isEmptyBlock(block))
						{
							break label24;
						}

						if (block == ModBlocks.CAELES_PORTAL_BLOCK)
						{
							++this.portalBlockCount;
						}

						if (i == 0)
						{
							block = this.world.getBlockState(blockpos.offset(this.leftDir)).getBlock();

							if (block != Blocks.QUARTZ_BLOCK)
							{
								break label24;
							}
						}
						else if (i == this.width - 1)
						{
							block = this.world.getBlockState(blockpos.offset(this.rightDir)).getBlock();

							if (block != Blocks.QUARTZ_BLOCK)
							{
								break label24;
							}
						}
					}
				}

		for (int j = 0; j < this.width; ++j)
		{
			if (this.world.getBlockState(this.bottomLeft.offset(this.rightDir, j).up(this.height)).getBlock() != Blocks.QUARTZ_BLOCK)
			{
				this.height = 0;
				break;
			}
		}

		if (this.height <= 21 && this.height >= 3)
		{
			return this.height;
		}
		else
		{
			this.bottomLeft = null;
			this.width = 0;
			this.height = 0;
			return 0;
		}
		}

		protected boolean isEmptyBlock(Block blockIn)
		{
			return blockIn.getMaterial(null) == Material.AIR || blockIn == ModBlocks.COLD_FIRE_BLOCK || blockIn == ModBlocks.CAELES_PORTAL_BLOCK;
		}

		public boolean isValid()
		{
			return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
		}

		public void placePortalBlocks()
		{
			for (int i = 0; i < this.width; ++i)
			{
				BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);
				for (int j = 0; j < this.height; ++j)
				{
					boolean a = this.world.setBlockState(blockpos.up(j), ModBlocks.CAELES_PORTAL_BLOCK.getDefaultState().withProperty(CaelesPortalBlock.AXIS, this.axis), 2);
				}
			}
		}
	}
}