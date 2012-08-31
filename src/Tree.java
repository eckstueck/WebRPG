class Tree{
	Tile mTile;
	int mType;
	PImage mSprite;

	Tree(Tile tile, int type, PImage sprite){
		mTile = tile;
		mType = type;
		mSprite = sprite;
	}

	void draw(){
		if (mType == 0){
			image(mSprite.get(200,0,50,100), mTile.getX(), mTile.getY() - mTile.getSize());
		}
		else{
			image(mSprite.get(250,0,50,100),mTile.getX(), mTile.getY() - mTile.getSize());
		}
		
	}
}
