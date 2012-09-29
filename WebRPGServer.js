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

// server.listen(8080);
server.listen(81);

app.get('/*/*.(js|css|java|png)', function(req, res){
  res.sendfile("."+req.url);
});

app.get('/', function (req, res) {
  res.sendfile(__dirname + '/WebRPG.html');
});
io.set('log level', 1);
io.sockets.on('connection', function (socket) {
	console.log(green + "Server: Player %s connected" + reset, socket.id);
	//app.set(socket.id, {x: 0, y: 0, map: 00});
	socket.emit('hello', { id: socket.id});
	app.set(socket.id, {id: socket.id, x: 500, y: 300, d: 0, map: 00});
	user.push(socket.id);
	userList = [];
	io.sockets.clients().forEach(function (so) {
			userList.push(app.get(so.id));
	});
	socket.emit('userList', userList);
  	socket.broadcast.emit('addPlayer', app.get(socket.id));
  	console.log(blue + "Server: Player %s added to List" + reset, app.get(socket.id).id);

  	socket.on('playerPosition', function(data){
		// console.log('Server: Player position (%s:%s) received', data.x, data.y);
		app.set(socket.id, {id: socket.id, x: data.x, y: data.y, d: data.d, map: data.map});
		// io.sockets.clients().forEach(function (so) {
		// 	var po = app.get(so.id);
		// 	console.log("Player %s at position (%s:%s)", so.id, po.x, po.y);
		// });
		socket.broadcast.emit('movePlayer', app.get(socket.id));
	});

	socket.on('disconnect', function () {
		console.log(red + "Server: Player %s disconnected" + reset, socket.id);
		var i = user.indexOf(socket.id);
      	user.splice(i,1);
      	socket.broadcast.emit('removePlayer', socket.id);
  		console.log(blue + "Server: Player %s removed from List" + reset, socket.id);
	});
});
