package com.teamlinkup.adapters

import unfiltered.response.Ok
import unfiltered.response.Html
import unfiltered.filter.Plan
import com.teamlinkup.pages.RegisterPage
import com.teamlinkup.users.UserRepository

object RegisterPlan extends Plan { 

    def intent = { 
		case _ => Ok ~> Html(new RegisterPage(UserRepository).html)	
    }

}
