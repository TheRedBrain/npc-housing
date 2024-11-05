package com.github.theredbrain.npchousing.block.entity;

import com.github.theredbrain.npchousing.registry.EntityRegistry;
import com.github.theredbrain.npchousing.registry.Tags;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.Optional;

public class NPCHousingBlockEntity extends BlockEntity/*RotatedBlockEntity*/ {

	private boolean showInfluenceArea = true;
	private Vec3i influenceAreaDimensions = new Vec3i(5, 3, 5);//Vec3i.ZERO;
	private BlockPos influenceAreaPositionOffset = new BlockPos(0, 1, 0);

	public NPCHousingBlockEntity(BlockPos pos, BlockState state) {
		super(EntityRegistry.NPC_HOUSING_BLOCK_ENTITY, pos, state);
	}

	@Override
	protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {

		nbt.putBoolean("showInfluenceArea", this.showInfluenceArea);

		nbt.putInt("influenceAreaDimensionsX", this.influenceAreaDimensions.getX());
		nbt.putInt("influenceAreaDimensionsY", this.influenceAreaDimensions.getY());
		nbt.putInt("influenceAreaDimensionsZ", this.influenceAreaDimensions.getZ());

		nbt.putInt("influenceAreaPositionOffsetX", this.influenceAreaPositionOffset.getX());
		nbt.putInt("influenceAreaPositionOffsetY", this.influenceAreaPositionOffset.getY());
		nbt.putInt("influenceAreaPositionOffsetZ", this.influenceAreaPositionOffset.getZ());

		super.writeNbt(nbt, registryLookup);
	}

	@Override
	public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {

		this.showInfluenceArea = nbt.getBoolean("showInfluenceArea");

		int i = MathHelper.clamp(nbt.getInt("influenceAreaDimensionsX"), 0, 48);
		int j = MathHelper.clamp(nbt.getInt("influenceAreaDimensionsY"), 0, 48);
		int k = MathHelper.clamp(nbt.getInt("influenceAreaDimensionsZ"), 0, 48);
		this.influenceAreaDimensions = new Vec3i(i, j, k);

		int l = MathHelper.clamp(nbt.getInt("influenceAreaPositionOffsetX"), -48, 48);
		int m = MathHelper.clamp(nbt.getInt("influenceAreaPositionOffsetY"), -48, 48);
		int n = MathHelper.clamp(nbt.getInt("influenceAreaPositionOffsetZ"), -48, 48);
		this.influenceAreaPositionOffset = new BlockPos(l, m, n);

		super.readNbt(nbt, registryLookup);
	}

	public BlockEntityUpdateS2CPacket toUpdatePacket() {
		return BlockEntityUpdateS2CPacket.create(this);
	}

	@Override
	public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
		return this.createComponentlessNbt(registryLookup);
	}

	public static void tick(World world, BlockPos pos, BlockState state, NPCHousingBlockEntity blockEntity) {
		if (!world.isClient && world.getTime() % 20L == 0L) {
//			if (blockEntity.hasWorld() && !blockEntity.isOwnerSet && blockEntity.ownerMode == OwnerMode.DIMENSION_OWNER) {
//				blockEntity.ownerUuid = initOwner(blockEntity.world);
//				if (UUIDUtilities.isStringValidUUID(blockEntity.ownerUuid)) {
//					ScriptBlocksMod.info(blockEntity.ownerUuid);
//					blockEntity.isOwnerSet = true;
//				}
//			}

			Box box = new Box(
					blockEntity.pos.getX() + blockEntity.influenceAreaPositionOffset.getX(),
					blockEntity.pos.getY() + blockEntity.influenceAreaPositionOffset.getY(),
					blockEntity.pos.getZ() + blockEntity.influenceAreaPositionOffset.getZ(),
					blockEntity.pos.getX() + blockEntity.influenceAreaPositionOffset.getX() + blockEntity.influenceAreaDimensions.getX(),
					blockEntity.pos.getY() + blockEntity.influenceAreaPositionOffset.getY() + blockEntity.influenceAreaDimensions.getY(),
					blockEntity.pos.getZ() + blockEntity.influenceAreaPositionOffset.getZ() + blockEntity.influenceAreaDimensions.getZ()
			);
//			List<PlayerEntity> list = world.getNonSpectatingEntities(PlayerEntity.class, box);
//			Iterator var11 = list.iterator();
//
//			PlayerEntity playerEntity;
//			while (var11.hasNext()) {
//				playerEntity = (PlayerEntity) var11.next();
//
//				String playerName = playerEntity.getName().getString();
//				String playerUuid = playerEntity.getUuidAsString();
//				if (Objects.equals(playerUuid, blockEntity.getOwnerUuid())) {
//					playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffectsRegistry.HOUSING_OWNER_EFFECT, 100, 0, true, false, false));
//				} else if (blockEntity.getCoOwnerList().contains(playerName)) {
//					playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffectsRegistry.HOUSING_CO_OWNER_EFFECT, 100, 0, true, false, false));
//				} else if (blockEntity.getTrustedList().contains(playerName)) {
//					playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffectsRegistry.HOUSING_TRUSTED_EFFECT, 100, 0, true, false, false));
//				} else if (blockEntity.getGuestList().contains(playerName)) {
//					playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffectsRegistry.HOUSING_GUEST_EFFECT, 100, 0, true, false, false));
//				} else {
//					playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffectsRegistry.HOUSING_STRANGER_EFFECT, 100, 0, true, false, false));
//				}
//				ComponentsRegistry.CURRENT_HOUSING_BLOCK_POS.get(playerEntity).setValue(blockEntity.pos);
//			}
		}
	}

	public boolean getShowInfluenceArea() {
		return this.showInfluenceArea;
	}

	public boolean setShowInfluenceArea(boolean showInfluenceArea) {
		this.showInfluenceArea = showInfluenceArea;
		return true;
	}

	public Vec3i getInfluenceAreaDimensions() {
		return this.influenceAreaDimensions;
	}

	// TODO check if input is valid
	public boolean setInfluenceAreaDimensions(Vec3i influenceAreaDimensions) {
		this.influenceAreaDimensions = influenceAreaDimensions;
		return true;
	}

	public BlockPos getRestrictBlockBreakingAreaPositionOffset() {
		return this.influenceAreaPositionOffset;
	}

	// TODO check if input is valid
	public boolean setRestrictBlockBreakingAreaPositionOffset(BlockPos influenceAreaPositionOffset) {
		this.influenceAreaPositionOffset = influenceAreaPositionOffset;
		return true;
	}

	public boolean influenceAreaContains(BlockPos pos) {
		return (double) (pos.getX() + 1) > (this.pos.getX() + this.influenceAreaPositionOffset.getX())
				&& (double) pos.getX() < (this.pos.getX() + this.influenceAreaPositionOffset.getX() + this.influenceAreaDimensions.getX())
				&& (double) (pos.getY() + 1) > (this.pos.getY() + this.influenceAreaPositionOffset.getY())
				&& (double) pos.getY() < (this.pos.getY() + this.influenceAreaPositionOffset.getY() + this.influenceAreaDimensions.getY())
				&& (double) (pos.getZ() + 1) > (this.pos.getZ() + this.influenceAreaPositionOffset.getZ())
				&& (double) pos.getZ() < (this.pos.getZ() + this.influenceAreaPositionOffset.getZ() + this.influenceAreaDimensions.getZ());
	}

//	@Override
//	protected void onRotate(BlockState state) {
//		if (state.getBlock() instanceof RotatedBlockWithEntity) {
//			if (state.get(RotatedBlockWithEntity.ROTATED) != this.rotated) {
//				BlockRotation blockRotation = BlockRotationUtils.calculateRotationFromDifferentRotatedStates(state.get(RotatedBlockWithEntity.ROTATED), this.rotated);
////				this.triggeredBlock.setLeft(BlockRotationUtils.rotateOffsetBlockPos(this.triggeredBlock.getLeft(), blockRotation));
//				MutablePair<BlockPos, Vec3i> offsetArea = BlockRotationUtils.rotateOffsetArea(this.influenceAreaPositionOffset, this.influenceAreaDimensions, blockRotation);
//				this.influenceAreaPositionOffset = offsetArea.getLeft();
//				this.influenceAreaDimensions = offsetArea.getRight();
//				this.rotated = state.get(RotatedBlockWithEntity.ROTATED);
//			}
//			if (state.get(RotatedBlockWithEntity.X_MIRRORED) != this.x_mirrored) {
////				this.triggeredBlock.setLeft(BlockRotationUtils.mirrorOffsetBlockPos(this.triggeredBlock.getLeft(), BlockMirror.FRONT_BACK));
//				MutablePair<BlockPos, Vec3i> offsetArea = BlockRotationUtils.mirrorOffsetArea(this.influenceAreaPositionOffset, this.influenceAreaDimensions, BlockMirror.FRONT_BACK);
//				this.influenceAreaPositionOffset = offsetArea.getLeft();
//				this.influenceAreaDimensions = offsetArea.getRight();
//				this.x_mirrored = state.get(RotatedBlockWithEntity.X_MIRRORED);
//			}
//			if (state.get(RotatedBlockWithEntity.Z_MIRRORED) != this.z_mirrored) {
////				this.triggeredBlock.setLeft(BlockRotationUtils.mirrorOffsetBlockPos(this.triggeredBlock.getLeft(), BlockMirror.LEFT_RIGHT));
//				MutablePair<BlockPos, Vec3i> offsetArea = BlockRotationUtils.mirrorOffsetArea(this.influenceAreaPositionOffset, this.influenceAreaDimensions, BlockMirror.LEFT_RIGHT);
//				this.influenceAreaPositionOffset = offsetArea.getLeft();
//				this.influenceAreaDimensions = offsetArea.getRight();
//				this.z_mirrored = state.get(RotatedBlockWithEntity.Z_MIRRORED);
//			}
//		}
//	}

	public static HouseStatus checkHouseStatus(NPCHousingBlockEntity npcHousingBlockEntity) {
		if (npcHousingBlockEntity.getWorld() instanceof ServerWorld serverWorld) {
			Vec3i vec3i = npcHousingBlockEntity.getInfluenceAreaDimensions();
			int nx = vec3i.getX();
			int ny = vec3i.getY();
			int nz = vec3i.getZ();

			// TODO check value boundaries

			boolean hasLight = false;
			boolean hasChair = false;
			boolean hasTable = false;
			boolean hasSpace = false;

			BlockPos blockPos;
			BlockState blockState;
			for (int x = 0; x < nx; x++) {
				for (int y = 0; y < ny; y++) {
					for (int z = 0; z < nz; z++) {
						blockPos = npcHousingBlockEntity.pos.add(x, y, z);
						blockState = serverWorld.getBlockState(blockPos);
						if ((x == 0 || x == (nx - 1)) && y > 0 && y < (ny - 1) &&  z > 0 && z < (nz - 1)) {
							if (!blockState.isIn(Tags.WALL_BLOCKS)) {
								return HouseStatus.INVALID;
							}
						}
						if (x > 0 && x < (nx - 1) && y > 0 && y < (ny - 1) && (z == 0 || z == (nz - 1))) {
							if (!blockState.isIn(Tags.WALL_BLOCKS)) {
								return HouseStatus.INVALID;
							}
						}
						if (x > 0 && x < (nx - 1) && (y == 0 || y == (ny - 1)) && z > 0 && z < (nz - 1)) {
							if (!blockState.isIn(Tags.FLOOR_BLOCKS)) {
								return HouseStatus.INVALID;
							}
						}
						if (x > 0 && x < (nx - 1) && y > 0 && y < (ny - 1) && z > 0 && z < (nz - 1)) {
							hasLight = blockState.isIn(Tags.LIGHT_BLOCKS);
							hasChair = blockState.isIn(Tags.CHAIR_BLOCKS);
							hasTable = blockState.isIn(Tags.TABLE_BLOCKS);
						}
						if (blockState.isAir() && !hasSpace) {
							hasSpace = serverWorld.getBlockState(npcHousingBlockEntity.pos.add(0, 1, 0)).isAir();
						}
					}

				}

			}
			if (!(hasLight && hasChair && hasTable && hasSpace)) {
				return HouseStatus.INVALID;
			}
		}
		return HouseStatus.IS_CLIENT;
	}

	public static enum HouseStatus implements StringIdentifiable {
		VALID("valid", true),
		INVALID("invalid", false),
		IS_CLIENT("is_client", false);

		private final String name;
		private final boolean isValid;

		private HouseStatus(String name, boolean isValid) {
			this.name = name;
			this.isValid = isValid;
		}

		@Override
		public String asString() {
			return this.name;
		}

		public boolean isValid() {
			return isValid;
		}

		public static Optional<HouseStatus> byName(String name) {
			return Arrays.stream(HouseStatus.values()).filter(houseStatus -> houseStatus.asString().equals(name)).findFirst();
		}

		public Text asText() {
			return Text.translatable("gui.teleporter_block.spawn_point_type." + this.name);
		}
	}
}
