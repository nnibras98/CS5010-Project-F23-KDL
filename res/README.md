# CS5010-Project-F23-KDL
# Doctor Lucky's Mansion Game

Welcome to Doctor Lucky's Mansion, a text-based game where players try to eliminate Doctor Lucky within a certain number of turns.

## Table of Contents
- [Introduction](#introduction)
- [Assumptions](#assumptions)
- [Limitations](#limitations)
- [Citations](#citations)
- [Getting Started](#getting-started)
- [Game Rules](#game-rules)
- [Gameplay](#gameplay)
- [Sample Run Explanation](#sample-run-explanation)
- [Code Structure](#code-structure)
- [Design Changes](#design-changes)
- [How to Run](#how-to-run)
  - [Using JAR File](#using-jar-file)


## Introduction

Doctor Lucky's Mansion is a turn-based game set in a mansion where players take on the roles of human and computer-controlled characters. The objective is to eliminate Doctor Lucky within a specific number of turns.

## Assumptions

I have assumed that the controller will interact with any kind of view with out any problem.

## Limitations

The controller is not yet designed for appendable and readable only.

## Citations
Map opening mechanism was inspired from https://stackoverflow.com/questions/5824916/how-do-i-open-an-image-in-the-default-image-viewer-using-java-on-windows

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher

### Installation
1. Clone the repository: `git clone https://github.com/nnibras98/CS5010-Project-F23-KDL.git`
2. Compile the code: `javac -d bin src/*.java`

## Game Rules

Doctor Lucky's Mansion follows these game rules:
1. Players can move, pick up items, look around, and attempt to kill Doctor Lucky.
2. Computer-controlled players randomly choose actions, with a preference for killing attempts.
3. Players can only attempt to kill Doctor Lucky if they are in the same room, the pet is absent, and the last turn was not a look around.
4. Doctor Lucky and the pet move around the mansion each turn.
5. Unseen killing attempts are always successful, reducing Doctor Lucky's health and removing the item used.

## Gameplay

Players take turns, and each turn consists of human and computer-controlled player actions. The game ends when Doctor Lucky is killed or a maximum number of turns is reached.

## Sample Run Explanation

In the test run:

The game starts with the setup phase, where the world, players, and items are initialized.
The game information and rules are displayed.
The map is shown.
Two players are added: one human player (Nafi) and one computer player.
The game proceeds with ten turns.
Players take their turns, making choices such as looking around, moving to neighboring rooms, and attempting to pick up items.
The human player, Nafi, successfully makes a kill attempt on Doctor Lucky using a Civil War Cannon, reducing Doctor Lucky's health.
The game ends after ten turns, and the target character, Doctor Lucky, escapes, resulting in no winner.

This test run covers various aspects of gameplay, including player actions, item interactions, computer player behavior, and game termination conditions.

## Code Structure

The code is organized into the following key components:
- `model`: Contains classes representing the game's model, such as `Player`, `Room`, `Item`, `Pet`, and `TargetCharacter`.
- `controller`: Manages the game's logic, including player actions, turns, and setup.

## Design Changes

In this edition significant refactoring has been done to accomodate for game play. Command Design Pattern has been implemented and I have tried to follow best practises as much as possible.

## How to Run

### Using JAR File

1. Double click on the setup.bat file to open the JAR file.

Follow on-screen prompts to set up the game, create players, and start playing.



