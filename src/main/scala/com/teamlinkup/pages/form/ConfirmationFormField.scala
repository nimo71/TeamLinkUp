package com.teamlinkup.pages.form

class ConfirmationFormField(
    val name: String, 
    val value: String, 
    val confirmValue: String, 
    val errorMessage: String) 
		extends FormField[String] 
{
	override def validate(): Either[FormError, String] = {
		if (value != confirmValue) 
			Left(new FormError(name, errorMessage))
		else 
			Right(confirmValue)
	}
}