package com.teamlinkup.users

object UserRepository extends UserRepository {
  
    override def save(user: User): Either[String, User] = {
        Left("To be implemented")
    }
    
}

trait UserRepository {
	
	def save(user: User): Either[String, User]
}