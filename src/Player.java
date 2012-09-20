class Player{
	float mX;
	float mY;
	String mMap;
	String mName;
	PImage mSprite;

	Player(float x, float y, String map, String name, PImage sprite){
		mX = x;
		mY = y;
		mMap = map;
		mName = name;
		mSprite = sprite;
	}

	void draw(){
		image(mSprite.get(150,0,50,50),mX, mY);
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
}
