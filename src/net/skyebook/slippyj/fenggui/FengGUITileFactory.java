/**
 * 
 */
package net.skyebook.slippyj.fenggui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.fenggui.FengGUI;
import org.fenggui.binding.render.Binding;
import org.fenggui.binding.render.Pixmap;

import net.skyebook.slippyj.tile.Tile;
import net.skyebook.slippyj.tile.TileFactory;

/**
 * @author Skye Book
 *
 */
public class FengGUITileFactory extends TileFactory{

	/**
	 * @param tileServer
	 */
	public FengGUITileFactory(String tileServer) {
		super(tileServer);
	}

	/* (non-Javadoc)
	 * @see net.skyebook.slippyj.tile.TileFactory#createTile(int, int, int, int, int)
	 */
	@Override
	public Tile createTile(int zoomLevel, int x, int y, int xLocation, int yLocation) {
		FengGUITile tile = FengGUI.createWidget(FengGUITile.class);
		try {
			tile.setPixmap(new Pixmap(Binding.getInstance().getTexture(new URL(tileServer+zoomLevel+"/"+x+"/"+y+".png"))));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tile;
	}

}
