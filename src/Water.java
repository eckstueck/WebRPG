class Water{
	Tile mTile;
	PImage mSprite;

	Water(Tile tile, PImage sprite){
		mTile = tile;
		mSprite = sprite;
	}

	void draw(){
		float size = sin(frameCount / 4);
		image(mSprite.get(0,0,50,50), mTile.getX() + size, mTile.getY() +size);		
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
