/*
 *
 *  This program is free software; you can redistribute it and/or modify

 * Jan Rune Holmevik, 3/31/01
 */

public class MooParser extends Thread {
	
		// modified this method so that the actual URL token is not shown to the user.
		// this method can now also determine if a url should be displayed in the main Xpress
		// window, or in a new browser window.
		// Jan Rune Holmevik, 3/31/01

  		String target = "web_frame"; // default target 
 		String last = ">."; // default url ending

		if (uncompletedURL.length() > 0) {
				if (s.indexOf("_blank>.") > -1) {
					target = "_BLANK";
					last = "_blank>.";						
				}


				showURL(urlstring, target);
					if (s.indexOf("_blank>.") > -1) {
						target = "_BLANK";
						last = "_blank>.";						
					}
