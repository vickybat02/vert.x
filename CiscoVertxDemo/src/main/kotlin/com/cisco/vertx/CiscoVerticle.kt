package com.cisco.vertx

import io.vertx.core.Vertx
import io.vertx.ext.web.Router


	fun main(args:Array<String>){
		println("Cisco test")

		val vertx = Vertx.vertx();
		val httpServer = vertx.createHttpServer()
		val mongoVertx = Vertx.vertx();

		val router = Router.router(vertx)
		router.get("/crudOperations")
			  .handler({
				  routingContext ->
				  val response = routingContext.response()
				  response.putHeader("content-type", "application/json")
						  .setChunked(true)
					  .write("Hi Cisco\n"+mongoVertx.deployVerticle(MongoClientVerticle()))
				  	  .end("Ended")
					})

		httpServer.requestHandler(router::accept)
				  .listen(8091)
	}

