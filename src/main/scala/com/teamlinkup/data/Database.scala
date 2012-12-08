package com.teamlinkup.data

import com.teamlinkup.users.User
import com.teamlinkup.users.Email

trait Database {
	def selectUser(userId: Email): Option[User]
	def insertUser(user: User): User
	def updateUser(user: User): User
}