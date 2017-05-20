package pcl.opensensors.common.items;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.world.World;
import pcl.opensensors.OpenSensors;
import pcl.opensensors.common.tileentity.TileEntitySensor;

public class ItemBlankSensor extends ItemSensorBase {
	
	public ItemBlankSensor() {
		super();
		setUnlocalizedName(OpenSensors.MODID + ".blanksensor");
		setTextureName(OpenSensors.MODID + ":blankSensor");
	}
	
	@Override
	@Callback
	public Object[] get(Context context, Arguments args, World worldIn, TileEntitySensor teIn) {
		return new Object[] { "This is a blank card." };
	}
}
