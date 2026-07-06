package com.github.theredbrain.npchousing.gui.screen.ingame;

import com.github.theredbrain.npchousing.NPCHousing;
import com.github.theredbrain.npchousing.block.entity.NPCHousingBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.StringRepresentable;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Environment(value = EnvType.CLIENT)
public class NPCHousingScreen extends Screen {
	// adventure
	private static final Component TITLE_OWNER_LABEL_TEXT = Component.translatable("gui.housing_screen.title.owner");
	private static final Component TITLE_CO_OWNER_LABEL_TEXT = Component.translatable("gui.housing_screen.title.co_owner");
	private static final Component TITLE_CO_OWNER_LIST_LABEL_TEXT = Component.translatable("gui.housing_screen.co_owner_list.title");
	private static final Component TITLE_CO_OWNER_LIST_DESCRIPTION_LABEL_TEXT = Component.translatable("gui.housing_screen.co_owner_list.description");
	private static final Component TITLE_TRUSTED_LABEL_TEXT = Component.translatable("gui.housing_screen.title.trusted");
	private static final Component TITLE_TRUSTED_LIST_LABEL_TEXT = Component.translatable("gui.housing_screen.trusted_list.title");
	private static final Component TITLE_TRUSTED_LIST_DESCRIPTION_LABEL_TEXT = Component.translatable("gui.housing_screen.trusted_list.description");
	private static final Component TITLE_GUEST_LABEL_TEXT = Component.translatable("gui.housing_screen.title.guest");
	private static final Component TITLE_GUEST_LIST_LABEL_TEXT = Component.translatable("gui.housing_screen.guest_list.title");
	private static final Component TITLE_GUEST_LIST_DESCRIPTION_LABEL_TEXT = Component.translatable("gui.housing_screen.guest_list.description");
	private static final Component TITLE_STRANGER_LABEL_TEXT = Component.translatable("gui.housing_screen.title.stranger");
	private static final Component LEAVE_CURRENT_HOUSE_BUTTON_LABEL_TEXT = Component.translatable("gui.housing_screen.leave_current_house_button_label");
	private static final Component OPEN_RESET_HOUSE_SCREEN_BUTTON_LABEL_TEXT = Component.translatable("gui.housing_screen.open_reset_house_screen_button_label");
	private static final Component TOGGLE_ADVENTURE_BUILDING_OFF_BUTTON_LABEL_TEXT = Component.translatable("gui.housing_screen.toggle_adventure_building_off_button_label");
	private static final Component TOGGLE_ADVENTURE_BUILDING_ON_BUTTON_LABEL_TEXT = Component.translatable("gui.housing_screen.toggle_adventure_building_on_button_label");
	private static final Component UNCLAIM_HOUSE_BUTTON_LABEL_TEXT = Component.translatable("gui.housing_screen.unclaim_house_button_label");
	private static final Component CLAIM_HOUSE_BUTTON_LABEL_TEXT = Component.translatable("gui.housing_screen.claim_house_button_label");
	private static final Component OPEN_CO_OWNER_LIST_BUTTON_LABEL_TEXT = Component.translatable("gui.housing_screen.open_co_owner_list_button_label");
	private static final Component NEW_CO_OWNER_FIELD_PLACEHOLDER_TEXT = Component.translatable("gui.housing_screen.new_co_owner_field.place_holder");
	private static final Component ADD_NEW_CO_OWNER_BUTTON_LABEL_TEXT = Component.translatable("gui.housing_screen.add_new_co_owner_button_label");
	private static final Component OPEN_TRUSTED_PERSONS_LIST_BUTTON_LABEL_TEXT = Component.translatable("gui.housing_screen.open_trusted_list_button_label");
	private static final Component NEW_TRUSTED_PERSON_FIELD_PLACEHOLDER_TEXT = Component.translatable("gui.housing_screen.new_trusted_person_field.place_holder");
	private static final Component ADD_NEW_TRUSTED_PERSON_BUTTON_LABEL_TEXT = Component.translatable("gui.housing_screen.add_new_trusted_person_button_label");
	private static final Component OPEN_GUEST_LIST_BUTTON_LABEL_TEXT = Component.translatable("gui.housing_screen.open_guest_list_button_label");
	private static final Component NEW_GUEST_FIELD_PLACEHOLDER_TEXT = Component.translatable("gui.housing_screen.new_guest_field.place_holder");
	private static final Component ADD_NEW_GUEST_BUTTON_LABEL_TEXT = Component.translatable("gui.housing_screen.add_new_guest_button_label");
	private static final Component REMOVE_LIST_ENTRY_BUTTON_LABEL_TEXT = Component.translatable("gui.housing_screen.remove_list_entry_button_label");

	// creative
	private static final Component HIDE_INFLUENCE_AREA_LABEL_TEXT = Component.translatable("gui.housing_screen.hide_influence_area_label");
	private static final Component SHOW_INFLUENCE_AREA_LABEL_TEXT = Component.translatable("gui.housing_screen.show_influence_area_label");
	private static final Component INFLUENCE_AREA_DIMENSIONS_LABEL_TEXT = Component.translatable("gui.housing_screen.influence_area_dimensions_label");
	private static final Component INFLUENCE_AREA_POSITION_OFFET_LABEL_TEXT = Component.translatable("gui.housing_screen.influence_area_position_offset_label");
	private static final Component RESET_OWNER_BUTTON_LABEL_TEXT = Component.translatable("gui.housing_block.reset_owner_button_label");
	private static final Component TRIGGERED_BLOCK_POSITION_OFFSET_LABEL_TEXT = Component.translatable("gui.triggered_block.triggeredBlockPositionOffset");
	public static final Identifier BACKGROUND_176_166_TEXTURE = Identifier.fromNamespaceAndPath("scriptblocks", "textures/gui/container/generic_176_166_background.png");
	public static final Identifier BACKGROUND_218_95_TEXTURE = NPCHousing.identifier("textures/gui/container/generic_218_95_background.png");
	public static final Identifier BACKGROUND_218_71_TEXTURE = NPCHousing.identifier("textures/gui/container/generic_218_71_background.png");
	private static final Identifier PLAYER_LISTS_SCROLLER_BACKGROUND_TEXTURE = NPCHousing.identifier("container/housing_screen/player_lists_scroller_background");
	private static final Identifier SCROLLER_TEXTURE = NPCHousing.identifier("container/scroller");

	private final NPCHousingBlockEntity npcHousingBlockEntity;

	//region adventure widgets
//	private ButtonWidget leaveCurrentHouseButton;
//
//	private ButtonWidget openResetHouseScreenButton;
//	private ButtonWidget resetHouseButton;
//	private ButtonWidget closeResetHouseScreenButton;
//
//	private ButtonWidget toggleAdventureBuildingEffectButton;
//	private ButtonWidget unclaimHouseButton;
//	private ButtonWidget claimHouseButton;

	private Button openCoOwnerListScreenButton;
	private EditBox newCoOwnerField;
	private Button addNewCoOwnerButton;
	private Button removeCoOwnerListEntryButton0;
	private Button removeCoOwnerListEntryButton1;
	private Button removeCoOwnerListEntryButton2;
	private Button removeCoOwnerListEntryButton3;
	private Button removeCoOwnerListEntryButton4;

//	private ButtonWidget openTrustedPersonsListScreenButton;
//	private TextFieldWidget newTrustedPersonField;
//	private ButtonWidget addNewTrustedPersonButton;
//	private ButtonWidget removeTrustedPersonListEntryButton0;
//	private ButtonWidget removeTrustedPersonListEntryButton1;
//	private ButtonWidget removeTrustedPersonListEntryButton2;
//	private ButtonWidget removeTrustedPersonListEntryButton3;
//	private ButtonWidget removeTrustedPersonListEntryButton4;
//
//	private ButtonWidget openGuestListScreenButton;
//	private TextFieldWidget newGuestField;
//	private ButtonWidget addNewGuestButton;
//	private ButtonWidget removeGuestListEntryButton0;
//	private ButtonWidget removeGuestListEntryButton1;
//	private ButtonWidget removeGuestListEntryButton2;
//	private ButtonWidget removeGuestListEntryButton3;
//	private ButtonWidget removeGuestListEntryButton4;

	private Button closeListEditScreensButton;

	//	private ButtonWidget closeAdventureScreenButton;
	//endregion adventure widgets
	//region creative widgets
	private CycleButton<CreativeScreenPage> creativeScreenPageButton;
	private CycleButton<Boolean> showRestrictBlockBreakingAreaButton;
	private EditBox restrictBlockBreakingAreaDimensionsXField;
	private EditBox restrictBlockBreakingAreaDimensionsYField;
	private EditBox restrictBlockBreakingAreaDimensionsZField;
	private EditBox restrictBlockBreakingAreaPositionOffsetXField;
	private EditBox restrictBlockBreakingAreaPositionOffsetYField;
	private EditBox restrictBlockBreakingAreaPositionOffsetZField;
	//	private TextFieldWidget triggeredBlockPositionOffsetXField;
//	private TextFieldWidget triggeredBlockPositionOffsetYField;
//	private TextFieldWidget triggeredBlockPositionOffsetZField;
//	private CyclingButtonWidget<Boolean> toggleTriggeredBlockResetsButton;
//	private boolean triggeredBlockResets;
//	private CyclingButtonWidget<HousingBlockEntity.OwnerMode> toggleOwnerModeButton;
//	private ButtonWidget resetOwnerButton;
	private Button saveCreativeButton;
	private Button cancelCreativeButton;
	//endregion creative widgets
	private CreativeScreenPage creativeScreenPage;
	private List<String> unlockedNPCs = new ArrayList<>(List.of());
	//	private List<String> trustedPersonsList = new ArrayList<>(List.of());
//	private List<String> guestList = new ArrayList<>(List.of());
	private boolean showInfluenceArea = false;
	//	private boolean showResetHouseScreen = false;
	private boolean showCoOwnerListScreen = false;
	//	private boolean showTrustedListScreen = false;
//	private boolean showGuestListScreen = false;
	private int backgroundWidth;
	private int backgroundHeight;
	private int x;
	private int y;
	private int scrollPosition = 0;
	private float scrollAmount = 0.0f;
	private boolean mouseClicked = false;
//	private HousingBlockEntity.OwnerMode ownerMode = HousingBlockEntity.OwnerMode.DIMENSION_OWNER;

	public NPCHousingScreen(NPCHousingBlockEntity npcHousingBlockEntity) {
		super(GameNarrator.NO_TITLE);
		this.npcHousingBlockEntity = npcHousingBlockEntity;
		this.creativeScreenPage = CreativeScreenPage.HOUSE;
	}

//	private void openResetHouseScreen() {
//		this.showResetHouseScreen = true;
//		this.updateWidgets();
//	}
//
//	private void closeResetHouseScreen() {
//		this.showResetHouseScreen = false;
//		this.updateWidgets();
//	}

//	private void openListScreen(int listType) {
////		if (listType == 0) {
//			this.showCoOwnerListScreen = true;
////		} else if (listType == 1) {
////			this.showTrustedListScreen = true;
////		} else if (listType == 2) {
////			this.showGuestListScreen = true;
////		}
//		this.scrollPosition = 0;
//		this.scrollAmount = 0.0f;
//		this.updateWidgets();
//	}
//
//	private void closeListScreens() {
//		this.showCoOwnerListScreen = false;
////		this.showTrustedListScreen = false;
////		this.showGuestListScreen = false;
//		this.scrollPosition = 0;
//		this.scrollAmount = 0.0f;
//		this.updateHousingBlockAdventure();
//		this.updateWidgets();
//	}

//	private void addNewEntryToList(String newEntry, int listType) {
//		if (listType == 0) {
//			this.coOwnerList.add(newEntry);
//		} else if (listType == 1) {
//			this.trustedPersonsList.add(newEntry);
//		} else if (listType == 2) {
//			this.guestList.add(newEntry);
//		}
//		this.scrollPosition = 0;
//		this.scrollAmount = 0.0f;
//		this.updateWidgets();
//	}

//	private void removeEntryFromList(int index, int listType) {
//		if (listType == 0 && index + this.scrollPosition < this.coOwnerList.size()) {
//			this.coOwnerList.remove(index + this.scrollPosition);
//		} else if (listType == 1 && index + this.scrollPosition < this.trustedPersonsList.size()) {
//			this.trustedPersonsList.remove(index + this.scrollPosition);
//		} else if (listType == 2 && index + this.scrollPosition < this.guestList.size()) {
//			this.guestList.remove(index + this.scrollPosition);
//		}
//		this.scrollPosition = 0;
//		this.scrollAmount = 0.0f;
//		this.updateWidgets();
//	}

	private void done() {
		this.updateNPCHousingBlock();
		this.onClose();
	}

	private void cancel() {
		// TODO reset blockEntity?
		this.onClose();
	}

//	private void leaveCurrentHouse() {
//		ClientPlayNetworking.send(new LeaveHouseFromHousingScreenPacket());
//		this.close();
//	}

	@Override
	protected void init() {
//		this.coOwnerList.clear();
//		this.trustedPersonsList.clear();
//		this.guestList.clear();
//		if (this.npcHousingBlockEntity != null) {
//			this.coOwnerList.addAll(this.npcHousingBlockEntity.getCoOwnerList());
//			this.trustedPersonsList.addAll(this.npcHousingBlockEntity.getTrustedList());
//			this.guestList.addAll(this.npcHousingBlockEntity.getGuestList());
		this.showInfluenceArea = npcHousingBlockEntity.getShowInfluenceArea();
//			this.ownerMode = npcHousingBlockEntity.getOwnerMode();
//		}
//		if (this.currentPermissionLevel == 0) {
//			this.backgroundWidth = 218;
//			this.backgroundHeight = 215;
//			this.x = (this.width - this.backgroundWidth) / 2;
//			this.y = (this.height - this.backgroundHeight) / 2;
//		} else if (this.currentPermissionLevel == 1) {
//			this.backgroundWidth = 218;
//			this.backgroundHeight = 95;
//			this.x = (this.width - this.backgroundWidth) / 2;
//			this.y = (this.height - this.backgroundHeight) / 2;
//		} else if (this.currentPermissionLevel == 2 || this.currentPermissionLevel == 3) {
//			this.backgroundWidth = 218;
//			this.backgroundHeight = 71;
//			this.x = (this.width - this.backgroundWidth) / 2;
//			this.y = (this.height - this.backgroundHeight) / 2;
//		} else {
		this.backgroundWidth = 176;
		this.backgroundHeight = 166;
		this.x = (this.width - this.backgroundWidth) / 2;
		this.y = (this.height - this.backgroundHeight) / 2;

		super.init();
		//region adventure screen

//		this.resetHouseButton = this.addDrawableChild(ButtonWidget.builder(ScreenTexts.PROCEED, button -> this.resetHouse()).dimensions(this.x + 7, this.y + this.backgroundHeight - 27, this.backgroundWidth / 2 - 18, 20).build());
//		this.closeResetHouseScreenButton = this.addDrawableChild(ButtonWidget.builder(ScreenTexts.CANCEL, button -> this.closeResetHouseScreen()).dimensions(this.x + this.backgroundWidth / 2 - 18 + 7, this.y + this.backgroundHeight - 27, this.backgroundWidth / 2 - 18, 20).build());

//		this.removeCoOwnerListEntryButton0 = this.addDrawableChild(ButtonWidget.builder(REMOVE_LIST_ENTRY_BUTTON_LABEL_TEXT, button -> this.removeEntryFromList(0, 0)).dimensions(this.x + this.backgroundWidth - 57, this.y + 33, 50, 20).build());
//		this.removeCoOwnerListEntryButton1 = this.addDrawableChild(ButtonWidget.builder(REMOVE_LIST_ENTRY_BUTTON_LABEL_TEXT, button -> this.removeEntryFromList(1, 0)).dimensions(this.x + this.backgroundWidth - 57, this.y + 57, 50, 20).build());
//		this.removeCoOwnerListEntryButton2 = this.addDrawableChild(ButtonWidget.builder(REMOVE_LIST_ENTRY_BUTTON_LABEL_TEXT, button -> this.removeEntryFromList(2, 0)).dimensions(this.x + this.backgroundWidth - 57, this.y + 81, 50, 20).build());
//		this.removeCoOwnerListEntryButton3 = this.addDrawableChild(ButtonWidget.builder(REMOVE_LIST_ENTRY_BUTTON_LABEL_TEXT, button -> this.removeEntryFromList(3, 0)).dimensions(this.x + this.backgroundWidth - 57, this.y + 105, 50, 20).build());
//		this.removeCoOwnerListEntryButton4 = this.addDrawableChild(ButtonWidget.builder(REMOVE_LIST_ENTRY_BUTTON_LABEL_TEXT, button -> this.removeEntryFromList(4, 0)).dimensions(this.x + this.backgroundWidth - 57, this.y + 129, 50, 20).build());
//		this.newCoOwnerField = new TextFieldWidget(this.textRenderer, this.x + 7, this.y + this.backgroundHeight - 27 - 24, this.backgroundWidth - 57 - 7 - 4, 20, Text.empty());
//		this.newCoOwnerField.setMaxLength(128);
//		this.newCoOwnerField.setPlaceholder(NEW_CO_OWNER_FIELD_PLACEHOLDER_TEXT);
//		this.addSelectableChild(this.newCoOwnerField);
//		this.addNewCoOwnerButton = this.addDrawableChild(ButtonWidget.builder(ADD_NEW_CO_OWNER_BUTTON_LABEL_TEXT, button -> this.addNewEntryToList(this.newCoOwnerField.getText(), 0)).dimensions(this.x + this.backgroundWidth - 57, this.y + this.backgroundHeight - 27 - 24, 50, 20).build());

//		this.removeTrustedPersonListEntryButton0 = this.addDrawableChild(ButtonWidget.builder(REMOVE_LIST_ENTRY_BUTTON_LABEL_TEXT, button -> this.removeEntryFromList(0, 1)).dimensions(this.x + this.backgroundWidth - 57, this.y + 33, 50, 20).build());
//		this.removeTrustedPersonListEntryButton1 = this.addDrawableChild(ButtonWidget.builder(REMOVE_LIST_ENTRY_BUTTON_LABEL_TEXT, button -> this.removeEntryFromList(1, 1)).dimensions(this.x + this.backgroundWidth - 57, this.y + 57, 50, 20).build());
//		this.removeTrustedPersonListEntryButton2 = this.addDrawableChild(ButtonWidget.builder(REMOVE_LIST_ENTRY_BUTTON_LABEL_TEXT, button -> this.removeEntryFromList(2, 1)).dimensions(this.x + this.backgroundWidth - 57, this.y + 81, 50, 20).build());
//		this.removeTrustedPersonListEntryButton3 = this.addDrawableChild(ButtonWidget.builder(REMOVE_LIST_ENTRY_BUTTON_LABEL_TEXT, button -> this.removeEntryFromList(3, 1)).dimensions(this.x + this.backgroundWidth - 57, this.y + 105, 50, 20).build());
//		this.removeTrustedPersonListEntryButton4 = this.addDrawableChild(ButtonWidget.builder(REMOVE_LIST_ENTRY_BUTTON_LABEL_TEXT, button -> this.removeEntryFromList(4, 1)).dimensions(this.x + this.backgroundWidth - 57, this.y + 129, 50, 20).build());
//		this.newTrustedPersonField = new TextFieldWidget(this.textRenderer, this.x + 7, this.y + this.backgroundHeight - 27 - 24, this.backgroundWidth - 57 - 7 - 4, 20, Text.empty());
//		this.newTrustedPersonField.setMaxLength(128);
//		this.newTrustedPersonField.setPlaceholder(NEW_TRUSTED_PERSON_FIELD_PLACEHOLDER_TEXT);
//		this.addSelectableChild(this.newTrustedPersonField);
//		this.addNewTrustedPersonButton = this.addDrawableChild(ButtonWidget.builder(ADD_NEW_TRUSTED_PERSON_BUTTON_LABEL_TEXT, button -> this.addNewEntryToList(this.newTrustedPersonField.getText(), 1)).dimensions(this.x + this.backgroundWidth - 57, this.y + this.backgroundHeight - 27 - 24, 50, 20).build());
//
//		this.removeGuestListEntryButton0 = this.addDrawableChild(ButtonWidget.builder(REMOVE_LIST_ENTRY_BUTTON_LABEL_TEXT, button -> this.removeEntryFromList(0, 2)).dimensions(this.x + this.backgroundWidth - 57, this.y + 33, 50, 20).build());
//		this.removeGuestListEntryButton1 = this.addDrawableChild(ButtonWidget.builder(REMOVE_LIST_ENTRY_BUTTON_LABEL_TEXT, button -> this.removeEntryFromList(1, 2)).dimensions(this.x + this.backgroundWidth - 57, this.y + 57, 50, 20).build());
//		this.removeGuestListEntryButton2 = this.addDrawableChild(ButtonWidget.builder(REMOVE_LIST_ENTRY_BUTTON_LABEL_TEXT, button -> this.removeEntryFromList(2, 2)).dimensions(this.x + this.backgroundWidth - 57, this.y + 81, 50, 20).build());
//		this.removeGuestListEntryButton3 = this.addDrawableChild(ButtonWidget.builder(REMOVE_LIST_ENTRY_BUTTON_LABEL_TEXT, button -> this.removeEntryFromList(3, 2)).dimensions(this.x + this.backgroundWidth - 57, this.y + 105, 50, 20).build());
//		this.removeGuestListEntryButton4 = this.addDrawableChild(ButtonWidget.builder(REMOVE_LIST_ENTRY_BUTTON_LABEL_TEXT, button -> this.removeEntryFromList(4, 2)).dimensions(this.x + this.backgroundWidth - 57, this.y + 129, 50, 20).build());
//		this.newGuestField = new TextFieldWidget(this.textRenderer, this.x + 7, this.y + this.backgroundHeight - 27 - 24, this.backgroundWidth - 57 - 7 - 4, 20, Text.empty());
//		this.newGuestField.setMaxLength(128);
//		this.newGuestField.setPlaceholder(NEW_GUEST_FIELD_PLACEHOLDER_TEXT);
//		this.addSelectableChild(this.newGuestField);
//		this.addNewGuestButton = this.addDrawableChild(ButtonWidget.builder(ADD_NEW_GUEST_BUTTON_LABEL_TEXT, button -> this.addNewEntryToList(this.newGuestField.getText(), 2)).dimensions(this.x + this.backgroundWidth - 57, this.y + this.backgroundHeight - 27 - 24, 50, 20).build());

//		this.closeListEditScreensButton = this.addDrawableChild(ButtonWidget.builder(ScreenTexts.CANCEL, button -> this.closeListScreens()).dimensions(this.x + 7, this.y + this.backgroundHeight - 27, this.backgroundWidth - 14, 20).build());

//		boolean isAdventureBuilding = false;
//		if (this.client != null && this.client.player != null) {
//			isAdventureBuilding = this.client.player.hasStatusEffect(StatusEffectsRegistry.BUILDING_MODE);
//		}
//		this.toggleAdventureBuildingEffectButton = this.addDrawableChild(ButtonWidget.builder(isAdventureBuilding ? TOGGLE_ADVENTURE_BUILDING_OFF_BUTTON_LABEL_TEXT : TOGGLE_ADVENTURE_BUILDING_ON_BUTTON_LABEL_TEXT, button -> this.toggleAdventureBuildingEffect()).dimensions(this.x + 7, this.y + 20, this.backgroundWidth - 14, 20).build());

//		this.openCoOwnerListScreenButton = this.addDrawableChild(ButtonWidget.builder(OPEN_CO_OWNER_LIST_BUTTON_LABEL_TEXT, button -> this.openListScreen(0)).dimensions(this.x + 7, this.y + 44, this.backgroundWidth - 14, 20).build());
//		this.openTrustedPersonsListScreenButton = this.addDrawableChild(ButtonWidget.builder(OPEN_TRUSTED_PERSONS_LIST_BUTTON_LABEL_TEXT, button -> this.openListScreen(1)).dimensions(this.x + 7, this.y + 68, this.backgroundWidth - 14, 20).build());
//		this.openGuestListScreenButton = this.addDrawableChild(ButtonWidget.builder(OPEN_GUEST_LIST_BUTTON_LABEL_TEXT, button -> this.openListScreen(2)).dimensions(this.x + 7, this.y + 92, this.backgroundWidth - 14, 20).build());
//
//		this.openResetHouseScreenButton = this.addDrawableChild(ButtonWidget.builder(OPEN_RESET_HOUSE_SCREEN_BUTTON_LABEL_TEXT, button -> this.openResetHouseScreen()).dimensions(this.x + 7, this.y + 116, this.backgroundWidth - 14, 20).build());
//
//		this.unclaimHouseButton = this.addDrawableChild(ButtonWidget.builder(UNCLAIM_HOUSE_BUTTON_LABEL_TEXT, button -> this.trySetHouseOwner(false)).dimensions(this.x + 7, this.y + this.backgroundHeight - 27 - 48, this.backgroundWidth - 14, 20).build());
//		this.claimHouseButton = this.addDrawableChild(ButtonWidget.builder(CLAIM_HOUSE_BUTTON_LABEL_TEXT, button -> this.trySetHouseOwner(true)).dimensions(this.x + 7, this.y + this.backgroundHeight - 27 - 48, this.backgroundWidth - 14, 20).build());
//
//		this.leaveCurrentHouseButton = this.addDrawableChild(ButtonWidget.builder(LEAVE_CURRENT_HOUSE_BUTTON_LABEL_TEXT, button -> this.leaveCurrentHouse()).dimensions(this.x + 7, this.y + this.backgroundHeight - 27 - 24, this.backgroundWidth - 14, 20).build());
//
//		this.closeAdventureScreenButton = this.addDrawableChild(ButtonWidget.builder(ScreenTexts.CANCEL, button -> this.cancel()).dimensions(this.x + 7, this.y + this.backgroundHeight - 27, this.backgroundWidth - 14, 20).build());

		//endregion adventure screen

		//region creative screen
		this.creativeScreenPageButton = this.addRenderableWidget(CycleButton.builder(CreativeScreenPage::asText, this.creativeScreenPage).withValues((CreativeScreenPage[]) CreativeScreenPage.values()).displayOnlyValue().create(this.width / 2 - 154, 20, 300, 20, Component.empty(), (button, creativeScreenPage) -> {
			this.creativeScreenPage = creativeScreenPage;
			this.updateWidgets();
		}));

		// --- influence area page ---

		this.showRestrictBlockBreakingAreaButton = this.addRenderableWidget(CycleButton.booleanBuilder(HIDE_INFLUENCE_AREA_LABEL_TEXT, SHOW_INFLUENCE_AREA_LABEL_TEXT, this.showInfluenceArea).displayOnlyValue().create(this.width / 2 - 153, 45, 300, 20, Component.empty(), (button, showInfluenceArea) -> {
			this.showInfluenceArea = showInfluenceArea;
		}));

		this.restrictBlockBreakingAreaDimensionsXField = new EditBox(this.font, this.width / 2 - 154, 80, 100, 20, Component.empty());
		this.restrictBlockBreakingAreaDimensionsXField.setMaxLength(128);
		this.restrictBlockBreakingAreaDimensionsXField.setValue(Integer.toString(this.npcHousingBlockEntity != null ? this.npcHousingBlockEntity.getInfluenceAreaDimensions().getX() : 0));
		this.addWidget(this.restrictBlockBreakingAreaDimensionsXField);

		this.restrictBlockBreakingAreaDimensionsYField = new EditBox(this.font, this.width / 2 - 50, 80, 100, 20, Component.empty());
		this.restrictBlockBreakingAreaDimensionsYField.setMaxLength(128);
		this.restrictBlockBreakingAreaDimensionsYField.setValue(Integer.toString(this.npcHousingBlockEntity != null ? this.npcHousingBlockEntity.getInfluenceAreaDimensions().getY() : 0));
		this.addWidget(this.restrictBlockBreakingAreaDimensionsYField);

		this.restrictBlockBreakingAreaDimensionsZField = new EditBox(this.font, this.width / 2 + 54, 80, 100, 20, Component.empty());
		this.restrictBlockBreakingAreaDimensionsZField.setMaxLength(128);
		this.restrictBlockBreakingAreaDimensionsZField.setValue(Integer.toString(this.npcHousingBlockEntity != null ? this.npcHousingBlockEntity.getInfluenceAreaDimensions().getZ() : 0));
		this.addWidget(this.restrictBlockBreakingAreaDimensionsZField);

		this.restrictBlockBreakingAreaPositionOffsetXField = new EditBox(this.font, this.width / 2 - 154, 115, 100, 20, Component.empty());
		this.restrictBlockBreakingAreaPositionOffsetXField.setMaxLength(128);
		this.restrictBlockBreakingAreaPositionOffsetXField.setValue(Integer.toString(this.npcHousingBlockEntity != null ? this.npcHousingBlockEntity.getRestrictBlockBreakingAreaPositionOffset().getX() : 0));
		this.addWidget(this.restrictBlockBreakingAreaPositionOffsetXField);

		this.restrictBlockBreakingAreaPositionOffsetYField = new EditBox(this.font, this.width / 2 - 50, 115, 100, 20, Component.empty());
		this.restrictBlockBreakingAreaPositionOffsetYField.setMaxLength(128);
		this.restrictBlockBreakingAreaPositionOffsetYField.setValue(Integer.toString(this.npcHousingBlockEntity != null ? this.npcHousingBlockEntity.getRestrictBlockBreakingAreaPositionOffset().getY() : 0));
		this.addWidget(this.restrictBlockBreakingAreaPositionOffsetYField);

		this.restrictBlockBreakingAreaPositionOffsetZField = new EditBox(this.font, this.width / 2 + 54, 115, 100, 20, Component.empty());
		this.restrictBlockBreakingAreaPositionOffsetZField.setMaxLength(128);
		this.restrictBlockBreakingAreaPositionOffsetZField.setValue(Integer.toString(this.npcHousingBlockEntity != null ? this.npcHousingBlockEntity.getRestrictBlockBreakingAreaPositionOffset().getZ() : 0));
		this.addWidget(this.restrictBlockBreakingAreaPositionOffsetZField);

//		// --- triggered block page ---
//
//		this.triggeredBlockPositionOffsetXField = new TextFieldWidget(this.textRenderer, this.width / 2 - 154, 80, 50, 20, Text.empty());
//		this.triggeredBlockPositionOffsetXField.setMaxLength(128);
//		this.triggeredBlockPositionOffsetXField.setText(Integer.toString(this.npcHousingBlockEntity != null ? this.npcHousingBlockEntity.getTriggeredBlock().getLeft().getX() : 0));
//		this.addSelectableChild(this.triggeredBlockPositionOffsetXField);
//
//		this.triggeredBlockPositionOffsetYField = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, 80, 50, 20, Text.empty());
//		this.triggeredBlockPositionOffsetYField.setMaxLength(128);
//		this.triggeredBlockPositionOffsetYField.setText(Integer.toString(this.npcHousingBlockEntity != null ? this.npcHousingBlockEntity.getTriggeredBlock().getLeft().getY() : 0));
//		this.addSelectableChild(this.triggeredBlockPositionOffsetYField);
//
//		this.triggeredBlockPositionOffsetZField = new TextFieldWidget(this.textRenderer, this.width / 2 - 46, 80, 50, 20, Text.empty());
//		this.triggeredBlockPositionOffsetZField.setMaxLength(128);
//		this.triggeredBlockPositionOffsetZField.setText(Integer.toString(this.npcHousingBlockEntity != null ? this.npcHousingBlockEntity.getTriggeredBlock().getLeft().getZ() : 0));
//		this.addSelectableChild(this.triggeredBlockPositionOffsetZField);
//
//		this.triggeredBlockResets = this.npcHousingBlockEntity != null ? this.npcHousingBlockEntity.getTriggeredBlock().getRight() : false;
//		this.toggleTriggeredBlockResetsButton = this.addDrawableChild(CyclingButtonWidget.onOffBuilder(Text.translatable("gui.triggered_block.toggle_triggered_block_resets_button_label.on"), Text.translatable("gui.triggered_block.toggle_triggered_block_resets_button_label.off")).initially(this.triggeredBlockResets).omitKeyText().build(this.width / 2 + 8, 80, 150, 20, Text.empty(), (button, triggeredBlockResets) -> {
//			this.triggeredBlockResets = triggeredBlockResets;
//		}));
//		// --- owner page ---
//
//		this.toggleOwnerModeButton = this.addDrawableChild(CyclingButtonWidget.builder(HousingBlockEntity.OwnerMode::asText).values((HousingBlockEntity.OwnerMode[]) HousingBlockEntity.OwnerMode.values()).initially(this.ownerMode).omitKeyText().build(this.width / 2 - 153, 70, 300, 20, Text.empty(), (button, ownerMode) -> {
//			this.ownerMode = ownerMode;
//		}));
//
//		this.resetOwnerButton = this.addDrawableChild(ButtonWidget.builder(RESET_OWNER_BUTTON_LABEL_TEXT, button -> this.trySetHouseOwner(false)).dimensions(this.width / 2 - 4 - 150, 94, 300, 20).build());

		this.saveCreativeButton = this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, button -> this.done()).bounds(this.width / 2 - 4 - 150, 210, 150, 20).build());
		this.cancelCreativeButton = this.addRenderableWidget(Button.builder(CommonComponents.GUI_CANCEL, button -> this.cancel()).bounds(this.width / 2 + 4, 210, 150, 20).build());

		//endregion creative screen
		this.updateWidgets();
	}

	private void updateWidgets() {
/*
		//region adventure widgets
//		this.leaveCurrentHouseButton.visible = false;
//
//		this.openResetHouseScreenButton.visible = false;
//		this.resetHouseButton.visible = false;
//		this.closeResetHouseScreenButton.visible = false;
//
//		this.toggleAdventureBuildingEffectButton.visible = false;
//		this.unclaimHouseButton.visible = false;
//		this.claimHouseButton.visible = false;

		this.openCoOwnerListScreenButton.visible = false;
		this.newCoOwnerField.setVisible(false);
		this.addNewCoOwnerButton.visible = false;
		this.removeCoOwnerListEntryButton0.visible = false;
		this.removeCoOwnerListEntryButton1.visible = false;
		this.removeCoOwnerListEntryButton2.visible = false;
		this.removeCoOwnerListEntryButton3.visible = false;
		this.removeCoOwnerListEntryButton4.visible = false;

//		this.openTrustedPersonsListScreenButton.visible = false;
//		this.newTrustedPersonField.setVisible(false);
//		this.addNewTrustedPersonButton.visible = false;
//		this.removeTrustedPersonListEntryButton0.visible = false;
//		this.removeTrustedPersonListEntryButton1.visible = false;
//		this.removeTrustedPersonListEntryButton2.visible = false;
//		this.removeTrustedPersonListEntryButton3.visible = false;
//		this.removeTrustedPersonListEntryButton4.visible = false;
//
//		this.openGuestListScreenButton.visible = false;
//		this.newGuestField.setVisible(false);
//		this.addNewGuestButton.visible = false;
//		this.removeGuestListEntryButton0.visible = false;
//		this.removeGuestListEntryButton1.visible = false;
//		this.removeGuestListEntryButton2.visible = false;
//		this.removeGuestListEntryButton3.visible = false;
//		this.removeGuestListEntryButton4.visible = false;

		this.closeListEditScreensButton.visible = false;

//		this.closeAdventureScreenButton.visible = false;
		//endregion adventure widgets

		//region creative widgets
//		this.creativeScreenPageButton.visible = false;

		this.showRestrictBlockBreakingAreaButton.visible = false;
		this.restrictBlockBreakingAreaDimensionsXField.setVisible(false);
		this.restrictBlockBreakingAreaDimensionsYField.setVisible(false);
		this.restrictBlockBreakingAreaDimensionsZField.setVisible(false);
		this.restrictBlockBreakingAreaPositionOffsetXField.setVisible(false);
		this.restrictBlockBreakingAreaPositionOffsetYField.setVisible(false);
		this.restrictBlockBreakingAreaPositionOffsetZField.setVisible(false);

//		this.triggeredBlockPositionOffsetXField.setVisible(false);
//		this.triggeredBlockPositionOffsetYField.setVisible(false);
//		this.triggeredBlockPositionOffsetZField.setVisible(false);
//		this.toggleTriggeredBlockResetsButton.visible = false;
//
//		this.toggleOwnerModeButton.visible = false;
//		this.resetOwnerButton.visible = false;

		this.saveCreativeButton.visible = false;
		this.cancelCreativeButton.visible = false;

		if (this.showCreativeTab) {
			this.creativeScreenPageButton.visible = true;

			if (this.creativeScreenPage == CreativeScreenPage.INFLUENCE) {

				this.showRestrictBlockBreakingAreaButton.visible = true;
				this.restrictBlockBreakingAreaDimensionsXField.setVisible(true);
				this.restrictBlockBreakingAreaDimensionsYField.setVisible(true);
				this.restrictBlockBreakingAreaDimensionsZField.setVisible(true);
				this.restrictBlockBreakingAreaPositionOffsetXField.setVisible(true);
				this.restrictBlockBreakingAreaPositionOffsetYField.setVisible(true);
				this.restrictBlockBreakingAreaPositionOffsetZField.setVisible(true);

			} else if (this.creativeScreenPage == CreativeScreenPage.TRIGGERED_BLOCK) {

				this.triggeredBlockPositionOffsetXField.setVisible(true);
				this.triggeredBlockPositionOffsetYField.setVisible(true);
				this.triggeredBlockPositionOffsetZField.setVisible(true);
				this.toggleTriggeredBlockResetsButton.visible = true;

			} else if (this.creativeScreenPage == CreativeScreenPage.OWNER) {

				this.toggleOwnerModeButton.visible = true;
				this.resetOwnerButton.visible = true;

			}

			this.saveCreativeButton.visible = true;
			this.cancelCreativeButton.visible = true;

		} else {
			if (this.showCoOwnerListScreen) {

				this.newCoOwnerField.setVisible(true);
				this.addNewCoOwnerButton.visible = true;

				int index = 0;
				for (int i = 0; i < Math.min(5, this.coOwnerList.size()); i++) {
					if (index == 0) {
						this.removeCoOwnerListEntryButton0.visible = true;
					} else if (index == 1) {
						this.removeCoOwnerListEntryButton1.visible = true;
					} else if (index == 2) {
						this.removeCoOwnerListEntryButton2.visible = true;
					} else if (index == 3) {
						this.removeCoOwnerListEntryButton3.visible = true;
					} else if (index == 4) {
						this.removeCoOwnerListEntryButton4.visible = true;
					}
					index++;
				}

				this.closeListEditScreensButton.visible = true;

//			} else if (this.showTrustedListScreen) {
//
//				this.newTrustedPersonField.setVisible(true);
//				this.addNewTrustedPersonButton.visible = true;
//				int index = 0;
//				for (int i = 0; i < Math.min(5, this.coOwnerList.size()); i++) {
//					if (index == 0) {
//						this.removeTrustedPersonListEntryButton0.visible = true;
//					} else if (index == 1) {
//						this.removeTrustedPersonListEntryButton1.visible = true;
//					} else if (index == 2) {
//						this.removeTrustedPersonListEntryButton2.visible = true;
//					} else if (index == 3) {
//						this.removeTrustedPersonListEntryButton3.visible = true;
//					} else if (index == 4) {
//						this.removeTrustedPersonListEntryButton4.visible = true;
//					}
//					index++;
//				}
//
//				this.closeListEditScreensButton.visible = true;
//
//			} else if (this.showGuestListScreen) {
//
//				this.newGuestField.setVisible(true);
//				this.addNewGuestButton.visible = true;
//
//				int index = 0;
//				for (int i = 0; i < Math.min(5, this.coOwnerList.size()); i++) {
//					if (index == 0) {
//						this.removeGuestListEntryButton0.visible = true;
//					} else if (index == 1) {
//						this.removeGuestListEntryButton1.visible = true;
//					} else if (index == 2) {
//						this.removeGuestListEntryButton2.visible = true;
//					} else if (index == 3) {
//						this.removeGuestListEntryButton3.visible = true;
//					} else if (index == 4) {
//						this.removeGuestListEntryButton4.visible = true;
//					}
//					index++;
//				}
//
//				this.closeListEditScreensButton.visible = true;
//
//			} else if (this.showResetHouseScreen) {
//
//				this.resetHouseButton.visible = true;
//				this.closeResetHouseScreenButton.visible = true;
//
//			} else {
//
//				if (this.currentPermissionLevel == 0) {
//
//					this.toggleAdventureBuildingEffectButton.visible = true;
//
//					this.openCoOwnerListScreenButton.visible = true;
//					this.openTrustedPersonsListScreenButton.visible = true;
//					this.openGuestListScreenButton.visible = true;
//
//					this.openResetHouseScreenButton.visible = true;
//
////					if (this.npcHousingBlockEntity != null && this.npcHousingBlockEntity.getOwnerMode() == HousingBlockEntity.OwnerMode.INTERACTION) {
////						this.unclaimHouseButton.visible = true;
////					}
//
//				} else if (this.currentPermissionLevel == 1) {
//
//					this.toggleAdventureBuildingEffectButton.visible = true;
//
//				} else if (this.currentPermissionLevel == 4) {
//
////					if (this.npcHousingBlockEntity != null && this.npcHousingBlockEntity.getOwnerMode() == HousingBlockEntity.OwnerMode.INTERACTION && !this.npcHousingBlockEntity.isOwnerSet()) {
////						this.claimHouseButton.visible = true;
////					}
//
//				}
//
//				this.leaveCurrentHouseButton.visible = true;
//
//				this.closeAdventureScreenButton.visible = true;
			}
		}*/
	}

	@Override
	public void resize(int width, int height) {
//		List<String> list = new ArrayList<>(this.coOwnerList);
//		List<String> list1 = new ArrayList<>(this.trustedPersonsList);
//		List<String> list2 = new ArrayList<>(this.guestList);
//		boolean bool = this.showInfluenceArea;
//		HousingBlockEntity.OwnerMode var = this.ownerMode;
//		int number = this.scrollPosition;
//		float number1 = this.scrollAmount;
//		String string = this.newCoOwnerField.getText();
//		String string1 = this.newTrustedPersonField.getText();
//		String string2 = this.newGuestField.getText();
//		String string3 = this.restrictBlockBreakingAreaDimensionsXField.getText();
//		String string4 = this.restrictBlockBreakingAreaDimensionsYField.getText();
//		String string5 = this.restrictBlockBreakingAreaDimensionsZField.getText();
//		String string6 = this.restrictBlockBreakingAreaPositionOffsetXField.getText();
//		String string7 = this.restrictBlockBreakingAreaPositionOffsetYField.getText();
//		String string8 = this.restrictBlockBreakingAreaPositionOffsetZField.getText();
//		String string9 = this.triggeredBlockPositionOffsetXField.getText();
//		String string10 = this.triggeredBlockPositionOffsetYField.getText();
//		String string11 = this.triggeredBlockPositionOffsetZField.getText();
//		boolean boolean2 = this.triggeredBlockResets;
		this.init(width, height);
//		this.coOwnerList.clear();
//		this.trustedPersonsList.clear();
//		this.guestList.clear();
//		this.coOwnerList.addAll(list);
//		this.trustedPersonsList.addAll(list1);
//		this.guestList.addAll(list2);
//		this.showInfluenceArea = bool;
//		this.ownerMode = var;
//		this.scrollPosition = number;
//		this.scrollAmount = number1;
//		this.newCoOwnerField.setText(string);
//		this.newTrustedPersonField.setText(string1);
//		this.newGuestField.setText(string2);
//		this.restrictBlockBreakingAreaDimensionsXField.setText(string3);
//		this.restrictBlockBreakingAreaDimensionsYField.setText(string4);
//		this.restrictBlockBreakingAreaDimensionsZField.setText(string5);
//		this.restrictBlockBreakingAreaPositionOffsetXField.setText(string6);
//		this.restrictBlockBreakingAreaPositionOffsetYField.setText(string7);
//		this.restrictBlockBreakingAreaPositionOffsetZField.setText(string8);
//		this.triggeredBlockPositionOffsetXField.setText(string9);
//		this.triggeredBlockPositionOffsetYField.setText(string10);
//		this.triggeredBlockPositionOffsetZField.setText(string11);
//		this.triggeredBlockResets = boolean2;
//		this.updateWidgets();
	}


	@Override
	public boolean mouseClicked(MouseButtonEvent click, boolean doubled) {
//		this.mouseClicked = false;
//		if (!this.showCreativeTab
//				&& ((this.showCoOwnerListScreen && this.unlockedNPCs.size() > 5)
//				|| (this.showTrustedListScreen && this.unlockedNPCs.size() > 5)
//				|| (this.showGuestListScreen && this.unlockedNPCs.size() > 5))) {
//			int i = this.x + 8;
//			int j = this.y + 34;
//			if (mouseX >= (double) i && mouseX < (double) (i + 6) && mouseY >= (double) j && mouseY < (double) (j + 115)) {
//				this.mouseClicked = true;
//			}
//		}
		return super.mouseClicked(click, doubled);
	}

	@Override
	public boolean mouseDragged(MouseButtonEvent click, double offsetX, double offsetY) {
//		if (!this.showCreativeTab
//				&& this.showCoOwnerListScreen
//				&& this.unlockedNPCs.size() > 5
//				&& this.mouseClicked) {
//			int i = this.unlockedNPCs.size() - 5;
//			float f = (float) deltaY / (float) i;
//			this.scrollAmount = MathHelper.clamp(this.scrollAmount + f, 0.0f, 1.0f);
//			this.scrollPosition = (int) ((double) (this.scrollAmount * (float) i));
//		}
//		if (!this.showCreativeTab
//				&& this.showTrustedListScreen
//				&& this.trustedPersonsList.size() > 5
//				&& this.mouseClicked) {
//			int i = this.trustedPersonsList.size() - 5;
//			float f = (float) deltaY / (float) i;
//			this.scrollAmount = MathHelper.clamp(this.scrollAmount + f, 0.0f, 1.0f);
//			this.scrollPosition = (int) ((double) (this.scrollAmount * (float) i));
//		}
//		if (!this.showCreativeTab
//				&& this.showGuestListScreen
//				&& this.guestList.size() > 5
//				&& this.mouseClicked) {
//			int i = this.guestList.size() - 5;
//			float f = (float) deltaY / (float) i;
//			this.scrollAmount = MathHelper.clamp(this.scrollAmount + f, 0.0f, 1.0f);
//			this.scrollPosition = (int) ((double) (this.scrollAmount * (float) i));
//		}
		return super.mouseDragged(click, offsetX, offsetY);
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
//		if (!this.showCreativeTab
//				&& this.showCoOwnerListScreen
//				&& this.unlockedNPCs.size() > 5
//				&& mouseX >= (double) (this.x + 7) && mouseX <= (double) (this.x + this.backgroundWidth - 61)
//				&& mouseY >= 34 && mouseY <= 148) {
//			int i = this.unlockedNPCs.size() - 5;
//			float f = (float) verticalAmount / (float) i;
//			this.scrollAmount = MathHelper.clamp(this.scrollAmount - f, 0.0f, 1.0f);
//			this.scrollPosition = (int) ((double) (this.scrollAmount * (float) i));
//		}
//		if (!this.showCreativeTab
//				&& this.showTrustedListScreen
//				&& this.trustedPersonsList.size() > 5
//				&& mouseX >= (double) (this.x + 7) && mouseX <= (double) (this.x + this.backgroundWidth - 61)
//				&& mouseY >= 34 && mouseY <= 148) {
//			int i = this.trustedPersonsList.size() - 5;
//			float f = (float) verticalAmount / (float) i;
//			this.scrollAmount = MathHelper.clamp(this.scrollAmount - f, 0.0f, 1.0f);
//			this.scrollPosition = (int) ((double) (this.scrollAmount * (float) i));
//		}
//		if (!this.showCreativeTab
//				&& this.showGuestListScreen
//				&& this.guestList.size() > 5
//				&& mouseX >= (double) (this.x + 7) && mouseX <= (double) (this.x + this.backgroundWidth - 61)
//				&& mouseY >= 34 && mouseY <= 148) {
//			int i = this.guestList.size() - 5;
//			float f = (float) verticalAmount / (float) i;
//			this.scrollAmount = MathHelper.clamp(this.scrollAmount - f, 0.0f, 1.0f);
//			this.scrollPosition = (int) ((double) (this.scrollAmount * (float) i));
//		}
		return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
	}

	@Override
	public boolean keyPressed(KeyEvent input) {
		if (input.isConfirmation()) {
			this.done();
			return true;
		}
		return super.keyPressed(input);
	}

	@Override
	public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {

//		this.renderBackground(context, mouseX, mouseY, delta);

//		if (this.showCreativeTab) {
		if (this.creativeScreenPage == CreativeScreenPage.HOUSE) {
			context.drawString(this.font, INFLUENCE_AREA_DIMENSIONS_LABEL_TEXT, this.width / 2 - 153, 70, 0xA0A0A0, false);
			this.restrictBlockBreakingAreaDimensionsXField.render(context, mouseX, mouseY, delta);
			this.restrictBlockBreakingAreaDimensionsYField.render(context, mouseX, mouseY, delta);
			this.restrictBlockBreakingAreaDimensionsZField.render(context, mouseX, mouseY, delta);
			context.drawString(this.font, INFLUENCE_AREA_POSITION_OFFET_LABEL_TEXT, this.width / 2 - 153, 105, 0xA0A0A0, false);
			this.restrictBlockBreakingAreaPositionOffsetXField.render(context, mouseX, mouseY, delta);
			this.restrictBlockBreakingAreaPositionOffsetYField.render(context, mouseX, mouseY, delta);
			this.restrictBlockBreakingAreaPositionOffsetZField.render(context, mouseX, mouseY, delta);
		} else if (this.creativeScreenPage == CreativeScreenPage.RESIDENT) {
//				context.drawTextWithShadow(this.textRenderer, TRIGGERED_BLOCK_POSITION_OFFSET_LABEL_TEXT, this.width / 2 - 153, 70, 0xA0A0A0);
//				this.triggeredBlockPositionOffsetXField.render(context, mouseX, mouseY, delta);
//				this.triggeredBlockPositionOffsetYField.render(context, mouseX, mouseY, delta);
//				this.triggeredBlockPositionOffsetZField.render(context, mouseX, mouseY, delta);
		}
//		} else {
//			if (this.showResetHouseScreen) {
//			} else if (this.showCoOwnerListScreen) {
//				context.drawText(this.textRenderer, TITLE_CO_OWNER_LIST_LABEL_TEXT, this.x + 8, this.y + 7, 0x404040, false);
//				context.drawText(this.textRenderer, TITLE_CO_OWNER_LIST_DESCRIPTION_LABEL_TEXT, this.x + 8, this.y + 20, 0x404040, false);
//				for (int i = this.scrollPosition; i < Math.min(this.scrollPosition + 5, this.unlockedNPCs.size()); i++) {
//					String text = this.unlockedNPCs.get(i);
//					context.drawText(this.textRenderer, text, this.x + 19, this.y + 39 + ((i - this.scrollPosition) * 24), 0x404040, false);
//				}
//				if (this.unlockedNPCs.size() > 5) {
////                    context.drawGuiTexture(PLAYER_LISTS_SCROLLER_BACKGROUND_TEXTURE, this.x + 7, this.y + 33, 8, 116);
//					context.drawTexture(PLAYER_LISTS_SCROLLER_BACKGROUND_TEXTURE, this.x + 7, this.y + 33, 0, 0, 8, 116);
//					int k = (int) (107.0f * this.scrollAmount);
////                    context.drawGuiTexture(SCROLLER_TEXTURE, this.x + 8, this.y + 33 + 1 + k, 6, 7);
//					context.drawTexture(SCROLLER_TEXTURE, this.x + 8, this.y + 33 + 1 + k, 0, 0, 6, 7);
//				}
//				this.newCoOwnerField.render(context, mouseX, mouseY, delta);
//			} else if (this.showTrustedListScreen) {
//				context.drawText(this.textRenderer, TITLE_TRUSTED_LIST_LABEL_TEXT, this.x + 8, this.y + 7, 0x404040, false);
//				context.drawText(this.textRenderer, TITLE_TRUSTED_LIST_DESCRIPTION_LABEL_TEXT, this.x + 8, this.y + 20, 0x404040, false);
//				for (int i = this.scrollPosition; i < Math.min(this.scrollPosition + 5, this.trustedPersonsList.size()); i++) {
//					String text = this.trustedPersonsList.get(i);
//					context.drawText(this.textRenderer, text, this.x + 19, this.y + 39 + ((i - this.scrollPosition) * 24), 0x404040, false);
//				}
//				if (this.trustedPersonsList.size() > 5) {
////                    context.drawGuiTexture(PLAYER_LISTS_SCROLLER_BACKGROUND_TEXTURE, this.x + 7, this.y + 33, 8, 116);
//					context.drawTexture(PLAYER_LISTS_SCROLLER_BACKGROUND_TEXTURE, this.x + 7, this.y + 33, 0, 0, 8, 116);
//					int k = (int) (107.0f * this.scrollAmount);
////                    context.drawGuiTexture(SCROLLER_TEXTURE, this.x + 8, this.y + 33 + 1 + k, 6, 7);
//					context.drawTexture(SCROLLER_TEXTURE, this.x + 8, this.y + 33 + 1 + k, 0, 0, 6, 7);
//				}
//				this.newTrustedPersonField.render(context, mouseX, mouseY, delta);
//			} else if (this.showGuestListScreen) {
//				context.drawText(this.textRenderer, TITLE_GUEST_LIST_LABEL_TEXT, this.x + 8, this.y + 7, 0x404040, false);
//				context.drawText(this.textRenderer, TITLE_GUEST_LIST_DESCRIPTION_LABEL_TEXT, this.x + 8, this.y + 20, 0x404040, false);
//				for (int i = this.scrollPosition; i < Math.min(this.scrollPosition + 5, this.guestList.size()); i++) {
//					String text = this.guestList.get(i);
//					context.drawText(this.textRenderer, text, this.x + 19, this.y + 39 + ((i - this.scrollPosition) * 24), 0x404040, false);
//				}
//				if (this.guestList.size() > 5) {
////                    context.drawGuiTexture(PLAYER_LISTS_SCROLLER_BACKGROUND_TEXTURE, this.x + 7, this.y + 33, 8, 116);
//					context.drawTexture(PLAYER_LISTS_SCROLLER_BACKGROUND_TEXTURE, this.x + 7, this.y + 33, 0, 0, 8, 116);
//					int k = (int) (107.0f * this.scrollAmount);
////                    context.drawGuiTexture(SCROLLER_TEXTURE, this.x + 8, this.y + 33 + 1 + k, 6, 7);
//					context.drawTexture(SCROLLER_TEXTURE, this.x + 8, this.y + 33 + 1 + k, 0, 0, 6, 7);
//				}
//				this.newGuestField.render(context, mouseX, mouseY, delta);
//			} else {
//				if (this.currentPermissionLevel == 0) {
//					context.drawText(this.textRenderer, TITLE_OWNER_LABEL_TEXT, this.x + 8, this.y + 7, 0x404040, false);
//				} else if (this.currentPermissionLevel == 1) {
//					context.drawText(this.textRenderer, TITLE_CO_OWNER_LABEL_TEXT, this.x + 8, this.y + 7, 0x404040, false);
//				} else if (this.currentPermissionLevel == 2) {
//					context.drawText(this.textRenderer, TITLE_TRUSTED_LABEL_TEXT, this.x + 8, this.y + 7, 0x404040, false);
//				} else if (this.currentPermissionLevel == 3) {
//					context.drawText(this.textRenderer, TITLE_GUEST_LABEL_TEXT, this.x + 8, this.y + 7, 0x404040, false);
//				} else if (this.currentPermissionLevel == 4) {
//					context.drawText(this.textRenderer, TITLE_STRANGER_LABEL_TEXT, this.x + 8, this.y + 7, 0x404040, false);
//				}
//			}
//		}

		super.render(context, mouseX, mouseY, delta);
	}


	@Override
	public boolean isPauseScreen() {
		return false;
	}

	@Override
	public void renderBackground(GuiGraphics context, int mouseX, int mouseY, float delta) {
		super.renderBackground(context, mouseX, mouseY, delta);
		this.drawBackground(context, delta, mouseX, mouseY);
	}

	public void drawBackground(GuiGraphics context, float delta, int mouseX, int mouseY) {
//		if (!this.showCreativeTab) {
		int i = this.x;
		int j = this.y;
//			if (this.currentPermissionLevel == 0) {
//				context.drawTexture(BACKGROUND_218_215_TEXTURE, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight, this.backgroundWidth, this.backgroundHeight);
//			} else if (this.currentPermissionLevel == 1 || this.ownerMode == HousingBlockEntity.OwnerMode.INTERACTION) {
//				context.drawTexture(BACKGROUND_218_95_TEXTURE, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight, this.backgroundWidth, this.backgroundHeight);
//			} else {
		context.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND_176_166_TEXTURE, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight, this.backgroundWidth, this.backgroundHeight);
//			}
//		}
	}

//	private boolean updateHousingBlockCreative() {
//		BlockPos housingBlockPos = new BlockPos(0, 0, 0);
//		if (this.npcHousingBlockEntity != null) {
//			housingBlockPos = this.npcHousingBlockEntity.getPos();
//		}
//		ClientPlayNetworking.send(new UpdateHousingBlockCreativePacket(
//				housingBlockPos,
//				this.showInfluenceArea,
//				new Vec3i(
//						ItemUtils.parseInt(this.restrictBlockBreakingAreaDimensionsXField.getText()),
//						ItemUtils.parseInt(this.restrictBlockBreakingAreaDimensionsYField.getText()),
//						ItemUtils.parseInt(this.restrictBlockBreakingAreaDimensionsZField.getText())
//				),
//				new BlockPos(
//						ItemUtils.parseInt(this.restrictBlockBreakingAreaPositionOffsetXField.getText()),
//						ItemUtils.parseInt(this.restrictBlockBreakingAreaPositionOffsetYField.getText()),
//						ItemUtils.parseInt(this.restrictBlockBreakingAreaPositionOffsetZField.getText())
//				),
//				new BlockPos(
//						ItemUtils.parseInt(this.triggeredBlockPositionOffsetXField.getText()),
//						ItemUtils.parseInt(this.triggeredBlockPositionOffsetYField.getText()),
//						ItemUtils.parseInt(this.triggeredBlockPositionOffsetZField.getText())
//				),
//				this.triggeredBlockResets,
//				this.ownerMode.asString()
//		));
//		return true;
//	}

	private void updateNPCHousingBlock() {
//		if (this.npcHousingBlockEntity != null) {
//			ClientPlayNetworking.send(new UpdateHousingBlockAdventurePacket(
//					this.npcHousingBlockEntity.getPos(),
//					this.unlockedNPCs,
//					this.trustedPersonsList,
//					this.guestList
//			));
//		}
	}

	//	private void toggleAdventureBuildingEffect() {
//		ClientPlayNetworking.send(new AddStatusEffectPacket(
//				Registries.STATUS_EFFECT.getId(StatusEffectsRegistry.BUILDING_MODE),
//				-1,
//				0,
//				false,
//				false,
//				true,
//				true
//		));
//		this.close();
//	}
//
//	private void resetHouse() {
//		if (this.npcHousingBlockEntity != null) {
//			ClientPlayNetworking.send(new ResetHouseHousingBlockPacket(
//					this.npcHousingBlockEntity.getPos()
//			));
//		}
//		this.close();
//	}
//
//	private void trySetHouseOwner(boolean claim) {
//		if (this.npcHousingBlockEntity != null && this.client != null && this.client.player != null) {
//			ClientPlayNetworking.send(new SetHousingBlockOwnerPacket(
//					this.npcHousingBlockEntity.getPos(),
//					claim ? this.client.player.getUuidAsString() : ""
//			));
//		}
//		this.close();
//	}
//
	public static enum CreativeScreenPage implements StringRepresentable {
		HOUSE("house"),
		RESIDENT("resident");

		private final String name;

		private CreativeScreenPage(String name) {
			this.name = name;
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}

		public static Optional<CreativeScreenPage> byName(String name) {
			return Arrays.stream(CreativeScreenPage.values()).filter(creativeScreenPage -> creativeScreenPage.getSerializedName().equals(name)).findFirst();
		}

		public Component asText() {
			return Component.translatable("gui.housing_screen.creativeScreenPage." + this.name);
		}
	}
}
