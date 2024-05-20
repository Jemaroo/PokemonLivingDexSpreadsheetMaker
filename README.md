# PokemonLivingDexSpreadsheetMaker
A tool to modify the amount of various slots an animation file has

UPDATED v1.1 (3/6/24): Fixed a bug where the animation data location wouldn't read correctly.

NOTICE (11/20/23): An expansion is being worked on for the tool, including an interface and editable fields. Release TBD.

UPDATED v1.0 (10/20/23): Full Release Version

Huge thanks to PistonMiner for documentation and tools on TTYD's animation formats https://github.com/PistonMiner/ttyd-tools

========LATEST USAGE=========

TBD - In Progress

=========v1.1 USAGE==========

Requirements: An updated version of Java with Developer Features
https://www.oracle.com/database/sqldeveloper/technologies/download/ 
and a text editor such as Notepad++

1: Download the v1.1

2: Should Contain: Run.bat and TTYDAnimationSlotTool.jar.

3: Create an "input" and an "output" folder in the root of the folder.

4: Paste ONLY the character animation file in the input folder.

5: Edit Run.bat in a text editor and change the following fields:
  - INPUT FILE DESTINATION (ie input\\a_mario)
  - ANIMATION SLOT COUNT (ie 5)
  - OUTPUT FILE DESTINATION (ie output)

It should look something like: input\\a_mario 20 output

Note that these are case-sensitive.

6: Run Run.bat

7: The new modified file should be in the output folder.
