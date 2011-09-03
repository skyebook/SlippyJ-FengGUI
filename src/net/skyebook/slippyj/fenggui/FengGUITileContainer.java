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

import net.skyebook.slippyj.TileContainer;

import org.fenggui.Container;
import org.fenggui.IWidget;
import org.fenggui.layout.StaticLayout;

/**
 * @author Skye Book
 *
 */
public class FengGUITileContainer<Tile> extends Container implements TileContainer<FengGUITile> {
	
	public FengGUITileContainer(){
		super();
		setLayoutManager(new StaticLayout());
	}

	/* (non-Javadoc)
	 * @see net.skyebook.slippyj.TileContainer#addTile(net.skyebook.slippyj.tile.Tile)
	 */
	@Override
	public void addTile(FengGUITile tile) {
		addWidget(tile);
		System.out.println("Widget added at " + tile.getX()+", "+tile.getY());
	}

	/* (non-Javadoc)
	 * @see net.skyebook.slippyj.TileContainer#removeTile(net.skyebook.slippyj.tile.Tile)
	 */
	@Override
	public void removeTile(FengGUITile tile) {
		removeWidget(tile);
	}

	/* (non-Javadoc)
	 * @see net.skyebook.slippyj.TileContainer#moveTiles(int, int)
	 */
	@Override
	public void moveTiles(int xDelta, int yDelta) {
		for(IWidget tile : getWidgets()){
			tile.setXY(tile.getX()+xDelta, tile.getY()+yDelta);
		}
	}

	/* (non-Javadoc)
	 * @see net.skyebook.slippyj.TileContainer#isXInverted()
	 */
	@Override
	public boolean isXInverted() {
		return false;
	}

	/* (non-Javadoc)
	 * @see net.skyebook.slippyj.TileContainer#isYInverted()
	 */
	@Override
	public boolean isYInverted() {
		return false;
	}

	@Override
	public void removeAllTiles() {
		for(IWidget widget : getWidgets()){
			if(widget instanceof FengGUITile){
				removeWidget(widget);
			}
		}
	}

}
