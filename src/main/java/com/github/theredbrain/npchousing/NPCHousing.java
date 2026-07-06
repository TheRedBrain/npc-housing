package com.github.theredbrain.npchousing;

import com.github.theredbrain.npchousing.registry.BlockRegistry;
import com.github.theredbrain.npchousing.registry.EntityRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NPCHousing implements ModInitializer {
	public static final String MOD_ID = "npchousing";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Housing your NPCs since 2024!");

		BlockRegistry.init();
		EntityRegistry.init();
	}

	public static Identifier identifier(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}