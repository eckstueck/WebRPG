class Water{
	Tile mTile;
	PImage mSprite;

	Water(Tile tile, PImage sprite){
		mTile = tile;
		mSprite = sprite;
	}

	void draw(){
		// console.log("blub");
		int size = floor((frameCount % 20) / 5);
		image(mSprite.get(0 + (size * 50),100,50,50), mTile.getX(), mTile.getY());
		// fill(-12779729);
		// rect(0, 0, 50, 50);
		// fill(-16711936);
		// rect(0, 50, 50, 50);
		// noFill();
	}
	// boolean isInside(float x, float y){
	// 	if (x >= mX && x <= mX + tileSize && y >= mY && y <= mY + tileSize){
	// 		return true;
	// 	}
	// 	else{
	// 		return false;
	// 	}
	// }
}
