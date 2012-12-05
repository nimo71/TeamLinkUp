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
	    val validatedForm = form.validate() 
	    if (validatedForm.hasErrors) 
		    Left(new RegistrationForm(validatedForm.email, "", validatedForm.password, "", validatedForm.errors))
	    else 
	    	Email.fromString(form.email).fold( 
	    	    l => Left(validatedForm), 
	    	    r => Right(new User(r, validatedForm.password)) )
	}
}

class RegistrationForm(
	val email: String, 
    val confirmEmail: String, 
    val password: String, 
    val confirmPassword: String, 
    val errors: FormErrors)
{
	private val emailField = new EmailFormField("email", email, "Invalid email address")
	private val emailConfirmationField = new ConfirmationFormField("confirmEmail", confirmEmail, email, "Please confirm email")
	private val passwordField = new PasswordFormField("password", password, "Invalid password")
	private val passwordConfirmationField = new ConfirmationFormField("confirmPassword", confirmPassword, password, "Please confirm password")
	
	def this(email: String, confirmEmail: String, password: String, confirmPassword: String) = 
	  	this(email, confirmEmail, password, confirmPassword, FormErrors.empty)
	  	
	def this() = this("", "", "", "", FormErrors.empty)

	def validate(): RegistrationForm = {
		val fields = emailField :: emailConfirmationField :: passwordField :: passwordConfirmationField :: Nil
		fields.foldLeft(this)( (form, field) => { 
		    field match {
		      	case f: FormField[_] => 
		      	  	f.validate match {
		      	  	  	case Left(error) => new RegistrationForm(email, confirmEmail, password, confirmPassword, form.errors :+ error) 
		      	  	  	case _ => form
		      	  	}
		      	case _ => form
		    }
		})	
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