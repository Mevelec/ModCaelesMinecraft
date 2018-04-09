package com.elros.elrosmod.blocks.types;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.IStringSerializable;

public class EnumType
{
    public static final PropertyEnum<EnumType.WoodEnumType> WOODVARIANT = PropertyEnum.<EnumType.WoodEnumType>create("variant", EnumType.WoodEnumType.class);
    public static final PropertyEnum<EnumType.StoneEnumType> STONEVARIANT = PropertyEnum.<EnumType.StoneEnumType>create("variant", EnumType.StoneEnumType.class);

    public static enum StoneEnumType implements IStringSerializable
    {
    	NUBE(0, MapColor.STONE, "nube");

        private static final EnumType.StoneEnumType[] META_LOOKUP = new EnumType.StoneEnumType[values().length];
        private final int meta;
        private final MapColor mapColor;
        private final String name;
        private final String unlocalizedName;

        private StoneEnumType(int p_i46381_3_, MapColor p_i46381_4_, String p_i46381_5_)
        {
            this(p_i46381_3_, p_i46381_4_, p_i46381_5_, p_i46381_5_);
        }

        private StoneEnumType(int p_i46382_3_, MapColor p_i46382_4_, String p_i46382_5_, String p_i46382_6_)
        {
            this.meta = p_i46382_3_;
            this.mapColor = p_i46382_4_;
            this.name = p_i46382_5_;
            this.unlocalizedName = p_i46382_6_;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public MapColor getMapColor()
        {
            return this.mapColor;
        }

        public String toString()
        {
            return this.name;
        }

        public static EnumType.StoneEnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
        }

        static
        {
            for (EnumType.StoneEnumType enumtype$StoneEnumType : values())
            {
                META_LOOKUP[enumtype$StoneEnumType.getMetadata()] = enumtype$StoneEnumType;
            }
        }
    }

	public static enum WoodEnumType implements IStringSerializable
    {
        OAKTEST(0, "oaktest", MapColor.WOOD);

        private static final EnumType.WoodEnumType[] META_LOOKUP = new EnumType.WoodEnumType[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;
        /** The color that represents this entry on a map. */
        private final MapColor mapColor;

        private WoodEnumType(int metaIn, String nameIn, MapColor mapColorIn)
        {
            this(metaIn, nameIn, nameIn, mapColorIn);
        }

        private WoodEnumType(int metaIn, String nameIn, String unlocalizedNameIn, MapColor mapColorIn)
        {
            this.meta = metaIn;
            this.name = nameIn;
            this.unlocalizedName = unlocalizedNameIn;
            this.mapColor = mapColorIn;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        /**
         * The color which represents this entry on a map.
         */
        public MapColor getMapColor()
        {
            return this.mapColor;
        }

        public String toString()
        {
            return this.name;
        }

        public static EnumType.WoodEnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
        }

        static
        {
            for (EnumType.WoodEnumType enumtype$WoodEnumType : values())
            {
                META_LOOKUP[enumtype$WoodEnumType.getMetadata()] = enumtype$WoodEnumType;
            }
        }
    }
}