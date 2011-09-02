/**
 *  SlippyJ - Slippery Maps in Java
 *  Copyright (C) 2011  Skye Book
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
