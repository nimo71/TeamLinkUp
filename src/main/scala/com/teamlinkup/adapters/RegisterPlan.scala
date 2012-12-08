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
import com.teamlinkup.adapters.db.DatabaseUserRepository
import com.teamlinkup.adapters.data.JdbcDatabase

object RegisterPlan extends Plan { 

	private val html = new HtmlFile("src/main/resources/www/html/index.html")
	private val userRepository = new DatabaseUserRepository(new JdbcDatabase())
  
    def intent = { 
      	case POST(Params(params)) => {
      		val page = new RegisterPage(userRepository, html).submit(RegistrationForm(params))
      		Ok ~> Html(page.content.nodes)      	  	
      	}
      	case GET(_) => Ok ~> Html(new RegisterPage(userRepository, html).content.nodes) 
    }
}
