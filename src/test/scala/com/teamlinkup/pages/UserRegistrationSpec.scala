package com.teamlinkup.pages;

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.teamlinkup.users.UserRepository
import com.teamlinkup.users.User

class UserRegistrationSpec extends FlatSpec with ShouldMatchers {
      
    "Submitting a the registration page" should "show the index page if the submission is valid" in {
        
    	object RegistrationFormStub extends RegistrationForm {
    	    var validateCalled = false
    		override def validate(): Either[InvalidRegistrationForm, User] = {
    	        validateCalled = true
    	        Right(new User("name@domain.com", "password"))
    		}
    	}
    	
    	object UserRepositoryStub extends UserRepository {
    		def save(user: User): Either[String, User] = {
    			Left("")
    		}
    	}
      
    	val registerPage = new RegisterPage(UserRepositoryStub)
    	registerPage.submit(RegistrationFormStub)
    	
    	RegistrationFormStub.validateCalled should be (true)
    }
    
    "Submitting a valid registration form" should "show the index page" in {
    	
    	object ValidRegistrationFormStub extends RegistrationForm {
    		override def validate(): Either[InvalidRegistrationForm, User] = {
    	        Right(new User("name@domain.com", "password"))
    		}
    	}
    	
    	object SuccessfulUserRepositoryStub extends UserRepository {
    		def save(user: User): Either[String, User] = {
    			Right(user)
    		}
    	}
    	
    	val registerPage = new RegisterPage(SuccessfulUserRepositoryStub)
    	val indexPage = registerPage.submit(ValidRegistrationFormStub)
    	
    	indexPage should equal (new IndexPage(Some("You have sucessufully registered, please log in with your username and password")))
    }

    "Submitting an invalid registration form" should "show the registration page" in {
    	
    	val errorMessage = "There was an error saving the user" 
      
    	object SuccessfulUserRepositoryStub extends UserRepository {
    		def save(user: User): Either[String, User] = {
    			Right(user)
    		}
    	}
    	
    	val registerPage = new RegisterPage(SuccessfulUserRepositoryStub)
    	val newRegisterPage = registerPage.submit(new InvalidRegistrationForm("", "", "", "", new FormErrors()))
    	
    	newRegisterPage should equal (new RegisterPage(SuccessfulUserRepositoryStub))
    }

//    "Submitting an invalid email" should "show the register page with a validation message on the email field" in {
//    	fail
//    }
    
}