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

	public String[] methods = {
			"biome args: x:int z:int", 
			"lightlevel args: x:int y:int z:int", 
			"raining args: none", 
			"thundering args: none", 
			"daytime args: none", 
			"moonphase args: none", 
			"celestialangle args: none",
			"dimension args: none", 
			"temperature args: x:int z:int", 
			"highhumidity args: x:int z:int", 
			"humidity args: x:int z:int",
			"worldseed args: none"
	};

	@Override
	@Callback
	public String[] getMethods(Context context, Arguments args) {
		return methods;
	}

	public int rangeLimit(int range) {
		if(range >= 0 && range <= 64) {
			return range;
		}
		return 64;
	}

	@Override
	@Callback
	public Object[] get(Context context, Arguments args, World worldIn, TileEntitySensor teIn) {
		if (args.checkString(0).equalsIgnoreCase("biome")) {
			return new Object[] { worldIn.getBiomeGenForCoords((teIn.xCoord + rangeLimit(args.optInteger(1, 0))), (teIn.zCoord + rangeLimit(args.optInteger(1, 0)))).biomeName };
		} else if (args.checkString(0).equalsIgnoreCase("lightlevel")) {
			return new Object[] { worldIn.getBlockLightValue((teIn.xCoord + rangeLimit(args.optInteger(1, 0))), (teIn.yCoord + rangeLimit(rangeLimit(args.optInteger(1, 0)))), (teIn.zCoord + rangeLimit(args.optInteger(1, 0)))) };
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
			return new Object[] { worldIn.getBiomeGenForCoords((teIn.xCoord + rangeLimit(args.optInteger(1, 0))), (teIn.zCoord + rangeLimit(args.optInteger(1, 0)))).temperature};
		} else if (args.checkString(0).equalsIgnoreCase("highhumidity")) {
			return new Object[] { worldIn.getBiomeGenForCoords((teIn.xCoord + rangeLimit(args.optInteger(1, 0))), (teIn.zCoord + rangeLimit(args.optInteger(1, 0)))).isHighHumidity()};
		} else if (args.checkString(0).equalsIgnoreCase("humidity")) {
			return new Object[] { worldIn.getBiomeGenForCoords((teIn.xCoord + rangeLimit(args.optInteger(1, 0))), (teIn.zCoord + rangeLimit(args.optInteger(1, 0)))).rainfall};
		} else if (args.checkString(0).equalsIgnoreCase("worldseed")) {
			return new Object[] { worldIn.getSeed()};
		}
		return new Object[] { "No method passed, or not found" };
	}
}
