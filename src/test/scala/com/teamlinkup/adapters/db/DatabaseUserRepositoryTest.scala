package com.teamlinkup.adapters.db

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers._
import com.teamlinkup.data.Database
import com.teamlinkup.users.User
import com.teamlinkup.users.Email

class DatabaseUserRepositoryTest extends FlatSpec {
  
	class TestDatabase extends Database {
		var user: Option[User] = None
		var selectUserCalled = false
		var insertUserCalled = false
		var updateUserCalled = false
		
		override def selectUser(email: Email): Option[User] = {
			selectUserCalled = true
			user
		}
		
		override def insertUser(user: User): User = {
		  	insertUserCalled = true
			this.user = Some(user)
			user
		}
		
		override def updateUser(user: User): User = {
		    updateUserCalled = true
		    user
		}
	}
  
    "saving a user in the database user repository" should "insert if user not present" in 
    {
    	val testDatabase = new TestDatabase()
    	
    	Email.fromString("email@test.com").fold(
    	    err => { fail(err) },  
    	    email => {
    	      
    	    	val user = new User(email, "password")
    	    	new DatabaseUserRepository(testDatabase).save(user)
    	    	
    	    	assert(testDatabase.selectUserCalled)
    	    	assert(!testDatabase.updateUserCalled)
    	    	assert(testDatabase.insertUserCalled)
    	    	testDatabase.user should equal (Some(user))
    	    }
    	)
    }
    
    "saving a user in the database user repository" should "update if user present" in 
    {
    	val testDatabase = new TestDatabase()
    	
    	Email.fromString("email@test.com").fold(
			err => { fail(err) },  
			email => {
				
				val user = new User(email, "password")
				testDatabase.user = Some(user)
				
				new DatabaseUserRepository(testDatabase).save(user)
				
				assert(testDatabase.selectUserCalled)
				assert(testDatabase.updateUserCalled)
			}
		)
    }
}