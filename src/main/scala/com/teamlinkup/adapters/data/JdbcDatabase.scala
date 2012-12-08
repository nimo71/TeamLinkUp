package com.teamlinkup.adapters.data

import com.teamlinkup.data.Database
import com.teamlinkup.users.User
import com.teamlinkup.users.Email

class JdbcDatabase extends Database {
	
    override def selectUser(userId: Email): Option[User] = {
        None
	}
	
	override def insertUser(user: User): User = {
	    user
	}
	
	override def updateUser(user: User): User = {
	    user
	}
}