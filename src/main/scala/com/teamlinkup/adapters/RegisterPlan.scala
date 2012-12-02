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
import com.teamlinkup.pages.form.RegistrationForm

object RegisterPlan extends Plan { 

	private val html = new HtmlFile("src/main/resources/www/html/index.html")
  
    def intent = { 
      	case POST(Params(params)) => {
      		val page = new RegisterPage(UserRepository, html).submit(RegistrationForm(params))
      		Ok ~> Html(page.content.nodes)      	  	
      	}
      	case GET(_) => Ok ~> Html(new RegisterPage(UserRepository, html).content.nodes) 
    }
}
