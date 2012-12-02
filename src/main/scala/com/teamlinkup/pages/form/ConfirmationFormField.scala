package com.teamlinkup.pages.form

class ConfirmationFormField(val name: String, val value: String) {

	def validate(confirmValue: String): Either[FormError, String] = {
		if (value != confirmValue) 
			Left(new FormError(name, "Values do not match"))
		else 
			Right(confirmValue)
	}
}