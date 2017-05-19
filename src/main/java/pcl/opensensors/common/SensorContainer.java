package pcl.opensensors.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import pcl.opensensors.common.tileentity.TileEntitySensor;
import pcl.opensensors.client.gui.CardSlot;

public class SensorContainer extends Container {

	protected TileEntitySensor tileEntity;
	private Slot CardSlot;
	private List<Slot> specialSlots;
	private List<Slot> outputSlots;
	private List<Slot> playerSlots;
	private List<Slot> hotbarSlots;
	
	public SensorContainer(InventoryPlayer inventory, TileEntitySensor te) {
		tileEntity = te;
		// RFID Input
		CardSlot = addSlotToContainer(new CardSlot(tileEntity, 0, 80, 36));

		specialSlots = new ArrayList<Slot>();
		specialSlots.add(CardSlot);

		// Output slots
		outputSlots = new ArrayList<Slot>();
		for (int i = 3; i < 12; i++) {
			outputSlots.add(addSlotToContainer(new Slot(tileEntity, i, 8 + i * 18 - 54, 87)));
		}

		// commonly used vanilla code that adds the player's inventory
		bindPlayerInventory(inventory);

	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}

	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		playerSlots = new ArrayList<Slot>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				playerSlots.add(addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18 + 15 + 15)));
			}
		}

		hotbarSlots = new ArrayList<Slot>();
		for (int i = 0; i < 9; i++) {
			hotbarSlots.add(addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142 + 15 + 15)));
		}
	}

}
