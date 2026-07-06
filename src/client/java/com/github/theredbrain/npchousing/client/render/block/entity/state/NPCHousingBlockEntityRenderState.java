package com.github.theredbrain.npchousing.client.render.block.entity.state;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.StructureBoxRendering;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.block.entity.state.StructureBlockBlockEntityRenderState;

@Environment(EnvType.CLIENT)
public class NPCHousingBlockEntityRenderState extends BlockEntityRenderState {

	public boolean visible;
	public StructureBoxRendering.RenderMode renderMode;
	public StructureBoxRendering.StructureBox structureBox;
	public StructureBlockBlockEntityRenderState.InvisibleRenderType[] invisibleBlocks;

	public NPCHousingBlockEntityRenderState() {

	}
}
