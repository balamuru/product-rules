# product-rules

## High level requirement
To implement a simple rules based scoring system for products. Detailed requirements available [here](docs/requirements.docx)

## API Docs
API Documentation can be generated via `mvn javadoc:javadoc` and by default is generated in the folder [docs/api](docs/api)

## Binary Release
A pre-built release has been added to this repository [here](release/). Source build follow later in the document.
To execute the demo, download the binary and type: `java -jar  product-rules-0.0.1-SNAPSHOT.jar` to execute

## Design Decisions / Choices
* Rules framework vs DIY naive matching - I chose a DIY approach as I figured the objective of the exercise was to evaluate my software dev skills rather than my Rules framework expertise
* Microservice approach vs all-in one application - It was requested that this PoC be self contained executable so I chose a curated demo application powered by Spring Boot
* I chose a sparse code approach. Each class doesn't do very much but conforms to a simple minimal API/interface. Therefore, it is possible to implement different behavior via alternate implementations
* APIs call each other directly in an SOA-ish manner to keep things simple. If this were to be designed for scalability
  * The API would be decoupled via a messaging broker - using a choregraphed saga pattern ("no central management service")
  * A NoSQL DB would be used to back the product and rule stores
  * We could leverage `parallelStream()` to parallelize certain comparison operations
  * 
## Alternate Implementation Possibilities
* Rule Engine based Implementation - This is what would most commonly be used if this was to be a production grade project
* Graph DB (Neo4J). The attributes and products could be loaded from a datastore into a Graph DB with Product and Attributes modelled as nodes and the associations as edges. Queries be written to determine the matches, although scoring / weighting might be rickier  

## Design Tasks
### Draw a rough UML diagram showing the classes for the objects described above, and in particular rules and conditions. See the sample UML for product below.
#### Domain Class Diagram

![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/balamuru/product-rules/master/docs/uml/class.puml)

![Sequence Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/balamuru/product-rules/master/docs/uml/messages.puml)

### Write out, in code or psuedocode, a function that will calculate the scores, and the total and average prices for the products.
#### Matching Algorithm
```
for each product in products
  find rules matching this product
  if matching rules found
    for each rule in rules
      for each condition in rules
        if the product has an attribute whose name matches the condition name
          evaluate the attribute against the condition
      calculate weighted score for each rule          
    record success threshold as (total conditions matched across all matched rules for the product) vs (total conditions across all matched rules for the product)
    record total weighted score for the product      
  else
    record 0 score for product
  
  return product match results > success threshold  
   
```

#### Scoring Algorithm
```
get a list of product matching results that pass the filter threshold (productid, price, qty ....) 
initialize counters for weightedSPriceSum, totalQty, distinctPriceSum, distinctQty

for each result
  accumulate 
    weightedSPriceSum+=price*qty
    totalQty+=qty
    distinctPriceSum+=price
    distinctQty+=1

display distinctPriceSum
display distinctQty
display distinct average = distinctPriceSum/distinctQty

display weightedPriceSum
display totalQty
display weighted average = weightedSPriceSum/totalQty
```


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
#### Make sure your solution is runnable from the command line via your tool of choice.
##### Build
* Build 
```
cd <project-dir>
mvn clean package
```
* Execute
```
cd target
mvn run
java -jar  product-rules-0.0.1-SNAPSHOT.jar 
```
#### The program must print out the total and average prices that score sufficiently highly when run from the command line.
* Dump all products for demonstrations' sake
```
All Products:
Product{id='658748d0-6389-4554-a702-e063556fb385', attributeMap={color=StringAttribute{name='color', value=Blue}, price=NumberAttribute{name='price', value=17.75}, qty=NumberAttribute{name='qty', value=1000.0}, name=StringAttribute{name='name', value=Nike TShirt}, weight=NumberAttribute{name='weight', value=5.0}, type=AbstractAttribute{name='type', value=CLOTHING}}}
Product{id='276ccd4d-306b-4fa3-88f3-3029f1f0538a', attributeMap={color=StringAttribute{name='color', value=Red}, price=NumberAttribute{name='price', value=40.75}, qty=NumberAttribute{name='qty', value=5000.0}, name=StringAttribute{name='name', value=Nike TShirt}, weight=NumberAttribute{name='weight', value=5.0}, type=AbstractAttribute{name='type', value=CLOTHING}}}
Product{id='7efcd5ed-c487-4ad0-bf51-fc61d920fd9a', attributeMap={color=StringAttribute{name='color', value=Blue}, price=NumberAttribute{name='price', value=10.0}, qty=NumberAttribute{name='qty', value=2000.0}, name=StringAttribute{name='name', value=Reebok Shoe}, weight=NumberAttribute{name='weight', value=8.0}, type=AbstractAttribute{name='type', value=FOOTWEAR}}}
Product{id='175426ec-eb07-4720-9db4-0329f7f2e5e9', attributeMap={color=StringAttribute{name='color', value=Yellow}, price=NumberAttribute{name='price', value=100.0}, qty=NumberAttribute{name='qty', value=1.0}, name=StringAttribute{name='name', value=Golden Goose Feather}, weight=NumberAttribute{name='weight', value=0.1}, type=AbstractAttribute{name='type', value=MISC}}}
Product{id='f55e08d4-49ee-41cd-9914-0829b836b73a', attributeMap={color=StringAttribute{name='color', value=Yellow}, price=NumberAttribute{name='price', value=1.0}, qty=NumberAttribute{name='qty', value=10000.0}, name=StringAttribute{name='name', value=Cheezy Bread}, weight=NumberAttribute{name='weight', value=1.0}, type=AbstractAttribute{name='type', value=FOOD}, fda-approved=BooleanAttribute{name='fda-approved', value=false}}}

```
* 3 out of 4 products passed the threshold filter
```
Matched Products:
ProductMatchResult{productId='658748d0-6389-4554-a702-e063556fb385', productName='Nike TShirt', qty=1000, price=17.75, match=true, percentConditionsSatisfied=80.0, weightedScore=350.0}
ProductMatchResult{productId='175426ec-eb07-4720-9db4-0329f7f2e5e9', productName='Golden Goose Feather', qty=1, price=100.0, match=true, percentConditionsSatisfied=66.666664, weightedScore=66.0}
ProductMatchResult{productId='f55e08d4-49ee-41cd-9914-0829b836b73a', productName='Cheezy Bread', qty=10000, price=1.0, match=true, percentConditionsSatisfied=100.0, weightedScore=-800.0}```
```

* Averages considering distinct items
```
Distinct Averages
Total price of all distinct products: 118.75
Number of distinct products that pass the conditional filter: 3
Average price of distinct products: 39.583332
```

* Weighted averages considering all instances of items
```
Weighted Averages
Total price of all products: 27850.0
Number of products that pass the conditional filter: 11001
Average weighted price of products: 2.531588
```
