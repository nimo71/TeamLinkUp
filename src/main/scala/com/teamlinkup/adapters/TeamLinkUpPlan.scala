package com.teamlinkup.adapters

import unfiltered.request._
import unfiltered.response._


class TeamLinkUpPlan extends unfiltered.filter.Plan {
	
	def intent = { 
		case req @ Path("/") => IndexPlan.intent(req)
		case req @ Path("/index") => IndexPlan.intent(req)
//		case req @ Path("/register") => RegisterPlan.intent(req)
//		case req @ Path("/login") => LoginPlan.intent(req)
//		case req @ Path("/profile") => AuthorisationPlan.intent.onPass(ProfilePlan.intent)(req)
	}
}
