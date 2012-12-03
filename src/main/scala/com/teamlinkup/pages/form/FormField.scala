package com.teamlinkup.pages.form

trait FormField[T] {

	def validate(): Either[FormError, T]

}