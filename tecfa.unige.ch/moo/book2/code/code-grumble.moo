@args #1324:"grumble" any to any
@program #1324:grumble
"The grumble verb allows you to grumble at persons + objects in the same room";
"as well as to persons in other rooms. Players in the same room will see";
"your message, players in the distant room will just notice a grumbling";
"The only thing that sucks is that this is a any to any verb";
"Try 'grumble this is a test to xxx' to see what I mean";
"";
" --- test if the object or person is in the same room";
if (iobj == #-3)
  addressee = $string_utils:match_player(iobjstr);
  " ------ if not, is it a player ?";
  if ($command_utils:player_match_failed(addressee, iobjstr))
    player:tell("This player does not exist nor is there any object of that name in this room");
  else
    " ------ it is a player and we can try to page it";
    if (addressee:is_listening())
      player:tell("You grumble-page to ", addressee.name, " [", addressee.location.name, "]: \"", dobjstr, "\"");
      addressee:receive_page(("You hear " + player.name) + " grumbling at you over distance: ", ("    \"" + dobjstr) + "!\"");
      addressee.location:announce_all_but({addressee}, "You vaguely hear that ", player.name, " grumbles something at ", addressee.name);
    else
      player:tell($string_utils:pronoun_sub("No need to grumble, %s is not connected right now", addressee));
    endif
  endif
else
  "------ the thing is in the same room, grumbling face to face";
  addressee = iobj;
  mess = ((((" \"" + dobjstr) + "\" ") + prepstr) + " ") + addressee.name;
  if (is_player(addressee))
    player:tell("You grumble", mess);
  else
    player:tell("The ", addressee.name, " is not overly impressed by your grumbling.");
  endif
  player.location:announce_all_but({addressee, player}, player.name, " grumbles", mess);
  addressee:tell(player.name, " grumbles at you, ", "\"", dobjstr, "!\"");
  addressee:tell(" ... maybe you should really start worrying...");
endif
.
