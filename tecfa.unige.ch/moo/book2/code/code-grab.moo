@program XXXXX:grab
"The grab verb allows you to ``grab'' persons + objects in the same room";
"as well as persons in other rooms";
"All it really does is just displaying a random message to the other person";
"";
" ---- permission: only the owner can execute this verb";
if (caller != this)
  player:tell("Sorry, you don't have permission to use this verb");
  return;
endif
" ---- list of messages and random generation of one --- ";
messages = {".... maybe you really should start worrying a bit !", "You feel that this might be the start of something really dangerous", "You sense his virtual eyes on you!", ".... maybe you should disconnect", "Did you say good bye to your friends ?, you might just want to do that before it's too late!"};
message = messages[random(length(messages))];
" --- test if the object or person is in the same room";
if (dobj == #-3)
  addressee = $string_utils:match_player(dobjstr);
  " ------ if not, is it a player ?";
  if ($command_utils:player_match_failed(addressee, dobjstr))
    player:tell("This player does not exist nor is there any object of that name in this room");
  else
    " ------ it is a player and we can try to page it";
    if (addressee:is_listening())
      player:tell("You grab ", addressee.name, "[at ", addressee.location.name, "] ", "over distance and ", addressee.ps, " sees: \"", message, "\".");
      addressee:receive_page(("You feel " + player.name) + " grabbing you over distance!", message);
    else
      player:tell($string_utils:pronoun_sub("No need to grab, %s is not connected right now", addressee));
    endif
  endif
else
  "------ the thing is in the same room, grumbling face to face";
  addressee = dobj;
  if (is_player(dobj))
    player:tell("You grab ", dobjstr, " and ", addressee.ps, " sees: \"", message, "\".");
  else
    player:tell("The ", addressee.name, " is not overly impressed by your grabbing.");
  endif
  addressee:tell(player.name, " grabs you, ", dobjstr, "!", message);
endif
.
