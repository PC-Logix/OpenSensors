package pcl.opensensors.common;

import com.sun.prism.Material;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import pcl.opensensors.client.CreativeTab;
import pcl.opensensors.common.blocks.BlockSensor;
import pcl.opensensors.common.items.ItemBlankSensor;
import pcl.opensensors.common.items.ItemWorldSensor;
import pcl.opensensors.common.tileentity.TileEntitySensor;

public class ContentRegistry {
	public static Block sensorBlock;
	public static Item  blankSensor;
	public static Item  worldSensor;
	
	public static CreativeTabs CreativeTab;
	
	public static void preInit() {
        registerTabs();
        registerBlocks();
        registerItems();
	}

	public static void init() {
        registerRecipes();
	}

	private static void registerTabs() {
		 CreativeTab = new CreativeTab("OpenSensors");
	}
	
	private static void registerBlocks() {
		sensorBlock = new BlockSensor();
		GameRegistry.registerBlock(sensorBlock, "sensor");
		sensorBlock.setCreativeTab(CreativeTab);
		GameRegistry.registerTileEntity(TileEntitySensor.class, "SensorTE");
	}
	
	private static void registerItems() {
		worldSensor = new ItemWorldSensor();
		GameRegistry.registerItem(worldSensor, "opensensors.worldSensor");
		worldSensor.setCreativeTab(CreativeTab);
		
		blankSensor = new ItemBlankSensor();
		GameRegistry.registerItem(blankSensor, "opensensors.blankSensor");
		worldSensor.setCreativeTab(CreativeTab);
	}
	
	private static void registerRecipes() {
		GameRegistry.addRecipe(new ItemStack(sensorBlock), "xzx", "xyx", "xzx",
				'x', new ItemStack(Blocks.iron_block),
				'y', new ItemStack(Items.ender_pearl),
				'z', new ItemStack(Items.redstone));
		GameRegistry.addRecipe(new ItemStack(blankSensor), "yzy", "   ", "yzy",
				'y', new ItemStack(Items.paper),
				'z', new ItemStack(Items.redstone));
		GameRegistry.addRecipe(new ItemStack(worldSensor), " z ", "xxx", " y ",
				'x', new ItemStack(Blocks.dirt),
				'y', new ItemStack(Items.redstone),
				'z', new ItemStack(blankSensor));
	}
	
}
