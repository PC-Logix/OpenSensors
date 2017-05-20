package pcl.opensensors.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import pcl.opensensors.OpenSensors;
import pcl.opensensors.common.SensorContainer;
import pcl.opensensors.common.tileentity.TileEntitySensor;

import org.lwjgl.opengl.GL11;

public class SensorGUI extends GuiContainer {

	public SensorGUI(Container p_i1072_1_) {
		super(p_i1072_1_);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initGui() {
		super.initGui();
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
	}

	public SensorGUI(InventoryPlayer inventoryPlayer, TileEntitySensor tileEntity) {
		// the container is instanciated and passed to the superclass for
		// handling
		super(new SensorContainer(inventoryPlayer, tileEntity));
		this.xSize = 175;
		this.ySize = 195;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		// the parameters for drawString are: string, x, y, color
		mc.fontRenderer.drawString(StatCollector.translateToLocal("gui.string.OpenSensor.cardslot"), 60, 60, 4210752);
		mc.fontRenderer.drawString(StatCollector.translateToLocal("gui.string.OpenSensor.sensor"), 71, 5, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		// draw your Gui here, only thing you need to change is the path
		ResourceLocation texture = new ResourceLocation(OpenSensors.MODID, "textures/gui/sensor.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(texture);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}
