package com.teamlinkup.users

import com.teamlinkup.pages.Email

class User(val email: Email, val password: String) {
  
  	override def equals(that: Any) = {
		that match {
			case u: User => u.email == email && u.password == password
			case _ => false
		}
	}
}