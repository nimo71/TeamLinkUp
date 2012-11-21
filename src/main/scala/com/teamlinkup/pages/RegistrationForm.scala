package com.teamlinkup.pages

import com.teamlinkup.users.User

object RegistrationForm {
  
	def apply(params: Map[String, Seq[String]]): RegistrationForm = {
	  
		def getParamValue(name: String): String = {
    		params.get(name).flatMap { _.headOption } getOrElse("")
    	}
		
    	new PopulatedRegistrationForm(
    	    getParamValue("email"), 
    	    getParamValue("confirmEmail"), 
      	  	getParamValue("password"), 
      	  	getParamValue("confirmPassword"))
	}
}

trait RegistrationForm {
	def validate(): Either[InvalidRegistrationForm, User]
}

class PopulatedRegistrationForm(
    val email: String, 
    val confirmEmail: String, 
    val password: String, 
    val confirmPassword: String) extends RegistrationForm 
{
	def this() = this("", "", "", "")
	
	override def validate(): Either[InvalidRegistrationForm, User] = {
		null
	} 
	
	override def equals(that: Any) = {
		that match {
	    	case form: PopulatedRegistrationForm => 
	    	  	form.email == email && 
	    	  	form.confirmEmail == confirmEmail && 
	    	  	form.password == password &&
	    	  	form.confirmPassword == confirmPassword
	    	  	
	    	case _ => false
		}
	}
}

class InvalidRegistrationForm(
    override val email: String, 
    override val confirmEmail: String, 
    override val password: String, 
    override val confirmPassword: String, 
    val errors: FormErrors
) 
   	extends PopulatedRegistrationForm(email, confirmEmail, password, confirmPassword) 
{
    override def validate(): Either[InvalidRegistrationForm, User] = {
        Left(this)
    }
    
    override def equals(that: Any) = {
		that match {
	    	case form: InvalidRegistrationForm => 
	    	  	form.email == email && 
	    	  	form.confirmEmail == confirmEmail && 
	    	  	form.password == password &&
	    	  	form.confirmPassword == confirmPassword &&
	    	  	form.errors == errors
	    	  	
	    	case _ => false
		}
	}
}