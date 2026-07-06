package com.github.theredbrain.npchousing.block;

import com.github.theredbrain.npchousing.block.entity.NPCHousingBlockEntity;
import com.github.theredbrain.npchousing.entity.player.DuckPlayerEntityMixin;
import com.github.theredbrain.npchousing.registry.EntityRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class NPCHousingBlock extends /*Rotated*/BaseEntityBlock {
	public static final MapCodec<NPCHousingBlock> CODEC = simpleCodec(NPCHousingBlock::new);

	public NPCHousingBlock(BlockBehaviour.Properties settings) {
		super(settings);
	}

	public MapCodec<NPCHousingBlock> codec() {
		return CODEC;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new NPCHousingBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
		return createTickerHelper(type, EntityRegistry.NPC_HOUSING_BLOCK_ENTITY, NPCHousingBlockEntity::tick);
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity instanceof NPCHousingBlockEntity npcHousingBlockEntity) {
			((DuckPlayerEntityMixin) player).npchousing$openNPCHousingBlockScreen(npcHousingBlockEntity);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel world, BlockPos pos, boolean moved) {
		// TODO remove entry from housingMap

		super.affectNeighborsAfterRemoval(state, world, pos, moved);
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		// TODO add entry to housingMap

		super.setPlacedBy(world, pos, state, placer, itemStack);
	}

	private static String getWorldOwnerUUIDString(Level world) {
		if (world != null) {
			String worldRegistryKey = world.dimension().identifier().getPath();
			String[] parts = worldRegistryKey.split("_");
			String uuidString = parts[0];
//			if (UUIDUtilities.isStringValidUUID(uuidString)) {
//				return uuidString;
//			}
		}
		return "";
	}

}
