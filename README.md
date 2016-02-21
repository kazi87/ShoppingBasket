# ShoppingBasket
Simple implementation of shopping basket price caculator.

#Java version
	java version "1.7.0_79"
  Java(TM) SE Runtime Environment (build 1.7.0_79-b15)
  Java HotSpot(TM) 64-Bit Server VM (build 24.79-b02, mixed mode)

#Maven
	Maven home: C:\tools\apache-maven-3.2.3
	Java version: 1.7.0_79, vendor: Oracle Corporation
	Java home: C:\Program Files\Java\jdk1.7.0_79\jre
	Default locale: en_US, platform encoding: Cp1252
	OS name: "windows 7", version: "6.1", arch: "amd64", family: "windows"

#How to run:
	To run the application please pass the basket item list as the program arguments {itemName1 quantity1 itemName2 quantity2} e.g.

	java -jar ShoppingBasket-1.0-SNAPSHOT.jar apple 2 orange 3 papaya 4
	
	valid item list: {apple, papaya, orange, garlic}


#Output:
The application prints a summary to the standard output.
Sample:

-------------------------------------------------------------------------------
Idx   Name            Price      Promotion            Quantity   TotalPrice
------------------------------------------------------------------------------

1     Apple           0.25 GBP   -                    3          0.75 GBP  
2     Orange          30 GBP     -                    2          60 GBP    
3     Papaya          0.5 GBP    -                    1          0.5 GBP   
4     papaya          0.33 GBP   3 for 2              3          0.99 GBP  
5     papaya          0.33 GBP   3 for 2              3          0.99 GBP  
------------------------------------------------------------------------------
