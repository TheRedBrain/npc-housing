package com.github.theredbrain.npchousing;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NPCHousing implements ModInitializer {
	public static final String MOD_ID = "npchousing";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Housing your NPCs since 2024!");
	}

	public static Identifier identifier(String path) {
		return Identifier.of(MOD_ID, path);
	}
}