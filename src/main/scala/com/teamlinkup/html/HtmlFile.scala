package com.teamlinkup.html

import scala.io.Source

class HtmlFile(val path: String) { 

    def content(): String = { 
//		val resource = getClass().getClassLoader().getResourceAsStream()
		val source = Source.fromFile(path, "utf-8")
		val content = source.mkString
		source.close
		content
    }

}
