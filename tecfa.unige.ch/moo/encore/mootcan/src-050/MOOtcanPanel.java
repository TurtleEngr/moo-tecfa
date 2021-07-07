/*
 *  The MOOtcan MOO-client
 *  Copyright (C) 1999-2001 Sindre S¿rensen and Jan Rune Holmevik
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */


import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * Set up the screen layout and user interface of the MOOtcan applet.
 * A border layout is used to place the output area at the top of the
 * screen with the input area and mode control panel side by side directly
 * beneath.
 *
 * @author Sindre S¿rensen and Jan Rune Holmevik
 */
 
class MOOtcanPanel extends Panel {

  UserOutputArea outputArea;
  CommandTextField inputArea;
  ControlPanel controlPanel;
  Panel commandPanel;
	
  MOOtcanPanel(Font f) {
        setLayout(new BorderLayout());
        
        inputArea = new CommandTextField(f, 100, controlPanel);
        outputArea = new UserOutputArea(f, inputArea);
		controlPanel = new ControlPanel(inputArea);
		
		inputArea.controlPanel = controlPanel;
        
		commandPanel = new Panel();
		commandPanel.setLayout(new BorderLayout());
		commandPanel.add("Center", inputArea);
		commandPanel.add("East", controlPanel);
		add("South", commandPanel);
	    add("Center", outputArea);
	    
  }


  public InputStream getInputStream() {
    return inputArea.getInputStream();
  }


  public OutputStream getOutputStream() {
    return outputArea.getOutputStream();
  }


}
