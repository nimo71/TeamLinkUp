package com.teamlinkup.pages

class PasswordFormField(val name: String, val value: String) {

	def validate(): Either[FormError, Password] = {
	    Password.fromString(value).left map { new FormError(name, _) }
	}

}