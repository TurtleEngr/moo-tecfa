/************************************************************
*************************************************************

Java source code for the Hot Mudslide mud client 
Author: David Boatwright - dboat@ccs.neu.edu 
Copyright (C) 1996 David J. Boatwright

Latest info and version can be obtained at:
http://www.drscc.com/staff/dboat/mudslide/

*************************************************************
*************************************************************

                    GNU GENERAL PUBLIC LICENSE
                       Version 2, June 1991

 Copyright (C) 1989, 1991 Free Software Foundation, Inc.
                          675 Mass Ave, Cambridge, MA 02139, USA
 Everyone is permitted to copy and distribute verbatim copies
 of this license document, but changing it is not allowed.

                            Preamble

  The licenses for most software are designed to take away your
freedom to share and change it.  By contrast, the GNU General Public
License is intended to guarantee your freedom to share and change free
software--to make sure the software is free for all its users.  This
General Public License applies to most of the Free Software
Foundation's software and to any other program whose authors commit to
using it.  (Some other Free Software Foundation software is covered by
the GNU Library General Public License instead.)  You can apply it to
your programs, too.

  When we speak of free software, we are referring to freedom, not
price.  Our General Public Licenses are designed to make sure that you
have the freedom to distribute copies of free software (and charge for
this service if you wish), that you receive source code or can get it
if you want it, that you can change the software or use pieces of it
in new free programs; and that you know you can do these things.

  To protect your rights, we need to make restrictions that forbid
anyone to deny you these rights or to ask you to surrender the rights.
These restrictions translate to certain responsibilities for you if you
distribute copies of the software, or if you modify it.

  For example, if you distribute copies of such a program, whether
gratis or for a fee, you must give the recipients all the rights that
you have.  You must make sure that they, too, receive or can get the
source code.  And you must show them these terms so they know their
rights.

  We protect your rights with two steps: (1) copyright the software, and
(2) offer you this license which gives you legal permission to copy,
distribute and/or modify the software.

  Also, for each author's protection and ours, we want to make certain
that everyone understands that there is no warranty for this free
software.  If the software is modified by someone else and passed on, we
want its recipients to know that what they have is not the original, so
that any problems introduced by others will not reflect on the original
authors' reputations.

  Finally, any free program is threatened constantly by software
patents.  We wish to avoid the danger that redistributors of a free
program will individually obtain patent licenses, in effect making the
program proprietary.  To prevent this, we have made it clear that any
patent must be licensed for everyone's free use or not licensed at all.

  The precise terms and conditions for copying, distribution and
modification follow.

                    GNU GENERAL PUBLIC LICENSE
   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION

  0. This License applies to any program or other work which contains
a notice placed by the copyright holder saying it may be distributed
under the terms of this General Public License.  The "Program", below,
refers to any such program or work, and a "work based on the Program"
means either the Program or any derivative work under copyright law:
that is to say, a work containing the Program or a portion of it,
either verbatim or with modifications and/or translated into another
language.  (Hereinafter, translation is included without limitation in
the term "modification".)  Each licensee is addressed as "you".

Activities other than copying, distribution and modification are not
covered by this License; they are outside its scope.  The act of
running the Program is not restricted, and the output from the Program
is covered only if its contents constitute a work based on the
Program (independent of having been made by running the Program).
Whether that is true depends on what the Program does.

  1. You may copy and distribute verbatim copies of the Program's
source code as you receive it, in any medium, provided that you
conspicuously and appropriately publish on each copy an appropriate
copyright notice and disclaimer of warranty; keep intact all the
notices that refer to this License and to the absence of any warranty;
and give any other recipients of the Program a copy of this License
along with the Program.

You may charge a fee for the physical act of transferring a copy, and
you may at your option offer warranty protection in exchange for a fee.

  2. You may modify your copy or copies of the Program or any portion
of it, thus forming a work based on the Program, and copy and
distribute such modifications or work under the terms of Section 1
above, provided that you also meet all of these conditions:

    a) You must cause the modified files to carry prominent notices
    stating that you changed the files and the date of any change.

    b) You must cause any work that you distribute or publish, that in
    whole or in part contains or is derived from the Program or any
    part thereof, to be licensed as a whole at no charge to all third
    parties under the terms of this License.

    c) If the modified program normally reads commands interactively
    when run, you must cause it, when started running for such
    interactive use in the most ordinary way, to print or display an
    announcement including an appropriate copyright notice and a
    notice that there is no warranty (or else, saying that you provide
    a warranty) and that users may redistribute the program under
    these conditions, and telling the user how to view a copy of this
    License.  (Exception: if the Program itself is interactive but
    does not normally print such an announcement, your work based on
    the Program is not required to print an announcement.)

These requirements apply to the modified work as a whole.  If
identifiable sections of that work are not derived from the Program,
and can be reasonably considered independent and separate works in
themselves, then this License, and its terms, do not apply to those
sections when you distribute them as separate works.  But when you
distribute the same sections as part of a whole which is a work based
on the Program, the distribution of the whole must be on the terms of
this License, whose permissions for other licensees extend to the
entire whole, and thus to each and every part regardless of who wrote it.

Thus, it is not the intent of this section to claim rights or contest
your rights to work written entirely by you; rather, the intent is to
exercise the right to control the distribution of derivative or
collective works based on the Program.

In addition, mere aggregation of another work not based on the Program
with the Program (or with a work based on the Program) on a volume of
a storage or distribution medium does not bring the other work under
the scope of this License.

  3. You may copy and distribute the Program (or a work based on it,
under Section 2) in object code or executable form under the terms of
Sections 1 and 2 above provided that you also do one of the following:

    a) Accompany it with the complete corresponding machine-readable
    source code, which must be distributed under the terms of Sections
    1 and 2 above on a medium customarily used for software interchange; or,

    b) Accompany it with a written offer, valid for at least three
    years, to give any third party, for a charge no more than your
    cost of physically performing source distribution, a complete
    machine-readable copy of the corresponding source code, to be
    distributed under the terms of Sections 1 and 2 above on a medium
    customarily used for software interchange; or,

    c) Accompany it with the information you received as to the offer
    to distribute corresponding source code.  (This alternative is
    allowed only for noncommercial distribution and only if you
    received the program in object code or executable form with such
    an offer, in accord with Subsection b above.)

The source code for a work means the preferred form of the work for
making modifications to it.  For an executable work, complete source
code means all the source code for all modules it contains, plus any
associated interface definition files, plus the scripts used to
control compilation and installation of the executable.  However, as a
special exception, the source code distributed need not include
anything that is normally distributed (in either source or binary
form) with the major components (compiler, kernel, and so on) of the
operating system on which the executable runs, unless that component
itself accompanies the executable.

If distribution of executable or object code is made by offering
access to copy from a designated place, then offering equivalent
access to copy the source code from the same place counts as
distribution of the source code, even though third parties are not
compelled to copy the source along with the object code.

  4. You may not copy, modify, sublicense, or distribute the Program
except as expressly provided under this License.  Any attempt
otherwise to copy, modify, sublicense or distribute the Program is
void, and will automatically terminate your rights under this License.
However, parties who have received copies, or rights, from you under
this License will not have their licenses terminated so long as such
parties remain in full compliance.

  5. You are not required to accept this License, since you have not
signed it.  However, nothing else grants you permission to modify or
distribute the Program or its derivative works.  These actions are
prohibited by law if you do not accept this License.  Therefore, by
modifying or distributing the Program (or any work based on the
Program), you indicate your acceptance of this License to do so, and
all its terms and conditions for copying, distributing or modifying
the Program or works based on it.

  6. Each time you redistribute the Program (or any work based on the
Program), the recipient automatically receives a license from the
original licensor to copy, distribute or modify the Program subject to
these terms and conditions.  You may not impose any further
restrictions on the recipients' exercise of the rights granted herein.
You are not responsible for enforcing compliance by third parties to
this License.

  7. If, as a consequence of a court judgment or allegation of patent
infringement or for any other reason (not limited to patent issues),
conditions are imposed on you (whether by court order, agreement or
otherwise) that contradict the conditions of this License, they do not
excuse you from the conditions of this License.  If you cannot
distribute so as to satisfy simultaneously your obligations under this
License and any other pertinent obligations, then as a consequence you
may not distribute the Program at all.  For example, if a patent
license would not permit royalty-free redistribution of the Program by
all those who receive copies directly or indirectly through you, then
the only way you could satisfy both it and this License would be to
refrain entirely from distribution of the Program.

If any portion of this section is held invalid or unenforceable under
any particular circumstance, the balance of the section is intended to
apply and the section as a whole is intended to apply in other
circumstances.

It is not the purpose of this section to induce you to infringe any
patents or other property right claims or to contest validity of any
such claims; this section has the sole purpose of protecting the
integrity of the free software distribution system, which is
implemented by public license practices.  Many people have made
generous contributions to the wide range of software distributed
through that system in reliance on consistent application of that
system; it is up to the author/donor to decide if he or she is willing
to distribute software through any other system and a licensee cannot
impose that choice.

This section is intended to make thoroughly clear what is believed to
be a consequence of the rest of this License.

  8. If the distribution and/or use of the Program is restricted in
certain countries either by patents or by copyrighted interfaces, the
original copyright holder who places the Program under this License
may add an explicit geographical distribution limitation excluding
those countries, so that distribution is permitted only in or among
countries not thus excluded.  In such case, this License incorporates
the limitation as if written in the body of this License.

  9. The Free Software Foundation may publish revised and/or new versions
of the General Public License from time to time.  Such new versions will
be similar in spirit to the present version, but may differ in detail to
address new problems or concerns.

Each version is given a distinguishing version number.  If the Program
specifies a version number of this License which applies to it and "any
later version", you have the option of following the terms and conditions
either of that version or of any later version published by the Free
Software Foundation.  If the Program does not specify a version number of
this License, you may choose any version ever published by the Free Software
Foundation.

  10. If you wish to incorporate parts of the Program into other free
programs whose distribution conditions are different, write to the author
to ask for permission.  For software which is copyrighted by the Free
Software Foundation, write to the Free Software Foundation; we sometimes
make exceptions for this.  Our decision will be guided by the two goals
of preserving the free status of all derivatives of our free software and
of promoting the sharing and reuse of software generally.

                            NO WARRANTY

  11. BECAUSE THE PROGRAM IS LICENSED FREE OF CHARGE, THERE IS NO WARRANTY
FOR THE PROGRAM, TO THE EXTENT PERMITTED BY APPLICABLE LAW.  EXCEPT WHEN
OTHERWISE STATED IN WRITING THE COPYRIGHT HOLDERS AND/OR OTHER PARTIES
PROVIDE THE PROGRAM "AS IS" WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED
OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.  THE ENTIRE RISK AS
TO THE QUALITY AND PERFORMANCE OF THE PROGRAM IS WITH YOU.  SHOULD THE
PROGRAM PROVE DEFECTIVE, YOU ASSUME THE COST OF ALL NECESSARY SERVICING,
REPAIR OR CORRECTION.

  12. IN NO EVENT UNLESS REQUIRED BY APPLICABLE LAW OR AGREED TO IN WRITING
WILL ANY COPYRIGHT HOLDER, OR ANY OTHER PARTY WHO MAY MODIFY AND/OR
REDISTRIBUTE THE PROGRAM AS PERMITTED ABOVE, BE LIABLE TO YOU FOR DAMAGES,
INCLUDING ANY GENERAL, SPECIAL, INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING
OUT OF THE USE OR INABILITY TO USE THE PROGRAM (INCLUDING BUT NOT LIMITED
TO LOSS OF DATA OR DATA BEING RENDERED INACCURATE OR LOSSES SUSTAINED BY
YOU OR THIRD PARTIES OR A FAILURE OF THE PROGRAM TO OPERATE WITH ANY OTHER
PROGRAMS), EVEN IF SUCH HOLDER OR OTHER PARTY HAS BEEN ADVISED OF THE
POSSIBILITY OF SUCH DAMAGES.

                     END OF TERMS AND CONDITIONS

        Appendix: How to Apply These Terms to Your New Programs

  If you develop a new program, and you want it to be of the greatest
possible use to the public, the best way to achieve this is to make it
free software which everyone can redistribute and change under these terms.

  To do so, attach the following notices to the program.  It is safest
to attach them to the start of each source file to most effectively
convey the exclusion of warranty; and each file should have at least
the "copyright" line and a pointer to where the full notice is found.

    <one line to give the program's name and a brief idea of what it does.>
    Copyright (C) 19yy  <name of author>

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.

Also add information on how to contact you by electronic and paper mail.

If the program is interactive, make it output a short notice like this
when it starts in an interactive mode:

    Gnomovision version 69, Copyright (C) 19yy name of author
    Gnomovision comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
    This is free software, and you are welcome to redistribute it
    under certain conditions; type `show c' for details.

The hypothetical commands `show w' and `show c' should show the appropriate
parts of the General Public License.  Of course, the commands you use may
be called something other than `show w' and `show c'; they could even be
mouse-clicks or menu items--whatever suits your program.

You should also get your employer (if you work as a programmer) or your
school, if any, to sign a "copyright disclaimer" for the program, if
necessary.  Here is a sample; alter the names:

  Yoyodyne, Inc., hereby disclaims all copyright interest in the program
  `Gnomovision' (which makes passes at compilers) written by James Hacker.

  <signature of Ty Coon>, 1 April 1989
  Ty Coon, President of Vice

This General Public License does not permit incorporating your program into
proprietary programs.  If your program is a subroutine library, you may
consider it more useful to permit linking proprietary applications with the
library.  If this is what you want to do, use the GNU Library General
Public License instead of this License.
*************************************************************

Version 1.2:	Hot Mudslide goes public	3/29/96
Version 1.3:	Fixed problem with close connection functions 
Version 1.4 	Added line wrapping and monospaced fonts
*************************************************************/

import java.awt.*;
import java.util.*;
import java.io.*;
import java.applet.Applet;
import java.net.Socket;
import java.net.InetAddress;


/** Mudslide applet class */
public class mudslide extends Applet implements Runnable{

  /* Buttons */
  static Button sendButton = null;
  static Button connectButton = null;
  static Button disconnectButton = null;
  static Button detachButton = null;
  static Button aboutButton = null;
  static Button quitButton = null;

  /* Text Boxes */
  static TextField sendBox = null; 
  static public TextConsole mudConsole;

  /* Images Variables */
  static Image logo = null;   
  static ImageCanvas logoCanvas = null;

  /* Network Related Variables*/   
  static Socket mudSocket = null;
  static PrintStream outputStream = null;
  static DataInputStream inputStream = null;
  static String mudHost = null;
  static int mudPort;	


  /* Thread that will be used to monitor the network for incoming IO */
  Thread reader = null;

  /* Misc Variables */
  static History sendHistory = null;
  static HMWindow detachedWindow = null;
  static boolean connected;
  static boolean keepRunning;

  /* Called automatically when applet is first started */
  public void init(){
    processParameters();  //Process the applets parameters
    sendHistory = new History();
    buildGUI();  // Set up the GUI
  }
  
  /* Destroy all objects and quit */
  public void destroy(){
    if( connected == true ){
      closeConnection();
    }
    removeAll();
    if( detachedWindow != null ){
      detachedWindow.dispose();
    }
    detachedWindow = null;
    reader = null;
    mudConsole = null;
    sendBox = null;
    sendHistory = null;
    sendButton = null;
    connectButton = null;
    disconnectButton = null;
    aboutButton = null;
    detachButton = null;
    quitButton = null;
  }
  
  /* Method gets parameters passed to applet. */
  public void processParameters(){
    int sendBoxCols;
    int mudConsoleRows;
    int mudConsoleCols;
    
    /* Get the required parameters */
    mudHost = getParameter( "host" );
    mudPort = ( new Integer(getParameter( "port" ))).intValue();
    mudConsoleRows = ( new Integer(getParameter( "rows" ))).intValue();
    mudConsoleCols = ( new Integer(getParameter( "cols" ))).intValue();
    sendBoxCols = ( new Integer(getParameter( "sbox" ))).intValue();
    
    mudConsole = new TextConsole( mudConsoleRows, mudConsoleCols );
    sendBox = new TextField( sendBoxCols );
  }
  
  /* Method that is the meat of the network input thread.*/
  public void run(){
    String inputLine = null;	
    keepRunning = true;
    
    /* Thread loops until stop() is called */
    while( keepRunning == true ){
      try{
	inputLine = inputStream.readLine();
        if( inputLine.compareTo( "null" ) == 0 ){
        }
        else{
	  mudConsole.writeConsole( ( inputLine + "\n" ) );
        }
      }
      catch( Exception excpt ){
	closeConnection();
	keepRunning = false;
      }
    }
  }

  /* Stops the reader thread */
  public void stop(){
    keepRunning = false;
  }

  /* Gives user information on applet. */
  public String getAppletInfo(){
    return("Hot Mudslide 1.4 by David Boatwright");
  }

  /* About prints information when user clicks on the about button */
  public void aboutInfo(){
    mudConsole.writeConsole("---------------------------------------------\n"); 
    mudConsole.writeConsole("Hot Mudslide Version 1.4\n"); 
    mudConsole.writeConsole("Written by David Boatwright\n"); 
    mudConsole.writeConsole("Information can be obtained at:\n"); 
    mudConsole.writeConsole("http://www.drscc.com/staff/dboat/mudslide\n"); 
    mudConsole.writeConsole("---------------------------------------------\n");
  }
  
  /* Sets up the GUI components on the page */
  private void buildGUI(){
    
    Panel topPanel = new Panel();
    Panel bottomPanel = new Panel();
    
    GridBagLayout gridBag = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    
    connected = false;
    
    connectButton = new Button("Connect");
    disconnectButton = new Button("Disconnect");
    detachButton = new Button("Detach");
    aboutButton = new Button("About");
    quitButton = new Button("Quit");
    sendButton = new Button("Send");
    logo = getImage( getCodeBase(), "mudslide.gif" );
    logoCanvas = new ImageCanvas( logo, 140, 30 );
    
    mudConsole.setEditable( false );    
    mudConsole.writeConsole("Hot Mudslide Version 1.4 - By David Boatwright\n");
    
    sendBox.disable();
    sendButton.disable();
    disconnectButton.disable();
    
    setBackground( new Color(255,255,255) );
    connectButton.setBackground( new Color(170,170,170) );
    disconnectButton.setBackground( new Color(170,170,170) );
    detachButton.setBackground( new Color(170,170,170) );
    aboutButton.setBackground( new Color(170,170,170) );
    quitButton.setBackground( new Color(170,170,170) );
    sendButton.setBackground( new Color(170,170,170) );
    mudConsole.setBackground( new Color(210,210,210) );
    sendBox.setBackground( new Color(210,210,210) );
    
    topPanel.add( logoCanvas );
    topPanel.add( connectButton );
    topPanel.add( disconnectButton );
    topPanel.add( detachButton );
    topPanel.add( aboutButton );
    topPanel.add( quitButton );
    bottomPanel.add( sendBox );
    bottomPanel.add( sendButton );
    
    setLayout( gridBag );
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = gbc.WEST;
    gridBag.setConstraints(topPanel, gbc);
    add( topPanel );
    
    gbc.gridy = 1;
    gridBag.setConstraints(mudConsole, gbc);
    add( mudConsole );
    
    gbc.gridy = 2;
    gridBag.setConstraints( bottomPanel, gbc);
    add( bottomPanel );
    
    repaint();
  }
  
  /* Sends a line of text to the mud server */
  public void sendLine(){
    outputStream.println( sendBox.getText() );
    outputStream.flush();  
    sendHistory.addLine( sendBox.getText() ); // Adds the line to sendHistory
    sendBox.setText("");  // Clears the input box
    repaint();
  }
  
  /* Opens a connection to the mud server */
  public void openConnection(){
    try{
      mudSocket = new Socket( mudHost, mudPort );
      inputStream = new DataInputStream( new BufferedInputStream( mudSocket.getInputStream() ) );
      outputStream = new PrintStream( new BufferedOutputStream( mudSocket.getOutputStream() ) );
      connected = true;
    }
    catch( Exception except ){
      mudConsole.writeConsole("mudslide: Failed to connect to server.\n");
      connected = false;
    }
    if( connected == true ){
      reader = new Thread( this );
      reader.start();
      connectButton.disable();
      disconnectButton.enable();
      sendBox.enable();
      sendButton.enable();
      mudConsole.writeConsole("Connection Established\n");
    }
    else{
      mudSocket = null;
      outputStream = null;
      inputStream = null;
    }
    repaint();
  }
  
  /* Closes the connection to the mud server */
  public void closeConnection(){
    try{
      outputStream.close();
      inputStream.close();
    }
    catch( Exception except ){
    }

    try{
      mudSocket.close();
    }
      catch( Exception except ){
    } 

    /* Sets old network variables to null so that the garbage collecter */ 
    /* can destroy them */
    mudSocket = null;
    outputStream = null;
    inputStream = null;
    
    /* Disables appropriate parts of the GUI */
    disconnectButton.disable();
    sendBox.disable();
    sendButton.disable();
    connectButton.enable();
    mudConsole.writeConsole("Connection Lost\n");
    connected = false;
    repaint();
  }
  
  
  /* Moves the GUI into a seprate window */
  private void detachWindow(){
    Panel topPanel = new Panel();
    Panel bottomPanel = new Panel();
    
    detachedWindow = new HMWindow("Hot Mudslide", this);
    topPanel.add( logoCanvas );
    topPanel.add( connectButton );
    topPanel.add( disconnectButton );
    topPanel.add( aboutButton );
    topPanel.add( quitButton );
    bottomPanel.add( sendBox );
    bottomPanel.add( sendButton );
    
    detachedWindow.setBackground( new Color(255,255,255) );

    GridBagLayout gridBag = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();


 
    removeAll();
    detachedWindow.setLayout( gridBag );
    
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = gbc.WEST;
    gbc.insets.top = 15;
    gbc.insets.bottom = 2;
    gbc.insets.left = 8;
    gbc.insets.right = 8;
    gridBag.setConstraints(topPanel, gbc);
    detachedWindow.add( topPanel );
    
    gbc.insets.top = 2;
    gbc.gridy = 1;

    gridBag.setConstraints(mudConsole, gbc);
    detachedWindow.add( mudConsole );
    
    gbc.gridy = 2;
    gbc.insets.bottom = 6;
    gridBag.setConstraints( bottomPanel, gbc);

    detachedWindow.add( bottomPanel );

    detachedWindow.setResizable(false);
    detachedWindow.pack();
    detachedWindow.show();       
    repaint();
  }
  
  
  /* Handles applet events */
  public boolean handleEvent( Event evt ){

    if( (evt.target == sendBox) && (evt.id == Event.KEY_ACTION) ){
      if( evt.key == Event.UP ){
	sendBox.setText( sendHistory.up( sendBox.getText()) );
      }
      else if( evt.key == Event.DOWN ){
	sendBox.setText( sendHistory.down() );
      }
    }
    return( super.handleEvent( evt ) );
  }
  
  
  /* Method handles GUI action events */
  public boolean action( Event evt, Object obj ){

    if( (evt.target == sendBox) ){
      sendLine();  // Sends whats currently in sendBox to mud server
    }
    else if( evt.target == sendButton ){
      sendLine(); // Sends whats currently in sendBox to mud server
    }
    else if( evt.target == connectButton ){
      openConnection();  // Open Connection to mud server and start reader
    }
    else if( evt.target == disconnectButton ){   
      reader.stop();  // Stops the reader thread	
      reader = null;  // Destroy the reader thread
      closeConnection(); // Disconnect from mud server
    }
    else if( evt.target == detachButton ){   
      detachWindow();
    }
    else if( evt.target == aboutButton ){
      aboutInfo();
    }
    else if( evt.target == quitButton ){
      destroy();
    }
    repaint();
    return super.action( evt, obj );  
  }
}


/** Text area class that handles displaying the output from the mud 
  server.  This class is a hack and could use a lot of improvement. */
class TextConsole extends TextArea{
  
  private int lines;

  public TextConsole( int rows, int cols ){
    super( rows, cols );
    lines = 0;
    setFont( new Font("Courier",Font.PLAIN,12));    
  }
  
  // Method assumes a newline is at the end of the string
  public synchronized void writeConsole( String str ){
    int end;
    
    if( lines >= 301 ){
      shrinkBuffer();
    }

    while( str.length() > getColumns()-3 ){
	super.appendText( str.substring(0,getColumns()-3) + "\n" );
	str = str.substring( getColumns()-3, str.length() );
	lines++;
    }

    	super.appendText( str );
    	lines++;	

    	end = ((getText()).length());
    	select( end-1, end-1 );
  }
  
  private void shrinkBuffer(){
    String tmpstr = null;
    char tmpchar;
    int i=0;
    int newLines=0;
    
    tmpstr = super.getText();
    while( newLines <= 80 ){
      if( ( tmpstr.charAt( i ) ) == '\n' ){
	newLines++;
      }
      i++;
    }
    super.replaceText( "", 0, i );
    lines = lines - 81;
  }
  
  /* This method gets around problem with X11 browsers not setting the  */
  /* scroll bar to the bottom on appends.  It is a hack, but I could    */
  /* find no direct way to control the scroll bar in code.              */
  public void paint( Graphics g ){
    int end;
    
    end = ((getText()).length());
    select( end-1, end-1 );

    super.paint( g );
  }	
}

/** Image canvas class that holds the Mudslide logo */
class ImageCanvas extends Canvas{

  private Image image;
  
  ImageCanvas( Image img, int x, int y ){
    image=img;
    resize( x, y);
  }
  public void paint( Graphics g){
    g.drawImage( image, 0 , 0, this);
  }
}


/** Class holds send box history */
class History{
  private String history[] = new String[100];
  private int current = 0;
  private int first = 0;
  private int last = 0;
  private static int size = 99;
  
  History(){
    history[0] = "";
  }
  
  public void addLine( String tmpStr ){
    if( tmpStr.compareTo( "" ) != 0 ){
      history[ first ] = tmpStr;
      first = upNumb( first );
      if( first == last ){
        last = upNumb( last );
      }
      current = first;
      history[ first ] = "";
    }
  }
  
  public String up( String tmpStr ){

    if( current == last ){
       return( history[last] );
    }
    else if( current == first ){
      history[ first ] = tmpStr;
    }
    
    current = downNumb( current );
    return( history[current] );
  }
  
  public String down( ){
    
    if( current == first ){
      return( history[first] );
    }
    
    current = upNumb( current );
    return( history[current] );
  }
  
  private int upNumb( int num ){
    if( num == size){
      return( 0 );
    }
    else{ 
      return( num + 1 );
    }
  }

  private int downNumb( int num ){
    if( num == 0){
      return( size );
    }
    else{ 
      return( num - 1 );
    }
  }
}

/** Window class that is used for the detached window */
class HMWindow extends Frame{
  
  mudslide hmsPtr;
  
  HMWindow( String tmpstr, mudslide hms ){
    hmsPtr = hms;
    setTitle( tmpstr );
  }
  
  /* Handles GUI Events */
  public boolean handleEvent( Event evt ){
    if( (evt.target == hmsPtr.sendBox) && (evt.id == Event.KEY_ACTION) ){
      if( evt.key == Event.UP ){
	hmsPtr.sendBox.setText( hmsPtr.sendHistory.up( hmsPtr.sendBox.getText()) );
      }
      else if( evt.key == Event.DOWN ){
	hmsPtr.sendBox.setText( hmsPtr.sendHistory.down() );
      }
    }
    return( super.handleEvent( evt ) );
  }
  

  /* Method handles GUI action events */
  public boolean action( Event evt, Object obj ){
    if( (evt.target == hmsPtr.sendBox) ){
      hmsPtr.sendLine();  // Sends whats currently in sendBox to mud server
    }
    else if( evt.target == hmsPtr.sendButton ){
      hmsPtr.sendLine(); // Sends whats currently in sendBox to mud server
    }
    else if( evt.target == hmsPtr.connectButton ){
      hmsPtr.openConnection();  // Open Connection to mud server and start reader
    }
    else if( evt.target == hmsPtr.disconnectButton ){   
      hmsPtr.closeConnection(); // Disconnect from mud server
      hmsPtr.reader.stop();  // Stops the reader thread	
      hmsPtr.reader = null;  // Destroy the reader thread
    }
    else if( evt.target == hmsPtr.aboutButton ){
      hmsPtr.aboutInfo();
    }
    else if( evt.target == hmsPtr.quitButton ){
      removeAll();
      hmsPtr.destroy();
    }
    repaint();
    return super.action( evt, obj );  
  }
}










