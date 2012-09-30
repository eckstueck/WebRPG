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
		
		//create a matrix with the objects of the Map, for a quick access 
		mMapImg.loadPixels();
    // console.log(mMapImg.pixels);
    // console.log(hex(-16711936));
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
	//create the basic CurrentMap (without Trees and Water)
  	PGraphics draw(PImage sprite){
  		mSprite = sprite;
		  mGraphic = createGraphics(mMapWidth * mTileSize, mMapHeight * mTileSize); 
  		mGraphic.beginDraw();
		  mGraphic.background(#CCCCCC);
  		for (int i=0; i < mMapMatrix.length(); i++) {
      		mGraphic.image(mSprite.get(50,0,50,50),getTile(i).getX(), getTile(i).getY());
  		}
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
      // console.log(mMapMatrix);
  		mWaterList = new ArrayList();
  		for (int i=0; i < mMapMatrix.length(); i++) {
  			if (mMapMatrix[i] == 2){
  				Water water = new Water(getTile(i), mSprite);
      			mWaterList.add(water);
  			}
  		}
		addSand();
  	}

  	void drawWater(){
//      console.log(mWaterList.size());
  		for(int i = 0; i < mWaterList.size(); i++){
			Water water = (Water) mWaterList.get(i);
			water.draw();
		}
  	}
	
	//add Sand to the edges of the Water
  	void addSand(){
  		for (int i=0; i < mMapMatrix.length(); i++) {
  			if (mMapMatrix[i] == 2){

          mGraphic.image(mSprite.get(0,0,50,50),getTile(i).getX(), getTile(i).getY());

          mGraphic.image(mSprite.get(0,0,50,50),getTile(i-1).getX(), getTile(i-1).getY());
          mGraphic.image(mSprite.get(0,0,50,50),getTile(i+1).getX(), getTile(i+1).getY());

          mGraphic.image(mSprite.get(0,0,50,50),getTile(i-mMapWidth).getX(), getTile(i-mMapWidth).getY());
          mGraphic.image(mSprite.get(0,0,50,50),getTile(i+mMapWidth).getX(), getTile(i+mMapWidth).getY());

  				if(mMapMatrix[i-mMapWidth + 1] == 2 &&  mMapMatrix[i-mMapWidth] != 2){
  					mGraphic.image(mSprite.get(100,50,50,50),getTile(i-mMapWidth - 1).getX(), getTile(i-mMapWidth - 1 ).getY());
  				}

  				if(mMapMatrix[i-mMapWidth - 1] == 2 &&  mMapMatrix[i-mMapWidth] != 2){
  					// mGraphic.pushMatrix();
  					// mGraphic.translate(getTile(i - mMapWidth + 1).getX(), getTile(i - mMapWidth + 2).getY());
  					mGraphic.image(mSprite.get(150,50,50,50),getTile(i - mMapWidth + 1).getX(), getTile(i - mMapWidth + 2).getY());
  					// mGraphic.popMatrix();
       //      mGraphic.translate(getTile(i - mMapWidth + 1).getX(), getTile(i - mMapWidth + 2).getY());
       //      mGraphic.image(mSprite.get(200,150,50,50));
       //      mGraphic.popMatrix();
  				}

  				if(mMapMatrix[i+mMapWidth - 1] == 2 &&  mMapMatrix[i+mMapWidth] != 2){
  					// mGraphic.pushMatrix();
  					// mGraphic.translate(getTile(i + (mMapWidth) + 1).getX(), getTile(i + (mMapWidth)).getY());
  					mGraphic.image(mSprite.get(50,50,50,50),getTile(i + (mMapWidth) + 1).getX(), getTile(i + (mMapWidth)).getY());
  					// mGraphic.popMatrix();
            // mGraphic.pushMatrix();
            // mGraphic.translate(getTile(i + (mMapWidth)).getX(), getTile(i + (mMapWidth)).getY());
            // mGraphic.image(mSprite.get(100,150,50,50));
            // mGraphic.popMatrix();
  				}

  				if(mMapMatrix[i+mMapWidth + 1] == 2 &&  mMapMatrix[i+mMapWidth] != 2){
  					// mGraphic.pushMatrix();
  					// mGraphic.translate(getTile(i + (mMapWidth) -1).getX(), getTile(i + (mMapWidth) -1).getY());
  					mGraphic.image(mSprite.get(0,50,50,50),getTile(i + (mMapWidth) -1).getX(), getTile(i + (mMapWidth) -1).getY());
  					// mGraphic.popMatrix();
  				}

  				// if(mMapMatrix[i - 2 * mMapWidth] == 2 &&  mMapMatrix[i - mMapWidth] != 2){
  				// 	mGraphic.image(mSprite.get(100,0,50,50),getTile(i-mMapWidth-1).getX(), getTile(i-mMapWidth-1).getY());
  				// 	mGraphic.image(mSprite.get(100,0,50,50),getTile(i-mMapWidth+1).getX(), getTile(i-mMapWidth+1).getY());
  				// }
  			}
  		}
  	}
  	PGraphics getGraphic(){
  		return mGraphic;
  	}

	//get a Tile with an index
  	Tile getTile(int t){
		int h = floor(t / mMapWidth);
	  	int w = floor(t / mMapWidth) * mMapWidth;
	  	return new Tile((t - w ) * mTileSize, h * mTileSize , mTileSize);
  	}

	//get the index of a Tile
  	int getI(Tile tile){
  		return (tile.getY() / mTileSize) * mMapWidth + (tile.getX() / mTileSize);
  	}
	
	//get the Type(e.g. Tree) of a Tile
  	int getTileTypeT(Tile tile){
  		return mMapMatrix[getI(tile)];
  	}

	//get the Type(e.g. Tree) of a index
  	int getTileType(int t){
  		return mMapMatrix[i]
  	}
}
