class Player{
	float mX;
	float mY;
	PImage mSprite;

	Player(float x, float y, PImage sprite){
		mX = x;
		mY = y;
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
