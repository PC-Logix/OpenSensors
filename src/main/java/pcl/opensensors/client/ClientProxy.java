package pcl.opensensors.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import pcl.opensensors.common.CommonProxy;
import pcl.opensensors.common.SensorContainer;
import pcl.opensensors.common.tileentity.TileEntitySensor;

public class ClientProxy extends CommonProxy {
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof TileEntitySensor) {
			TileEntitySensor icte = (TileEntitySensor) te;
			return new SensorContainer(player.inventory, icte);
		} else {
			return null;
		}
	}
}
