package com.teamlinkup.pages.form

import com.teamlinkup.users.Password


class PasswordFormField(val name: String, val value: String) {

	def validate(): Either[FormError, Password] = {
	    Password.fromString(value).left map { new FormError(name, _) }
	}

}