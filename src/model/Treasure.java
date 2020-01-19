package model;

public class Treasure {

	public static final int TREASURE_AMOUNT = 24;

    private int treasureNum;
    private boolean collected = false;
    private Tile tileOwner;

	/**
	 * model.Treasure constructor
	 *
	 * @param treasureNum integer id of this treasure
	 */
	public Treasure(int treasureNum) {
    	this.treasureNum = treasureNum;
	}

	// Setters and getters
	public int getTreasureNum() {
		return this.treasureNum;
	}

	public void setCollected(boolean collected) {
		this.collected = collected;
	}
	public boolean isCollected() {
		return this.collected;
	}

	public void setTileOwner(Tile tileOwner) {
		this.tileOwner = tileOwner;
	}
	public Tile getTileOwner() {
		return this.tileOwner;
	}

}
