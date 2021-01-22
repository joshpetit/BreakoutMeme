# Game Plan

### NAME

#### Examples

## Interesting Breakout Variants

[Vortex](https://www.youtube.com/watch?v=YLHIybXCRbA) is definitely really cool.
It has the early 2000s PS2 game aestetic.

[Centipong](https://www.youtube.com/watch?v=q9270dDfYWI) is really cool also.
The way that the balls are created one a successful hit is really clever. That
and the atari aestetic lol.

[Devilish](https://www.youtube.com/watch?v=nrSrNGpQ-ho) implements two seperate
boards. It would be pretty cool if there was a two player aspect in a breakout game.

## Paddle Ideas

*   Have two paddles at a distance capable of being moved separately. Possibly giving
    different configurable keys to make it two player.
*   Paddles can be configured with different colors. Cuz that's cool.
*   Enable vetical motion as well as horizontal
*   When the two paddles are stacked and a ball is hit do something special
    (maybe enable it to break a block that requires multiple hits).
*   Each paddle has a specific health (in cases where special blocks fall on top of it)

## Block Ideas

I literally didn't look at your suggestions and half of mine are on there 🙁

*   Whenever a (special?) block breaks it generates a certain number of extra balls
    that can be used. Still, if a special demarkated ball
    hits the bottom, the player will lose a life.
*   Certain blocks fall and if they hit the paddle a life is lost.
*   Blocks that explode and take out other blocks with them (with no extra points tho).
*   Certain blocks require multiple hits to be broken.
*   Extra Special blocks that do cool things only appear for a time.
*   When there's only 1 block left it dodges and is impossible to hit,
    just to frustrate the user
*   Certain blocks must be hit on certain sides

## Power-up Ideas

### Anti-Powerups

*   **Freeze**: Whenever is touched the player (or last touched pad) freezes.
*   Paddle dissappears for a time. Bonus points if things are hit though!
*   **Anti-Physics**, the paddle hits balls in the opposite direction as expected.
*	**Too fast**, the paddle goes really fast lol

### Unique 💅

*   Spawn a helper that can hit the balls for you.
*   Randomly flip the screen so that the player is on the opposite side
*   For a certain amount of time the player's second paddle goes to the
    opposite side and the blocks remain the middle. If the speciall ball
    hits either side then the player loses a life.
*   Change the song playing when certain blocks are hit

### Conventional

*   A block that multiplies the score of all other blocks by N.
*   The floor is "deactivated" and balls bounce off of all walls
    for a time.
*	Hit strength increases for a time
## Cheat Key Ideas

## Level Descriptions
*	As the levels progress the speed of the balls increase
*	As levels progress the height of scene decrease
*	Blocks require more strength to be broken progressively
*	Certain blocks only appear on certain levels
## Class Ideas

The main application should have methods for adding things to the scene
(power ups and things. It'd be helpful if all classes inherited from a
"GameObject" class so that whether it's a powerup, block, or anything else
we can add action listeners to do whatever that block needs to do when hit.
```plantuml
abstract class GameObject {
	# speed
	# direction
	# image
	+ onHit(Context)
}
```
Passing a context object on hit will allow the objects to determine
what to do (or not do to prevent duplicate actions).
```plantuml
class Context {
	+ strength: int
	+ strikerType: TYPE_ENUM
	+ position: Pair<Integer, Integer>
}
```

During the gameloop and everytime an object is hit we can call the hit
method and the object can determine what to do based on the context.

abstract class called Block which has a method called action which is
called when the brick "dies".

Paddle class with fields like relating to some of the paddle ideas.

abstract powerup class

```plantuml
abstract class Block extends GameObject {
	# health
	+ abstract action(Context?) 
	+ hit(Context)
}
```

Possibly the action within block is a method that interacts
with the current scene?? idk. If block objects are nested somewhere
then this may allow for cool stuff.

```plantuml
interface Action {
	+ action(Context)
}
```
a class for powerups that also contains an action.

A class (separate from the main application)
specifically tasked with generating blocks, managing levels,
creating powerups and that stuff. Possibly nested class
type for each level specifying possible powerups, speeds, etc.
```plantuml
class Manager {
	- level: int
	- difficulties: List<Level>
}

class  level {
	+ powerups: List<PowerUp.class>
	+ specialBlocks:
}

```
