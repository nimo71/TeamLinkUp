package com.teamlinkup.pages.form

trait FormField {

	def validate(): Either[FormError, String]

}