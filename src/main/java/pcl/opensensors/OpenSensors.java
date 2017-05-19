package pcl.opensensors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.config.Configuration;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import pcl.opensensors.common.CommonProxy;
import pcl.opensensors.common.ContentRegistry;

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
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		long time = System.nanoTime();
		cfg = new Config(new Configuration(event.getSuggestedConfigurationFile()));
	    ContentRegistry.preInit();
	    logger.info("Finished pre-init in %d ms", (System.nanoTime() - time) / 1000000);
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		long time = System.nanoTime();
		ContentRegistry.init();
		logger.info("Finished init in %d ms", (System.nanoTime() - time) / 1000000);
	}
}
