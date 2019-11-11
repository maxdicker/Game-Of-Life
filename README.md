# Game-Of-Life
## About
This is a Java implementation of Conway's Game of Life (GOL). 

GOL more resembles a simulation than it does a game. The universe of GOL is an infinite, two-dimensional grid of square cells, each of which is in one of two possible states; alive or dead. Cells in GOL survive, die or respawn according to the (automatic) application of the Game's rules (specified below). It is therefore a 'zero-player game' - the Game's progression/evolution is determined by it's initial state, requiring no further input beyond setup.

Since its publication, GOL has attracted much interest because of the surprising ways in which patterns can evolve on the grid, when considering its simple rules. 

[GOL Kata description]

[GOL Kata description]: https://github.com/MYOB-Technology/General_Developer/blob/master/katas/kata-conways-game-of-life/kata-conways-game-of-life.md

## Game rules
Every cell interacts with its eight neighbours, being the cells that are directly horizontally, vertically, or diagonally adjacent. The grid also wraps around - for a cell on the edge of the grid, some of its neighbours would be located on the opposite edge/s.

At each step in time, the following transitions occur:

- Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.

- Any live cell with more than three live neighbours dies, as if by overcrowding.

- Any live cell with two or three live neighbours lives on to the next generation.

- Any dead cell with exactly three live neighbours becomes a live cell.

Births and deaths happen simultaneously. This occurs repeatedly until the simulation ends (which, here, is user specified).

## User input
- To begin, the user specifies 'the seed' of the system; grid width, height and positions of living cells. They must also specify the number of evolutions to simulate.

- The Game specifies the input formatting requirements. Any invalid input will be ignored and the user will be asked to re-enter it.

## Requirements
This project was developed with:

- IntelliJ IDEA Ultimate 2019.2

- JDK 11.0.4

- JUnit 4

## How to run

- Clone the repository

`git clone git@github.com:maxdicker/Game-Of-Life.git`

- Open IntelliJ and import as Gradle project

- Build, then run Main file

It is recommended to run this in the console. Some difficulties arose in visualizing the grid (console clearing/refreshing) through the IDE's built-in Terminal.

## Testing

In IntelliJ, right click on "src" folder -> "Run 'Tests in 'Game-Of-Life''"