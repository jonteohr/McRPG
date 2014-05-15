McRPG
=====

# Table of Contents:
* [Overview](https://github.com/condolent/McRPG/blob/master/README.md#overview)
* [Installation](https://github.com/condolent/McRPG/blob/master/README.md#installation)
* [Configuration](https://github.com/condolent/McRPG/blob/master/README.md#configuration)
*    [Configuration-codes]((https://github.com/condolent/McRPG/blob/master/README.md#configuration-codes)
# Overview
McRPG is a plugin that will make your whole server into a new role playing game!
With weapons, armor and items inspired by the enormous RPG World of Warcraft your server
will draw forth players who will stay for a long while when enjoying this game-transforming plugin.
What this plugin basically does is creating and renaming several items, with new enchantments and recipes
with inspiration from World of Warcraft.


# Installation
It's very simple! You simply click [Download ZIP file](https://github.com/condolent/McRPG/archive/master.zip) on the right side of the project and drag the //plugin// folder into your server root.

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
