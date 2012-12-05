package com.teamlinkup.pages.form

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.teamlinkup.users.Password

class PasswordFormFieldSpec extends FlatSpec with ShouldMatchers {
  
	val errorMessage = "some err msg"
  
	"Password" should "equal another password object of the same address" in {
	    Password.fromString("password") should be (
	        Password.fromString("password")
	    )
	}
  
    "Password form field containing a valid password string" should "create an password object" in {
    	val passwordStr = "password"
    	new PasswordFormField("password", passwordStr, errorMessage).validate() should be (
    	    Password.fromString(passwordStr)
    	)
    }
    
    "Email form field containing an empty email string" should "create a FormError" in {
    	val emptyPassword = ""
    	new PasswordFormField("password", emptyPassword, errorMessage).validate() should be (
    		Left(new FormError("password", errorMessage))
    	)
    }

    "Password form field containing an invalid password string" should "create a FormError" in {
    	val shortPassword = "123"
    	new PasswordFormField("password", shortPassword, errorMessage).validate() should be (
    		Left(new FormError("password", errorMessage))
    	)
    }
    
}