package com.teamlinkup.pages.form


class FormError(val fieldName: String, val message: String) {
  
	override def equals(that: Any) = {
		that match {
			case fe: FormError => fe.fieldName == fieldName && fe.message == message
			case _ => false
		}
	}
  
}