package com.teamlinkup.pages

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.teamlinkup.users.UserRepository
import com.teamlinkup.users.User

class RegistrationFormSpec extends FlatSpec with ShouldMatchers {
  
	"Submitting a registration form with valid values entered into the form fields" should 
		"result in a populated User object" in 
	{
		val email = "test@email.com"
		val password = "password"
		val validForm = new RegistrationForm(email, email, password, password)
		
		val expectedUser = Email.fromString(email).right map { new User(_, password) }
		RegistrationForm.validate(validForm) should be (
		    expectedUser
		)
	}
      
    "Submitting a RegistrationForm with an empty email address" should 
		"result in an RegistrationForm with invalid email FormError" in 
	{
    	val invalidForm = new RegistrationForm("", "", "password", "password")
		RegistrationForm.validate(invalidForm) should be (
		    Left(new RegistrationForm(
		        "", "", "password", "", FormErrors.empty :+ new FormError("email", "Invalid email address") )))
    }
    
    "Submitting a RegistrationForm with an invalid email address" should 
    	"result in an RegistrationForm with invalid email FormError" in 
    {
    	val invalidForm = new RegistrationForm("invalidemail", "invalidemail", "password", "password")
    	RegistrationForm.validate(invalidForm) should be (
    			Left(new RegistrationForm(
    					"invalidemail", "", "password", "", FormErrors.empty :+ new FormError("email", "Invalid email address") )))
    }

    "Submitting a RegistrationForm where email and confirmation don't match" should 
    	"result in an RegistrationForm with confirm email FormError" in 
    {
    	val invalidForm = new RegistrationForm("valid@email.com", "not confirmed", "password", "password")
    	RegistrationForm.validate(invalidForm) should be (
    			Left(new RegistrationForm(
    					"valid@email.com", "", "password", "", FormErrors.empty :+ new FormError("confirmEmail", "Please confirm email") )))
    }
    
    
}