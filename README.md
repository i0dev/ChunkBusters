# Chunk Buster Plugin!
This plugin allows you to craft/give yourself or others a ChunkBuster, which you can place down, and it will destroy every block inside of the chunk!
This is a PLUGIN, and it goes into your plugins folder on your server. It will not work if its anywhere else!

## How it works:
You can craft a chunk buster, OR give it with the /chunkbuster give command, Then you can place it down anywhere you have access to, and it will destroy all the blocks 
In the chunk. There is a blacklist for blocks to not be broken. The plugin fully configurable including messaging!

### Commands:
*Values in **[]** are required, values in **<>** are optional*
 - ```/chunkbuster reload```                   - ``Reloads the plugin.``
 - ```/chunkbuster give [Player] <Amount>```   - ``Gives a player a ChunkBuster.``


### Permissions:
 - ```chunkbuster.*```        - ``Gives permissions to run any command.``
 - ```chunkbuster.give```     - ``Gives permissions to use /chunkbuster give.``
 - ```chunkbuster.reload```  - ``Gives permissions to use /chunkbuster reload.``
 
### Config.yml, with comments explaining everything.
```yaml
###########################################
#                                         #
#      Plugin made by i01 @i0dev.com      #
#                                         #
###########################################
#
# This plugin only has 2 simple commands.
#   - /chunkbuster give [player] <amount>
#   - /chunkbuster reload
#
# There are 4 permissions as well! don't forget to assign the proper people
# the correct permissions
#   - chunkbuster.*      > Gives all permissions inside the plugin.
#   - chunkbuster.place  > Gives permissions to place chunk busters.
#   - chunkbuster.give   > Gives permissions to give chunk busters.
#   - chunkbuster.reload > Gives permissions to reload the plugin.
#
# [!] After you make any changes to config, you need to use /chunkbuster reload, for the changes
# to apply in game!


#Here is the config for general things within the plugin.
GeneralConfig:
  #These are the materials that will NOT be cleared by the chunk buster.
  Chunk_Buster_Ignored_Materials:
    - BEDROCK
    - MOB_SPAWNER
    - BEACON
    - BARRIER
    - ENDER_PORTAL_FRAME
    - ENDER_PORTAL
    - PORTAL
    - ENDER_CHEST
    - CHEST
    - TRAPPED_CHEST
    - HOPPER
    - ENCHANTMENT_TABLE
    - DISPENSER
    - DROPPER
    - BREWING_STAND
    - FURNACE
    - BURNING_FURNACE
    - CAULDRON
    - ANVIL
  #This is the lore that will show up on the Chunk Buster Item.
  Chunk_Buster_Lore:
    - ' '
    - '&cPlace this to break &4ALL &cthe blocks in the current chunk.'
    - '&cWill not break important blocks, but still be careful!'
    - ' '
  #This is the name of the Chunk Buster item.
  Chunk_Buster_Name: '&c&lChunk Buster'
  #This is just a toggle to enable/disable the crafting of the ChunkBuster...
  #To configure the crafting recipe,
  #Scroll down and change the materials for each slot in the crafting table!
  Chunk_Buster_Craft_Enabled: true
  #This is to enable/disable the ChunkBuster item glowing in your inventory.
  Chunk_Buster_Item_Glow_Enabled: true
  #This is the material type that the ChunkBuster is.
  Chunk_Buster_Item_Material: ENDER_PORTAL_FRAME

#This is the config for all the messaging inside of the plugin!
#You can use any color codes you want, and change any of the values.
Messaging:
  #This is the message that sends when you run the command /chunkbuster reload.
  Reload_Successful: '&a&lYou successfully reloaded the plugin!'
  #This is the usage message for the reload command.
  Reload_Usage: '&3Please use &9/ChunkBuster reload &3to reload the plugin!'
  #This is the usage message for giving chunk busters.
  Chunk_Buster_Give_Usage: '&3Please use &9/ChunkBuster Give [Player] <amount>&3!'
  #This is the message that sends when you put in an unknown member when giving a chunk buster.
  Chunk_Buster_Give_Unknown_Member_Error: '&cError: &3Unknown Member. Please try again!'
  #This is the message that sends when you put in an unknown amount of chunk busters to give
  Chunk_Buster_Give_Amount_Error: '&cError: &3Unknown amount. Please enter a valid
    amount!'
  #This is the message that sends when you successfully give someone a chunk buster.
  #Notice the {player} & {amount}, Those are place holders, and you can include them, if you want to
  #They are not required. {player}, changes it to the players name, {amount} changes it to the
  #amount of chunk busters given
  Chunk_Buster_Give_Success: '&aSuccess! &3You gave &9{player} &f{amount} &3Chunk
    Busters!'
  #This is the message that sends when you receive a chunk buster from someone else.
  #The place holders work the same as mentioned above.
  Chunk_Buster_Received: '&aYou received &f{amount} &3ChunkBusters from &9{player}&3!'
  #This is the message that sends when you place a chunk buster.
  Chunk_Buster_Place_Success: '&a&lYou successfully placed a ChunkBuster! &3You destroyed
    &f{amount} &3blocks!'
  #This is the message that sends when you don't have permission to give chunk busters.
  Chunk_Buster_Give_No_Permission: '&4You do not have &cchunkbusters.give &4permission!'
  #This is the message that sends when you don't have permission to place chunk busters.
  Chunk_Buster_Place_No_Permission: '&4You do not have &cchunkbusters.place &4permission!'
  #This is the message that sends when you don't have permission to reload the plugin.
  Reload_No_Permission: '&4You do not have &cchunkbusters.reload &4permission!'


#Here is the crafting recipe for the chunk buster. You can disable this if you want in the GeneralConfig section
#The order of slots is from top left, and ends at the bottom right. You can input any material you wish.
#If you want it to be a blank spot, just put `AIR`.
#The default crafting is shown in this screenshot: https://gyazo.com/83d680867f88ac55ac7d2b11dc742614
CraftingRecipe:
  Crafting_Recipe_Slot_1: AIR
  Crafting_Recipe_Slot_2: TNT
  Crafting_Recipe_Slot_3: AIR
  Crafting_Recipe_Slot_4: ENDER_STONE
  Crafting_Recipe_Slot_5: NETHER_STAR
  Crafting_Recipe_Slot_6: ENDER_STONE
  Crafting_Recipe_Slot_7: ENDER_STONE
  Crafting_Recipe_Slot_8: ENDER_STONE
  Crafting_Recipe_Slot_9: ENDER_STONE

#DO NOT TOUCH! This will break your config if you change this
Config_Version_DO_NOT_CHANGE: 1.0.0
```

## Need help? 
Join the [Support Server](https://discord.gg/h7MRPyJ) and feel free to ask any questions to me directly too, My discord is i0#0001
