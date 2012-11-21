package com.teamlinkup.pages

import com.teamlinkup.html.HtmlFile
import com.teamlinkup.users.UserRepository
import com.teamlinkup.users.User

class RegisterPage(
    val registrationForm: RegistrationForm, 
    val userRepository: UserRepository
) 
	extends Page 
{
	def this(userRepository: UserRepository) = this(new PopulatedRegistrationForm(), userRepository)
  
    def html = new HtmlFile("src/main/resources/www/html/register.html").contentNodes

    def submit(form: RegistrationForm): Page = {
	    form.validate() match {
  	  	    case Left(formWithErrors) => new RegisterPage(formWithErrors, userRepository) 
  	  	    case Right(user) => register(user)
  	  	}
	} 
      
    private def register(user: User): IndexPage = {
    	userRepository.save(user) match {
    		case Right(_) => new IndexPage(Some("You have sucessufully registered, please log in with your username and password"))
    		case Left(errorMessage) => new IndexPage(Some(errorMessage))
    	}
    }
	
	override def equals(that: Any) = {
		that match {
	    	case registerPage: RegisterPage => registerPage.registrationForm == registrationForm
	    	case _ => false
		}
	}
	
}