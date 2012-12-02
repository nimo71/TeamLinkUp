package com.teamlinkup.adapters

import unfiltered.response.Ok
import unfiltered.response.Html
import unfiltered.filter.Plan
import com.teamlinkup.pages.IndexPage

object IndexPlan extends unfiltered.filter.Plan {

	def intent = { 
		case _ => Ok ~> Html(new IndexPage().content.nodes)
	}

}