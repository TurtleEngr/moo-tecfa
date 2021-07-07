"This verb will print out either all objects or a selected item on the holding";
"property to the room. Tests are made to insure that the direct object string";
"represents a number AND an number not bigger than the lenght of the list";
n_items = length(this.holding);
" --- showing all the items ---- ";
if (dobjstr == "all")
  player.location:announce_all(player.name, " shows all the items on ", this.name);
  for k in [1..n_items]
    player.location:announce_all(k, ": ", this.holding[k]);
  endfor
else
  " ---- testing bad dobjstr's ----";
  num = tonum(dobjstr);
  if (num == 0)
    player:tell("The correct syntax for the show command is: 'show all on <object>' or show <number> on <object>");
  elseif (num > n_items)
    player:tell("Sorry this list has less items then ",num);
  else
    " ---- displaying a single element ----";
    player.location:announce_all(player.name, " shows item ", num, " on ", this.name, ": ", this.holding[num]);
  endif;
endif;
