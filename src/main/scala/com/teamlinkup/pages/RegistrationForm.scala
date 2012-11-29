package com.teamlinkup.pages

import com.teamlinkup.users.User

object RegistrationForm {
  
	def apply(params: Map[String, Seq[String]]): RegistrationForm = {
	  
		def getParamValue(name: String): String = {
    		params.get(name).flatMap { _.headOption } getOrElse("")
    	}
		
    	new RegistrationForm(
    	    getParamValue("email"), 
    	    getParamValue("confirmEmail"), 
      	  	getParamValue("password"), 
      	  	getParamValue("confirmPassword"))
	}
	
	def validate(form: RegistrationForm): Either[RegistrationForm, User] = {
	    val validatedForm = form.validateEmail().validatePassword() 
	    if (validatedForm.hasErrors) 
		    Left(validatedForm)
	    else {
	    	Email.fromString(form.email).fold( 
	    	    l => { Left(validatedForm) }, 
	    	    r => { Right(new User(r, validatedForm.password)) } )
	    }
	}
}

class RegistrationForm(
	val email: String, 
    val confirmEmail: String, 
    val password: String, 
    val confirmPassword: String, 
    val errors: FormErrors)
{
	private val emailField = new EmailFormField("email", email)
	
	def this(email: String, confirmEmail: String, password: String, confirmPassword: String) = 
	  	this(email, confirmEmail, password, confirmPassword, FormErrors.empty)
	  	
	def this() = this("", "", "", "", FormErrors.empty)

	def validateEmail(): RegistrationForm = {
	  
		def validateEmailConfirmation(form: RegistrationForm) = 
			if (confirmEmail.trim == email.trim) {
			    form
			} 
			else {
				val error = new FormError("confirmEmail", "Please confirm email")
				new RegistrationForm(form.email, "", form.password, "", form.errors :+ error)
			}
	  
		emailField.validate() match {
		    case Right(email) => validateEmailConfirmation(this) 
		    case Left(error) => new RegistrationForm(
		        email, "", password, "", errors :+ error )
		}
	}
	
	def validatePassword(): RegistrationForm = {
	    this
	}
	
	def hasErrors(): Boolean = errors.length > 0
	
	override def equals(that: Any) = {
		that match {
	    	case form: RegistrationForm => 
	    	  	form.email == email && 
	    	  	form.confirmEmail == confirmEmail && 
	    	  	form.password == password &&
	    	  	form.confirmPassword == confirmPassword &&
	    	  	form.errors == errors
	    	  	
	    	case _ => false
		}
	}
}