Questions and Assumptions

1. Are you looking for me to a. implement **my own rules framework** or b. leverage an **existing rules framework** such
   as drools etc
   **Implement a simple comparison based rules framework keeping in mind that this is a PoC exercise**
   **Implement a simple map based object storage rather than DB to keep things simple (may change later)** - I choose this to keep things simple
2. What is input format
   **Assume hard-coded programmatic input for demo purposes (may change later)**
3. What types of logic are supported to chain the conditions within a rule
   **For sinplicity's sake, assume only AND (&&) logic is supported - in a flat logic chain i.e. no nesting**
   **Since the criteria is to aggregate rule score based on number of conditions satisfied, AND is implicitly specified
   i.e. OR makes no sense if we are aggregating score as a single OR satisfied condition will cause thee fired rule to
   be satisfied**
4. Are the attributes fixed + statically defined i.e name, type, color, cost, weight or are they freeform i.e. a set of
   key / value pairs
   **Set of key / value pairs with some mandatory (name, qty, price)**
5. Rules criteria - description is a bit ambiguous 
   a. Does the purchasing company specify **multiple rules per
   product** (each rule can contain multiple conditions) or 
   b. just **a single rule per product (that can contain
   multiple conditions)**    . 
   c. Are there **different rules for different products** ?
   **Yes - multiple rules per product**
   **Yes - different products can have different rules**
   **As a result, we calculate the weighted score for a product as the sum of weighted scores for all it's rules**
6. Are **all** the attributes expected to be present i.e. does each product have a name, type, color, cost, weight
   **No - some attributes may be missing**
   **In case an attribute is missing and it is checked against as part of a rule / condition, it will be scored as 0)**
7. Is quantity a Product attribute or is something defined by the purchasing company (i.e. does it refer to available
   inventory quantity or desired purchase quantity )? 
   a. **I assume it refers to available inventory quantity (a Product
   attribute)**
   b. also see 1, 2 i.e. is this a mandatory Product attribute or what ?
   **Assume it is an attribute and will always be present**
8. Re. "Filter the potential products to just those that pass a given threshold (assume 50% as the cutoff)" . 
   a. What is this cutoff - does it refer to the number of rules that pass completely or something else - so far, we were asked to
   check for conditions in a rule **It refers to percent of total passed condition for a product (across all rules)**
       b. What if a product has several (multi-condition) rules and one rule passes more than 50 % conditions while the
       other passes 0 % conditions. Please clarify behavior - as I'm not clear on how many rules a product can have.
       **The cutoff is consolidated across all conditions for a rule**
    2. **Assume we calculate total conditions across all rules eg if rule 1 satisfies 10/12 conditions but rule 2
       satisfies 1/3 conditions, overall 12/15 conditions are still satisfied (> 50 %) so the product passes the 50 %
       filter - YES **
10. Can you **explain a sample calculation**. This might help clarify a lot of the above questions ? I've performed a
    demo calculation - any corrections will help clear any uncertainties / incorrect assumptions
    **In progress**
    eg
```
Given

Product
	name 	| String 	: Nike TShirt
	type 	| String 	: Clothing
	color	| String 	: BLUE
	weight	| Float 	: 5
	cost 	| Float		: 17.75
	qty 	| Integer	: 1000


Product
	name 	| String 	: Reebok Shoe
	type 	| String 	: Footwear
	color	| String 	: BLUE
	weight	| Float 	: 8
	cost 	| Float		: 10
	qty 	| Integer	: 2000

Product
	name 	| String 	: Golden Goose Feather
	type 	| String 	: Misc
	color	| String 	: Yellow
	weight	| Float 	: 0.1
	cost 	| Float		: 100
	qty 	| Integer	: 1	

Rules:

Nike TShirt: 
Rule 1. if (color == BLUE && price < 15 && quantity > 750) then score = 100  ==> 67% PASS
Rule 2. if (color == BLUE && weight > 3  ) then score = 200  ==> 100% PASS

Number of rules passed 
Rule 1: passed 67 % conditions: sub-score = 67
Rule 2: passed 100 % conditions: sub-score = 200
Total score for (Nike TShirt): 267
Percent of Conditions Passed = 100*(2+2)/(3+2) = 80%


Reebok Shoe 
Rule 1. if (color == BLUE && price < 15 && quantity > 750) then score = 100  ==> 100% PASS
Rule 2. if (color == BLUE && weight > 3  ) then score = 200  ==> 100% PASS

Rule 1: passed 100 % conditions: sub-score = 100
Rule 2: passed 100 % conditions: sub-score = 200
Total score for (Reebok Shoe): 300
Percent of Conditions Passed = 100*(3+2)/(3+2) = 100%

Golden Goose Feather 
Rule 1. if (color == BLUE && price < 15 && quantity > 750) then score = 100  ==> 0% PASS
Rule 2. if (color == BLUE && weight > 3  ) then score = 200  ==> 0% PASS

Rule 1: passed 0 % conditions: sub-score = 0
Rule 2: passed 0 % conditions: sub-score = 0
Total score for (Golden Goose Feather: 0
Percent of Conditions Passed = 100*(0+0)/(3+2) = 0%

```
