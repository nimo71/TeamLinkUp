package com.teamlinkup.adapters

import unfiltered.request._
import unfiltered.response._
import org.clapper.avsl.Logger


import unfiltered.jetty.ContextBuilder

object Server {
	val logger = Logger(Server.getClass)
	val http = unfiltered.jetty.Http.local(8080)

	def main(args: Array[String]) {
		http.context("/assets") { ctx: ContextBuilder =>
			ctx.resources(getClass().getResource("/www/css"))
		}
		.filter(new TeamLinkUpPlan())
		.run({ svr =>
				unfiltered.util.Browser.open(http.url)
			}, { svr =>
				logger.info("Shutting down server")
			})
	}
}
