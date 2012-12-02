package com.teamlinkup.pages;

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import scala.xml.NodeSeq
import com.teamlinkup.users.UserRepository
import com.teamlinkup.adapters.HtmlFile

class RegisterPageSpec extends FlatSpec with ShouldMatchers {
  
	def registerPage = new RegisterPage(null, new HtmlFile("src/main/resources/www/html/register.html"))
  
    "Register Page" should "Display a heading TeamLinkUp" in {
             
		expect("TeamLinkUp") {
            (registerPage.html.nodes \\ "h1").text
        }
    }
  
    "Register Page" should "Display a 'Home' link" in {
    	expect("Home") {
    		(registerPage.html.nodes \\ "a").text
    	}
    }
    
    "Register Page" should "Display a registration form" in {
    	
    	def assertInputField(form: NodeSeq, tagName: String, attrType: String, attrName: String) {
    		val inputField = (form \ tagName).find { t => 
    			((t \ "@name").text == attrName) && ((t \ "@type").text == attrType) 
    		}
    		inputField should not be None
    	}
    	
    	val registrationForm = registerPage.html.nodes \\ "form" 
    	
    	(registrationForm \ "@name").text should equal ("registrationForm")
    	(registrationForm \ "@method").text should equal ("post")
    	(registrationForm \ "@action").text should equal ("./register")
    	
    	assertInputField(registrationForm, "input", "text", "email")
    	assertInputField(registrationForm, "input", "text", "confirmEmail")
    	assertInputField(registrationForm, "input", "password", "password")
    	assertInputField(registrationForm, "input", "password", "confirmPassword")

    	val submitButton = (registrationForm \ "input").find { 
    		t => ((t \ "@type").text == "submit") && ((t \ "@value").text == "Register") 
    	}
    	submitButton should not be None
    }
    
}