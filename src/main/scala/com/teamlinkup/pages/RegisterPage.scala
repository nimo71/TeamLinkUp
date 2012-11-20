package com.teamlinkup.pages

import com.teamlinkup.html.HtmlFile
import com.teamlinkup.users.UserRepository
import com.teamlinkup.users.User

class RegisterPage(private val userRepository: UserRepository, val message: Option[String]) extends Page {

	def this(userRepository: UserRepository) = this(userRepository, None)
  
    def html = new HtmlFile("src/main/resources/www/html/register.html").contentNodes

    def register(user: User): Page = {
    	userRepository.save(user) match {
    		case Right(_) => new IndexPage(Some("You have sucessufully registered, please log in with your username and password"))
    		case Left(errorMessage) => new RegisterPage(userRepository, Some(errorMessage))
    	}
    }
	
	override def equals(that: Any) = {
		that match {
	    	case registerPage: RegisterPage => registerPage.message == message
	    	case _ => false
		}
	}
}