=============================
HIGH WIRED ENCORE
EDUCATIONAL MOO CORE DATABASE
ADMINISTRATORS GUIDE
=============================

COPYRIGHT AND LICENSE NOTICE 
---------------------------- 
The core of this program, as released in the Feb02-97 version of LambdaCore, is Copyright of LambdaMOO (C) 1991-97. The enCore layer is Copyright (C) 1997-1999 of its respective authors. Built-in support for the MCP/2.1 protocol developed for JHCore is copyright (C) 1998 of Ken Fox. The MCP/2.1 implementation is also available separately, and under a separate licence at: http://www.awns.com/mcp/

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. (http://lingua.utdallas.edu/hw/gnu-public-license.html)

This software is in no way affiliated with the University of Michigan Press, the University of Bergen, the University of Texas at Dallas, or any other institutions or organizations mentioned in this document or on the web sites associated with this program. Any questions regarding the High Wired enCore should be directed to:

Jan Rune Holmevik                                              Cynthia Haynes
Dept. of Humanistic Informatics                 School of Arts and Humanities
University of Bergen                            University of Texas at Dallas
Sydnesplass 7 HF-bygget                                    PO.Box 830688-JO31
N-5007 Bergen, Norway                               Richardson, TX 75083-0688
Email: jan.holmevik@hedb.uib.no                  Email: cynthiah@utdallas.edu


ACKNOWLEDGEMENTS 
---------------- 
The High Wired enCore is based on LambdaCore ver. 02 Feb. 1997. We are grateful to the LambdaMOO community and wiz team for sharing this core with us and the rest of the Internet community. We also want to extend our thanks to Ken Schweller, Mark Blanchard, Jorge Barrios, Amy Bruckman, Matthew Campbell, John Towell, Gustavo Glusman, Craig Leikis, Juli Burk, Michael Thompson, Nils McCarthy, Rui Miguel Barbosa Pinto, Andrew Wilson, Ken Fox, Sindre Sørensen and others who have generously contributed code to the High Wired enCore. In addition we want to thank those enCore users who have sent us bug reports and feedback so we can continue to improve this software. Lastly we want to thank the University of Texas at Dallas for providing us with technical resources in the development of enCore, and the University of Bergen, Norway for institutional and financial support.


INTRODUCTION 
------------ 
The High Wired Educational MOO Core Database, enCore, is a constructivist virtual reality environment designed for educational use. It is based on the Feb 02 1997 version of the original LambdaCore Database and supplemented with a number of useful educational tools and enhancements, such as support for the enCore Xpress and MacMOOSE interfaces. The enCore was developed in order to give educators a MOO environment that they can immediately put to productive use in online teaching and learning. The most recent version of the enCore database is available at: http://lingua.utdallas.edu/hw/encore.html

The enCore is complemented by our book _High Wired: On the Design Use, and Theory of Educational MOOs_ available from University of Michigan Press. In this book you will find more in-depth information about the use and administration of MOOs as well as specific information on use of MOOs in education. For more information see http://www.press.umich.edu/titles/09665.html.

As a new MOO administrator we encourage you to subscribe to the enCore mailing list for announcements and discussion of the enCore software. To subscribe to this list send an email to majordomo@utdallas.edu with the following line in the body of your message: subscribe encore <your email address>


GETTING STARTED 
--------------- 
Before you can start using your MOO you need to set/edit a few preferences. After you connect to your MOO for the first time you should type:

@configure-core

This command will take you through the initialization of the most important preference so that you can start using your new MOO productively right away. 

In order for enCore Xpress to work properly it is *very* important that you specify the correct URL pointing to the directories holding the MOOtcan applet and the Xpress image and sound files. 

HOW TO MAKE NEW CHARACTERS 
-------------------------- 

- Encore Xpress' new MOO administration module makes character creation simple. Just log on with Xpress and click on the menu button namned Wizard. If you prefer to create characters the old fashioned way, simply follow the steps outlined below.

- '@make-player name email address real name' This command will create a new character. If you have enabled outbound networking (e.g. compiled the server with outbound networking on and set $network.active to 1) the password will be emailed automatically to the new user's email address. If not, you must write down the password and mail it to the new user via email. Example: 

@make-player Jan jan@someplace.no "Jan Rune Holmevik"

- '@make-guest name' Will make a new guest character with the name you specify with '_Guest' appended. Example: 

@make-guest First - create a guest with the name First_Guest. 

Note: If you have enabeled the enCore guest self naming system (see below) you still need to give the guest characters default names using the procedure outlined above.

- '@programmer name' Will make the character a programmer. Example: 

@programmer jan


MAKING WIZARDS 
-------------- 
Being a wizard carries not only absolute powers in the MOO world, but also a great responsibility. As the owner of the MOO you must therefore make sure that the people you choose for your wizard team can be trusted 100% and that they know the amount of work that is involved. Ideally, you should only choose people that you know well and that you know to be mature and responsible. Don´t ever choose a person to become a wizard in your MOO just because of his or her technical knowledge or skills. Once you have wizzed someone you have basically given that person access to every bit of information in the MOO including wizard commands that might be used to destroy it. So before you make the decision to make someone a wizard, you should trust and know that person completely.

To make a new wizard follow these steps. You should never wiz an already existing character. Always start with a new player character.

- @make-player name email-address real-name a new player character with object number, say #100, is created

- @programmer #100

- @chparent #100 to $wiz

- @set #100.wizard to 1

- @set #100.public_identity to [the player's nonwiz character's object#]


GENERIC EDUCATIONAL TOOLS 
------------------------- 
You can find all of enCore's educational objects in the Box of Educational Tools which you will find in the enCore Starting Point. Most of these educational objects have help texts, so just type 'help obj-name' to learn more about how to use them. (You must hold the objects for the command to work) 

Example:

help Generic Moderated Room, or help #98

Some tools have special helptexts or manuals. Type '@examine object-name' to find out more about this.

Important! The Generic Objects should never be used themselves. Instead, use them as templates for new objects. For example, if you want to make a new tv, type 

@create $tv named New TV

For quick and easy access to the tools included in the enCore database, you may also simply type @create.

NEWS SYSTEM 
----------- 
The High Wired enCore comes with a different news system than the one found in the standard LambdaCore. It consists of two components, the Newspaper itself ($news), and a news item ($news_item). To post something to the newspaper follow these three steps:

- '@create $news_item named whatever' - this creates a new article. 
- '@edit whatever' - Type the text of your article, save and quit. 
- '@move whatever to $news' - Moves the article to the newspaper.


QUOTA 
----- 
By default, enCore runs with byte-based-quota. This quota system is different from the default object-based quota system in the LambdaCore. Instead of measuring quota in objects, this system measures how many bytes each person actually uses, and thus, byte-based-quota is much more accurate than object-based-quota. New builders start with default quota of 50,000 bytes. You must start the byte-based quota measurement with the following command:

;$quota_utils:schedule_measurement_task()

Important! Until you start the measurement task there is no limit to what people can create, so be sure to start it as soon as possible after you MOO is up and running.

CHARACTER REQUEST SYSTEM 
------------------------ 
A new character @request system has been implemented. If you disable automatic character creation, (recommended) all guests are sent to a special room, $character_request_room, where they must fill in a form which is then sent to an in-MOO mail list called Character Request List. You subscribe to this list with the command '@subscribe *cr'

GUEST SELF NAMING SYSTEM
------------------------
In enCore MOOs guests may be allowed to use a name of their own choosing instead of the pre-defined guest names. If you wish to give your guests this ability you must enable it with the @configure command. By default, the suffix [Guest] is added to all guest names in self naming mode. The @configure command will let you change this suffix to whatever you want, or, remove altogether.


THE ENCORE XPRESS SYSTEM 
------------------------ 
The High Wired enCore comes with a new built-in, WWW-interface system called Xpress that can provide a rich multi-media content for your MOO. Through the Xpress interface people can browse your MOO as a non-interactive hypertext, or use the integrated Xpress MOO client to communicate with others online. enCore Xpress comes with a variety of graphical point-and-click applications that will make mailing, editing and programming really easy and hassle free. The web interface allows you to connect webpages, images, sound files, Real Audio and video streams, Shockwave animations, java applets and more to objects in your MOO.   

The enCore Xpress web interface is listening for incoming connections on port 7000, so the URL for your MOO's homepage is http://somemachine.somesite.edu:7000. The URL of any object in the MOO is determined by the object's number. For example, the URL to the enCore Starting Point is: http://somemachine.somesite.edu:7000/62.


MACMOOSE CLIENT SUPPORT 
----------------------- 
For Macintosh users, Amy Bruckman's MacMOOSE client program will make it easier to use the MOO and its many advanced editing features (A PC version of the program is in the works). The enCore comes fully equipped with the latest MacMOOSE system. All you have to do is download the client program itself. You can find it at http://www.cc.gatech.edu/fac/ Amy.Bruckman/MacMOOSE/. With Andrew Wilson's tkMOO-light client you can also use some of the advanced editing features provided by MacMOOSE on UNIX, Windows, or Macintosh computers. You can download the tkMOO-light client from http://www.cm.cf.ac.uk/User/Andrew.Wilson/tkmoo/

MCP/2.1 SUPPORT
---------------
From version 1.1, the High Wired enCore database includes support for the
MCP/2.1 MOO Client Protocol. This is an out of band protocol used for sending messages between servers and clients. Several useful applications are available that are built on MCP/2.1. Programmers can use MCP/2.1 to create new applications for MOO. For more information about MCP/2.1 see http://www.moo.mud.org/mcp2/


Good Luck With Your New Educational MOO, 
Jan Rune Holmevik and Cynthia Haynes
