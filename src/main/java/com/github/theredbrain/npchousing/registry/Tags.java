package com.github.theredbrain.npchousing.registry;

import com.github.theredbrain.npchousing.NPCHousing;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class Tags {
	public static final TagKey<Block> WALL_BLOCKS = TagKey.of(RegistryKeys.BLOCK, NPCHousing.identifier("wall_blocks"));
	public static final TagKey<Block> FLOOR_BLOCKS = TagKey.of(RegistryKeys.BLOCK, NPCHousing.identifier("floor_blocks"));
	public static final TagKey<Block> LIGHT_BLOCKS = TagKey.of(RegistryKeys.BLOCK, NPCHousing.identifier("light_blocks"));
	public static final TagKey<Block> CHAIR_BLOCKS = TagKey.of(RegistryKeys.BLOCK, NPCHousing.identifier("chair_blocks"));
	public static final TagKey<Block> TABLE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, NPCHousing.identifier("table_blocks"));
}
