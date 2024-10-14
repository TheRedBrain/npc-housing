package com.github.theredbrain.npchousing.mixin.entity.player;

import com.github.theredbrain.npchousing.block.entity.NPCHousingBlockEntity;
import com.github.theredbrain.npchousing.entity.player.DuckPlayerEntityMixin;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements DuckPlayerEntityMixin {

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public void npchousing$openNPCHousingBlockScreen(NPCHousingBlockEntity npcHousingBlockEntity) {
	}

}
