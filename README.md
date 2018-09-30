# Pixel Based Bot

## Extended from [JavaFX Template project](https://github.com/mt9304/javafxtemplate). 

- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
	- [Suggested Environment](#suggested-environment)
	- [Installation](#installation)
- [Extending Functions](#extending-functionality)
	- [Starting A Routine](#starting-a-routine)
- [Other Information](#other-information)
	- [Bot Service Info](#bot-service-info)
	- [Delay Between Actions](#delay-between-actions)
	- [Expanding On UI](#expanding-on-ui)

## Introduction

This is a bot originally created for one of my favourite rpg games in my childhood. This serves as a template for other pixel based bots for other tasks/games. 

## Prerequisites

### Suggested Environment

1. Java IDE with Maven installed
2. JavaFX installed for IDE

### Installation
1. In terminal, go to directory you want to save project in and type: 
```
git clone https://github.com/mt9304/botforfun.git
```
2. Go into project folder and type: 
```
mvn install
```
3. Build/Run the Main.java file in the javafxtemplate folder. 

## Extending Functionality

### Starting A Routine

- The main part of the bot logic is found in app > businesslogic > MainBotRoutine.java. This is what is run when the start button is clicked. 
- If you want to create a new routine, you can extend the Routine class. Routines are just threads, with the addition that they allow the bot to be paused/stopped with a global key listener (F1 for pause, F2 for resume, and F3 for stop). To change what happens when the Start butotn is pressed, go to controllers > contentarea > HomePageController.java in the `startApplication(MouseEvent)` function. 
- Add your routine in the run() method (comments will specify where to start and end). The main bot functions I use are from the [RobotService class](#bot-service-info), which extends Java's built-in Robot class. Below are the main functions that I use: 
	1. `delay(int);` for pausing between actions in milliseconds. 
	2. `mouseClick();` for pressing and releasing left mouse button. `mouseRightClick();` for clicking the right button. 
	3. `moveCursorTo(int, int);` for gliding the mouse to the designated X and Y location. 
	4. `type(String);` for typing strings of text. 

## Other Information

### Bot Service Info

1. The `moveCursorTo(int, int);` gliding function is necessary to avoid some bot detection methods. The default mouseMove option teleports the cursor to the specified location, but seems unnatural. The game I created this for seems to send information on mouse movement, so I used this as a precaution to mimic human movement: 
	- The cursor will curve at a random direction while moving toward its target. 
	- The speed of reaching the target will be random. In addition, it will tend to speed up in the middle of its journey while slowing down near the end. 
	- Once in a while, the cursor will miss its target on the first try by a little bit, and then head toward the target. 
	- The endpoint will have an offset of 5px in both X and Y (may have to implement more accurate one function if there is no margin for error in the routine). 
2. The `type(String);` function can type regular text, since the original Robot library's functions only allow single key presses. For special and unexpected characters (maybe a different language), use `typeFromClipboard(String);` to type using the clipboard. 
3. The `delay(int);` function is used in most of the functions and has a semi-random int value. Having 0 delay between actions might seem suspicious. 

### Delay Between Actions

- Use `Thread.sleep(int);` instead of `bot.delay(int);` for waiting longer than a few seconds or minutes. 
- You can use the default [Java's Robot class](https://docs.oracle.com/javase/7/docs/api/java/awt/Robot.html) to get pixel colors at an X/Y coordinate. For example: `Color color = robot.getPixelColor(20, 20);`

### Expanding On UI

- For more information on expanding on the UI, please see the original template [here](https://github.com/mt9304/javafxtemplate). 
