# product-rules

## High level requirement
To implement a simple rules based scoring system for products. Detailed requirements available [here](docs/requirements.docx)

## Design Tasks
### Draw a rough UML diagram showing the classes for the objects described above, and in particular rules and conditions. See the sample UML for product below.
TODO

### Write out, in code or psuedocode, a function that will calculate the scores, and the total and average prices for the products.
TODO

### If you make any assumptions, write them down.
#### Project Scope / Assumptions
Several assumptions were made. The scope of the Questions/Answers/Assumptions is captured [here](docs/questions-and-assumptions.md)
Some notable points key to the implementation are:
* The project will be implemented as a simple self-contained Jave/Spring demo
* The demo does not support customizable data / rule input. The demo dataset is hardcoded.
* Possible input schemes for rules could be
  * flat rules (1 per line) + a parser
  * Json representation of the rules that are deserialized by the program driver
* Flat rules are assumed to keep things simple. A more complex query scheme could involve a nested query tree
* All conditions in all rules for a product are evaluated resulting in a weighted score
* Products are filtered out based on a minimum percentage of successful conditions for a product
* Map based in-memory data repositories are used for demo purposes. In real life, these repositories would probably be backed by a more robust datastore
* Unclear on what the query parameters are to co-relate documents and rules, so I chose to match rules with products based on the product name.
  * Several alternatives are possible such as
    * match by product category
    * define general rules that are not product specific
    * In any case, I understood the intent of the exercise is for the seller to find matching product items given that they "know" what product names they are searching for and devise the rules accordingly.


### Criteria
### Make sure your solution is runnable from the command line via your tool of choice.
TODO: build and execution instructions
#### The program must print out the total and average prices that score sufficiently highly when run from the command line.
TODO: sample output / explanation