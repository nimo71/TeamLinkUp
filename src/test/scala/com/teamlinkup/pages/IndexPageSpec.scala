package com.teamlinkup.pages;

import org.scalatest.FlatSpec

class HomePageSpec extends FlatSpec {
  
  "Index Page" should "Display a heading TeamLinkUp" in {
      val indexPage = new IndexPage();
      assert(indexPage.html == <html><body><h1>TeamLinkUp</h1></body></html>)
  }
  
}