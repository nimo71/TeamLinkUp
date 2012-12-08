package com.teamlinkup.adapters.db

import com.teamlinkup.data.Database
import com.teamlinkup.users.UserRepository
import com.teamlinkup.users.User

class DatabaseUserRepository(val database: Database) extends UserRepository {
  
    def save(user: User): Either[String, User] = {
    	val saved = database.selectUser(user.email) match {
    	    case Some(u) => database.updateUser(user)
    	    case None => database.insertUser(user)
    	}
    	Right(saved)
    }

}