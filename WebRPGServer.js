var user = new Array();
var userList = new Array();
var red, blue, green, reset;
red   = '\033[31m';
blue  = '\033[34m';
green  = '\033[32m';
reset = '\033[0m';
var app = require('express')()
  , server = require('http').createServer(app)
  , io = require('socket.io').listen(server);

//MongoDB
var mongodb = require('mongodb');
var db = new mongodb.Db('nodejitsudb8381461316',
	new mongodb.Server('alex.mongohq.com', 10009, {})
);
db.open(function (err, db_p) {
	if (err) { throw err; }
	db.authenticate('nodejitsu', 'd41feeef5d0dd7ceb3a65d93c2239c73',
		function (err, replies) {
			// You are now connected and authenticated.
			db.createCollection('player', function(err, collection) {
				// collection.remove({name: 'peter'}, {safe:true}, function(err, result) {});
				// collection.find().toArray(function(err, items) {
				// 	console.log(items);
				// });
			// collection.remove();
			});
		});
});

// server.listen(8080);
server.listen(80);

//Send files
app.get('/*/*.(js|css|java|png|bmp|mp3|swf|wav)', function(req, res){
  res.sendfile("."+req.url);
});

app.get('/', function (req, res) {
  res.sendfile(__dirname + '/WebRPG.html');
});

//socket.io
io.set('log level', 1);
io.sockets.on('connection', function (socket) {
	console.log(green + "Server: Player %s connected" + reset, socket.id);
	//app.set(socket.id, {x: 0, y: 0, map: 00});
	socket.emit('hello',{});
	// app.set(socket.id, {id: socket.id, x: 500, y: 300, d: 0, map: 00});
	// user.push(socket.id);
	// userList = [];
	// io.sockets.clients().forEach(function (so) {
	// 		userList.push(app.get(so.id));
	// });
	// socket.emit('userList', userList);
 //  	socket.broadcast.emit('addPlayer', app.get(socket.id));
 //  	console.log(blue + "Server: Player %s added to List" + reset, app.get(socket.id).id);

 	socket.on('playerName', function(data,fn){
 		console.log(user);
 		console.log(data.name);
 		if (user.indexOf(data.name) == -1){
 			fn("ok");
	 		console.log('Server: Player Name %s received', data.name);
	 		db.collection('player', function(err, collection) {
	 			if (err) {console.log(err);}
	 			collection.findOne({name: data.name}, function(err, item) {
	 				if (err) { console.log("error while finding Player " + data.name)};
	 				if (!item){
	 					console.log("DB: new Player");
	 					var now = new Date();
						var jsonDate = now.toJSON();
	 					var player = {name: data.name, x: 500, y: 300, d: 0, map: "00", update: jsonDate};
	 					collection.insert(player);
	 					app.set(socket.id, {name: data.name, x: 500, y: 300, d: 0, map: "00"});
	 					user.push(data.name);
	 					createUserList(socket);
	 				}
	 				if (item){
	 					console.log("DB: received Player");
	 					// console.log(item.x);
	 					app.set(socket.id, {name: item.name, x: item.x, y: item.y, d: item.d, map: item.map});
	 					user.push(item.name);
	 					createUserList(socket);
	 				}
	 			});
	 		});
 		}
 		else{
 			fn("error");
 		}
 	// 	user.push(data.name);
 	// 	userList = [];
		// io.sockets.clients().forEach(function (so) {
		// 	userList.push(app.get(so.id));
		// });
		// socket.emit('userList', userList);
  // 		socket.broadcast.emit('addPlayer', app.get(socket.id));
  		// console.log(blue + "Server: Player %s added to List" + reset, app.get(socket.id).name);
	});

  	socket.on('playerPosition', function(data){
		// console.log('Server: Player position (%s:%s) received', data.x, data.y);
		app.set(socket.id, {name: data.name, x: data.x, y: data.y, d: data.d, map: data.map, moved: data.moved});
		// io.sockets.clients().forEach(function (so) {
		// 	var po = app.get(so.id);
		// 	console.log("Player %s at position (%s:%s)", so.id, po.x, po.y);
		// });
		socket.broadcast.emit('movePlayer', app.get(socket.id));
	});

	socket.on('playerAttack', function(data){
		socket.broadcast.emit('attackPlayer', data);
	});

	socket.on('chatSend', function(data){
		socket.emit('chat', data);
		socket.broadcast.emit('chat', data);
	});

	socket.on('disconnect', function () {
		console.log(red + "Server: Player %s disconnected" + reset, socket.id);
		var player = app.get(socket.id);
		if (player != null){
			var now = new Date();
			var jsonDate = now.toJSON();
			var dbPlayer = {name: player.name, x: player.x, y: player.y, d: player.d, map: player.map, update: jsonDate};
			db.collection('player', function(err, collection) {
	 			collection.update({name: player.name}, dbPlayer, {safe:true}, function(err, result) {
	 				if (err) {console.log("error while set Playerposition in DB")};
	 				// console.log(result);
	 			});
	 		});
	 		var i = user.indexOf(socket.id);
      		user.splice(i,1);
      		socket.broadcast.emit('removePlayer', player.name);
		}
      	// app.set(socket.id,{});
  		console.log(blue + "Server: Player %s removed from List" + reset, socket.id);
	});
});

function createUserList(socket){
	userList = [];
	io.sockets.clients().forEach(function (so) {
		var player = app.get(so.id);
		if (player != null){
			userList.push(player);
		}
	});
	socket.emit('userList', userList);
	socket.broadcast.emit('addPlayer', app.get(socket.id));
	console.log(blue + "Server: Player %s added to List" + reset, app.get(socket.id).name);
}
