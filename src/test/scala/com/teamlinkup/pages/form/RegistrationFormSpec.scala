package com.teamlinkup.pages.form

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.teamlinkup.users.User
import com.teamlinkup.users.Email

class RegistrationFormSpec extends FlatSpec with ShouldMatchers {
  
	val validEmail = "test@email.com"
	val validPassword = "password"
  
	"Submitting a registration form with valid values entered into the form fields" should 
		"result in a populated User object" in 
	{
		val validForm = new RegistrationForm(validEmail, validEmail, validPassword, validPassword)
		
		RegistrationForm.validate(validForm) should be (
		    Email.fromString(validEmail).right map { new User(_, validPassword) }
		)
	}
      
    "Submitting a RegistrationForm with an empty email address" should 
		"result in an RegistrationForm with invalid email FormError" in 
	{
    	val invalidForm = new RegistrationForm("", "", validPassword, validPassword)
    	val expected = Left(new RegistrationForm(
    				"", "", validPassword, "", FormErrors.empty :+ new FormError("email", "Invalid email address") ))
    				
    	val validated = RegistrationForm.validate(invalidForm) 
    	
    	validated should be ( expected )
    }
    
    "Submitting a RegistrationForm with an invalid email address" should 
    	"result in an RegistrationForm with invalid email FormError" in 
    {
    	val invalidForm = new RegistrationForm("invalidemail", "invalidemail", validPassword, validPassword)
    	RegistrationForm.validate(invalidForm) should be (
    			Left(new RegistrationForm(
    					"invalidemail", "", validPassword, "", FormErrors.empty :+ new FormError("email", "Invalid email address") )))
    }

    "Submitting a RegistrationForm where email and confirmation don't match" should 
    	"result in an RegistrationForm with confirm email FormError" in 
    {
    	val invalidForm = new RegistrationForm(validEmail, "not confirmed", validPassword, validPassword)
    	RegistrationForm.validate(invalidForm) should be (
    			Left(new RegistrationForm(
    					validEmail, "", validPassword, "", FormErrors.empty :+ new FormError("confirmEmail", "Please confirm email") )))
    }
    
    "Submitting a RegistrationForm wwith an invalid password" should 
    	"result in an RegistrationForm with an invalid password FormError" in 
    {
    	val shortPassword = "bad"
    	val invalidForm = new RegistrationForm(validEmail, validEmail, shortPassword, shortPassword)
    	RegistrationForm.validate(invalidForm) should be (
    			Left(new RegistrationForm(
    					validEmail, "", shortPassword, "", FormErrors.empty :+ new FormError("password", "Invalid password") )))
    }
    
    "Submitting a RegistrationForm where password and confirmation don't match" should 
    	"result in an RegistrationForm with confirm password FormError" in 
    {
    	val invalidForm = new RegistrationForm(validEmail, validEmail, validPassword, "unconfirmed")
    	RegistrationForm.validate(invalidForm) should be (
    			Left(new RegistrationForm(
    					validEmail, "", validPassword, "", FormErrors.empty :+ new FormError("confirmPassword", "Please confirm password") )))
    }
    
//    "Consider moving form values into form fields" should "" in { fail }
    
}