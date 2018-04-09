package com.elros.elrosmod.world.test;

import com.elros.elrosmod.world.ModDimensions;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.DimensionManager;

public class TestWorldProvider extends WorldProvider
{ 
	public void registerWorldChunkManager()
	{
		this.biomeProvider = new BiomeProviderSingle(Biomes.VOID);
		this.setDimension(3);
		this.setAllowedSpawnTypes(false, false);
		//this.hasNoSky = false;
		
		DimensionManager.registerDimension(3, ModDimensions.TEST_DIMENSION);
	}

	@Override
	public IChunkGenerator createChunkGenerator()
	{
		return new TestChunkGenerator(this.world);
	}

	public Biome getBiomeGenForCoords(BlockPos pos)
	{
		return Biomes.VOID;
	}

	@Override
	public boolean canRespawnHere()
	{
		return true;
	}

	@Override
	public int getRespawnDimension(EntityPlayerMP player)
	{
		return 3;
	}

	@Override
	public boolean isSurfaceWorld()
	{
		return true;
	}

	@Override
	public DimensionType getDimensionType() 
	{
		return ModDimensions.TEST_DIMENSION;
	}
}