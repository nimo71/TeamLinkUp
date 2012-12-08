package com.teamlinkup.users

trait UserRepository {
	
	def save(user: User): Either[String, User]
	
}