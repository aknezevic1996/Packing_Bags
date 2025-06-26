# Packing Bags

## Description

This is a Java program that efficiently packs a bag given a capacity and a list
of available items with their respective weights, ensuring at least one of each item
is packed.

---

## Requirements

* Java 17 or higher
* Gradle

## Configuring and Running the App

The `items.json` file in the `src/main/resources` directory can be modified to add, 
remove or update items and their weights.

The program can be run on an IDE by running `Main.main()` or run via terminal by entering
```bash
./gradlew --console plain run
```
within the project's root directory.

When running the app, the console will ask for an integer input as the capacity.

The packed bag contents will be printed as a JSON string to the console.

---

## Assumptions

* All item weights are positive integers
* Input capacity is an integer greater than or equal to the sum of each item's weight
* The items.json file exists in `src/main/resources` and is properly formatted
* Any remaining capacity that is unable to be filled is acceptable (e.g., one item 
available with weight five and a bag capacity of 14 will have two counts packed in 
the bag with four remaining capacity unable to be filled)