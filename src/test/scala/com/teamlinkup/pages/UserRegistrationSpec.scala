package com.teamlinkup.pages

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.teamlinkup.users.UserRepository
import com.teamlinkup.users.User
import com.teamlinkup.pages.form.RegistrationForm
import com.teamlinkup.pages.form.FormErrors
import com.teamlinkup.pages.form.FormError
import com.teamlinkup.adapters.HtmlFile

class UserRegistrationSpec extends FlatSpec with ShouldMatchers {
      
	def registerHtml = new HtmlFile("src/main/resources/www/html/register.html")
  
	def registrationForm = new {
		val valid = new RegistrationForm("valid@email.com", "valid@email.com", "password", "password") 	
	}
	
	def userRepository = new {
    	val successful = new UserRepository {
    		def save(user: User): Either[String, User] = {
    			Right(user)
    		}
    	}
    	
    	val unsuccessful = new UserRepository {
    		def save(user: User): Either[String, User] = {
    			Left("")
    		}
    	}
	}
    
    "Submitting a valid registration form" should 
    	"show the index page" in 
    {
    	val registerPage = new RegisterPage(userRepository.successful, registerHtml)
    	val indexPage = registerPage.submit(registrationForm.valid)
    	
    	indexPage should equal (new IndexPage(Some("You have sucessufully registered, please log in with your username and password")))
    }

    "Submitting an invalid registration form" should 
    	"show the registration page" in 
    {
    	val errorMessage = "There was an error saving the user" 
    	val registerPage = new RegisterPage(userRepository.successful, registerHtml)
    	val newRegisterPage = registerPage.submit(new RegistrationForm())
    	
    	val invalidRegistrationForm = new RegistrationForm("", "", "", "", 
    	    FormErrors.empty :+ 
    	    	new FormError("email", "Invalid email address") :+
    	    	new FormError("password", "Invalid password"))
    	
    	newRegisterPage should equal (new RegisterPage(invalidRegistrationForm, userRepository.successful, registerHtml))
    }

    "Submitting an empty email" should 
    	"show the register page with a error message on the email field" in 
    {
    	val registerPage = new RegisterPage(userRepository.successful, registerHtml)
    	val newRegisterPage = registerPage.submit(new RegistrationForm("", "", "password", "password"))
    	
    	newRegisterPage should be (
    	    new RegisterPage(
    	    	new RegistrationForm("", "", "password", "", 
    	    	    FormErrors.empty :+ 
    	    	    	new FormError("email", "Invalid email address")), 
    	        userRepository.successful,
    	        registerHtml))
    }
    
}