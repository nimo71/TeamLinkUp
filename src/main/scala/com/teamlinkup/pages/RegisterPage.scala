package com.teamlinkup.pages

import com.teamlinkup.users.UserRepository
import com.teamlinkup.users.User
import com.teamlinkup.pages.form.RegistrationForm
import com.teamlinkup.html.Html

class RegisterPage(
    val registrationForm: RegistrationForm, 
    val userRepository: UserRepository, 
    val html: Html
) 
    extends Page
{
	def this(userRepository: UserRepository, html: Html) = this(new RegistrationForm(), userRepository, html)
  
	override def content: Html = html
	
    def submit(form: RegistrationForm): Page = {
	    RegistrationForm.validate(form) match {
  	  	    case Left(invalidForm) => new RegisterPage(invalidForm, userRepository, html) 
  	  	    case Right(user) => register(user)
  	  	}
	}
      
    private def register(user: User): Page = {
    	userRepository.save(user) match {
    		case Right(_) => new IndexPage(Some("You have sucessufully registered, please log in with your username and password"))
    		case Left(errorMessage) => new IndexPage(Some(errorMessage))
    	}
    }
	
	override def equals(that: Any): Boolean = {
		that match {
	    	case registerPage: RegisterPage => registerPage.registrationForm == registrationForm
	    	case _ => false
		}
	}
	
}