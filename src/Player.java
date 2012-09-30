class Player{
	float mX;
	float mY;
	String mMap;
	String mName;
	PImage mSprite;
	int mDirection;
	boolean mMoving;
	int mMovingLeft;
	boolean mAttacking;
	int mAttackingLeft;

	Player(float x, float y, int d, String map, String name, PImage sprite){
		mX = x;
		mY = y;
		mMap = map;
		mName = name;
		mSprite = sprite;
		mDirection = d;  // 0 = right; 1 = up; 2 = left; 3 = down
		mMoving = false;
		mMovingLeft = 0;
		mAttacking = false;
 		mAttackingLeft = 0;
	}

	void draw(){
		font = loadFont("Arial.ttf"); 
		textFont(font); 
		textAlign(CENTER);
		text(mName, mX + 25, mY);
		if (mMovingLeft != 0) {
			switch (mDirection){
				case 0:
					var sprite = abs((mMovingLeft - 50) / 5);
					mX += 5;
					mMovingLeft -= 5;
					image(mSprite.get(0 + (50*sprite),0,50,50),mX, mY);
					if (mMovingLeft == 0) mMoving = false;
					break;

				case 1:
					var sprite = abs((mMovingLeft - 50) / 5);
					mY -= 5;
					mMovingLeft -= 5;
					image(mSprite.get(0 + (50*sprite),150,50,50),mX, mY);
					if (mMovingLeft == 0) mMoving = false;
					break;

			    case 2:
					var sprite = abs((mMovingLeft - 50) / 5);
					mX -= 5;
					mMovingLeft -= 5;
					image(mSprite.get(0 + (50*sprite),50,50,50),mX, mY);
					if (mMovingLeft == 0) mMoving = false;
					break;

				case 3:
					var sprite = abs((mMovingLeft - 50) / 5);
					mY += 5;
					mMovingLeft -= 5;
					image(mSprite.get(0 + (50*sprite),100,50,50),mX, mY);
					if (mMovingLeft == 0) mMoving = false;
					break;
			}
		}
		else{
			if (mAttackingLeft != 0) {
				var sprite = abs((mAttackingLeft - 100) / 10);
				mAttackingLeft -= 10;
				if (mAttackingLeft == 0) mAttacking = false;
				switch (mDirection){
					case 0:
						image(mSprite.get(0 + (50*sprite),200,50,50),mX, mY);
						break;

					case 1:
						image(mSprite.get(0 + (50*sprite),350,50,50),mX, mY);
						break;

				    case 2:
						image(mSprite.get(0 + (50*sprite),250,50,50),mX, mY);
						break;

					case 3:
						image(mSprite.get(0 + (50*sprite),300,50,50),mX, mY);
						break;
				}
			}
			else{
				mMoving = false;
				switch (mDirection){
					case 0:
						image(mSprite.get(0,0,50,50),mX, mY);
						break;
					case 1:
						image(mSprite.get(0,150,50,50),mX, mY);
						break;
					case 2:
						image(mSprite.get(0,50,50,50),mX, mY);
						break;
					case 3:
						image(mSprite.get(0,100,50,50),mX, mY);
						break;
				}
			}
		}
	}

	void move(int direction){
		if (!mAttacking){
			mMoving = true;
			mDirection = direction;
			mMovingLeft = 50;
		}
	}

	void attack(){
		if (!mMoving){
			mAttacking = true;
			mAttackingLeft = 100;
		}
	}

	void moveAgain(int direction){
		mMoving = true;
		mDirection = direction;
		mMovingLeft = 50;
		switch(direction){
			case 0:
				mX -= 50;
				break;
			case 1:
				mY += 50;
				break;
			case 2:
				mX += 50;
				break;
			case 3:
				mY -= 50;
				break;
		}
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
	boolean moving(){
		return mMoving;
	}
	boolean attacking(){
		return mAttacking;
	}
}
