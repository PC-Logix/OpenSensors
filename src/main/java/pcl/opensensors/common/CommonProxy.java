package pcl.opensensors.common;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import pcl.opensensors.common.tileentity.TileEntitySensor;

public class CommonProxy implements IGuiHandler {

	public void registerRenderers() { }
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

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
