/*@pjs 	preload="./maps/00.png, ./maps/10.png, ./maps/01.png, ./sprites/sprite.png";*/
final int viewWidth = 1000;
final int viewHeight = 600;
final int tileSize = 50;
ArrayList mapList;
PImage sprite;
Player player;
Maps maps;
JavaScript javascript;
HashMap userList = new HashMap();
// boolean test = true;

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
	
	//creating Map list and loading Maps
	mapList = new ArrayList();
	mapList.add("00.png");
	mapList.add("10.png");
	mapList.add("01.png");
	maps =  new Maps(mapList, viewWidth, viewHeight, tileSize);
	
	//creating Player
  	player = new Player(500, 300, 0, "00", "player", sprite);
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
			// if (test){
			// 	console.log(maps.getCurrentMap());
			// 	test = false;
			// }
			//draw Trees of the CurrentMap
			maps.getCurrentMap().drawTrees();
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
				player.changeMap(0,1);
				player.setY(viewHeight - tileSize - 5);
			}
		}
		else{	
			//move Player if no object block the path
			if(maps.getCurrentMap().getTileTypeT(new Tile(player.getX() + tileSize/2, player.getY() - move, tileSize)) == 0){
 				player.moveY(-move);
 				player.setDirection(1);
 			}
		}
 	}

 	if(key == 'a'){
 		if(player.getX() - move <= 0){
			if(maps.changeMap(-1,0)){
				player.changeMap(-1,0);
				player.setX(viewWidth - tileSize - 5);
			}
		}
		else{
			if(maps.getCurrentMap().getTileTypeT(new Tile(player.getX() -move, player.getY() + tileSize/2, tileSize)) == 0){
 				player.moveX(-move);
 				player.setDirection(2);
 			}
		}
 	}

 	if(key == 's'){
 		if(player.getY() + move >= viewHeight - tileSize){
			if(maps.changeMap(0,-1)){
				player.changeMap(0,-1);
				player.setY(5);
			}
		}
		else{
			if(maps.getCurrentMap().getTileTypeT(new Tile(player.getX() + tileSize/2, player.getY() + tileSize + move, tileSize)) == 0){
 				player.moveY(move);
 				player.setDirection(3);
 			}
		}
 	}

 	if(key == 'd'){
 		if(player.getX() + move >= viewWidth - tileSize){
			if(maps.changeMap(1,0)){
				player.changeMap(1,0);
				player.setX(5);
			}
		}
		else{
			if(maps.getCurrentMap().getTileTypeT(new Tile(player.getX() + tileSize + move, player.getY() + tileSize/2, tileSize)) == 0){
 				player.moveX(move);
 				player.setDirection(0);
 			}
		}
 	}
	//log Player position
//	console.log("Processing: moved to " + player.getX() + " : " + player.getY());
	if (javascript!=null){
			javascript.playerMoved(player.getX(), player.getY(), player.getDirection(), player.getMap());
	}
 }

void createPlayer(String id, float x, float y, int d, String map){
	console.log("Player " + id + " created at (" + x +":" + y +") on Map " + map);
	Player otherPlayer = new Player(x, y, d, map, id, sprite);
	userList.put(id, otherPlayer);
}

void removePlayer(String id){
	console.log("Player " + id + " removed");
	userList.remove(id);
}

void movePlayer(String id, float x, float y, int d, String map){
//	console.log("Player " + id + " moved to ("  + x + ":" + y +") on Map " + map );
	Player otherPlayer = userList.get(id);
	otherPlayer.setX(x);
	otherPlayer.setY(y);
	otherPlayer.setMap(map);
	otherPlayer.setDirection(d);
}

void alert(){
	alert("bla");
}