# Snake
## Clone Instructions
1. Use terminal, or the zip download to get the full project
2. Drag and drop the entire thing into a new project (any IDE should be fine)
3. All sound files/images should work correctly. If they do not however, the game should still run the alternate version (no images/sound)
4. Press run, and enjoy the game!

A stable version of Java is required to run this game

## Inspiration
I created this project for my APCSA final. I have always found Snake to be a fun game, so I thought of making it myself!

## What it does
Snake is pretty much a classic Snake game. It uses a block based Snake body, and has an apple as the food. The top of the JFrame will display the score. If the user gets 100 apples, then they win!

## How I built it
I used Java for all aspects of this project. It was created using the Swing Java library, and incorporates use of Object Oriented concepts.

## Challenges I ran into
The biggest challenge I ran into was being able to make a resource efficient way to track the Snakes previous position. I ended up using a for each loop to track the position of each cube in relation to the one ahead of it. For example, if the current direction of cube 1 is left, and the direction of cube 2 is up (this means the user has turned left), then cube 2 will wait until it is 10 pixels away from cube 1, and then move into the correct position. This repeats for all cubes. It is a processor efficient solution that provides flexibilty in the code. 

## Accomplishments that I'm proud of
I am very proud of getting the movement of the Snake working well. It is quite difficult to keep track of the position, as well as make sure the apple does not spawn inside the snake, which I was able to accomplish succesfully. 

## What I learned
I learned the basics of game development in Java, which is something I find really interesting. I now also have the ability to play my favorite game!
