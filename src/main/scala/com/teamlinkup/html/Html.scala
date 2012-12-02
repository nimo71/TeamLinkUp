package com.teamlinkup.html

import scala.xml.NodeSeq

trait Html {
  
	def content(): String
  
	def nodes(): NodeSeq
  
}
