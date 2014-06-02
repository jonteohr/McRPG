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
 * [Auto-Updating](https://github.com/condolent/McRPG/blob/master/README.md#Auto-updating)
* [Commands](https://github.com/condolent/McRPG/blob/master/README.md#commands)
 * [Classes](https://github.com/condolent/McRPG/blob/master/README.md#classes)
 * [Factions](https://github.com/condolent/McRPG/blob/master/README.md#factions)
* [How to update](https://github.com/condolent/McRPG/blob/master/README.md#how-to-update)
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

# AUTO-UPDATE
# Do you want the plugin to update automatically if there's an update available everytime you reload?
# disable = No
# enable = Yes
auto_update: disable

# Send the player a welcome-message when joining?
# If enable it will send the player a message
welcome_msg: enable

# If welcome_msg is 'enable', then what should the message be?
# %player% is playername.
# To use colors, type & and then the COLOR CODE. Find all at: http://minecraft.gamepedia.com/Formatting_codes#Color_codes
MOTD: '&6&oDarion whispers: Welcome to the server, %player%.'


##################################
# 			  CHAT               #
##################################

# This is for enabling or disabling the colors in chat, for instance if someone types &2Hello it will show the message in green.
# Disabling this will NOT disable the colors for other messages, such as MOTD or other plugin messages. Only player-sent messages.
chat_colors: enable

# Make the /y <message> command only availible to players with admin permission? (mcrpg.admin)
admin_yell: disable
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
The plugin logs players when they choose factions and classes. This is logged in 2 separate files. Classes are logged in the _plugins\McRPG\PlayerClasses.yml_ file and factions in the _plugins\McRPG\PlayerFactions.yml_.   

This is the _plugins\McRPG\PlayerClasses.yml_    
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
This is the _plugins\McRPG\PlayerFactions.yml_
```
# You can edit this, simply delete users that you want to be able to choose factions again!
# Players are no longer logged with their names, because of the new name change feature Mojang added to Minecraft.
# Therefor players' UUID are logged instead, because of that it's not change-able.

Horde: []
Alliance: []
Registered_Players: []
```

### Permissions
| Node  | Gives |
| ------------- | ------------- |
| mcrpg.admin  | Access to admin-related commands  |

### Auto-Updating
Auto updating is when the plugin is automatically dowmloading a new update if there is one on BukkitDev. This is disabled by default, but can easily be changed in the config-file.   
If it remains disabled, it will still send a message to server operators when logging in that there's a new update available and send a link. It also says that in the console on start-up.   
If enabled, it will download when starting up the server. **Remember to reload the server after the automatic-update is finished!**

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
* **/vanish** - Toggles invisibility for you.

### Classes
Here's all available classes listed:
* **Warrior** - Fight as a warrior and slay your enemies with no mercy!
* **Mage** - Play as a Mage, a wise wizard with magical powers.
* **Paladin** - Follow the light's guidance and fight off your enemies with the power of the light!

### Factions
* **Horde** - Fight with brutality for the horde, for the magestive warchief Orgrim Doomhammer
* **Alliance** - Fight honorably for the allmighty king Varian Wrynn

# How to update
This is the best way to update, follow these steps to update the plugin:
 1. Download the latest .jar file
 2. Copy everything inside your config.yml
 3. Place the new .jar file inside your //<server-folder\plugins\// folder and replace the old one
 4. Delete the current config.yml file
 5. Run the server (the plugin will now generate a new config-file that's up to date)
 6. Paste the copied (old) config.yml into a new notepad file
 7. Change the settings that's in the old to the new config file.

**Q: Why is it so complicated?**    
_A: Well since the plugin is still in development, we add a bunch of new things to the config-file almost after every update. The config file doesn't automatically update when the plugin does, therefor these steps are required to maintain the plugin in full capabilitiy_

# To-do
* Different spawn for each classes
* Powers for classes (Perhaps)

# Links
[**Forum**](http://dev.bukkit.org/bukkit-plugins/mc-rpg/forum/)    
[**Leave a suggestion!**](http://dev.bukkit.org/bukkit-plugins/mc-rpg/forum/suggestions)    
[**Crafting recipes**](http://dev.bukkit.org/bukkit-plugins/mc-rpg/pages/recipes/)    
[**BukkitDev**](http://dev.bukkit.org/bukkit-plugins/mc-rpg/)
