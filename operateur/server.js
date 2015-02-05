var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var morgan = require('morgan');
var local = require('amqplib').connect('amqp://localhost');
//var remote = require('amqplib').connect('amqp:https://sqs.us-west-2.amazonaws.com/921269828628/direct-ace-remote');
var localq = 'direct-ace-local';
var remoteq = 'direct-ace-remote';
// set up our express application
// HTTP request logger
app.use(morgan('dev'));

// configure app to use bodyParser()
// this will let us get the data from a POST
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

// ROUTES FOR OUR API
// =============================================================================
var router = express.Router();              // get an instance of the express Router
var port = process.env.PORT || 8080;        // set our port

router.get('/', function(req,res) {
  res.json({message: 'hooray! welcome to our api!'});
});

router.post('/data', function(req,res){
  // Publisher
  local.then(function(conn) {
    var ok = conn.createChannel();
    ok = ok.then(function(ch) {
      ch.assertQueue(localq);
      ch.sendToQueue(localq, new Buffer(JSON.stringify(req.body)));
    });
    return ok;
  }).then(null, console.warn);
  res.json(req.body);
})


app.use('/api', router);
app.listen(port);


// Consume local and publish remote
local.then(function(conn) {
  var oklocal = conn.createChannel();
  oklocal = oklocal.then(function(ch) {
    ch.assertQueue(localq);
    ch.consume(localq, function(msg) {
      if (msg !== null) {
        local.then(function(conn) {
          var okremote = conn.createChannel();
          okremote = okremote.then(function(ch) {
            ch.assertQueue(remoteq);
            ch.sendToQueue(remoteq, new Buffer(msg));
          });
          return okremote;
        }).then(null, console.warn);
        ch.ack(msg);
      }
    });
  });
  return oklocal;
}).then(null, console.warn);

console.log('Magic happens on port ' + port);
