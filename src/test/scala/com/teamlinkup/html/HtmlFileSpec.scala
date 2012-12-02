package com.teamlinkup.html

import org.scalatest.FlatSpec
import com.teamlinkup.adapters.HtmlFile

class HtmlFileSpec extends FlatSpec {
  
    "HtmlFile" should "Load the contents of an html file" in { 

        expect("<html><body><p>Test file</p></body></html>") {
            val html = new HtmlFile("src/test/resources/test.html")
            html.content
        }
    }

}
