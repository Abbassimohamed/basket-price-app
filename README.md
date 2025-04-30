## Description

Write a program and associated unit tests that can price a basket of goods taking into account some special offers.
The goods that can be purchased, together with their normal prices are:
• Soup – 65p per tin
• Bread – 80p per loaf
• Milk – £1.30 per bottle
• Apples – £1.00 per bag
Current special offers:
• Apples have a 10% discount off their normal price this week
• Buy 2 tins of soup and get a loaf of bread for half price
The program should accept a list of items in the basket and output the subtotal, the special offer discounts and the final price.
Input should be via the command line in the form PriceBasket item1 item2 item3 …

## Prerequisites

Before you begin, ensure you have the following installed on your machine:

- Java 17 or higher
- Maven 3.6.3 or higher
- 
## Installation

1. Clone the repository:
   git clone https://github.com/Abbassimohamed/basket-price-app.git

2. Build and run the application:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Project Structure

- src/main/java/basket/priceapp/pricebasket : Contains the main source code of the application.
- model : contains the data models
- service : Contains service classes implementing business logic.
- util: Contains utility classes.