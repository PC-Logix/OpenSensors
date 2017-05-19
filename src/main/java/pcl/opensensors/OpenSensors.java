package pcl.opensensors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import pcl.opensensors.common.CommonProxy;

@Mod(modid = OpenSensors.MODID, name = "OpenSensors", version = BuildInfo.versionNumber + "." + BuildInfo.buildNumber, dependencies = "required-after:OpenComputers")
public class OpenSensors {
	public static final String MODID = "opensensors";
	@Instance(value = MODID)
	public static OpenSensors instance;
	@SidedProxy(clientSide = "pcl.opensensors.client.ClientProxy", serverSide = "pcl.opensensors.common.CommonProxy")
	public static CommonProxy proxy;
	public static Config cfg = null;

	public static boolean debug = false;
	
	public static final Logger logger = LogManager.getFormatterLogger(MODID);
}
