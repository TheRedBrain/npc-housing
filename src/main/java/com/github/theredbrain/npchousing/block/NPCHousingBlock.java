package com.github.theredbrain.npchousing.block;

import com.github.theredbrain.npchousing.block.entity.NPCHousingBlockEntity;
import com.github.theredbrain.npchousing.entity.player.DuckPlayerEntityMixin;
import com.github.theredbrain.npchousing.registry.EntityRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NPCHousingBlock extends /*Rotated*/BlockWithEntity {
	public static final MapCodec<NPCHousingBlock> CODEC = createCodec(NPCHousingBlock::new);

	public NPCHousingBlock(AbstractBlock.Settings settings) {
		super(settings);
	}

	public MapCodec<NPCHousingBlock> getCodec() {
		return CODEC;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new NPCHousingBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return validateTicker(type, EntityRegistry.NPC_HOUSING_BLOCK_ENTITY, NPCHousingBlockEntity::tick);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity instanceof NPCHousingBlockEntity npcHousingBlockEntity) {
			((DuckPlayerEntityMixin) player).npchousing$openNPCHousingBlockScreen(npcHousingBlockEntity);
		}
		return ActionResult.SUCCESS;
	}

	@Override
	protected void onStateReplaced(BlockState state, ServerWorld world, BlockPos pos, boolean moved) {
		// TODO remove entry from housingMap

		super.onStateReplaced(state, world, pos, moved);
	}

	@Override
	public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		// TODO add entry to housingMap

		super.onPlaced(world, pos, state, placer, itemStack);
	}

	private static String getWorldOwnerUUIDString(World world) {
		if (world != null) {
			String worldRegistryKey = world.getRegistryKey().getValue().getPath();
			String[] parts = worldRegistryKey.split("_");
			String uuidString = parts[0];
//			if (UUIDUtilities.isStringValidUUID(uuidString)) {
//				return uuidString;
//			}
		}
		return "";
	}

}
