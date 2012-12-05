package com.teamlinkup.pages.form

import com.teamlinkup.users.Email

class EmailFormField(val name: String, val value: String, val errorMessage: String) extends FormField[Email] {

	override def validate(): Either[FormError, Email] = {
	    Email.fromString(value).left map { _ => new FormError(name, errorMessage) }
	}
}