**Description**

*File: src/main/java/org/example/Main.java*

Write a console application using the architectural pattern
MVC that:
* describes the Drawable interface with the draw() method for drawing a shape
* describes the abstract class Shape, which implements the Drawable interface and contains a shapeColor field of type String for the shape color and a constructor for its initialization, an abstract method for calculating the shape area calcArea() and an overridden method toString();
* describes the Rectangle, Triangle, Circle classes, which are inherited from the Shape class and implement the calcArea() method, and also override the toString() method;
* creates a dataset of type Shape (an array with a dimension of at least 10 elements);
* processes the array:
  - displays the dataset;
  - calculates the total area of ​​all shapes in the dataset;
  - calculates the total area of ​​shapes of a given type;
  - sorts the dataset in order of increasing area of ​​shapes, using the Comparator interface object;
  - sorts the data set by shape color using the Comparator interface object.
Values ​​for initializing objects are selected from pre-prepared data (selected randomly or in order of passing).


*File: src/main/java/org/example/Ski_pass.java*

Simulates the operation of the built-in processor of the ski lift turnstile to check access by ski-pass.
The turnstile controls the entry of skiers to the lift by ski-pass. Ski passes come in the following types:
1. On weekdays:
   * Without taking into account the number of climbs: for half a day (from 9 to 13 or from 13 to 17), for a day, two days, 5 days.
   * By the number of climbs: for 10 climbs, for 20 climbs, for 50 climbs, for 100 climbs.
2. On weekends:
   * Without taking into account the number of trips: for half a day (from 9 to 13 or from 13 to 17), for a day, two days.
   * By the number of climbs: for 10 climbs, for 20 climbs, for 50 climbs, for 100 climbs.
3. Season ticket.

The turnstile must be connected to a system that keeps a register of issued cards. In this system, it is possible to:
  1. issue a ski pass;
  2. block a ski pass due to violation of the lift rules.

Data about the card is stored on the card itself, namely: unique identifier, card type, validity period, number of trips, etc.
The turnstile reads the data from the card and checks it. If the data cannot be read, the card is expired, blocked, or there are no credits left for trips, then passage is prohibited. Otherwise, one trip is removed from the card (if the card is designed to record lifts) and passage is allowed.
The turnstile keeps track of permits and refusals to pass. At the same time, the turnstile can issue
1. total data
2. data broken down by ski pass type upon request.
