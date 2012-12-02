package com.teamlinkup.pages

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.teamlinkup.pages.form.ConfirmationFormField
import com.teamlinkup.pages.form.FormError

class ConfirmationFormFieldSpec extends FlatSpec with ShouldMatchers {
  
	"Validation of a confirmation form field with an equal value" should "create return the confirmed value" in {
    	val value = "valid@emailaddress.com"
    	new ConfirmationFormField("confirm", value).validate(value) should be (
    	    Right(value)
    	)
    }
    
    "Confirmation form field containing an empty string" should "create a FormError" in {
    	val emptyValue = ""
    	new ConfirmationFormField("confirm", emptyValue).validate("something") should be (
    		Left(new FormError("confirm", "Values do not match"))
    	)
    }

    "Confirmation form field containing a non matching value" should "create a FormError" in {
    	val nonMatch = "dontmatch"
    	new ConfirmationFormField("confirm", nonMatch).validate("something") should be (
    		Left(new FormError("confirm", "Values do not match"))
    	)
    }
    

}