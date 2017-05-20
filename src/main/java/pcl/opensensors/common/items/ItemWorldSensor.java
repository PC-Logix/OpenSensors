package pcl.opensensors.common.items;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.world.World;
import pcl.opensensors.OpenSensors;
import pcl.opensensors.common.tileentity.TileEntitySensor;

public class ItemWorldSensor extends ItemSensorBase {
	
	public ItemWorldSensor() {
		super();
		setUnlocalizedName(OpenSensors.MODID + ".worldsensor");
		setTextureName(OpenSensors.MODID + ":worldSensor");
	}
	
	@Override
	@Callback
	public Object[] get(Context context, Arguments args, World worldIn, TileEntitySensor teIn) {
		if (args.checkString(0).equalsIgnoreCase("biome")) {
			return new Object[] { worldIn.getBiomeGenForCoords((teIn.xCoord + args.checkInteger(1)), (teIn.zCoord + args.checkInteger(2))).biomeName };
		} else if (args.checkString(0).equalsIgnoreCase("lightlevel")) {
			return new Object[] { worldIn.getBlockLightValue((teIn.xCoord + args.checkInteger(1)), (teIn.yCoord + args.checkInteger(2)), (teIn.zCoord + args.checkInteger(3))) };
		} else if (args.checkString(0).equalsIgnoreCase("raining")) {
			return new Object[] { worldIn.isRaining() };
		} else if (args.checkString(0).equalsIgnoreCase("thundering")) {
			return new Object[] { worldIn.isThundering() };
		} else if (args.checkString(0).equalsIgnoreCase("daytime")) {
			return new Object[] { worldIn.isDaytime() };
		} else if (args.checkString(0).equalsIgnoreCase("moonphase")) {
			return new Object[] { worldIn.getCurrentMoonPhaseFactor() };
		} else if (args.checkString(0).equalsIgnoreCase("celestialangle")) {
			return new Object[] { worldIn.getCelestialAngle(1.0F)};
		} else if (args.checkString(0).equalsIgnoreCase("dimension")) {
			return new Object[] { worldIn.getWorldInfo().getVanillaDimension()};
		} else if (args.checkString(0).equalsIgnoreCase("temperature") || args.checkString(0).equalsIgnoreCase("temp")) {
			return new Object[] { worldIn.getBiomeGenForCoords((teIn.xCoord + args.checkInteger(1)), (teIn.zCoord + args.checkInteger(2))).temperature};
		} else if (args.checkString(0).equalsIgnoreCase("highhumidity")) {
			return new Object[] { worldIn.getBiomeGenForCoords((teIn.xCoord + args.checkInteger(1)), (teIn.zCoord + args.checkInteger(2))).isHighHumidity()};
		} else if (args.checkString(0).equalsIgnoreCase("humidity")) {
			return new Object[] { worldIn.getBiomeGenForCoords((teIn.xCoord + args.checkInteger(1)), (teIn.zCoord + args.checkInteger(2))).rainfall};
		}
		return new Object[] { "No method passed, or not found" };
	}
}
