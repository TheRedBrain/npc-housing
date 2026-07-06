package com.github.theredbrain.npchousing.registry;

import com.github.theredbrain.npchousing.NPCHousing;
import com.github.theredbrain.npchousing.block.NPCHousingBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.List;

public class BlockRegistry {
	public static RegistryKey<Block> NPC_HOUSING_BLOCK_BLOCK_KEY = RegistryKey.of(RegistryKeys.BLOCK, NPCHousing.identifier("npc_housing_block"));

	public static final Block NPC_HOUSING_BLOCK = registerBlockWithoutItem(NPC_HOUSING_BLOCK_BLOCK_KEY, new NPCHousingBlock(AbstractBlock.Settings.create().registryKey(NPC_HOUSING_BLOCK_BLOCK_KEY).mapColor(MapColor.LIGHT_GRAY).requiresTool().strength(-1.0f, 3600000.0f).dropsNothing()));

	private static Block registerBlock(RegistryKey<Block> block_key, RegistryKey<Item> item_key, Block block, List<RegistryKey<ItemGroup>> itemGroupList) {
		Registry.register(Registries.ITEM, item_key, new BlockItem(block, new Item.Settings().registryKey(item_key)));
		for (RegistryKey<ItemGroup> itemGroup : itemGroupList) {
			ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> content.add(block));
		}
		return Registry.register(Registries.BLOCK, block_key, block);
	}

	private static Block registerBlockWithoutItem(RegistryKey<Block> block_key, Block block) {
		return Registry.register(Registries.BLOCK, block_key, block);
	}

	public static void init() {
	}
}
