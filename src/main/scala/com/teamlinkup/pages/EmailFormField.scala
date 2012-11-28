package com.teamlinkup.pages

class EmailFormField(val name: String, val value: String) {

	def validate(): Either[FormError, Email] = {
	    Email.fromString(value).left map { new FormError(name, _) }
	}
}