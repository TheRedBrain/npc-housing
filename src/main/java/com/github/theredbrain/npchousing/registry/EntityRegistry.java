package com.github.theredbrain.npchousing.registry;

import com.github.theredbrain.npchousing.NPCHousing;
import com.github.theredbrain.npchousing.block.entity.NPCHousingBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class EntityRegistry {

	public static final BlockEntityType<NPCHousingBlockEntity> NPC_HOUSING_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
			NPCHousing.identifier("npc_housing_block"),
			FabricBlockEntityTypeBuilder.create(NPCHousingBlockEntity::new, BlockRegistry.NPC_HOUSING_BLOCK).build());

	public static void init() {
	}
}
