# VendingMachineSoftware
Coding Challenge. An application that takes a JSON file input and displays it like a vending machine would. Items are labeled and the user can attempt to purchase an item or a new item list JSON can be added.

# Current Instructions to Run in Windows
1. Download the VendingMachineSoftware.jar file from the /out/artifacts/VendingMachineSoftware_jar directory
2. Open up the command prompt in Windows
3. Navigate to where the file is located
4. Type 'java -jar VendingMachineSoftware.jar' and hit enter

You should also be able to navigate to the .jar file and double click it to run.

# About
This is a realtivly simple project in theory. The main idea is that we have a JSON input that will be decoded and the items will be displayed by row (letters) and column (numbers). The user will be able to select an item and atempt to make a payment. The vending machine will give the user updates on its state such as if it is asking for a selection, asking for a payment, or informing the user of a error. Each action will be logged so someone can go back and review if the machine is working correctly and to see if someone has interacted with it.

# Before
There are a few things I haven't done before that I tried on this project. First is working with JSON in Java. This shouldn't be too bad since there are many resources to help me with it. Next is using Maven. The instructions given said that using Maven and open source libraries is encouraged so I decided to give it a try. I also learned how to do unit test about a year ago and then never used them so I'll try and include some here.

# Note
Not sure if it is intentional or not but the columns value in the config object of the input.json file is a string and not a number. I changed the columns value so that it is a number.

# Approach
I decided to use a model view controller architecture since I have used it before and it seems like it would fit here well. The model repersents the items in the vending machine and it processes the JSON files. The view represents the UI and creates the frame, panels, and buttons. The controller acts as the interface between the model and the view. It processes the inputs from the view and tells the model what to do. It then takes the processed data from the model and updates the view.

I used the json.simple library to parse through the .json files. It took a couple tries but I eventually figured out how to retrive the values from the input.json file. Something I didn't add which may of been useful is a validator to check if the .json file is in the correct format. Also, figuring out why I couldn't get the bundled .json file in the jar to run was a real pain. I eventually figured out that that inputstreams would help me achive this task. I created another class to represent an vending machine item with its name, price, and ammount. I didn't add a location since I figured that the item doesn't really need to know where itself is located. The data from the .json file is then processed into these vending machine items and then put into a hashmap. A key is created using a letter and number which represents the vending machine item's location in the machine. I was having trouble thinking of a way to take a single list of items and putting it into a 2D array or a list of list so I used the hashmap instead and I think it turned out pretty good.

Building GUIs in Java with Swing by hand is not the most fun thing in the world. I got it to a point where it can do its function but it doesn't look pretty. I didn't add a layout manger and it just uses the default of lining everything up side by side. I managed to implement a button that allows for new .json files to be loaded while the program is running. The data retived from the .json is put into a jtable. The table includes column names but not the row names. The rows are still represented by letters in ascending order.

The controller class contains a very crude cycle for giving the machine some basic operation. There is proably a much better way of going about it but this works.

I created a 'log' of actions by printing stuff to the console. A better way of doing this would proably be putting the info in a textfile.

This is the first time I have tried to use maven and it seems like it would be a pretty good tool for larger projects. It made importing the .json simple library very easy. Something it didn't make easy was creating a jar file with dependencies included. I just left intellij up to that task which was still a struggle for some reason.

I also implemented a few unit test for the model class. I learned how to do unit test over a year ago but I never really had a reason to use them. I decided to try and use them again since maven has a cycle that runs tests.
