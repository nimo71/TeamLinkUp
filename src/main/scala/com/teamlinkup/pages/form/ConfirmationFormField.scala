package com.teamlinkup.pages.form

class ConfirmationFormField(val name: String, val value: String, val confirmValue: String) extends FormField {

	override def validate(): Either[FormError, String] = {
		if (value != confirmValue) 
			Left(new FormError(name, "Values do not match"))
		else 
			Right(confirmValue)
	}
}