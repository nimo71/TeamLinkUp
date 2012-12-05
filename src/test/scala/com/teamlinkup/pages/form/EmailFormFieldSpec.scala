package com.teamlinkup.pages.form

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

import com.teamlinkup.users.Email

class EmailFormFieldSpec extends FlatSpec with ShouldMatchers {
  
	val errorMessage = "some error message"
  
	"Email" should "equal another email object of the same address" in {
	    Email.fromString("email@address.com") should be (
	        Email.fromString("email@address.com")
	    )
	}
  
    "Email form field containing a valid email string" should "create an email object" in {
    	val emailStr = "valid@emailaddress.com"
    	new EmailFormField("email", emailStr, errorMessage).validate() should be (
    	    Email.fromString(emailStr)
    	)
    }
    
    "Email form field containing an empty email string" should "create a FormError" in {
    	val emptyEmail = ""
    	new EmailFormField("email", emptyEmail, errorMessage).validate() should be (
    		Left(new FormError("email", errorMessage))
    	)
    }

    "Email form field containing an invalid email string" should "create a FormError" in {
    	val invalidEmail = "invalidEmail"
    	new EmailFormField("email", invalidEmail, errorMessage).validate() should be (
    		Left(new FormError("email", errorMessage))
    	)
    }
    
}