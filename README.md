# Entity Purge
### A simple ClearLag mod that aims to be as customizable as possible

## Configuration File
### clearItem
- **Description :** Determines whether the ground objects should be deleted during ClearLag
- **Default value :** `true`

### minutesBetweenEachPurge
- **Description :** Specifies the time in minutes between each entity and object purge cycle
- **Default value :** `10`

### entitiesToClear
- **Description :** Contains the identifiers of the Minecraft entities that will be deleted during ClearLag
- **Example :** `["minecraft:pig","mymod:importantentity"]`

### warning10s
- **Description :** Enables or disables the display of a warning message 10 seconds before the ClearLag is triggered
- **Default value :** `true`

### warning10sMessage
- **Description :** Sets the warning message displayed to players 10 seconds before entities are removed
- **Default value :** `"§cCaution, the clear lag will append in 10 seconds!"`

### warninghappend
- **Description :** Enables or disables the display of a message indicating that purge has been performed
- **Default value :** `true`

### warninghappendMessage
- **Description :** Sets the message displayed to players after ClearLag has been executed
- **Default value :** `"§2Clear lag done!"`



## Dependencies
### Architectury API required
![Architectury API logo](https://cdn.modrinth.com/data/hUDBrSHN/dcc7d780f3522c50c9298c11fd0b73f27f17b225_96.webp)
### MidnightLib required
![MidnightLib logo](https://cdn.modrinth.com/data/codAaoxh/icon.png)
