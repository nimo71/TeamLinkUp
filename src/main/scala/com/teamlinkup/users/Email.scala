package com.teamlinkup.users
import scala.util.matching._

object Email {
  
	def fromString(value: String): Either[String, Email] = {
		validateEmail(value) match {
		  	case Some(email) => Right(new Email(email))
		  	case _ => Left("Invalid email address")
		}
	}

	def validateEmail(value: String): Option[String] = {
		val reg = """(?i)\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b""".r
		reg findFirstIn value
	}
}

class Email private (val value: String) {
  
	override def equals(that: Any) = {
		that match {
			case e: Email => e.value == value
			case _ => false
		}
	}
}