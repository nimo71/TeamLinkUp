package com.teamlinkup.html

import scala.io.Source
import scala.xml.NodeSeq
import scala.xml.XML

class HtmlFile(val path: String) { 

    def content(): String = { 
		val source = Source.fromFile(path, "utf-8")
		val content = source.mkString
		source.close
		content
    }
    
    def contentNodes(): NodeSeq = { 
    	XML.loadString(this.content)
    }
}
