package com.github.theredbrain.npchousing.client.render.block.entity.state;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityWithBoundingBoxRenderState;
import net.minecraft.world.level.block.entity.BoundingBoxRenderable;

@Environment(EnvType.CLIENT)
public class NPCHousingBlockEntityRenderState extends BlockEntityRenderState {

	public boolean visible;
	public BoundingBoxRenderable.Mode renderMode;
	public BoundingBoxRenderable.RenderableBox structureBox;
	public BlockEntityWithBoundingBoxRenderState.InvisibleBlockType[] invisibleBlocks;

	public NPCHousingBlockEntityRenderState() {

	}
}
