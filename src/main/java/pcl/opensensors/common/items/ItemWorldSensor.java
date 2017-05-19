package pcl.opensensors.common.items;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.world.World;

public class ItemWorldSensor extends ItemSensorBase {
	@Override
	@Callback
	public Object[] get(Context context, Arguments args, World worldIn) {
		return new Object[] { worldIn.getBiomeGenForCoords(args.checkInteger(0), args.checkInteger(1)).biomeName};
	}
}
