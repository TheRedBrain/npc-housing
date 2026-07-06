package com.github.theredbrain.npchousing.mixin.client.network;

import com.github.theredbrain.npchousing.block.entity.NPCHousingBlockEntity;
import com.github.theredbrain.npchousing.entity.player.DuckPlayerEntityMixin;
import com.github.theredbrain.npchousing.gui.screen.ingame.NPCHousingScreen;
import com.mojang.authlib.GameProfile;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Environment(EnvType.CLIENT)
@Mixin(LocalPlayer.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayer implements DuckPlayerEntityMixin {

	@Shadow
	@Final
	protected Minecraft minecraft;

	@Shadow
	public abstract boolean isUsingItem();

	@Shadow
	public abstract float getViewXRot(float tickDelta);

	@Shadow
	public abstract void displayClientMessage(Component message, boolean overlay);

	public ClientPlayerEntityMixin(ClientLevel world, GameProfile profile) {
		super(world, profile);
	}

	@Override
	public void npchousing$openNPCHousingBlockScreen(NPCHousingBlockEntity npcHousingBlockEntity) {
		this.minecraft.setScreen(new NPCHousingScreen(npcHousingBlockEntity));
	}
}
