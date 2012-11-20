package com.teamlinkup.pages

import com.teamlinkup.html.HtmlFile

class IndexPage(val message: Option[String]) extends Page {
  
	def this() = this(None)
	
	override def html = new HtmlFile("src/main/resources/www/html/index.html").contentNodes
	
	override def equals(that: Any) = {
		that match {
	    	case indexPage: IndexPage => indexPage.message == message
	    	case _ => false
		}
	}
}