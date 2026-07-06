package com.github.theredbrain.npchousing.client.render.block.entity;

import com.github.theredbrain.npchousing.client.render.block.entity.state.NPCHousingBlockEntityRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityWithBoundingBoxRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.gizmos.GizmoStyle;
import net.minecraft.gizmos.Gizmos;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BoundingBoxRenderable;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

// TODO clean up
@Environment(value = EnvType.CLIENT)
public class NPCHousingBlockEntityRenderer<T extends BlockEntity & BoundingBoxRenderable> implements BlockEntityRenderer<T, NPCHousingBlockEntityRenderState> {
	public NPCHousingBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
	}

	@Override
	public NPCHousingBlockEntityRenderState createRenderState() {
		return new NPCHousingBlockEntityRenderState();
	}

	@Override
	public void extractRenderState(T blockEntity, NPCHousingBlockEntityRenderState state, float tickProgress, Vec3 cameraPos, ModelFeatureRenderer.@Nullable CrumblingOverlay crumblingOverlay) {
		BlockEntityRenderer.super.extractRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
		updateStructureBoxRenderState(blockEntity, state);
	}

	public static <T extends BlockEntity & BoundingBoxRenderable> void updateStructureBoxRenderState(T blockEntity, NPCHousingBlockEntityRenderState state) {
		LocalPlayer clientPlayerEntity = Minecraft.getInstance().player;
		state.visible = clientPlayerEntity.canUseGameMasterBlocks() || clientPlayerEntity.isSpectator();
		state.structureBox = ((BoundingBoxRenderable) blockEntity).getRenderableBox();
		state.renderMode = ((BoundingBoxRenderable) blockEntity).renderMode();
		BlockPos blockPos = state.structureBox.localPos();
		Vec3i vec3i = state.structureBox.size();
		BlockPos blockPos2 = state.blockPos;
		BlockPos blockPos3 = blockPos2.offset(blockPos);
		if (state.visible && blockEntity.getLevel() != null && state.renderMode == BoundingBoxRenderable.Mode.BOX_AND_INVISIBLE_BLOCKS) {
			state.invisibleBlocks = new BlockEntityWithBoundingBoxRenderState.InvisibleBlockType[vec3i.getX() * vec3i.getY() * vec3i.getZ()];

			for (int i = 0; i < vec3i.getX(); ++i) {
				for (int j = 0; j < vec3i.getY(); ++j) {
					for (int k = 0; k < vec3i.getZ(); ++k) {
						int l = k * vec3i.getX() * vec3i.getY() + j * vec3i.getX() + i;
						BlockState blockState = blockEntity.getLevel().getBlockState(blockPos3.offset(i, j, k));
						if (blockState.isAir()) {
							state.invisibleBlocks[l] = BlockEntityWithBoundingBoxRenderState.InvisibleBlockType.AIR;
						} else if (blockState.is(Blocks.STRUCTURE_VOID)) {
							state.invisibleBlocks[l] = BlockEntityWithBoundingBoxRenderState.InvisibleBlockType.STRUCTURE_VOID;
						} else if (blockState.is(Blocks.BARRIER)) {
							state.invisibleBlocks[l] = BlockEntityWithBoundingBoxRenderState.InvisibleBlockType.BARRIER;
						} else if (blockState.is(Blocks.LIGHT)) {
							state.invisibleBlocks[l] = BlockEntityWithBoundingBoxRenderState.InvisibleBlockType.LIGHT;
						}
					}
				}
			}
		} else {
			state.invisibleBlocks = null;
		}

		if (state.visible) {
		}

//		state.field_62682 = null;
	}

	@Override
	public void submit(NPCHousingBlockEntityRenderState npcHousingBlockEntityRenderState, PoseStack matrixStack, SubmitNodeCollector orderedRenderCommandQueue, CameraRenderState cameraRenderState) {
		if (npcHousingBlockEntityRenderState.visible) {
			BoundingBoxRenderable.Mode renderMode = npcHousingBlockEntityRenderState.renderMode;
			if (renderMode != BoundingBoxRenderable.Mode.NONE) {
				BoundingBoxRenderable.RenderableBox structureBox = npcHousingBlockEntityRenderState.structureBox;
				BlockPos blockPos = structureBox.localPos();
				Vec3i vec3i = structureBox.size();
				if (vec3i.getX() >= 1 && vec3i.getY() >= 1 && vec3i.getZ() >= 1) {
					float f = 1.0F;
					float g = 0.9F;
					BlockPos blockPos2 = blockPos.offset(vec3i);
					Gizmos.cuboid((new AABB((double) blockPos.getX(), (double) blockPos.getY(), (double) blockPos.getZ(), (double) blockPos2.getX(), (double) blockPos2.getY(), (double) blockPos2.getZ())).move(npcHousingBlockEntityRenderState.blockPos), GizmoStyle.stroke(ARGB.colorFromFloat(1.0F, 0.9F, 0.9F, 0.9F)), true);
					this.renderInvisibleBlocks(npcHousingBlockEntityRenderState, blockPos, vec3i);
				}
			}
		}
	}

//	@Override
//	public void render(NPCHousingBlockEntityRenderState state, MatrixStack matrixStack, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
//		double o;
//		double n;
//		double m;
//		double k;
////		if (!MinecraftClient.getInstance().player.isCreativeLevelTwoOp() && !MinecraftClient.getInstance().player.isSpectator()) {
////			return;
////		}
//		BlockPos blockPos = state.getRestrictBlockBreakingAreaPositionOffset();
//		Vec3i vec3i = state.getInfluenceAreaDimensions();
//		if (vec3i.getX() < 1 || vec3i.getY() < 1 || vec3i.getZ() < 1) {
//			return;
//		}
////        if (housingBlockBlockEntity.getMode() != StructureBlockMode.SAVE && housingBlockBlockEntity.getMode() != StructureBlockMode.LOAD) {
////            return;
////        }
//
//		double d = blockPos.getX();
//		double e = blockPos.getZ();
//		double g = blockPos.getY();
//		double h = g + (double) vec3i.getY();
////        double l = switch (housingBlockBlockEntity.getMirror()) {
////            case BlockMirror.LEFT_RIGHT -> {
////                k = vec3i.getX();
////                yield -vec3i.getZ();
////            }
////            case BlockMirror.FRONT_BACK -> {
////                k = -vec3i.getX();
////                yield vec3i.getZ();
////            }
////            default -> {
////                k = vec3i.getX();
////                yield vec3i.getZ();
////            }
////        };
//		k = vec3i.getX(); // temp
//		double l = vec3i.getZ(); // temp
////        double p = switch (housingBlockBlockEntity.getRotation()) {
////            case BlockRotation.CLOCKWISE_90 -> {
////                m = l < 0.0 ? d : d + 1.0;
////                n = k < 0.0 ? e + 1.0 : e;
////                o = m - l;
////                yield n + k;
////            }
////            case BlockRotation.CLOCKWISE_180 -> {
////                m = k < 0.0 ? d : d + 1.0;
////                n = l < 0.0 ? e : e + 1.0;
////                o = m - k;
////                yield n - l;
////            }
////            case BlockRotation.COUNTERCLOCKWISE_90 -> {
////                m = l < 0.0 ? d + 1.0 : d;
////                n = k < 0.0 ? e : e + 1.0;
////                o = m + l;
////                yield n - k;
////            }
////            default -> {
////                m = k < 0.0 ? d + 1.0 : d;
////                n = l < 0.0 ? e + 1.0 : e;
////                o = m + k;
////                yield n + l;
////            }
////        };
//		m = k < 0.0 ? d + 1.0 : d; // temp
//		n = l < 0.0 ? e + 1.0 : e; // temp
//		o = m + k; // temp
//		double p = n + l; // temp
//		float q = 1.0f;
//		float r = 0.9f;
//		float s = 0.5f;
//		VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getLines());
//		if (state.getShowInfluenceArea()) {
//			WorldRenderer.drawBox(matrixStack, vertexConsumer, m, g, n, o, h, p, 0.9f, 0.9f, 0.9f, 1.0f, 0.5f, 0.5f, 0.5f);
//			this.renderInvisibleBlocks(state, vertexConsumer, blockPos, matrixStack);
//		}
////        if (housingBlockBlockEntity.getMode() == StructureBlockMode.SAVE && housingBlockBlockEntity.shouldShowAir()) {
////            this.renderInvisibleBlocks(housingBlockBlockEntity, vertexConsumer, blockPos, matrixStack); // TODO re enable?
////        }
//	}

	private void renderInvisibleBlocks(NPCHousingBlockEntityRenderState state, BlockPos pos, Vec3i size) {
		if (state.invisibleBlocks != null) {
			BlockPos blockPos = state.blockPos;
			BlockPos blockPos2 = blockPos.offset(pos);

			for (int i = 0; i < size.getX(); ++i) {
				for (int j = 0; j < size.getY(); ++j) {
					for (int k = 0; k < size.getZ(); ++k) {
						int l = k * size.getX() * size.getY() + j * size.getX() + i;
						BlockEntityWithBoundingBoxRenderState.InvisibleBlockType invisibleRenderType = state.invisibleBlocks[l];
						if (invisibleRenderType != null) {
							float f = invisibleRenderType == BlockEntityWithBoundingBoxRenderState.InvisibleBlockType.AIR ? 0.05F : 0.0F;
							double d = (double) ((float) (blockPos2.getX() + i) + 0.45F - f);
							double e = (double) ((float) (blockPos2.getY() + j) + 0.45F - f);
							double g = (double) ((float) (blockPos2.getZ() + k) + 0.45F - f);
							double h = (double) ((float) (blockPos2.getX() + i) + 0.55F + f);
							double m = (double) ((float) (blockPos2.getY() + j) + 0.55F + f);
							double n = (double) ((float) (blockPos2.getZ() + k) + 0.55F + f);
							AABB box = new AABB(d, e, g, h, m, n);
							if (invisibleRenderType == BlockEntityWithBoundingBoxRenderState.InvisibleBlockType.AIR) {
								Gizmos.cuboid(box, GizmoStyle.stroke(ARGB.colorFromFloat(1.0F, 0.5F, 0.5F, 1.0F)));
							} else if (invisibleRenderType == BlockEntityWithBoundingBoxRenderState.InvisibleBlockType.STRUCTURE_VOID) {
								Gizmos.cuboid(box, GizmoStyle.stroke(ARGB.colorFromFloat(1.0F, 1.0F, 0.75F, 0.75F)));
							} else if (invisibleRenderType == BlockEntityWithBoundingBoxRenderState.InvisibleBlockType.BARRIER) {
								Gizmos.cuboid(box, GizmoStyle.stroke(-65536));
							} else if (invisibleRenderType == BlockEntityWithBoundingBoxRenderState.InvisibleBlockType.LIGHT) {
								Gizmos.cuboid(box, GizmoStyle.stroke(-256));
							}
						}
					}
				}
			}

		}
	}

	@Override
	public boolean shouldRenderOffScreen() {
		return true;
	}

	@Override
	public int getViewDistance() {
		return 96;
	}
}

