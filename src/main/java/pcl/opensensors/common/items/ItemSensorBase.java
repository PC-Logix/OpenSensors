package pcl.opensensors.common.items;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import pcl.opensensors.common.tileentity.TileEntitySensor;

public class ItemSensorBase extends Item {
	
	@Callback
	public Object[] get(Context context, Arguments args, World worldIn, TileEntitySensor teIn) {
		return new Object[] { "It's got." };
	}
	
	public String[] getMethods(Context context, Arguments args) {
		return null;
	}
	
}
