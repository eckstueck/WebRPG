/*@pjs preload="maps/00.png";*/
/*@pjs preload="maps/10.png";*/
/*@pjs preload="maps/01.png";*/
/*@pjs preload="sprite.png";*/
int viewWidth = 1000;
int viewHeight = 600;
int tileSize = 50;
ArrayList mapList;
//ArrayList waterList;
PImage sprite;
PImage map1Img;
PImage map2Img;
boolean mapDrawn;
Player player;
Map currentMap;
Maps maps;
void setup(){
	mapDrawn = false;
	sprite = loadImage("sprite.png");
	//map1Img = loadImage("map1.png");
	//map2Img = loadImage("map2.png");
	mapList = new ArrayList();
	mapList.add("00.png");
	mapList.add("10.png");
	mapList.add("01.png");	
	//waterList = new ArrayList();
	//mapMatrix = new int[(viewWidth/tileSize)*(viewHeight/tileSize)];
  	size(viewWidth, viewHeight);
  	player = new Player(500, 300);
  	//currentMap = new Map(viewWidth, viewHeight, tileSize, map1Img);
	maps =  new Maps(mapList);
  	frameRate(30);
}

void draw(){
	if (sprite != null && maps != null){
		if(maps.getCurrentMap().getGraphic() != null){
			image(maps.getCurrentMap().getGraphic(), 0, 0);			
			maps.getCurrentMap().drawWater();
			player.draw();
			maps.getCurrentMap().drawTrees();
		}
		else{
			//currentMap = new Map(viewWidth, viewHeight, tileSize, map1Img);
			image(maps.getCurrentMap().draw(sprite), 0, 0);
			// for(int i = 0; i < mapList.size(); i++){
			// 	PGraphics map = (PGraphics) mapList.get(i);
			// 	image(map, 0, 0);
			// }

			// for(int i = 0; i < treeList.size(); i++){
			// 	Tree tree = (Tree) treeList.get(i);
			// 	tree.draw();
			// }

			// for(int i = 0; i < waterList.size(); i++){
			// 	Water water = (Water) waterList.get(i);
			// 	water.draw();
			// }
			// 
		}
	}
	else{
		//console.log("not");
	}
}

// PGraphics drawBackgroundMap(PImage img){
// 	PGraphics mapG;
// 	mapG = createGraphics(viewWidth, viewHeight); 
//   	mapG.beginDraw();
// 	mapG.background(#CCCCCC);
  	
//   	for (int i=0; i < (img.width*img.height); i++) {
//   		// int h = floor(i / img.width);
//     //   	int w = floor(i / img.width) * img.width;
//       	if(img.pixels[i] == color(0,255,0)){
//       		mapMatrix[i] = 0;
//       		// mapG.image(sprite.get(50,0,50,50),(i - w ) * tileSize, h*tileSize);
//       	}
//       	if(img.pixels[i] == color(17,68,0)){
//       		Tree tree = new Tree((i - w ) * tileSize, h*tileSize);
//       		treeList.add(tree);
//       	}
//       	if(img.pixels[i] == color(0,0,255)){
//       		Water water = new Water((i - w ) * tileSize, h*tileSize);
//       		waterList.add(water);
//       	}
//   	}
//   	mapG.endDraw();
//   	mapDrawn = true;
//   	return mapG;
//  }

 void keyPressed(){
 	int move = 5;
 	// Tile currentTile = new Tile(player.getX(), player.getY, tileSize);
 	if(key == 'w'){
 		if(player.getY() - move <= 0){
			if(maps.changeMap(0,1)){
				player.setY(viewHeight - tileSize - 5);
			}
		}
		else{
			if(maps.getCurrentMap().getTileTypeT(new Tile(player.getX() + tileSize/2, player.getY() - move, tileSize)) == 0){
 			player.moveY(-move);
 			}
		}
 	// 	boolean move = true;
 	// 	for(int i = 0; i < treeList.size(); i++){
		// 	Tree tree = (Tree) treeList.get(i);
		// 	if(tree.isInside(player.getX() + tileSize/2, player.getY() - tileSize/2)){
		// 		move = false;
		// 	}
		// }
		// for(int i = 0; i < waterList.size(); i++){
		// 	Water water = (Water) waterList.get(i);
		// 	if(water.isInside(player.getX() + tileSize/2, player.getY() - tileSize/2)){
		// 		move = false;
		// 	}
		// }
		// if (move) {
		// 	player.moveY(-tileSize);
		// }
 	}
 	if(key == 'a'){
 		if(player.getX() - move <= 0){
			if(maps.changeMap(-1,0)){
				player.setX(viewWidth - tileSize - 5);
			}
		}
		else{
			if(maps.getCurrentMap().getTileTypeT(new Tile(player.getX() -move, player.getY() + tileSize/2, tileSize)) == 0){
 			player.moveX(-move);
 			}
		}
 	// 	boolean move = true;
 	// 	for(int i = 0; i < treeList.size(); i++){
		// 	Tree tree = (Tree) treeList.get(i);
		// 	if(tree.isInside(player.getX() - tileSize/2, player.getY() + tileSize/2)){
		// 		move = false;
		// 	}
		// }
		// for(int i = 0; i < waterList.size(); i++){
		// 	Water water = (Water) waterList.get(i);
		// 	if(water.isInside(player.getX() - tileSize/2, player.getY() + tileSize/2)){
		// 		move = false;
		// 	}
		// }
		// if (move) {
		// 	player.moveX(-tileSize);
		// }
 	}
 	if(key == 's'){
 		// Tile tile = new Tile(player.getX(), player.getY() + tileSize + 5, tileSize);
 		// println("s pressed " + tile.getX() + " : "+ tile.getY() + " = " + currentMap.getTileTypeT(tile));
 		if(player.getY() + move >= viewHeight - tileSize){
			if(maps.changeMap(0,-1)){
				player.setY(5);
			}
		}
		else{
			if(maps.getCurrentMap().getTileTypeT(new Tile(player.getX() + tileSize/2, player.getY() + tileSize + move, tileSize)) == 0){
 			player.moveY(move);
 			}
		}
 	}
 	if(key == 'd'){
 		if(player.getX() + move >= viewWidth - tileSize){
			if(maps.changeMap(1,0)){
				player.setX(5);
			}
		}
		else{
			if(maps.getCurrentMap().getTileTypeT(new Tile(player.getX() + tileSize + move, player.getY() + tileSize/2, tileSize)) == 0){
 			player.moveX(move);
 			}
		}
 	// 	boolean move = true;
 	// 	for(int i = 0; i < treeList.size(); i++){
		// 	Tree tree = (Tree) treeList.get(i);
		// 	if(tree.isInside(player.getX() + tileSize * 1.5 , player.getY() + tileSize/2)){
		// 		move = false;
		// 	}
		// }
		// for(int i = 0; i < waterList.size(); i++){
		// 	Water water = (Water) waterList.get(i);
		// 	if(water.isInside(player.getX() + tileSize * 1.5, player.getY() + tileSize/2)){
		// 		move = false;
		// 	}
		// }
		// if (move) {
		// 	player.moveX(tileSize);
		// }
 	}
console.log(player.getX() + " : " + player.getY());
 }

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

	// boolean isInside(float x, float y){
	// 	if (x >= mX && x <= mX + tileSize && y >= mY && y <= mY + tileSize){
	// 		return true;
	// 	}
	// 	else{
	// 		return false;
	// 	}
	// }
}

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

class Player{
	float mX;
	float mY;
	Player(float x, float y){
		mX = x;
		mY = y;
	}

	void draw(){
		image(sprite.get(150,0,50,50),mX, mY);
	}

	void moveX(float x){
		mX += x;
	}

	void moveY(float y){
		mY += y;
	}
	float getX(){
		return mX;
	}
	float getY(){
		return mY;
	}
	void setX(float x){
		mX = x;
	}
	void setY(float y){
		mY = y;
	}
}

class Map{
	int mMapWidth; //in tiles
	int mMapHeight;
	int mTileSize;
	PImage mMapImg;
	int[] mMapMatrix;
	PGraphics mGraphic;
	PImage mSprite;
	ArrayList mTreeList;
	ArrayList mWaterList;
	Map(int mapWidth, int  mapHeight, int tileSize, PImage mapImg){
		mMapWidth = mapWidth / tileSize;
		mMapHeight = mapHeight / tileSize;
		mTileSize = tileSize;
		mMapImg = mapImg;
		mMapMatrix = new int[(mMapWidth)*(mMapHeight)];

		mMapImg.loadPixels();
  		for (int i=0; i < (mMapImg.width*mMapImg.height); i++) {
      		if(mMapImg.pixels[i] == color(0,255,0)){
      			mMapMatrix[i] = 0;
      		}
      		if(mMapImg.pixels[i] == color(17,68,0)){
      			mMapMatrix[i] = 1;
      		}
      		if(mMapImg.pixels[i] == color(0,0,255)){
      			mMapMatrix[i] = 2;
      		}
  		}
  	}

  	PGraphics draw(PImage sprite){
  		mSprite = sprite;
		mGraphic = createGraphics(mMapWidth * tileSize, mMapHeight * tileSize); 
  		mGraphic.beginDraw();
		mGraphic.background(#CCCCCC);
  		for (int i=0; i < mMapMatrix.length(); i++) {
      		mGraphic.image(mSprite.get(50,0,50,50),getTile(i).getX(), getTile(i).getY());
  		}
  		addSand();
  		mGraphic.endDraw();
  		loadTrees();
  		loadWater();
  		return mGraphic;
  	}
  	void loadTrees(){
  		mTreeList = new ArrayList();
  		for (int i=0; i < mMapMatrix.length(); i++) {
  			if (mMapMatrix[i] == 1){
  				Tree tree = new Tree(getTile(i), floor(random(2)), mSprite);
      			mTreeList.add(tree);
  			}
  		}
  	}

  	void drawTrees(){
  		for(int i = 0; i < mTreeList.size(); i++){
			Tree tree = (Tree) mTreeList.get(i);
			tree.draw();
		}
  	}

  	void loadWater(){
  		mWaterList = new ArrayList();
  		for (int i=0; i < mMapMatrix.length(); i++) {
  			if (mMapMatrix[i] == 2){
  				Water water = new Water(getTile(i), mSprite);
      			mWaterList.add(water);
  			}
  		}
  	}

  	void drawWater(){
  		for(int i = 0; i < mWaterList.size(); i++){
			Water water = (Water) mWaterList.get(i);
			water.draw();
		}
  	}

  	void addSand(){
  		for (int i=0; i < mMapMatrix.length(); i++) {
  			if (mMapMatrix[i] == 2){

  				mGraphic.image(mSprite.get(100,0,50,50),getTile(i).getX(), getTile(i).getY());

  				mGraphic.image(mSprite.get(100,0,50,50),getTile(i-1).getX(), getTile(i-1).getY());
  				mGraphic.image(mSprite.get(100,0,50,50),getTile(i+1).getX(), getTile(i+1).getY());

  				mGraphic.image(mSprite.get(100,0,50,50),getTile(i-mMapWidth).getX(), getTile(i-mMapWidth).getY());
  				mGraphic.image(mSprite.get(100,0,50,50),getTile(i+mMapWidth).getX(), getTile(i+mMapWidth).getY());

  				if(mMapMatrix[i-mMapWidth + 1] == 2 &&  mMapMatrix[i-mMapWidth] != 2){
  					mGraphic.image(mSprite.get(0,50,50,50),getTile(i-mMapWidth - 1).getX(), getTile(i-mMapWidth - 1 ).getY());
  				}

  				if(mMapMatrix[i-mMapWidth - 1] == 2 &&  mMapMatrix[i-mMapWidth] != 2){
  					mGraphic.pushMatrix();
  					mGraphic.translate(getTile(i - mMapWidth + 2).getX(), getTile(i - mMapWidth + 2).getY());
  					mGraphic.rotate(radians(90));
  					mGraphic.image(mSprite.get(0,50,50,50));
  					mGraphic.popMatrix();
  				}

  				if(mMapMatrix[i+mMapWidth - 1] == 2 &&  mMapMatrix[i+mMapWidth] != 2){
  					mGraphic.pushMatrix();
  					mGraphic.translate(getTile(i + (2*mMapWidth) + 2).getX(), getTile(i + (2*mMapWidth) + 2).getY());
  					mGraphic.rotate(radians(180));
  					mGraphic.image(mSprite.get(0,50,50,50));
  					mGraphic.popMatrix();
  				}

  				if(mMapMatrix[i+mMapWidth + 1] == 2 &&  mMapMatrix[i+mMapWidth] != 2){
  					mGraphic.pushMatrix();
  					mGraphic.translate(getTile(i + (2*mMapWidth) -1).getX(), getTile(i + (2*mMapWidth) -1).getY());
  					mGraphic.rotate(radians(270));
  					mGraphic.image(mSprite.get(0,50,50,50));
  					mGraphic.popMatrix();
  				}
  				if(mMapMatrix[i - 2 * mMapWidth] == 2 &&  mMapMatrix[i - mMapWidth] != 2){
  					mGraphic.image(mSprite.get(100,0,50,50),getTile(i-mMapWidth-1).getX(), getTile(i-mMapWidth-1).getY());
  					mGraphic.image(mSprite.get(100,0,50,50),getTile(i-mMapWidth+1).getX(), getTile(i-mMapWidth+1).getY());
  				}
  			}
  		}
  	}

  	Tile getTile(int t){
		int h = floor(t / mMapWidth);
	  	int w = floor(t / mMapWidth) * mMapWidth;
	  	return new Tile((t - w ) * mTileSize, h * mTileSize , mTileSize);
  	}

  	int getI(Tile tile){
  		return (tile.getY() / mTileSize) * mMapWidth + (tile.getX() / mTileSize);
  	}

  	PGraphics getGraphic(){
  		return mGraphic;
  	}
  	int getTileTypeT(Tile tile){
  		return mMapMatrix[getI(tile)];
  	}
  	int getTileType(int t){
  		return mMapMatrix[i]
  	}
}

class Tile{
	float mX;
	float mY;
	int mSize;
	Tile(float x, float y, int size){
		mX = floor(x / size) * size;
		mY = floor(y / size) * size;
		mSize = size;
	}
	float getX(){
		return mX;
	}
	float getY(){
		return mY;
	}
	int getSize(){
		return mSize;
	}
	boolean isInside(float x, float y){
		if (x >= mX && x <= mX + mSize && y >= mY && y <= mY + mSize){
			return true;
		}
		else{
			return false;
		}
	}
}

class Maps{
	Map mCurrentMap;
	int mX;
	int mY;
	HashMap mMapMap = new HashMap();
	Maps(ArrayList mapList){
		for(int i = 0; i < mapList.size(); i++){
			String map = (String) mapList.get(i);
			PImage temp = loadImage("maps/" + map);
		 	mMapMap.put(map.substring(0, 2), temp)
		}
	mCurrentMap = new Map(viewWidth, viewHeight, tileSize, mMapMap.get("00"));
	mX = 0;
	mY = 0;
	}

	void getCurrentMap(){
		return mCurrentMap;
	}
	boolean changeMap(int x, int y){
		console.log("" + (mX+x) + (mY+y));
		if (mMapMap.get("" + (mX+x) + (mY+y))){
			mCurrentMap = new Map(viewWidth, viewHeight, tileSize, mMapMap.get("" + (mX+x) + (mY+y)));
			mX = mX+x;
			mY = mY+y;
			return true;
		}
		else{
			return false;
		}
	}
}
