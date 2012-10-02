/*@pjs 	preload=" 
./maps/-11.bmp, 
./maps/01.bmp, 
./maps/11.bmp, 
./maps/-10.bmp, 
./maps/00.bmp, 
./maps/10.bmp,
./maps/-1-1.bmp,
./maps/0-1.bmp,
./maps/1-1.bmp, 
./sprites/sprite.png,
./sprites/charSprites.png";*/
final int viewWidth = 1000;
final int viewHeight = 600;
final int tileSize = 50;
ArrayList mapList;
PImage sprite;
Player player;
Maps maps;
JavaScript javascript;
HashMap userList = new HashMap();

interface JavaScript {
	void playerMoved(int x, int y); 
}

void bindJavascript(JavaScript js) {
	javascript = js; 
}

JavaScript javascript;

void setup(){
	//loading Sprites
	sprite = loadImage("./sprites/sprite.png");
	sprite2 = loadImage("./sprites/charSprites.png");
	
	size(viewWidth, viewHeight);
  	frameRate(30);
}

void start(String name, float x, float y, int d, String map){
	//creating Map list and loading Maps
	mapList = new ArrayList();
	mapList.add("-11.bmp");
	mapList.add("01.bmp");
	mapList.add("11.bmp");
	mapList.add("-10.bmp");
	mapList.add("00.bmp");
	mapList.add("10.bmp");
	mapList.add("-1-1.bmp");
	mapList.add("0-1.bmp");
	mapList.add("1-1.bmp");
	maps =  new Maps(mapList, viewWidth, viewHeight, tileSize, map);
	
	//creating Player
  	player = new Player(x, y, d, map, name, sprite2);
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
			// if (test){
			// 	console.log(maps.getCurrentMap());
			// 	test = false;
			// }
			//draw other Player
			Iterator i = userList.entrySet().iterator();
			while (i.hasNext()) {
 				Map.Entry me = (Map.Entry)i.next();
 				if(maps.getCurrentMapString() == me.getValue().getMap()){
 					me.getValue().draw();
 				}
			}
			// for(int i = 0; i < userList.size(); i++){
			// 	console.log("draw Player");
			// 	Player otherPlayer = (Player) userList.get(i);
			// 	otherPlayer.draw();
			// }
			//draw the Player
			player.draw();
			//draw Trees of the CurrentMap
			maps.getCurrentMap().drawTrees();
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
 	// int moved = 0;

 	if(key == 'w'){
 		if (!player.moving()) {
 			if (player.getDirection() != 1){
 				player.setDirection(1);
 				if (javascript!=null) javascript.playerMoved(player.getX(), player.getY(), player.getDirection(), player.getMap(), 1);
 			}
 			else{
				//if Player leave the CurrentMap
		 		if(player.getY() - tileSize < 0){
					//change the CurrentMap if there is one
					if(maps.changeMap(0,1)){
						//set Player to new position on the new Map
						player.changeMap(0,1);
						player.setY(viewHeight - tileSize);
						if (javascript!=null) javascript.playerMoved(player.getX(), player.getY(), player.getDirection(), player.getMap(), 1);
					}
				}
				else{	
					//move Player if no object block the path
					if(maps.getCurrentMap().getTileTypeT(new Tile(player.getX(), player.getY()- tileSize, tileSize)) == 0){
		 				player.move(1);
		 				if (javascript!=null) javascript.playerMoved(player.getX(), player.getY() - 50, 1, player.getMap(), 2);
		 			}
				}
			}
		}
 	}

 	if(key == 'a'){
 		if (!player.moving()) {
 			if (player.getDirection() != 2){
 				player.setDirection(2);
 				if (javascript!=null) javascript.playerMoved(player.getX(), player.getY(), player.getDirection(), player.getMap(), 1);
 			}
 			else{
				if(player.getX() - tileSize < 0){
					if(maps.changeMap(-1,0)){
						player.changeMap(-1,0);
						player.setX(viewWidth - tileSize);
						if (javascript!=null) javascript.playerMoved(player.getX(), player.getY(), player.getDirection(), player.getMap(), 1);
					}
				}
				else{
					if(maps.getCurrentMap().getTileTypeT(new Tile(player.getX() - tileSize, player.getY(), tileSize)) == 0){
		 				player.move(2);
		 				if (javascript!=null) javascript.playerMoved(player.getX() -50, player.getY(), 2, player.getMap(), 2);
		 			}
				}
			}
		}
 	}

 	if(key == 's'){
 		if (!player.moving()) {
 			if (player.getDirection() != 3){
 				player.setDirection(3);
 				if (javascript!=null) javascript.playerMoved(player.getX(), player.getY(), player.getDirection(), player.getMap(), 1);
 			}
 			else{
				if(player.getY() + tileSize >= viewHeight){
					if(maps.changeMap(0,-1)){
						player.changeMap(0,-1);
						player.setY(0);
						if (javascript!=null) javascript.playerMoved(player.getX(), player.getY(), player.getDirection(), player.getMap(), 1);
					}
				}
				else{
					if(maps.getCurrentMap().getTileTypeT(new Tile(player.getX(), player.getY() + tileSize, tileSize)) == 0){
		 				player.move(3);
		 				if (javascript!=null) javascript.playerMoved(player.getX(), player.getY() + 50, 3, player.getMap(), 2);
		 			}
				}
			}
		}
 	}

 	if(key == 'd'){
 		if (!player.moving()) {
 			if (player.getDirection() != 0){
 				player.setDirection(0);
 				if (javascript!=null) javascript.playerMoved(player.getX(), player.getY(), player.getDirection(), player.getMap(), 1);
 			}
 			else{
		 		if(player.getX() + tileSize >= viewWidth){
					if(maps.changeMap(1,0)){
						player.changeMap(1,0);
						player.setX(0);
						if (javascript!=null) javascript.playerMoved(player.getX(), player.getY(), player.getDirection(), player.getMap(), 1);
					}
				}
				else{
					if(maps.getCurrentMap().getTileTypeT(new Tile(player.getX() + tileSize, player.getY(), tileSize)) == 0){
		 				player.move(0);
		 				if (javascript!=null) javascript.playerMoved(player.getX() + 50, player.getY(), 0, player.getMap(), 2);
		 			}
				}
			}
		}
 	}
 	if (keyCode == 32) {
 		if (!player.attacking()){
 			player.attack();
 			if (javascript!=null) javascript.playerAttacked();
 		}
 	}
	//log Player position
//	console.log("Processing: moved to " + player.getX() + " : " + player.getY());
	// if (javascript!=null && moved != 0){
	// 	if (moved == 2) {
	// 		switch(player.getDirection()){
	// 			case 0:
	// 				javascript.playerMoved(player.getX() + 50, player.getY(), player.getDirection(), player.getMap(), moved);
	// 				break;
	// 			case 1:
	// 				javascript.playerMoved(player.getX(), player.getY() - 50, player.getDirection(), player.getMap(), moved);
	// 				break;
	// 			case 2:
	// 				javascript.playerMoved(player.getX() - 50, player.getY(), player.getDirection(), player.getMap(), moved);
	// 				break;
	// 			case 3:
	// 				javascript.playerMoved(player.getX(), player.getY() + 50, player.getDirection(), player.getMap(), moved);
	// 				break;																
	// 		}
	// 	}
	// 	else{
	// 		javascript.playerMoved(player.getX(), player.getY(), player.getDirection(), player.getMap(), moved);
	// 	}
	// }
 }

void createPlayer(String id, float x, float y, int d, String map){
	console.log("Player " + id + " created at (" + x +":" + y +") on Map " + map);
	Player otherPlayer = new Player(x, y, d, map, id, sprite2);
	userList.put(id, otherPlayer);
}

void removePlayer(String id){
	console.log("Player " + id + " removed");
	userList.remove(id);
}

void movePlayer(String id, float x, float y, int d, String map, int moved){
//	console.log("Player " + id + " moved to ("  + x + ":" + y +") on Map " + map );
	Player otherPlayer = userList.get(id);
	otherPlayer.setX(x);
	otherPlayer.setY(y);
	otherPlayer.setMap(map);
	otherPlayer.setDirection(d);
	if (moved == 2){
		otherPlayer.moveAgain(d);
	}
}

void attackPlayer(String id){
	Player otherPlayer = userList.get(id);
	otherPlayer.attack();
}

void alert(){
	alert("bla");
}