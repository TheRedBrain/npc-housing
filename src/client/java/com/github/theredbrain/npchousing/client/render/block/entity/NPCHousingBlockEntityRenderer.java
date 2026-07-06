package com.github.theredbrain.npchousing.client.render.block.entity;

import com.github.theredbrain.npchousing.client.render.block.entity.state.NPCHousingBlockEntityRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.StructureBoxRendering;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.DrawStyle;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.state.StructureBlockBlockEntityRenderState;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.debug.gizmo.GizmoDrawing;
import org.jspecify.annotations.Nullable;

// TODO clean up
@Environment(value = EnvType.CLIENT)
public class NPCHousingBlockEntityRenderer<T extends BlockEntity & StructureBoxRendering> implements BlockEntityRenderer<T, NPCHousingBlockEntityRenderState> {
	public NPCHousingBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
	}

	@Override
	public NPCHousingBlockEntityRenderState createRenderState() {
		return new NPCHousingBlockEntityRenderState();
	}

	@Override
	public void updateRenderState(T blockEntity, NPCHousingBlockEntityRenderState state, float tickProgress, Vec3d cameraPos, ModelCommandRenderer.@Nullable CrumblingOverlayCommand crumblingOverlay) {
		BlockEntityRenderer.super.updateRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
		updateStructureBoxRenderState(blockEntity, state);
	}

	public static <T extends BlockEntity & StructureBoxRendering> void updateStructureBoxRenderState(T blockEntity, NPCHousingBlockEntityRenderState state) {
		ClientPlayerEntity clientPlayerEntity = MinecraftClient.getInstance().player;
		state.visible = clientPlayerEntity.isCreativeLevelTwoOp() || clientPlayerEntity.isSpectator();
		state.structureBox = ((StructureBoxRendering) blockEntity).getStructureBox();
		state.renderMode = ((StructureBoxRendering) blockEntity).getRenderMode();
		BlockPos blockPos = state.structureBox.localPos();
		Vec3i vec3i = state.structureBox.size();
		BlockPos blockPos2 = state.pos;
		BlockPos blockPos3 = blockPos2.add(blockPos);
		if (state.visible && blockEntity.getWorld() != null && state.renderMode == StructureBoxRendering.RenderMode.BOX_AND_INVISIBLE_BLOCKS) {
			state.invisibleBlocks = new StructureBlockBlockEntityRenderState.InvisibleRenderType[vec3i.getX() * vec3i.getY() * vec3i.getZ()];

			for (int i = 0; i < vec3i.getX(); ++i) {
				for (int j = 0; j < vec3i.getY(); ++j) {
					for (int k = 0; k < vec3i.getZ(); ++k) {
						int l = k * vec3i.getX() * vec3i.getY() + j * vec3i.getX() + i;
						BlockState blockState = blockEntity.getWorld().getBlockState(blockPos3.add(i, j, k));
						if (blockState.isAir()) {
							state.invisibleBlocks[l] = StructureBlockBlockEntityRenderState.InvisibleRenderType.AIR;
						} else if (blockState.isOf(Blocks.STRUCTURE_VOID)) {
							state.invisibleBlocks[l] = StructureBlockBlockEntityRenderState.InvisibleRenderType.STRUCTURE_VOID;
						} else if (blockState.isOf(Blocks.BARRIER)) {
							state.invisibleBlocks[l] = StructureBlockBlockEntityRenderState.InvisibleRenderType.BARRIER;
						} else if (blockState.isOf(Blocks.LIGHT)) {
							state.invisibleBlocks[l] = StructureBlockBlockEntityRenderState.InvisibleRenderType.LIGHT;
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
	public void render(NPCHousingBlockEntityRenderState npcHousingBlockEntityRenderState, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState) {
		if (npcHousingBlockEntityRenderState.visible) {
			StructureBoxRendering.RenderMode renderMode = npcHousingBlockEntityRenderState.renderMode;
			if (renderMode != StructureBoxRendering.RenderMode.NONE) {
				StructureBoxRendering.StructureBox structureBox = npcHousingBlockEntityRenderState.structureBox;
				BlockPos blockPos = structureBox.localPos();
				Vec3i vec3i = structureBox.size();
				if (vec3i.getX() >= 1 && vec3i.getY() >= 1 && vec3i.getZ() >= 1) {
					float f = 1.0F;
					float g = 0.9F;
					BlockPos blockPos2 = blockPos.add(vec3i);
					GizmoDrawing.box((new Box((double) blockPos.getX(), (double) blockPos.getY(), (double) blockPos.getZ(), (double) blockPos2.getX(), (double) blockPos2.getY(), (double) blockPos2.getZ())).offset(npcHousingBlockEntityRenderState.pos), DrawStyle.stroked(ColorHelper.fromFloats(1.0F, 0.9F, 0.9F, 0.9F)), true);
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
			BlockPos blockPos = state.pos;
			BlockPos blockPos2 = blockPos.add(pos);

			for (int i = 0; i < size.getX(); ++i) {
				for (int j = 0; j < size.getY(); ++j) {
					for (int k = 0; k < size.getZ(); ++k) {
						int l = k * size.getX() * size.getY() + j * size.getX() + i;
						StructureBlockBlockEntityRenderState.InvisibleRenderType invisibleRenderType = state.invisibleBlocks[l];
						if (invisibleRenderType != null) {
							float f = invisibleRenderType == StructureBlockBlockEntityRenderState.InvisibleRenderType.AIR ? 0.05F : 0.0F;
							double d = (double) ((float) (blockPos2.getX() + i) + 0.45F - f);
							double e = (double) ((float) (blockPos2.getY() + j) + 0.45F - f);
							double g = (double) ((float) (blockPos2.getZ() + k) + 0.45F - f);
							double h = (double) ((float) (blockPos2.getX() + i) + 0.55F + f);
							double m = (double) ((float) (blockPos2.getY() + j) + 0.55F + f);
							double n = (double) ((float) (blockPos2.getZ() + k) + 0.55F + f);
							Box box = new Box(d, e, g, h, m, n);
							if (invisibleRenderType == StructureBlockBlockEntityRenderState.InvisibleRenderType.AIR) {
								GizmoDrawing.box(box, DrawStyle.stroked(ColorHelper.fromFloats(1.0F, 0.5F, 0.5F, 1.0F)));
							} else if (invisibleRenderType == StructureBlockBlockEntityRenderState.InvisibleRenderType.STRUCTURE_VOID) {
								GizmoDrawing.box(box, DrawStyle.stroked(ColorHelper.fromFloats(1.0F, 1.0F, 0.75F, 0.75F)));
							} else if (invisibleRenderType == StructureBlockBlockEntityRenderState.InvisibleRenderType.BARRIER) {
								GizmoDrawing.box(box, DrawStyle.stroked(-65536));
							} else if (invisibleRenderType == StructureBlockBlockEntityRenderState.InvisibleRenderType.LIGHT) {
								GizmoDrawing.box(box, DrawStyle.stroked(-256));
							}
						}
					}
				}
			}

		}
	}

	@Override
	public boolean rendersOutsideBoundingBox() {
		return true;
	}

	@Override
	public int getRenderDistance() {
		return 96;
	}
}

