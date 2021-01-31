# breakout

This project implements the game of Breakout.

Name: Joshua Petitma

### Timeline

Start Date: Sunday 01-24

Finish Date: Sunday 01-31

Hours Spent: My eyes bleed. 20-25 hours.

### Resources Used

*   JavaFX documentation.
*   [this video that showed me smooth movements](https://www.youtube.com/watch?v=L5GJ-i\_6uGQ).
*   Stackoverflow whenever something broke.

### Running the Program

Main class: Main

Data files needed: Provided in resources under standard maven hierarchy.

Key/Mouse inputs:

*   Move Top Paddle: A and F

*   Move Bottom Paddle: H and L

### Paddle Featurs:

*   Launch Ball (costs 50 points) Space

*   Dash (costs 20 points) Shift

Where are my VIM homies!?!?!

### Cheat keys:

*   INSERT to add more balls
*   the EQUALS key to increase paddle speed
*   the PLUS numpad key to add health
*   the MINUS numpad key ot subtract health
*   the button to the right of 0 to slow down ball. (The 'Dash' key??)

### Notes/Assumptions

Assumptions or Simplifications:

*   I added most of the powerups I wanted originally, all the main ones besides music.
*   I wanted to have more game mechanics, but decided that focusing on powerups would be a better

Known Bugs:

*   If a ball hits a brick from the top it is automatically broken.
*   If the game starts with too many bricks there's a 1% chance that the game will crash. It happened one time.
    use of time.

Extra credit:
I made a dollar with doctor Phil on it. And like 10 different bricks + 9 power ups/anti powerups.

### Impressions

THIS WAS REALLY FUN. I don't even play video games. I feel like I spent too much time working on this,
but I still feel like I learned a lot about project structure which will help me in the near future. It was
also really nice to see a project develop comletely within a week's time, usually my projects take months to finish
since I generally take my time. Seeing the game come from nothing to a very meme worthy application gives me a sense that
I don't have to always have long winded projects that take months to complete, I can have a little fun and do more one
off things like this. I also learned that apparently my formatter for isn't the java specification for tab size. Honestly
that doesn't really matter but I'm going to try and fix it. This project is MUCH different than anything I have
ever done. I made one game for a project my senior year but after that nothing. Being able to try something new
like this does kind of inspire me to try other things outside of software development. Seeing how the gameloop
works and the ways we can use it to manipulate the game is really interesting. I may create another game in
the future! But I'm not doing it in a week, my eyes hurt too much.

Coding is fun, but my eyes burn now.

### Bricks and Powerups

*   HealthBrick - When a paddle hits it you get +1 to health
*   PauseBrick - Freezes paddles when it's broken by the main (blue) ball
*   StrongBrick - Takes 5 hits or a hit from the top to break
*   MoneyBrick (Dr. Phil dollar) - Points are multiplied by 10 for 10 seconds
*   InvincibilityBrick (HomeWork Pass) - Take no damage for 5 seconds
*   FireBrick - When broken sends fire, if hit by paddle health decrements
*   GenerativeBrick - When hit a non volatile strong ball is created. When this ball hits the
    the floor it will not decrement health. This ball can plow through certain bricks without
    bouncing back, which is useful.
*   SANICEBRICK - GOTTA GO FAST!
*   BrickBrick - Brick brick brick.

Functional differences:

*   Certain bricks fall when hit
*   Certain bricks cause effects when they are hit by the special (hot) ball
*   Some Bricks require multiple hits to be defeated
*   Falling powerups can still cause balls to react to them
*   Some Bricks create powerups that flall.
