package com.teamlinkup.adapters

import scala.io.Source
import scala.xml.NodeSeq
import scala.xml.XML
import com.teamlinkup.html.Html

class HtmlFile(val path: String) extends Html { 

	override def content(): String = { 
		val source = Source.fromFile(path, "utf-8")
		val content = source.mkString
		source.close
		content
	}
  
	override def nodes(): NodeSeq = { 
		XML.loadString(this.content)
	}
}
