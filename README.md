McRPG
=====

![](http://dev.bukkit.org/media/images/74/432/logo_beta.png)

# Table of Contents:
* [Overview](https://github.com/condolent/McRPG/blob/master/README.md#overview)
* [Features](https://github.com/condolent/McRPG/blob/master/README.md#features)
* [Installation](https://github.com/condolent/McRPG/blob/master/README.md#installation)
* [Configuration](https://github.com/condolent/McRPG/blob/master/README.md#configuration)
 * [Configuration codes](https://github.com/condolent/McRPG/blob/master/README.md#configuration-codes)
 * [Player logging](https://github.com/condolent/McRPG/blob/master/README.md#player-logging)
 * [Permissions](https://github.com/condolent/McRPG/blob/master/README.md#permissions)
* [Commands](https://github.com/condolent/McRPG/blob/master/README.md#commands)
 * [Classes](https://github.com/condolent/McRPG/blob/master/README.md#classes)
 * [Factions](https://github.com/condolent/McRPG/blob/master/README.md#factions)
* [To-do](https://github.com/condolent/McRPG/blob/master/README.md#to-do)
* [Links](https://github.com/condolent/McRPG/blob/master/README.md#links)

# Overview
McRPG is a plugin that will make your whole server into a new role playing game!
With weapons, armor and items inspired by the enormous RPG World of Warcraft your server
will draw forth players who will stay for a long while when enjoying this game-transforming plugin.
What this plugin basically does is creating and renaming several items, with new enchantments and recipes
with inspiration from World of Warcraft.

### Features
* **New items** - Inspired from World of Warcraft, craftable and obtainable.
* **Private whispers** - You can send player private whispers, only visible to the sender and receiver.
* **Classes** - With different classes, you can choose what your character will become and what powers you will possess
* **Factions** - With 2 different factions to choose, you can either become one of the fierce bad guys, or the team playing good guys!

_More features will be added as we implement them into the plugin_

# Installation
It's very simple! You simply click [Download ZIP file](https://github.com/condolent/McRPG/archive/master.zip) on the right side of the project and drag the _McRPG-master\plugins_ folder into your server root and merge the plugins-folder with the one in your root.

# Configuration
This is how the configuration-file looks like:
```
# Default configuration for McRPG
# Read through and change what's needed before publicing on your server for other players!
# To disable something, change the 'enable' to 'disable' vice versa.

# Make the /y <message> command only availible to players with admin permission? (mcrpg.admin)
admin_yell: 'disable'

# Send the player a welcome-message when joining?
# If enable it will send the player a message
welcome_msg: 'enable'

# If welcome_msg is 'enable', then what should the message be?
# %player% is playername.
# To use colors, type & and then the COLOR CODE. Find all at: http://minecraft.gamepedia.com/Formatting_codes#Color_codes
MOTD: '&6&oDarion whispers: Welcome back, %player%.'
```
And it is fairly simple to set it up.
It also gives a fair explanation of everything inside the config!
But basically the```admin_yell:``` is a control to set if only admins are allowed to use the command /y <message> which is a server-wide message that will be sent, or if everyone can use it. If ```admin_yell: 'disable'``` then it will make it so everyone can use it. If enabled, only admins can use it. Simple isn't it?

Moving on to ```welcome_msg:```, which asks you if you want to have MOTD (Message Of The Day) enabled. This will send a message to the player when he/she logs in to the server. If ```welcome_msg: 'disable'``` it will **not** send a welcome message when logging in, but if it's ```welcome_msg: 'enable'``` it will send.
The ``MOTD:`` is setting what it should say. Default is:    
```MOTD: '&6&oDarion whispers: Welcome back, %player%.'```    
and it's OK to keep it, but if you'd like to, you can easily change it to whatever you want!

### Configuration codes
There's a few codes added into the configuration to make it much more cooler! (Like it's possible, eh?)    
``%player%`` gets the player name.   
``&<color>`` makes the following a certain color. All color codes are found at [the Minecraft Gamepedia](http://minecraft.gamepedia.com/Formatting_codes#Color_codes)

### Player logging
The plugin logs players when they choose factions and classes. This is logged in 2 separate files. Classes are logged in the _plugin\McRPG\PlayerClasses.yml_ file and factions in the _plugins\McRPG\PlayerFactions.yml_.   

This is the _plugin\McRPG\PlayerClasses.yml_    
```
# You can edit this, simply delete users that you want to be able to choose class again!
mages: []
paladins: []
warriors: []
classed_players: []
```
It tells you the players that have chosen classes and what classes they chose. So for example, if I did /class warrior, it would change into:    
```
# You can edit this, simply delete users that you want to be able to choose class again!
mages: []
paladins: []
warriors: 
 - Condolent
classed_players: 
 - Condolent
```
It's fairly easy to remove a player that's been logged.    
**The factions have still not been implemented into a release of the plugin, it's currently being worked on and therefor I cannot show you how it looks like!**

### Permissions
| Node  | Gives |
| ------------- | ------------- |
| mcrpg.admin  | Access to admin-related commands  |

# Commands
_All commands are also listed by doing /rpg help_

* **/w <player> <message>** - Sends a whisper to selected player
* **/y <message>** - Sends out a server-wide yell
* **/supply <player> <item>** - Give the specified player a item. (Only items that McRPG implements)
* **/heal <player>** - Heals specified player, just typing /heal will automatically set you as target.
* **/class <mage/paladin/warrior>** - Selects your class.
* **/ci <player>** - Clears inventory of selected player.
* **/gm <0/1/survival/creative> <player>** - Sets players gamemode.
* **/setspawn** - Sets server-spawn.
* **/spawn** - Teleports you to spawn.
* **/faction <Horde/Alliance>** - Choose which faction you want to fight for!
* **/faction deluser <player>** - Remove a player from the faction-log, making it possible to re-choose faction.
* **/class deluser <player>** - Remove a player from the class-log, making it possible to re-choose class.

### Classes
Here's all available classes listed:
* **Warrior** - Fight as a warrior and slay your enemies with no mercy!
* **Mage** - Play as a Mage, a wise wizard with magical powers.
* **Paladin** - Follow the light's guidance and fight off your enemies with the power of the light!

### Factions
* **Horde** - Fight with brutality for the horde, for the magestive warchief Orgrim Doomhammer
* **Alliance** - Fight honorably for the allmighty king Varian Wrynn

# To-do
* Different spawn for each classes
* Powers for classes (Perhaps)

# Links
[**Forum**](http://dev.bukkit.org/bukkit-plugins/mc-rpg/forum/)    
[**Leave a suggestion!**](http://dev.bukkit.org/bukkit-plugins/mc-rpg/forum/suggestions)    
[**Crafting recipes**](http://dev.bukkit.org/bukkit-plugins/mc-rpg/pages/recipes/)    
[**BukkitDev**](http://dev.bukkit.org/bukkit-plugins/mc-rpg/)
