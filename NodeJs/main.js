const express = require('express');
var path = require('path');

var app = express();

console.log('Starting server...');

app.use(express.static(__dirname + '/public'));

app.get('/', (req, res) => {
	res.sendFile(path.join(__dirname + '/public/index.html'));
});
app.listen(process.env.PORT || 3000)
console.log('Server is listening on port 3000')