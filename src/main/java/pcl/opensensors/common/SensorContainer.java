package pcl.opensensors.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import pcl.opensensors.common.tileentity.TileEntitySensor;
import pcl.opensensors.client.gui.CardSlot;

public class SensorContainer extends Container {

	protected TileEntitySensor tileEntity;
	private Slot CardSlot;
	private List<Slot> specialSlots;
	private List<Slot> playerSlots;
	private List<Slot> hotbarSlots;
	
	public SensorContainer(InventoryPlayer inventory, TileEntitySensor te) {
		tileEntity = te;
		// RFID Input
		CardSlot = addSlotToContainer(new CardSlot(tileEntity, 0, 80, 36));

		specialSlots = new ArrayList<Slot>();
		specialSlots.add(CardSlot);


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
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		// Assume inventory and hotbar slot IDs are contiguous
		int inventoryStart = playerSlots.get(0).slotNumber;
		int hotbarStart = hotbarSlots.get(0).slotNumber;
		int hotbarEnd = hotbarSlots.get(hotbarSlots.size() - 1).slotNumber + 1;

		// null checks and checks if the item can be stacked (maxStackSize > 1)
		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();

			if (slot >= inventoryStart && slot < hotbarEnd) {
				// If the item is a 'special' item, try putting it into its
				// special slot
				boolean handledSpecialItem = false;
				for (Slot ss : specialSlots) {
					if (!tileEntity.isItemValidForSlot(ss.getSlotIndex(), stackInSlot))
						continue;
					handledSpecialItem = mergeItemStack(stackInSlot, ss.slotNumber, ss.slotNumber + 1, false);
					if (handledSpecialItem)
						break;
				}

				// Else treat it like any normal item
				if (!handledSpecialItem) {
					if (slot >= inventoryStart && slot < hotbarStart) {
						if (!mergeItemStack(stackInSlot, hotbarStart, hotbarEnd, false))
							return null;
					} else if (slot >= hotbarStart && slot < hotbarEnd && !this.mergeItemStack(stackInSlot, inventoryStart, hotbarStart, false))
						return null;
				}
			}
			// Try merge stack into inventory
			else if (!mergeItemStack(stackInSlot, inventoryStart, hotbarEnd, false))
				return null;

			if (stackInSlot.stackSize == 0) {
				slotObject.putStack(null);
			} else {
				slotObject.onSlotChanged();
			}

			if (stackInSlot.stackSize == stack.stackSize) {
				return null;
			}
			slotObject.onPickupFromSlot(player, stackInSlot);
		}
		return stack;
	}
	
	@Override
	public boolean mergeItemStack(ItemStack stack, int begin, int end, boolean backwards) {
		int i = backwards ? end-1 : begin, increment = backwards ? -1 : 1;
		boolean flag = false;
		while(stack.stackSize > 0 && i >= begin && i < end) {
			Slot slot = this.getSlot(i);
			ItemStack slotStack = slot.getStack();
			int slotStacklimit = i<tileEntity.getSizeInventory() ? tileEntity.getInventoryStackLimit() : 64;
			int totalLimit = slotStacklimit < stack.getMaxStackSize() ? slotStacklimit : stack.getMaxStackSize();

			if(slotStack==null) {
				int transfer = totalLimit < stack.stackSize ? totalLimit : stack.stackSize;
				ItemStack stackToPut = stack.copy();
				stackToPut.stackSize = transfer;
				slot.putStack(stackToPut);
				slot.onSlotChanged();
				stack.stackSize -= transfer;
				flag = true;
			}
			else if((!stack.getHasSubtypes() || stack.getItemDamage() == slotStack.getItemDamage()) && ItemStack.areItemStackTagsEqual(stack, slotStack)) {
				int maxTransfer = totalLimit - slotStack.stackSize;
				int transfer = maxTransfer > stack.stackSize ? stack.stackSize : maxTransfer;
				slotStack.stackSize += transfer;
				slot.onSlotChanged();
				stack.stackSize -= transfer;
				flag = true;
			}


			i += increment;
		}

		return flag;

	}

}
