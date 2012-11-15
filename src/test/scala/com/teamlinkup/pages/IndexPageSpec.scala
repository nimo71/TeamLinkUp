package com.teamlinkup.pages;

import org.scalatest.FlatSpec

class HomePageSpec extends FlatSpec {
  
  "Index Page" should "Display a heading TeamLinkUp" in {
      val indexPage = new IndexPage();     

      expect("TeamLinkUp") {
        (indexPage.html \\ "h1").text
      }
  }
  
}