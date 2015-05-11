var express = require('express');
var router = express.Router();
var redis = require("redis");
var logger = require('../libs/log').logger;
var settings = require('../settings');

  /* GET home page. */
  router.get('/', function(req, res, next) {

    if(req.query!=null && req.query.userid!=null){
      console.log("console练级过来的参数:"+ req.query.userid);
    }
    else{
      req.redirect('/welcome.html')
    }
    var client  = redis.createClient(settings.redis.port, settings.redis.ip);
    client.max_attempts = 5;

    if(client != null){
      client.on('disconnect', function () {
        logger.info("redis断开连接");
        console.info("redis断开连接");
      });

      client.on('error',function(err) {
        if (err) {
          logger.error("redis error"+err);
          console.error("redis断开连接");
          throw err;
        }
      });


      //client.get(push_userid,function(err,reply){
      //  if(err){
      //    console.error("获取邮件信息异常："+err)
      //    return null;
      //  }
      //  else{
      //    console.info("redis data：：："+reply);
      //    if(reply){
      //      var result = JSON.parse(reply);
      //      if(result){
      //
      //        // 关闭链接
      //        client.end();
      //      }
      //    }
      //  }
      //});
    }
    res.render('index', { title: 'Express' });
    }
  );

  router.get('/chat',function(req, res, next){

    if(req.query!=null && req.query.userid!=null){
      console.log("参数:"+ req.query.userid);
    }

      res.render('chat', { title: 'webrtc' });
  });



module.exports = router;
