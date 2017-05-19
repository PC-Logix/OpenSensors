package pcl.opensensors.client.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import pcl.opensensors.common.items.ItemSensorBase;

public class CardSlot extends Slot {

	public CardSlot(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
	}

	@Override
    public int getSlotStackLimit()
    {
        return 1;
    }
	
	@Override
	public boolean isItemValid(ItemStack itemstack) {
		if (itemstack.getItem() instanceof ItemSensorBase) {
			return true;
		}
		return false;
	}
}