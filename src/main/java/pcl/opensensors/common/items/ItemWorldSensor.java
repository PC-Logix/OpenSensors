package pcl.opensensors.common.items;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.world.World;

public class ItemWorldSensor extends ItemSensorBase {
	@Override
	@Callback
	public Object[] get(Context context, Arguments args, World worldIn) {
		if (args.checkString(0).equalsIgnoreCase("biome")) {
			return new Object[] { worldIn.getBiomeGenForCoords(args.checkInteger(1), args.checkInteger(2)).biomeName};
		}
		return new Object[] { "No method passed, or not found" };
	}
}
