package com.elros.elrosmod.world;

import java.util.ArrayList;
import java.util.List;

import com.elros.elrosmod.world.test.TestWorldProvider;

import net.minecraft.block.Block;
import net.minecraft.world.DimensionType;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldProviderSurface;
import net.minecraftforge.common.DimensionManager;

public class ModDimensions 
{

    public static DimensionType testDimensionType;
    public static final DimensionType TEST_DIMENSION = DimensionType.register("TEST", "_test", 3, WorldProviderSurface.class, false);

    public static void init() {
        registerDimensions();
    }

    private static void registerDimensions() {
      DimensionManager.registerDimension(3, TEST_DIMENSION);
    }
    
}