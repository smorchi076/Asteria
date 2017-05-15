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
-Intro screens + menus
-Working enemy and boss spawn
-Working spaceship with abilities
-10 level campaign
-Versus game mode
-Ability to upgrade spaceship
-In game currency system


Want to Have:
-Multiple ships
-More ship upgrades
-Ship skins
-2-player team co-op game mode
-Save game in a file using serializable

Stretch features:
-Network play
-Custom map creator
-3D graphics and detailed textures
-Have greater rewards such as chests containing currency, and upgrades
-Planets with gravity mechanic

Class List:

Package: Main
Asteria - Creates a new game



Package: Gameplay Elements
GameObject - A superclass for things that go on the map
Ship: Spaceship that you control
Space Junk - the stuff littering the map that grants currency
Planet - a planet that has gravity affecting the ship, and that can be run into
Enemy - an enemy ship
Boss1 - the first boss
Boss2 - the second boss
…
Map - the map representing where everything is
Spawner - a stationary object that spawns enemies
MovingImage - An image that can move around the map


Package: File IO
FileIO - stores game to text file and saves current state using Serializable 


Package:GUI
GameFrame - Represents the overall layout of the game screens
HomePanel- Holds graphics for the intro screen
PanelListener - Recognizes actions between screens
BankPanel - Panel representing a BankAccount and button to enter shop
BuyablePanel - A single item in the shop with an image and option to purchase
InstructionPanel - Contains the instructions for the game	
GameModePanel - Contains the game modes for the game
ShipPanel - A JPanel displaying the ship in its current state(with/without upgrades) 
ShopPanel - A panel containing multiple BuyablePanels
MapPanel - Hold the graphics for the map
AbilityPanel - contains the ship’s special abilities at the top of the game screen
MissionFinishPanel (constructor takes in # representing amount of space junk collected) - displays the mission over screen (whether success or finish)


Responsibilities List:
Garrett: Gameplay elements
Sahil: Map,GUI
Gilbert: FileIO, GUI,controls
