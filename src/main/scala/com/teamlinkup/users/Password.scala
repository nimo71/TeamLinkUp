package com.teamlinkup.users

object Password {
  
	private val MIN_PASSWORD_LENGTH = 4
  
	def fromString(value: String): Either[String, Password] = {
		validatePassword(value) match {
		  	case Some(password) => Right(new Password(password))
		  	case _ => Left("Invalid password")
		}
	}

	def validatePassword(value: String): Option[String] = {
		if (value.length < MIN_PASSWORD_LENGTH) 
			None
		else 
		    Some(value)
	}
}

class Password private (val value: String) {
  
	override def equals(that: Any) = {
		that match {
			case p: Password => p.value == value
			case _ => false
		}
	}
}