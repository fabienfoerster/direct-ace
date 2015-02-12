var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var morgan = require('morgan');
var local = require('amqplib').connect('amqp://localhost');
var remote = require('amqplib').connect('amqp://54.154.176.223');
var localq = 'direct-ace-local';
var remoteq = 'sensor-values-queue';
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

router.use(function(req, res, next) {

       res.header('Access-Control-Allow-Origin', '*');
       res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE');
       res.header('Access-Control-Allow-Headers', 'Content-Type');

       next();
   });


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
        remote.then(function(conn) {
          var okremote = conn.createChannel();
          console.log(msg.content.toString());
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
