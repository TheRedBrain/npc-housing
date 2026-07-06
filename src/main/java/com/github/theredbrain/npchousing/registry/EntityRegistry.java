package com.github.theredbrain.npchousing.registry;

import com.github.theredbrain.npchousing.NPCHousing;
import com.github.theredbrain.npchousing.block.entity.NPCHousingBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class EntityRegistry {

	public static final BlockEntityType<NPCHousingBlockEntity> NPC_HOUSING_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
			NPCHousing.identifier("npc_housing_block"),
			FabricBlockEntityTypeBuilder.create(NPCHousingBlockEntity::new, BlockRegistry.NPC_HOUSING_BLOCK).build());

	public static void init() {
	}
}
