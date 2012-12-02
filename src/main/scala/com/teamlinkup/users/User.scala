package com.teamlinkup.users


class User(val email: Email, val password: String) {
  
  	override def equals(that: Any) = {
		that match {
			case u: User => u.email == email && u.password == password
			case _ => false
		}
	}
}