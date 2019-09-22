# SkyBlock

SkyBlock is a module for [fabric-carpet](https://github.com/gnembon/fabric-carpet)

## Features
- Generator for empty worlds, keeping biomes, structure bounding bounding boxes and end portals
- Additional wandering trader trades
- Super long potions (brewed with a redstone block)
- Block light detector (mode of the daylight detector)

## Installation
- Install [Fabric](https://fabricmc.net/use)
- Download [fabric-carpet](https://github.com/gnembon/fabric-carpet/releases)
- Download [SkyBlock](https://github.com/skyrising/skyblock/releases)
- Place fabric-carpet and SkyBlock into `<minecraft-directory>/mods/`

## Usage (World Generation)
### Singleplayer
- `Create New World`
- `More World Options...`
- Choose `World Type: SkyBlock`

### Multiplayer
- Open `server.properties`
- Change `level-type=default` to `level-type=skyblock`
- Make sure to delete or move the world folder in order to create a new world