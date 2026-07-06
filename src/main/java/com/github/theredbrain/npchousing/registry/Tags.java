package com.github.theredbrain.npchousing.registry;

import com.github.theredbrain.npchousing.NPCHousing;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class Tags {
	public static final TagKey<Block> WALL_BLOCKS = TagKey.create(Registries.BLOCK, NPCHousing.identifier("wall_blocks"));
	public static final TagKey<Block> FLOOR_BLOCKS = TagKey.create(Registries.BLOCK, NPCHousing.identifier("floor_blocks"));
	public static final TagKey<Block> LIGHT_BLOCKS = TagKey.create(Registries.BLOCK, NPCHousing.identifier("light_blocks"));
	public static final TagKey<Block> CHAIR_BLOCKS = TagKey.create(Registries.BLOCK, NPCHousing.identifier("chair_blocks"));
	public static final TagKey<Block> TABLE_BLOCKS = TagKey.create(Registries.BLOCK, NPCHousing.identifier("table_blocks"));
}
