package com.teamlinkup.pages.form

import com.teamlinkup.users.Password


class PasswordFormField(val name: String, val value: String, val errorMessage: String) extends FormField[Password] {

	def validate(): Either[FormError, Password] = {
	    Password.fromString(value).left map { _ => new FormError(name, errorMessage) }
	}

}