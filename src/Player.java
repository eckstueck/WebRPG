class Player{
	float mX;
	float mY;
	String mMap;
	String mName;
	PImage mSprite;
	int mDirection;

	Player(float x, float y, int d, String map, String name, PImage sprite){
		mX = x;
		mY = y;
		mMap = map;
		mName = name;
		mSprite = sprite;
		mDirection = d;  // 0 = right; 1 = up; 2 = left; 3 = down
	}

	void draw(){
		image(mSprite.get(0 + (mDirection * 50),150,50,50),mX, mY);
	}

	void moveX(float x){
		mX += x;
	}

	void moveY(float y){
		mY += y;
	}
	void changeMap(int x, int y){
		int mapX = int(mMap.charAt(0)) + x;
		int mapY = int(mMap.charAt(1)) + y;
		mMap = "" + mapX + mapY;
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
	String getMap(){
		return mMap;
	}
	void setMap(String map){
		mMap = map;
	}
	void setDirection(int direction){
		mDirection = direction;
	}

	int getDirection(){
		return mDirection;
	}
}
