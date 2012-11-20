package com.teamlinkup.pages;

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.teamlinkup.users.UserRepository
import com.teamlinkup.users.User

class UserRegistrationSpec extends FlatSpec with ShouldMatchers {
      
    "Submitting the registration form" should "save a user registration in the user repository" in {
      
    	object TestUserRepository extends UserRepository {
    		var savedUser: User = null
    		override def save(user: User): Either[String, User] = {
    			savedUser = user	
    			Right(user)
    		}
    	}
      
    	val registerPage = new RegisterPage(TestUserRepository)
    	val testUser = new User("TestUserName", "TestPassword")
    	val homePage = registerPage.register(testUser)
    	
    	TestUserRepository.savedUser should equal (testUser)
    }
    
    "Submitting the registration form" should "show the index page if the user is saved successfully" in {
    	
    	object SuccessfulUserRepository extends UserRepository {
    		override def save(user: User): Either[String, User] = {
    			Right(user)
    		}
    	}
    	
    	val registerPage = new RegisterPage(SuccessfulUserRepository)
    	val testUser = new User("TestUserName", "TestPassword")
    	val indexPage = registerPage.register(testUser)
    	
    	indexPage should equal (new IndexPage(Some("You have sucessufully registered, please log in with your username and password")))
    }

    "Submitting the registration form" should "show the registration page if there is a problem saving the user" in {
    	
    	val errorMessage = "There was an error saving the user" 
      
    	object UnsuccessfulUserRepository extends UserRepository {
    		override def save(user: User): Either[String, User] = {
    			Left(errorMessage)
    		}
    	}
    	
    	val registerPage = new RegisterPage(UnsuccessfulUserRepository)
    	val testUser = new User("TestUserName", "TestPassword")
    	val newRegisterPage = registerPage.register(testUser)
    	
    	newRegisterPage should equal (new RegisterPage(UnsuccessfulUserRepository, Some(errorMessage)))
    }
    
}