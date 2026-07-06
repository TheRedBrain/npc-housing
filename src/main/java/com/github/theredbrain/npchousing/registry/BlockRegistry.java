package com.github.theredbrain.npchousing.registry;

import com.github.theredbrain.npchousing.NPCHousing;
import com.github.theredbrain.npchousing.block.NPCHousingBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import java.util.List;

public class BlockRegistry {
	public static ResourceKey<Block> NPC_HOUSING_BLOCK_BLOCK_KEY = ResourceKey.create(Registries.BLOCK, NPCHousing.identifier("npc_housing_block"));

	public static final Block NPC_HOUSING_BLOCK = registerBlockWithoutItem(NPC_HOUSING_BLOCK_BLOCK_KEY, new NPCHousingBlock(BlockBehaviour.Properties.of().setId(NPC_HOUSING_BLOCK_BLOCK_KEY).mapColor(MapColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(-1.0f, 3600000.0f).noLootTable()));

	private static Block registerBlock(ResourceKey<Block> block_key, ResourceKey<Item> item_key, Block block, List<ResourceKey<CreativeModeTab>> itemGroupList) {
		Registry.register(BuiltInRegistries.ITEM, item_key, new BlockItem(block, new Item.Properties().setId(item_key)));
		for (ResourceKey<CreativeModeTab> itemGroup : itemGroupList) {
			ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> content.accept(block));
		}
		return Registry.register(BuiltInRegistries.BLOCK, block_key, block);
	}

	private static Block registerBlockWithoutItem(ResourceKey<Block> block_key, Block block) {
		return Registry.register(BuiltInRegistries.BLOCK, block_key, block);
	}

	public static void init() {
	}
}
