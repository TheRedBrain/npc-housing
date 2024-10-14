package com.github.theredbrain.npchousing.block;

import com.github.theredbrain.npchousing.block.entity.NPCHousingBlockEntity;
import com.github.theredbrain.npchousing.entity.player.DuckPlayerEntityMixin;
import com.github.theredbrain.npchousing.registry.EntityRegistry;
import com.github.theredbrain.scriptblocks.block.RotatedBlockWithEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NPCHousingBlock extends RotatedBlockWithEntity {
	public NPCHousingBlock(AbstractBlock.Settings settings) {
		super(settings);
	}

	// TODO Block Codecs
	public MapCodec<NPCHousingBlock> getCodec() {
		return null;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new NPCHousingBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
//        return validateTicker(type, EntityRegistry.HOUSING_BLOCK_ENTITY, HousingBlockEntity::tick);
		return checkType(type, EntityRegistry.NPC_HOUSING_BLOCK_ENTITY, NPCHousingBlockEntity::tick);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity instanceof NPCHousingBlockEntity npcHousingBlockEntity) {
			((DuckPlayerEntityMixin) player).npchousing$openNPCHousingBlockScreen(npcHousingBlockEntity);
			return ActionResult.success(world.isClient);
		}
		return ActionResult.PASS;
	}
}
