package com.teamlinkup.pages

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.teamlinkup.pages.form.FormError
import com.teamlinkup.pages.form.EmailFormField
import com.teamlinkup.users.Email

class EmailFormFieldSpec extends FlatSpec with ShouldMatchers {
  
	"Email" should "equal another email object of the same address" in {
	    Email.fromString("email@address.com") should be (
	        Email.fromString("email@address.com")
	    )
	}
  
    "Email form field containing a valid email string" should "create an email object" in {
    	val emailStr = "valid@emailaddress.com"
    	new EmailFormField("email", emailStr).validate() should be (
    	    Email.fromString(emailStr)
    	)
    }
    
    "Email form field containing an empty email string" should "create a FormError" in {
    	val emptyEmail = ""
    	new EmailFormField("email", emptyEmail).validate() should be (
    		Left(new FormError("email", "Invalid email address"))
    	)
    }

    "Email form field containing an invalid email string" should "create a FormError" in {
    	val invalidEmail = "invalidEmail"
    	new EmailFormField("email", invalidEmail).validate() should be (
    		Left(new FormError("email", "Invalid email address"))
    	)
    }
    
}