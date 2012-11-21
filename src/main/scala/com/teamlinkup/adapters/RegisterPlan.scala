package com.teamlinkup.adapters

import unfiltered.response.Ok
import unfiltered.response.Html
import unfiltered.filter.Plan
import com.teamlinkup.pages.RegisterPage
import com.teamlinkup.users.UserRepository
import unfiltered.request.POST
import unfiltered.request.GET
import unfiltered.request.Params
import com.teamlinkup.pages.IndexPage
import com.teamlinkup.pages.RegistrationForm

object RegisterPlan extends Plan { 

    def intent = { 
      	case POST(Params(params)) => {
      		val page = new RegisterPage(UserRepository).submit(RegistrationForm(params))
      		Ok ~> Html(page.html)      	  	
      	}
      	case GET(_) => Ok ~> Html(new RegisterPage(UserRepository).html) 
    }
}
