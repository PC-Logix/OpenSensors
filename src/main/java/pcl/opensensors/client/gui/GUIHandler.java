package pcl.opensensors.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import pcl.opensensors.common.SensorContainer;
import pcl.opensensors.common.tileentity.TileEntitySensor;
import cpw.mods.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if (id == 0) { //Sensor Block GUI
			TileEntity tileEntity = world.getTileEntity(x, y, z);
			if(tileEntity instanceof TileEntitySensor){
				return new SensorContainer(player.inventory, (TileEntitySensor) tileEntity);
			}
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if (id == 0) { //Sensor Block GUI
			TileEntity tileEntity = world.getTileEntity(x, y, z);
			if(tileEntity instanceof TileEntitySensor){
				return new SensorGUI(player.inventory, (TileEntitySensor) tileEntity);
			}
			return null;
		}
		return null;
	}
}