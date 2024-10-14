package com.github.theredbrain.npchousing;

import com.github.theredbrain.npchousing.registry.EntityRegistry;
import com.github.theredbrain.npchousing.render.block.entity.NPCHousingBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class NPCHousingClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockEntityRendererFactories.register(EntityRegistry.NPC_HOUSING_BLOCK_ENTITY, NPCHousingBlockEntityRenderer::new);
	}
}