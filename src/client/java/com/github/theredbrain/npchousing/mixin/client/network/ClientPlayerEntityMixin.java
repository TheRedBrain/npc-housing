package com.github.theredbrain.npchousing.mixin.client.network;

import com.github.theredbrain.npchousing.block.entity.NPCHousingBlockEntity;
import com.github.theredbrain.npchousing.entity.player.DuckPlayerEntityMixin;
import com.github.theredbrain.npchousing.gui.screen.ingame.NPCHousingScreen;
import com.mojang.authlib.GameProfile;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity implements DuckPlayerEntityMixin {

	@Shadow
	@Final
	protected MinecraftClient client;

	@Shadow
	public abstract boolean isUsingItem();

	@Shadow
	public abstract float getPitch(float tickDelta);

	@Shadow
	public abstract void sendMessage(Text message, boolean overlay);

	public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
		super(world, profile);
	}

	@Override
	public void npchousing$openNPCHousingBlockScreen(NPCHousingBlockEntity npcHousingBlockEntity) {
		this.client.setScreen(new NPCHousingScreen(npcHousingBlockEntity));
	}
}
