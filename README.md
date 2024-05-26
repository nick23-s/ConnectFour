# ConnectFour

## Overview
Two-player connection game where the objective is to connect four of your colored discs in a row, either vertically, horizontally, or diagonally, before your opponent does. This project implements the game using JavaFX for the graphical user interface.

## Features
- **Graphical User Interface**: The game features an intuitive and interactive GUI built with JavaFX, providing a smooth and engaging user experience.
- **Two-Player Mode**: Players take turns to drop their colored discs into the grid, aiming to connect four discs in a row.
- **Win Detection**: The game automatically checks for win conditions after each move, alerting the players when someone wins.
- **Reset Functionality**: Players can reset the game at any time to start a new match.
- **Column Fullness Check**: The game prevents players from placing discs in a full column and provides a visual alert.

## Project Structure
- **FourInARowGame.java**: Contains the core game logic, including methods for placing tokens, checking for win conditions, and resetting the game.
- **FourInARowController.java**: Manages the user interface and user interactions, delegating game logic to the `FourInARowGame` class.
- **ConnectFour.fxml**: Defines the layout of the user interface using JavaFX Scene Builder.

