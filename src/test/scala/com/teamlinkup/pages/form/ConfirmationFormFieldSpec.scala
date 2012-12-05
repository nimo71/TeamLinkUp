package com.teamlinkup.pages.form

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ConfirmationFormFieldSpec extends FlatSpec with ShouldMatchers {
  
	"Validation of a confirmation form field with an equal value" should "create return the confirmed value" in {
    	val value = "valid@emailaddress.com"
    	new ConfirmationFormField("confirm", value, value, "").validate() should be (
    	    Right(value)
    	)
    }
    
    "Confirmation form field containing an empty string" should "create a FormError" in {
    	val emptyValue = ""
    	val errorMessage = "Some error message"
    	new ConfirmationFormField("confirm", emptyValue, "something", errorMessage).validate() should be (
    		Left(new FormError("confirm", errorMessage))
    	)
    }

    "Confirmation form field containing a non matching value" should "create a FormError" in {
    	val nonMatch = "dontmatch"
    	val errorMessage = "Some error"
    	new ConfirmationFormField("confirm", nonMatch, "something", errorMessage).validate() should be (
    		Left(new FormError("confirm", errorMessage))
    	)
    }
    
}