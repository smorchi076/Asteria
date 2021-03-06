# Asteria
by Garrett Cotter, Sahil Morchi, Gilbert Feng

Description:
It is the year 2550. You were exiled from the planet of Jupiter, and were forced to fend for yourself in the brutal and harsh conditions of space where evil lurks. In an interesting combination of sci-fi and adventure, this 2D space shooter game will let you compete in a campaign or against another player. You will be able to upgrade your ship, gain new weapons to assist you in the fight, exploring maps, and encountering bosses to move onto the next level.

Campaign											
-Objective: A mode involving multiple levels in increasing difficulty. In each level you will face multiple waves of enemies that you will have to defend yourself against. At the end of each level you will face a difficult boss that must be defeated in order to advance.

Versus
-Objective: A mode involving two players competing against each other in a purely skill based arena setting. Each person will receive the same level ship and weapons to make gameplay fair. Controls remain the same.

Co-op
-Objective: Both players navigate levels together and have to work as a team to defeat each level’s boss and advance to the next level. In the team co-op game mode, turning will be disabled. For co-op you will spawn facing the opposite direction.



Instructions:
Run the program and select one of the game modes. In single player campaign mode, you will use the arrow keys to navigate the ship. The up key is moving in the current direction, the left and right keys turn the ship, and the down key activates the break (which stops the ship). Use the spacebar to shoot down enemies, and 8-9-0 for the special abilities in single player. When playing multiplayer (team co-op and versus modes), player 1 will use the single player controls, and player 2 will use W-A-S-D to navigate, 1 to shoot, and 2-3-4 for special abilities.

Feature List:
Must Have:
-(DONE)Intro screens + menus
-(DONE)Working enemy and boss spawn
-(DONE)Working spaceship with abilities
-(DONE)10 level campaign
-(DONE)Ability to upgrade spaceship
-(DONE)In game currency system


Want to Have:
-(IN PROGRESS, NOT COMPLETED)Versus game mode
-(NOT DONE)Multiple ships
-(NOT DONE)More ship upgrades
-(NOT DONE)Ship skins
-(NOT DONE)2-player team co-op game mode
-(IN PROGRESS, NOT COMPLETED) Save game in a file using serializable

Stretch features:
-(NOT DONE)Network play
-(NOT DONE)Custom map creator
-(NOT DONE)3D graphics and detailed textures
-(NOT DONE)Have greater rewards such as chests containing currency, and upgrades
-(NOT DONE)Planets with gravity mechanic

Class List:

Package: Main
Asteria - Creates a new game



Package: Gameplay Elements
Ship: Spaceship that you control
Space Junk - the stuff littering the map that grants currency
Enemy - an enemy ship
Boss1 - the first boss
Boss2 - the second boss
…etc
Projectile- Ship projectile
BossProjectile- Boss projectile
Spawner - a stationary object that spawns enemies
MovingImage - An image that can move around the map


Package: File IO
FileIO - stores game to text file and saves current state using Serializable 


Package:GUI
GamePanel - Represents the overall layout of the campaign
HomePanel- Holds graphics for the intro screen
InstructionPanel - Contains the instructions for the game	
ModePanel - Contains the game modes for the game
ShopPanel - A panel containing multiple BuyablePanels
MissionCompletePanel - displays the mission over screen if you succeed
GameOverPanel- displays the mission over screen if you fail
VersusPanel- displays the versus game
VersusWinnerPanel- Displays statement based off who won in versus
WinnerPanel- displays statement if you defeat campaign



Responsibilities List:
Garrett: Gameplay elements
Sahil: GamePanel,GUI(HomePanel, ShopPanel, ModePanel, MissionFinishPanel)
Gilbert: FileIO, GUI(InstructionPanel, ShopPanel, AbilityPanel, ModePanel),controls
