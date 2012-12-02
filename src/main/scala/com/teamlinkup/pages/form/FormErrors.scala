package com.teamlinkup.pages.form

object FormErrors {
    val empty = new FormErrors(Nil)
}

class FormErrors(val errors: Seq[FormError]) {
  
	def :+(error: FormError): FormErrors = {
	    new FormErrors(errors :+ error)
	}
  
	def length = errors.length
	
	override def equals(that: Any) = {
	  	that match {
	  	   case fes: FormErrors => fes.errors == errors
	  	   case _ => false
	  	}
	}

}

class FormError(val fieldName: String, val message: String) {
  
	override def equals(that: Any) = {
		that match {
			case fe: FormError => fe.fieldName == fieldName && fe.message == message
			case _ => false
		}
	}
  
}