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
