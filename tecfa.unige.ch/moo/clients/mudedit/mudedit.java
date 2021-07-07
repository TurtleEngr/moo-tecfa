import java.awt.*;
import java.net.*;
import java.io.*;

/**
 * mudedit is the application class for the MUDEDIT edtor/browser
 * @bug AWT does not properly redraw menus when they are enabled or
 *      disabled. AWT is so good at optimizing redraw that there
 *      proves to be no workaround that isn't even uglier.
 *      mudedit begins with only the Views menu enabled.  After
 *      login the Mud Objects menu is enabled.  After a current
 *      object is set, the Parents and Children menus are enabled.
 *      On logout all but Views are disabled again.  Until AWT is
 *      fixed this wont be obvious.
 * @bug Modal dialogs are broken in AWT.  This forces an uglier
 *      model of dialog handling then would be otherwise possible.
 *
 * @version 	1.0, 3/21/96
 * @author 	Jeffrey P. Kesselman
 * @Copyright 1996 Jeffrey P. Kesselman
 */
public class mudedit extends Frame {
    boolean inAnApplet = true;
    Menu ParentsMenu, ChildrenMenu, ObjectsMenu, ViewsMenu;
    List ParamList,MethodList;
    TextArea DataText;
    Button LoginButton,NewMethodButton,NewParameterButton,
           DelParameterButton,DelMethodButton,CheckoutButton,
           InitObjectButton,ExecuteButton;
    Label  CurrObject,StatusMessage;
    Socket mysocket;
    DataInputStream istream;
    PrintStream ostream;
    loginDialog mylogin;
    boolean LoggedIn;
    Bag DialogBag = new Bag();
    String parentArray[],objectArray[],childrenArray[];
    int parentCount,objectCount;
    Bag lineBuffer = new Bag();;
    boolean LOCALTEST = false; // flag for debugging
    String MUDMACHINE="204.153.193.49";
    int MUDPORT=1131;
    outputDialog errOutput = new outputDialog(this,"Errors:");
    outputDialog fromMud = new outputDialog(this,"From Mud:");

    /**
     * Constructs a new mudedit window that appears immediately
     * on screen.  The MUD ip adress and port are currently compiled
     * in.  In the future it would be better to pass those in here
     */

    public mudedit(){
        objectArray = getObjects();
        parentArray = getParents();
        Panel bottomPanel = new Panel();
        Panel centerPanel = new Panel();
        Panel topPanel = new Panel();
        Panel methodPanel = new Panel();
        Panel paramPanel = new Panel();
        Panel dataPanel = new Panel();

        MenuBar mb = new MenuBar();
        ParentsMenu = new Menu("Parents");
        ParentsMenu.add(new MenuItem("Add Parents"));
        ParentsMenu.add(new MenuItem("Remove Parents"));
        ParentsMenu.addSeparator();
        mb.add(ParentsMenu);
        ChildrenMenu = new Menu("Children");
        mb.add(ChildrenMenu);
        ObjectsMenu = new Menu("MUD Objects");
        ObjectsMenu.add(new MenuItem("Add Object"));
        ObjectsMenu.add(new MenuItem("Remove Object"));
        ObjectsMenu.addSeparator();
        mb.add(ObjectsMenu);
        ViewsMenu = new Menu("Views");
        ViewsMenu.add(new MenuItem("MUD I/O"));
        ViewsMenu.add(new MenuItem("Errors"));
        mb.add(ViewsMenu);
        setMenuBar(mb);

        //Add small things for center.
        LoginButton = new Button("Login");
        CheckoutButton = new Button("Check out object");
        InitObjectButton = new Button("Initialize Object");
        ExecuteButton = new Button("Execute ColdC");
        Panel p = new Panel();
        p.setLayout(new GridLayout(3,2));
        p.add(LoginButton);
        p.add(CheckoutButton);
        p.add(InitObjectButton);
        p.add(ExecuteButton);
        bottomPanel.setLayout(new BorderLayout());
        CurrObject = new Label("Current Object: <none>",Label.CENTER);
        bottomPanel.add("North",CurrObject);
        bottomPanel.add("South",p);

        //Add big things to the center area.
        MethodList = new List(10,false);
        ParamList  = new List(10,false);

        DataText = new TextArea();
        DataText.setEditable(false);
        methodPanel.setLayout(new BorderLayout());
        paramPanel.setLayout(new BorderLayout());
        dataPanel.setLayout(new BorderLayout());

        NewMethodButton = new Button("New");
        DelMethodButton = new Button("Delete");
        p= new Panel();
        p.setLayout(new BorderLayout());
        p.add("West",NewMethodButton);
        p.add("East",DelMethodButton);
        methodPanel.add("South",p);
        methodPanel.add("North",new Label("Methods",Label.CENTER));
        methodPanel.add("Center",MethodList);
        paramPanel.add("North",new Label("Parameters",Label.CENTER));
        paramPanel.add("Center",ParamList);
        dataPanel.add("North",new Label("Object State",Label.CENTER));
        dataPanel.add("Center",DataText);
        NewParameterButton = new Button("New");
        DelParameterButton = new Button("Delete");
        setLayout(new BorderLayout());
        p = new Panel();
        p.setLayout(new BorderLayout());
        p.add("West",NewParameterButton);
        p.add("East",DelParameterButton);
        paramPanel.add("South",p);;
        add("East" ,paramPanel);
        add("West",methodPanel);
        p = new Panel();
        p.setLayout(new BorderLayout());
        StatusMessage = new Label("Status: Disconnected",Label.CENTER);
        p.add("North",bottomPanel);
        p.add("Center",dataPanel);
        add("South",StatusMessage);
        add("Center",p);

        // not browsing yet, all of these are disabled
        CheckoutButton.disable();
        NewParameterButton.disable();
        DelParameterButton.disable();
        NewMethodButton.disable();
        DelMethodButton.disable();
        InitObjectButton.disable();
        ExecuteButton.disable();
        ParentsMenu.disable();
        ChildrenMenu.disable();
        ObjectsMenu.disable();

        LoggedIn = false;
        pack();
        show();

    }

    /**
     * preferredSize is redefined in order to take control and display
     * the window in a wider mode then AWT woud chose by default.
     */

    public Dimension preferredSize()
    {
       Dimension d;
       d= super.preferredSize();
       d.width = 400;
       return(d);
    }

    /**
     * setStatus is called by various other routines in order
     * to give updates to the user.
     *
     * (ISSUE: Does this really have to be public?)
     * @param s The status string to display
     *
     */


    public void setStatus(String s)
    {
       StatusMessage.setText("Status: "+s);
    }

    /**
     * setConnections is called by the login dialog in order to pass
     * the opened socket, input stream, and outut stream back to
     * the app.
     *
     * (ISSUE: This is messy as it requires the login dialog to know
     *  about the app object.  This is necessitated by the modal
     *  dialog flag being broken.  If modal dialog were fixed, this
     *  could be done by querying the dialog instead.)
     *
     * @param s The socket mudedit will be using to communicate with
     *          the mud.
     * @param i The stream used to read input from the mud (a DataInputStream
     *          opened on s).
     * @param o The stream used to write output to the mud (a PrintStream
     *          opened on s).
     *
     */

    public void setConnections(Socket s,DataInputStream i, PrintStream o)
    {
        mysocket = s;
        istream = i;
        ostream = o;
        try {
           while (istream.available()>0)
             istream.readLine();
        } catch (IOException e) {

        }
    }

    /**
     * setLoggedin is used by the login dialog to register a successful
     * login.
     *
     * (ISSUE: same as setConnection above.)
     */

    public void setLoggedin()
    {
       if (!LoggedIn) {
         LoginButton.setLabel("Logout");
         LoggedIn = true;

         setStatus("getting mud object list");
         objectArray = getObjects();
         buildObjectsMenu(objectArray);
         setStatus("logged in");
         ObjectsMenu.enable();
       }
       ExecuteButton.enable();
    }

    /**
     * setLoggedout causes mudedit to cease communication with
     * the mud and set the interface back to the logged out state.
     *
     * (ISSUES: same as above, also I don't believe connection is
     *  dropped on mudedit's end until a new login or until mudedit
     *  actually quits running.  This is not
     *  a big issue as muds generally drop the connection fine on
     *  their own, and in any case its dropped once the user leaves
     *  the application.
     */

    public void setLoggedout()
    {
       if (doCommand("@logout")){
         closeAllDialogs();
         LoginButton.setLabel("Login");
         LoggedIn = false;
         buildParentsMenu(new String[0]);
         buildObjectsMenu(new String[0]);
         MethodList.delItems(0,MethodList.countItems()-1);
         ParamList.delItems(0,ParamList.countItems()-1);
         DataText.setText("");
         CurrObject.setText("Current Object: <none>");
         setStatus("logged out");
         CheckoutButton.disable();
         NewParameterButton.disable();
         DelParameterButton.disable();
         NewMethodButton.disable();
         DelMethodButton.disable();
         ExecuteButton.disable();
         InitObjectButton.disable();
         ParentsMenu.disable();
         ChildrenMenu.disable();
         ObjectsMenu.disable();
       }
    }

    /**
     * closeAllDialogs closes all the dialog boxes currently on
     * screen that were created by mudedit.  This is used whenever
     * the current object changes as dialogs are generally
     * object-specific.
     */

    public void closeAllDialogs()
    {
        Object dlist[] = DialogBag.contents();
        for(int i=0;i<dlist.length;i++)
          ((Dialog)dlist[i]).dispose();
        DialogBag.clear();
    }

    /**
     * doCommand is the method that actually sends commands
     * to the mud and collects the output.
     * Mud output is collected as an ordered list of strings
     * in a Bag object. Lines that begin with Error: are not
     * put in the bag but instead piped straight to the error
     * output dialog, which is then forced visible.  The entire
     * transaction may be viewed in the MUD I/O dialog accesible
     * from the Views menu.
     * @see Bag
     *
     * @param cmd The string to send as a command to the Mud.
     */

    public synchronized boolean doCommand(String cmd)
    {
       setStatus(cmd);
       int linecount=0;
       String tempBuffer[];
       String inputline;
       boolean status = true;

       if (LOCALTEST) {
       // stubbed out
         lineBuffer.clear(); // empty line buffer
         status = true;
       } else {
         ostream.println(cmd);
         fromMud.addLine("SENT: "+cmd);
         inputline ="";
         while (!inputline.equals("DONE")) {
           try {
              inputline = istream.readLine();
           } catch (IOException e){
              inputline = "Error: read error in mudedit!";
           }
           fromMud.addLine(inputline);

           if (inputline.startsWith("Error:")) {
               status = false;
               if (!errOutput.isShowing())
                  errOutput.show();
               errOutput.addLine(inputline);
            } else {
               lineBuffer.add(inputline);
            }
         }
      }
      if (status) {
         setStatus("Command succeeded");
      } else {
         setStatus("Command failed");
      }
      if (!LOCALTEST) {
        try {
          while (istream.available()>0) {
              inputline = istream.readLine();
              fromMud.addLine("FLUSHED: "+inputline);
          }
        } catch (IOException e){
              inputline = "Error: read error in mudedit!";
        }
      }
      return status;
    }

    /**
     * setObject sets the current object.  All windows are
     * updated and all previosuly open menus are closed
     * provided the "@set object" command on the mud-side
     * succeeds.
     * @param objname The name of the new object as a string
     *                (no leading $, a leading # for a numerical
     *                 dbref -- see the ColdX manual for more
     *                 details on identifying objects.)
     * @see The ColdX Project -- http://www.cold.org
     */

    public void setObject(String objname)
    {
       if (doCommand("@set object "+objname)) {
          closeAllDialogs();
          MethodList.delItems(0,MethodList.countItems()-1);
          ParamList.delItems(0,ParamList.countItems()-1);
          DataText.setText("");
          CurrObject.setText("Current Object: "+objname);
          parentArray = getParents();
          buildParentsMenu(parentArray);
          childrenArray = getChildren();
          buildChildrenMenu(childrenArray);
          String temp[] = getMethods();
          for (int i=0;i<temp.length;i++) {
             MethodList.addItem(temp[i]);
          }
          temp = getParameters();
          for (int i=0;i<temp.length;i++) {
             ParamList.addItem(temp[i]);
          }
          temp = getState();
          for (int i=0;i<temp.length;i++) {
             DataText.appendText(temp[i]+"\n");
          }
          ParentsMenu.enable();
          ChildrenMenu.enable();
          NewMethodButton.enable();
          DelMethodButton.enable();
          NewParameterButton.enable();
          DelParameterButton.enable();
          InitObjectButton.enable();
       }
    }

    /**
     * getParents queries the mud and returns a list of the names
     * of the current object's parents. It returns a 0 length list
     * if the command fails on the mud side. doCommand() will
     * display any error sent back by the mud.
     */

    public String[] getParents()
    {
       if (LOCALTEST) {
            lineBuffer.clear();
            lineBuffer.add("foo");
            lineBuffer.add("bar");
            lineBuffer.add("blech");
       } else if (LoggedIn) {
          doCommand("@list parents");
       }
       return((String [])(lineBuffer.contents(
                    new String[lineBuffer.count()])));

    }

    /**
     * getChildren queries the mud and returns a list of the
     * current object's children.  It returns a 0 length list
     * if the command fails on the mud side. doCommand() will
     * display any error sent back by the mud.
     */

    public String[] getChildren()
    {
       if (LOCALTEST) {
            lineBuffer.clear();
            lineBuffer.add("bleep");
            lineBuffer.add("bloop");
            lineBuffer.add("boing");
       } else if (LoggedIn) {
          doCommand("@list children");
       }
       return((String [])(lineBuffer.contents(
                                new String[lineBuffer.count()])));
    }

    /**
     * getMethods queries the mud and returns a list of the
     * methods defined on the current object.  It returns a 0 length list
     * if the command fails on the mud side. doCommand() will
     * display any error sent back by the mud.
     */

    public String[] getMethods()
    {
       if (LOCALTEST) {
            lineBuffer.clear();
            lineBuffer.add("print");
            lineBuffer.add("list");
            lineBuffer.add("dosomething");
            lineBuffer.add("dosomethingelse");
            lineBuffer.add("banghead");
       } else if (LoggedIn) {
            doCommand("@list methods");
       }
       return((String [])(lineBuffer.contents(
                                   new String[lineBuffer.count()])));
    }

    /**
     * getParameters queries the mud and returns a list of the
     * parameters defined on the current object.  It returns a 0 length list
     * if the command fails on the mud side. doCommand() will
     * display any error sent back by the mud.
     */

    public String[] getParameters()
    {
       if (LOCALTEST) {
            lineBuffer.clear();
            lineBuffer.add("width");
            lineBuffer.add("height");
            lineBuffer.add("depth");
            lineBuffer.add("color");
            lineBuffer.add("charm");
       } else if (LoggedIn) {
            doCommand("@list parameters");
       }
       return((String [])(lineBuffer.contents(
                                   new String[lineBuffer.count()])));
    }


    /**
     * getObjects queries the mud and returns a list of all the
     * objects currently defined on the mud.  It returns a 0 length list
     * if the command fails on the mud side. doCommand() will
     * display any error sent back by the mud.
     */

    public String[] getObjects()
    {
       if (LOCALTEST) {
            lineBuffer.clear();
            lineBuffer.add("foo");
            lineBuffer.add("bar");
            lineBuffer.add("bleep");
            lineBuffer.add("blech");
            lineBuffer.add("boing");
       } else if (LoggedIn) {
            doCommand("@list objects");
       }
       return((String [])(lineBuffer.contents(
                                   new String[lineBuffer.count()])));
    }

    /**
     * getState queries the mud and returns a list of all the
     * variables instanced on the current object. This includes
     * parameters defined on the current object and on all of
     * its parent objects. This command returns a 0 length list
     * if it fails on the mud side. doCommand() will
     * display any error sent back by the mud.
     */

    public String[] getState()
    {
       if (LOCALTEST) {
            lineBuffer.clear();
            lineBuffer.add("avar   0");
            lineBuffer.add("another \"foo\"");
            lineBuffer.add("onemore  [1,3,2]");
            lineBuffer.add("asym 'woof");
            lineBuffer.add("anum  3");
       } else if (LoggedIn) {
            doCommand("@list data");
       }
       return((String [])(lineBuffer.contents(
                                  new String[lineBuffer.count()])));
    }

    /**
     * buildParentsMenu recreates the parents menu from a string
     * list of parents.
     *
     * @param parents a list of the names of all the parents to
     *                show on the menu.
     * @see #getParents
     */

    public void buildParentsMenu(String parents[])
    {
       while(ParentsMenu.countItems()>3) {
          ParentsMenu.remove(3);
       }

       for(int i=0;i<parents.length;i++){
          ParentsMenu.add(parents[i]);
       }

    }

    /**
     * buildChildrenMenu recreates the children menu from a string
     * list of children.
     *
     * @param children a list of the names of all the children to
     *                show on the menu.
     * @see #getChildren
     */

   public void buildChildrenMenu(String children[])
   {
      int count = ChildrenMenu.countItems();
      for (int i=0; i< count;i++) {
         ChildrenMenu.remove(0);
      }

      for(int i=0;i<children.length;i++){
         ChildrenMenu.add(children[i]);
      }
   }


    /**
     * buildObjectsMenu recreates the objects menu from a string
     * list of parents.
     *
     * @param objects a list of the names of all the parents to
     *                show on the menu.
     * @see #getObjects
     */


    public void buildObjectsMenu(String objects[])
    {
       while(ObjectsMenu.countItems()>3) {
          ObjectsMenu.remove(3);
       }

       for(int i=0;i<objects.length;i++){
          ObjectsMenu.add(objects[i]);
       }

    }

    /**
     * setParents is used to reset the parents of the current object.
     * If the mud command succeeds, It causes the parents menu and
     * object state display to be refreshed.
     *
     * @param parents a list of String objects denoting the names
     *                of the new parents for the current object.
     *
     */

    public void setParents(String parents[])
    {
       String cmd;

       cmd = "@set parents ";
       for (int i=0;i<parents.length-1;i++) {
          cmd = cmd + parents[i]+ " ";
       }
       if (parents.length>0) {
          cmd = cmd + parents[parents.length-1];
       }
       if (doCommand(cmd)){
          parentArray = parents;
          buildParentsMenu(parentArray);
       } else {
          parentArray= getParents();
          buildParentsMenu(parentArray);
       }
       setObject(CurrObject.getText().substring(16)); // refresh with new info
    }

    /*
     * addParents adds the passed list of parents to the current object's
     * parent list.  It accomplishes the change of parents through a
     * call to setParents above.
     *
     * @param newps an array of Strings containing the names of the
     *              objects to add to the current object's parent
     *              list.
     */
    public void addParents(String newps[])
    {
       String temp[] = new String[parentArray.length+newps.length];
       System.arraycopy(parentArray,0,temp,0,parentArray.length);
       System.arraycopy(newps,0,temp,parentArray.length,newps.length);
       setParents(temp);
    }

    /*
     * removeParents removes the passed list of parents from the current object's
     * parent list.  It accomplishes the change of parents through a
     * call to setParents above.
     *
     * @param delindexes an array of ints containing the indexs of the
     *              objects to remove from the current object's parent
     *              list.
     */

    public void removeParents(int delindexes[])
    {
       int delindexcount = 0;
       int indexcount = 0;

       String temp[] = new String[parentArray.length - delindexes.length];
       for (int i=0;i<parentArray.length;i++) {
          if ((delindexcount<delindexes.length)&&
              (i == delindexes[delindexcount])) {
             delindexcount++;
          } else {
             temp[indexcount] = parentArray[i];
             indexcount++;
          }
       }
       setParents(temp);
    }

    /**
     * addObject creates a new object with the passed name.  New objects
     * have no parameters or methods and have root as their only
     * parent.
     *
     * @param newobj A String containing the name of the new object.
     *                The name must be unique within this MUD.
     */

    public void addObject(String newobj)
    {
       if (doCommand("@create object "+newobj)) {
          String temp[] = new String[objectArray.length+1];
          System.arraycopy(objectArray,0,temp,0,objectArray.length);
          temp[temp.length-1] = newobj;
          objectArray = temp;
          buildObjectsMenu(objectArray);
       } else {
          objectArray = getObjects();
          buildObjectsMenu(objectArray);
       }
    }

    /**
     * removeObjects deletes the objects passed in from the MUD.
     * This should be used carefully as all methods and parameters
     * defined on the objects are permenantly lost.  Any child of
     * a deleted object gets re-parented to the deleted object's
     * first parent.
     *
     * @param delindexes A list of the indexes in the object list
     *                    of the objects to delete.
     */

    public void removeObjects(int delindexes[])
    {
       int delindexcount = 0;
       int indexcount = 0;

       String temp[] = new String[objectArray.length - delindexes.length];
       for (int i=0;i<objectArray.length;i++) {
          if ((delindexcount<delindexes.length)&&
              (i == delindexes[delindexcount])) {
             // NOTE: Sloppy, should handle failure.  Look at later
             doCommand("@delete object "+objectArray[i]);
             delindexcount++;
          } else {
             temp[indexcount] = objectArray[i];
             indexcount++;
          }
       }
       setParents(temp);
    }


    /**
     * addMethod creates a new method with the passed name on the current
     * object.  The method created has an empty body (no code).  Method
     * code can be added by using the method editor.
     *
     * @param newmeth The name to give the new method. It must be
     *                 unique on the current object.
     */

    public void addMethod(String newmeth)
    {
      if (doCommand("@compile method "+newmeth+"\n.")) {
         MethodList.addItem(newmeth);
      }
    }

    /**
     * addParameter creates a new parameter with the passed name on the
     * current object.  This parameter has a value of 0.
     *
     * @param newparam The name to give the new parameter, it must be
     *                  unique on the current object.
     */

    public void addParameter(String newparam)
    {
      if (doCommand("@new parameter "+newparam)) {
         ParamList.addItem(newparam);
      }
    }


    /**
     * delMethods deletes the methods passed to it from the current
     * object's method list.  This methods should be used with care
     * as the method's code is permenantly lost.
     *
     * @param indexes A list of the indexes in the current
     *                 object's method list of the methods to remove.
     */

    public void delMethods(int indexes[])
    {
       int delcount = 0;
       for(int i=0;i<indexes.length;i++){
          if (doCommand("@delete method "+
              MethodList.getItem(indexes[i]-delcount))) {
            MethodList.delItem(indexes[i]-delcount);
            delcount++;
          }
       }
    }

    /**
     * delParameters removes the parameters passed to it from the current
     * object's parameter list.
     *
     * @param indexes A list of the indexes in the current object's
     *                paramter list of the parameters to remove.
     */

    public void delParameters(int indexes[])
    {
       int delcount = 0;
       for(int i=0;i<indexes.length;i++){
          if (doCommand("@delete parameter "+
              ParamList.getItem(indexes[i]-delcount))) {
            ParamList.delItem(indexes[i]-delcount);
            delcount++;
          }
       }
    }


    /**
     * doCompile sends the passed in code to the MUD to be compiled
     * as a method with the passed in name on the current object.
     * If successful, it forces the interface to refresh the object
     * method list.
     *
     * @param methodname This string contains the method name to
     *                   give the compiled code.  If it already exists,
     *                   the new code will replace the old method.
     * @param code A String containing a series of lines of ColdC code,
     *             each line terminated by a newline.
     */

    public boolean doCompile(String methodname, String code)
    {
       return doCommand("@compile method "+methodname+"\n"+code+"\n.");
    }

    /**
     * doExecute runs an arbitray peice of ColdC on the MUD.
     * @param code A string consisting of a set of lines of ColdC
     *             code seperated by newlines.
     */
    public boolean doExecute(String code)
    {
       return doCommand("@execute\n"+code+"\n.");
    }


    /**
     * handleEvent is the standard AWT method for handling GUI events.
     * This handleEvent, on the main object, handle everything except
     * events on dialogs (which handle all but the close event locally,
     * often generating calls to the above action methods.)
     */

    public boolean handleEvent(Event evt)
    {

        if (evt.id == Event.ACTION_EVENT)
        {
           if (evt.target instanceof MenuItem)
           {
             String label = (String)evt.arg;
             if (label.equals("Add Parents")) {
               String candidateArray[] =
                  new String[objectArray.length - parentArray.length];
               int q=0;
               for (int i=0; i < objectArray.length; i++)
               {
                 boolean found = false;
                 for (int j=0;(j < parentArray.length)&&!found;j++)
                 {
                    if (objectArray[i].equals(parentArray[j]))
                      found = true;
                 }
                 if (!found)
                   candidateArray[q++] = objectArray[i];
               }
               DialogBag.add(new addParentsDialog(this,candidateArray));
               return true;
             } else if (label.equals("Remove Parents")) {
               DialogBag.add(new removeParentsDialog(this,parentArray));
               return true;
             } else if (label.equals("Add Object")) {
               DialogBag.add(new addObjectDialog(this));
             } else if (label.equals("Remove Object")) {
               DialogBag.add(new removeObjectsDialog(this,objectArray));
             } else if (label.equals("MUD I/O")) {
                fromMud.show();
             } else if (label.equals("Errors")) {
                errOutput.show();
             } else if (((MenuItem)evt.target).getParent() == ObjectsMenu) {
               // new edit object selected from object menu
               setObject(label);
               return true;
             } else if (((MenuItem)evt.target).getParent() == ParentsMenu) {
               // new edit object selected from parent menu
               setObject(label);
               return true;
             } else if (((MenuItem)evt.target).getParent() == ChildrenMenu) {
               setObject(label);
               return true;
             }
           } else if (evt.target == LoginButton) {
             if (!LoggedIn)
                DialogBag.add(new loginDialog(this,MUDMACHINE,MUDPORT,
                                             LOCALTEST));
             else
                setLoggedout();
             return true;
           } else if (evt.target == NewMethodButton) {
             DialogBag.add(new newMethodDialog(this));
           } else if (evt.target == DelMethodButton) {
             delMethods(MethodList.getSelectedIndexes());
           } else if (evt.target == NewParameterButton) {
             DialogBag.add(new newParameterDialog(this));
           } else if (evt.target == DelParameterButton) {
             delParameters(ParamList.getSelectedIndexes());
           } else if (evt.target == InitObjectButton) {
             doCommand("@initialize");
             setObject(CurrObject.getText().substring(16)); // refresh with new info
           } else if (evt.target == ExecuteButton) {
             DialogBag.add(new executeDialog(this));
           } else if (evt.target == MethodList) {
             if (doCommand("@list method "+(String)evt.arg)) {
                DialogBag.add(
                   new editMethodDialog(this,(String)evt.arg,
                                        (String [])(lineBuffer.contents(
                                            new String[lineBuffer.count()]))));
                return true;
             }
           }

        }
        else if (evt.id == Event.WINDOW_DESTROY)
        {
           if ((evt.target == fromMud)||(evt.target == errOutput)) {
             // really only hide these windows for later show
             ((Window)evt.target).hide();
           } else if ((evt.target != this)||(inAnApplet)) {
             if (evt.target instanceof Dialog)
               DialogBag.remove(evt.target);
             ((Window)evt.target).dispose();
             return true;
           } else {
             System.exit(0);
           }
        }
        return super.handleEvent(evt);
    }

    /**
     * main is the routine invoked to start the app.
     *
     */
    public static void main(String args[]) {
        mudedit window = new mudedit();
        window.inAnApplet = false;

        //window.setTitle(window.getClass().getName() + " Application");
        window.setTitle("The Diogenes Mud Editor");
        window.pack();
        window.show();
    }
}

/**
 * The newMethodDialog class is a dialog that prompts for the names
 * of methods and adds then to the current object by calling back to mudedit.
 */
class newMethodDialog extends Dialog
{
     TextField namef;
     Button okbutton,cancelbutton;
     mudedit myparent;

     public newMethodDialog(Frame parent)
     {
          super(parent,"Add Method:",true);
          myparent = (mudedit)parent;
          Panel p= new Panel();
          setLayout(new BorderLayout());
          p.setLayout(new BorderLayout());
          p.add("West",new Label("Name:"));
          namef = new TextField(20);
          p.add("East",namef);
          add("North",p);
          p= new Panel();
          p.add("Center",new Button("Add Method"));
          add("South",p);
          pack();
          show();
          namef.requestFocus();
     }

    public boolean handleEvent(Event evt)
    {
        if (evt.id == Event.ACTION_EVENT)
        {
           String label = (String)evt.arg;
           if (label.equals("Add Method")) {
              myparent.addMethod(namef.getText());
              myparent.postEvent(
                 new Event(this,Event.WINDOW_DESTROY,this));
              return true;
           }
        }
        return super.handleEvent(evt);
    }
}

/**
 * The newParameterDialog class is a dialog that prompts for the names
 * of parameters and adds then to the current object by calling back to mudedit.
 */
class newParameterDialog extends Dialog
{
     TextField namef;
     Button okbutton,cancelbutton;
     mudedit myparent;

     public newParameterDialog(Frame parent)
     {
          super(parent,"Add Parameter:",true);
          myparent = (mudedit)parent;
          Panel p= new Panel();
          setLayout(new BorderLayout());
          p.setLayout(new BorderLayout());
          p.add("West",new Label("Name:"));
          namef = new TextField(20);
          p.add("East",namef);
          add("North",p);
          p= new Panel();
          p.add("Center",new Button("Add Parameter"));
          add("South",p);
          pack();
          show();
          namef.requestFocus();
     }

    public boolean handleEvent(Event evt)
    {
        if (evt.id == Event.ACTION_EVENT)
        {
           String label = (String)evt.arg;
           if (label.equals("Add Parameter")) {
              myparent.addParameter(namef.getText());
              myparent.postEvent(
                 new Event(this,Event.WINDOW_DESTROY,this));
              return true;
           }
        }
        return super.handleEvent(evt);
    }
}

/**
 * The newParentsDialog class is a dialog that shows a list of parents
 * and adds all those selected to the current object
 */
class addParentsDialog extends Dialog
{
     List objl;
     mudedit myparent;

     public addParentsDialog(Frame parent,String objarray[])
     {
          super(parent,"Add Parents:",true);
          int objcount = objarray.length;
          myparent = (mudedit)parent;
          objl = new List(10,true);
          for(int i=0;i<objcount;i++) {
             objl.addItem(objarray[i]);
          }
          add("North",objl);
          add("South",new Button("Add to Parents"));
          pack();
          show();
     }

     public boolean handleEvent(Event evt)
     {
        if (evt.id == Event.ACTION_EVENT)
        {
           String label = (String)evt.arg;
           if (label.equals("Add to Parents"))
           {
              String temp[] = objl.getSelectedItems();
              myparent.addParents(temp);
              myparent.postEvent(
                new Event(this,Event.WINDOW_DESTROY,this));
              return true;
           }
        }
        return super.handleEvent(evt);
     }

}

/**
 * The removeParentsDialog class is a dialog that shows a list of the current
 * objects parents and removes all those selected.
 */

class removeParentsDialog extends Dialog
{
     List parentl;
     mudedit myparent;

     public removeParentsDialog(Frame parent,String parentarray[])
     {
          super(parent,"Remove Parents:",true);
          int parentcount = parentarray.length;
          myparent = (mudedit)parent;
          parentl = new List(10,true);
          for(int i=0;i<parentcount;i++) {
             parentl.addItem(parentarray[i]);
          }
          add("North",parentl);
          add("South",new Button("Remove Parents"));
          pack();
          show();
     }

     public boolean handleEvent(Event evt)
     {
        if (evt.id == Event.ACTION_EVENT)
        {
             int temp[] = parentl.getSelectedIndexes();
             myparent.removeParents(temp);
             myparent.postEvent(
                new Event(this,Event.WINDOW_DESTROY,this));
             return true;
        }
        return super.handleEvent(evt);
     }

}

/**
 * This class creates a dialog for creating new objects.
 */

class addObjectDialog extends Dialog
{
     mudedit myparent;
     TextField onamef;

     public addObjectDialog(Frame parent)
     {
          super(parent,"Add Object:",true);
          myparent = (mudedit)parent;
          onamef = new TextField(20);
          add("North",onamef);
          add("South",new Button("Create Object"));
          pack();
          show();
          onamef.requestFocus();
     }

     public boolean handleEvent(Event evt)
     {
        if (evt.id == Event.ACTION_EVENT)
        {
           String label = (String)evt.arg;
           if (label.equals("Create Object"))
           {
              myparent.addObject(onamef.getText());
              myparent.postEvent(
                new Event(this,Event.WINDOW_DESTROY,this));
              return true;
           }
        }
        return super.handleEvent(evt);
     }
}

/**
 * This class creats a dialog that shows a list of the currently defined
 * objects and deletes all selected ones.
 */

class removeObjectsDialog extends Dialog
{
     List objectl;
     mudedit myparent;

     public removeObjectsDialog(Frame parent,String objectarray[])

     {
          super(parent,"Remove Objects:",true);
          int objectcount = objectarray.length;
          myparent = (mudedit)parent;
          objectl = new List(10,true);
          for(int i=0;i<objectcount;i++) {
             objectl.addItem(objectarray[i]);
          }
          add("North",objectl);
          add("South",new Button("Remove Objects"));
          pack();
          show();
     }

     public boolean handleEvent(Event evt)
     {
        if (evt.id == Event.ACTION_EVENT)
        {
           String label = (String)evt.arg;
           if (label.equals("Remove Objects"))
           {
              int temp[] = objectl.getSelectedIndexes();
              myparent.removeObjects(temp);
              myparent.postEvent(
                new Event(this,Event.WINDOW_DESTROY,this));
              return true;
           }
        }
        return super.handleEvent(evt);
     }

}

/**
 * This class prompts for a login name and password and, when the login
 * button is pressed, attempts to open a socket connection to the MUD
 * edit port and log in.
 *
 */

class loginDialog extends Dialog
{
     TextField namef,passf;
     Socket mysocket;
     Button loginbutton;
     Thread mythread;
     boolean loggedin;
     String myipaddr;
     int myport;
     mudedit myparent;
     boolean LOCALTEST;


     /**
      * @param machine The IP address of the MUD.
      * @param port The port number of the edit port.
      * @param localtest If this is true, then the Dialog skips the actual
      *                  attempts to login and just acts like it was
      *                  successful (for testing without an internet
      *                  connection.)
      */
     public loginDialog(Frame parent,String machine, int port, boolean localtest)
     {
          super(parent,"Login to "+machine+":"+port,true);
          LOCALTEST = localtest;
          myparent = (mudedit) parent;
          Panel p= new Panel();
          setLayout(new BorderLayout());
          p.setLayout(new BorderLayout());
          p.add("West",new Label("User:"));
          namef = new MyTextField(20);
          p.add("East",namef);
          Panel loginPanel = new Panel();
          loginPanel.setLayout(new BorderLayout());
          loginPanel.add("North",p);
          p= new Panel();
          p.setLayout(new BorderLayout());
          p.add("West",new Label("Password:"));
          passf = new MyTextField(20);
          p.add("East",passf);
          loginPanel.add("South",p);
          add("North",loginPanel);
          loginbutton = new Button("Login");
          add("South",loginbutton);
          pack();
          loggedin = false;
          myipaddr = machine;
          myport = port;
          show();
          namef.requestFocus();
     }

     public boolean handleEvent(Event evt)
     {
          if (evt.id == Event.ACTION_EVENT)
          {
              if (evt.target == namef)
              {
                 passf.requestFocus();
              }
              else if (evt.target == passf)
              {
                 loginbutton.requestFocus();
              }
              else if (evt.target == loginbutton)
              {
                 myparent.setStatus("attempting login");
                 if (LOCALTEST)
                 {
                    myparent.setStatus("logged in");
                    myparent.setLoggedin();
                    myparent.postEvent(
                       new Event(this,Event.WINDOW_DESTROY,this));
                       return true;
                 } else {
                    try {
                       mysocket = new Socket(myipaddr,myport);
                       DataInputStream istream =
                          new DataInputStream(mysocket.getInputStream());
                       PrintStream ostream =
                          new PrintStream(mysocket.getOutputStream());
                       myparent.setConnections(mysocket,istream,ostream);
                       if (myparent.doCommand("login "+namef.getText()+" "+
                                      passf.getText())) {
                             myparent.setStatus("logged in");
                             myparent.setLoggedin();
                             myparent.postEvent(
                                new Event(this,Event.WINDOW_DESTROY,this));
                             return true;
                       }
                    } catch (UnknownHostException e1) {
                        myparent.setStatus("Unkown host "+myipaddr);
                    } catch (IOException e) {
                       myparent.setStatus("IO Error: "+e.getMessage());
                    }
                 return true;
                 }
              }
          }
          return super.handleEvent(evt);
    }
}

/**
 * This class brings up the passed in text in a TexArea for edit.  When
 * the Compile button is pressed, a call back to the doCompile method
 * is made to attempt to compile the code onto the method name passed
 * in on the current object.
 */

class editMethodDialog extends Dialog
{
     TextArea editpad;
     String currmethod;
     mudedit myparent;

     /**
      * @param parent The mudedit object that created us.
      * @param methodname The name of the method to edit.
      * @param lines A list of strings.  Each string is 1 line of
      *              the coldc code currently defining the method.
      */
     public editMethodDialog(Frame parent,String methodname,
            String lines[])

     {
          super(parent,"Edit Method: "+methodname,true);
          currmethod = methodname;
          myparent = (mudedit)parent;
          editpad = new TextArea();
          for(int i=0;i<lines.length;i++)
             editpad.appendText(lines[i]+"\n");
          add("North",editpad);
          Panel p = new Panel();
          p.setLayout(new BorderLayout());
          add("South",new Button("Compile"));
          pack();
          show();
     }

     public boolean handleEvent(Event evt)
     {
        if (evt.id == Event.ACTION_EVENT)
        {
           String label = (String)evt.arg;
           if (label.equals("Compile"))
           {
              myparent.doCompile(currmethod,editpad.getText());
              return true;
           }
        }
        return super.handleEvent(evt);
     }
}


/**
 * This class is a Dialog with a non-editable TextArea for showing
 * output.  It is used for both the Error and MUD I/O windows.
 */
class outputDialog extends Dialog
{
   TextArea out;
   public outputDialog(Frame p, String title)
   {
      super(p,title,false);
      out = new TextArea();
      out.setEditable(false);
      add("North",out);
      pack();
   }
   public void addLine(String str)
   {
      out.appendText(str+"\n");
   }
}

/**
 * This dialog is similar to the editMethodDialog but instead of
 * Compiling the text entered ontyo a method, it asks the MUD to
 * invoke it immediately.
 */

class executeDialog extends Dialog
{
   mudedit myparent;
   TextArea in;
   public executeDialog(Frame p)
   {
      super(p,"ColdC to Execute",false);
      myparent = (mudedit)p;
      in = new TextArea();
      setLayout(new BorderLayout());
      add("North",in);
      add("South",new Button("Execute"));
      pack();
      show();
   }

   public boolean handleEvent(Event evt)
   {
      if (evt.id == Event.ACTION_EVENT)
      {
          String label = (String)evt.arg;
          if (label.equals("Execute"))
          {
              myparent.doExecute(in.getText());
              return true;
          }
      }
      return super.handleEvent(evt);
   }
}

/**
 * This class is a generic container containing an ordered list
 * of objects.  The order is defined by the order in which the add()
 * method is called for the various elements.
 * This is a utility class that should eventually be moved to
 * a utility library package.
 */

class Bag
{
    Object objarray[] = new Object[0];
    int objcount = 0;

    public Bag()
    {
        super();
    }

    /**
     * This method is used to add an object at the end of the
     * internal list of objects.
     * @param obj The object to add.
     */

    public void add(Object obj)
    {
        if (objcount == objarray.length){ //extend bag
          Object temp[] = new Object[objarray.length+10];
          System.arraycopy(objarray,0,temp,0,objarray.length);
          objarray = temp;
        }
        objarray[objcount++] = obj;
    }

    /**
     * This method removes an object from the internal list
     * @param obj The object to be removed.
     */

    public boolean remove(Object obj)
    {
        for(int i=0; i<objarray.length;i++)
          if (objarray[i] == obj)
          {
            System.arraycopy(objarray,i+1,objarray,i,
                             objarray.length - (i+1));
            objcount--;
            return true;
          }
        return false;
    }

    /**
     * This method returns the list of objects currently in the Bg.
     */
    public Object[] contents()
    {
        Object temp[] = new Object[objcount];
        System.arraycopy(objarray,0,temp,0,objcount);
        return(temp);
    }

    /**
     * This method copies the internal list into the list passed in.
     * This allows you to take the internal list, which is of generic
     * Objects and extract it as a list of another type (which presumably
     * matches what you actually stored there.)
     * The array gets a list of objects equal to either the size of the
     * array, or the number of objects in the Bag, whichever is smaller.
     *
     * @param newarray The array to store the object list into.
     */
    public Object[] contents(Object newarray[])
    {
         System.arraycopy(objarray,0,newarray,0,
                          Math.min(objcount,newarray.length));
         return(newarray);
    }

    /**
     * This method empties the bag.
     */

    public void clear()
    {
        objcount =0;
        objarray = new Object[0];
    }

    /**
     * This method  returns the number of items currently stored in the
     * Bag.
     */

    public int count()
    {
        return(objcount);
    }
}

/**
 * This class provides for some default behavior of TextFields that
 * I prefer-- mainly that hitting return in the text field advances the focus.
 */



