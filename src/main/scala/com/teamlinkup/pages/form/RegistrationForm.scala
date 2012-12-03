package com.teamlinkup.pages.form

import com.teamlinkup.users.User
import com.teamlinkup.users.Email

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
		    Left(new RegistrationForm(validatedForm.email, "", validatedForm.password, "", validatedForm.errors))
	    else 
	    	Email.fromString(form.email).fold( 
	    	    l => { Left(validatedForm) }, 
	    	    r => { Right(new User(r, validatedForm.password)) } )
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
	private val emailConfirmationField = new ConfirmationFormField("confirmEmail", confirmEmail, email)
	private val passwordField = new PasswordFormField("password", password)
	private val passwordConfirmationField = new ConfirmationFormField("confirmPassword", confirmPassword, password)
	
	def this(email: String, confirmEmail: String, password: String, confirmPassword: String) = 
	  	this(email, confirmEmail, password, confirmPassword, FormErrors.empty)
	  	
	def this() = this("", "", "", "", FormErrors.empty)

	def validateEmail(): RegistrationForm = {
	  
		def validateEmailConfirmation(form: RegistrationForm) = {
			if (confirmEmail.trim == email.trim) {
			    form
			} 
			else {
				val error = new FormError("confirmEmail", "Please confirm email")
				new RegistrationForm(form.email, form.confirmEmail, form.password, form.confirmPassword, form.errors :+ error)
			}
		}
		
		emailField.validate() match {
		    case Right(email) => validateEmailConfirmation(this) 
		    case Left(error) => new RegistrationForm(
		        email, confirmEmail, password, confirmPassword, errors :+ error )
		}
		
	}
	
	def validatePassword(): RegistrationForm = {
		
		def validatePasswordConfirmation(form: RegistrationForm) = { 
			if (confirmPassword.trim == password.trim) {
				form
			}
			else {
				val error = new FormError("confirmPassword", "Please confirm password")
				new RegistrationForm(form.email, form.confirmEmail, form.password, form.confirmPassword, form.errors :+ error)
			}
		}
	  
	    passwordField.validate() match {
	      	case Right(password) => validatePasswordConfirmation(this)
	      	case Left(error) => new RegistrationForm(email, confirmEmail, password, confirmPassword, errors :+ error )
	    }
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