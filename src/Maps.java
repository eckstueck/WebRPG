class Maps{
	Map mCurrentMap;
	int mX;
	int mY;
	HashMap mMapMap = new HashMap();
	int mViewWidth;
	int mViewHeight;
	int mTileSize;
	//Maps are named after there position
	//e.g.
	//  -11  01  11
	//  -10  00  10
	//  -1-1 0-1 1-1
	Maps(ArrayList mapList, viewWidth, viewHeight, tileSize){
		for(int i = 0; i < mapList.size(); i++){
			String map = (String) mapList.get(i);
			PImage temp = loadImage("./maps/" + map);
		 	mMapMap.put(map.substring(0, 2), temp)
		}
	mCurrentMap = new Map(viewWidth, viewHeight, tileSize, mMapMap.get("00"));
	mX = 0;
	mY = 0;
	mViewWidth = viewWidth;
	mViewHeight = viewHeight;
	mTileSize = tileSize;
	}

	void getCurrentMap(){
		return mCurrentMap;
	}
	boolean changeMap(int x, int y){
		console.log("" + (mX+x) + (mY+y));
		if (mMapMap.get("" + (mX+x) + (mY+y))){
			mCurrentMap = new Map(mViewWidth, mViewHeight, mTileSize, mMapMap.get("" + (mX+x) + (mY+y)));
			mX = mX+x;
			mY = mY+y;
			return true;
		}
		else{
			return false;
		}
	}
}
