/*@pjs 	preload="./maps/00.png, ./maps/10.png, ./maps/01.png, ./sprites/sprite.png";*/
final int viewWidth = 1000;
final int viewHeight = 600;
final int tileSize = 50;
ArrayList mapList;
PImage sprite;
Player player;
Maps maps;

void setup(){
	//loading Sprites
	sprite = loadImage("./sprites/sprite.png");
	
	//creating Map list and loading Maps
	mapList = new ArrayList();
	mapList.add("00.png");
	mapList.add("10.png");
	mapList.add("01.png");
	maps =  new Maps(mapList, viewWidth, viewHeight, tileSize);
	
	//creating Player
  	player = new Player(500, 300, sprite);
	size(viewWidth, viewHeight);
  	frameRate(30);
}

void draw(){
	//draw if Sprites & Maps have been loaded
	if (sprite != null && maps != null){
		//draw if CurrentMap has been created
		if(maps.getCurrentMap().getGraphic() != null){
			//draw CurrentMap
			image(maps.getCurrentMap().getGraphic(), 0, 0);	
			//draw Water of the CurrentMap		
			maps.getCurrentMap().drawWater();
			//draw Trees of the CurrentMap
			maps.getCurrentMap().drawTrees();
			//draw the Player
			player.draw();
		}
		else{
			//created CurrentMap
			image(maps.getCurrentMap().draw(sprite), 0, 0);
		}
	}
	else{
		//console.log("not");
	}
}

void keyPressed(){
 	int move = 5;

 	if(key == 'w'){
		//if Player leave the CurrentMap
 		if(player.getY() - move <= 0){
			//change the CurrentMap if there is one
			if(maps.changeMap(0,1)){
				//set Player to new position on the new Map
				player.setY(viewHeight - tileSize - 5);
			}
		}
		else{	
			//move Player if no object block the path
			if(maps.getCurrentMap().getTileTypeT(new Tile(player.getX() + tileSize/2, player.getY() - move, tileSize)) == 0){
 				player.moveY(-move);
 			}
		}
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
 	}

 	if(key == 's'){
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
 	}
	//log Player position
	console.log(player.getX() + " : " + player.getY());
 }
