package com.teamlinkup.pages;

import org.scalatest.FlatSpec

class HomePageSpec extends FlatSpec {
  
    "Index Page" should "Display a heading TeamLinkUp" in {
        val indexPage = new IndexPage()     

        expect("TeamLinkUp") {
            (indexPage.content.nodes \\ "h1").text
        }
    }
  
    "Index Page" should "Display a 'Register' link" in {
    	val indexPage = new IndexPage()
    	
    	expect("Register") {
    		(indexPage.content.nodes \\ "a").text
    	}
    }

}