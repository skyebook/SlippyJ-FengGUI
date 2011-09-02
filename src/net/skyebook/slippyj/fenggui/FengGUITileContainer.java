/**
 * 
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

}
