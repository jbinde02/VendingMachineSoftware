# VendingMachineSoftware
Coding Challenge. An application that takes a JSON file input and displays it like a vending machine would. Items are labeled and the user can attempt to purchase an item or a new item list JSON can be added.

# Current instructions to run
1. Download the VendingMachineSoftware.jar file
2. Open up the command prompt in Windows
3. Navigate to where the file is located
4. Type 'java -jar VendingMachineSoftware.jar' and hit enter

You should also be able to navigate to the .jar file and double click it to run.

# About

This is a realtivly simple project in theory. The main idea is that we have a JSON input that will be decoded and the items will be displayed by row (letters) and column (numbers). The user will be able to select an item and atempt to make a payment. The vending machine will give the user updates on its state such as if it is asking for a selection, asking for a payment, or informing the user of a error. Each action will be logged so someone can go back and review if the machine is working correctly and to see if someone has interacted with it.

There are a few things I haven't done before that I tried on this project. First is working with JSON in Java. This shouldn't be too bad since there are many resources to help me with it. Next is using Maven. The instructions given said that using Maven and open source libraries is encouraged so I decided to give it a shot. It was a learning curve but I think I kind of understand it (at least enough to get something working).

(Note) Not sure if it is intentional or not but the columns value in the config object of the JSON is a string and not a number. I changed the columns value so that it is a number.

I decided to use a model view controller architecture since I have used it before and it seems like it would fit here well. The model repersents the items in the vending machine and it processes the JSON files. The view represents the UI and creates the frame, panels, and buttons. The controller acts as the interface between the model and the view. It processes the inputs from the view and tells the model what to do. It then takes the processed data from the model and updates the view.

I used the json.simple library to parse through the .json files. It took a couple tries but I eventually figured out how to retrive the values from the input.json file. Something I didn't add which may of been useful is a validator to check if the .json file is in the correct format. Also, figuring out why I couldn't get the bundled .json file in the jar to run was a real pain. I eventually figured out that that inputstreams would help me achive this task.

Building GUIs in Java with Swing by hand is not the most fun thing in the world. I got it to a point where it can do its function but it doesn't look pretty. I managed to implement a button that allows for new .json files to be loaded while the program is running.

The controller class contains a very crude cycle for giving the machine some basic operation. There is proably a much better way of going about it but I got what I got.

This is the first time I have tried to use maven and it seems like it would be a pretty good tool for larger projects. It made importing the .json simple library very easy. Something it didn't make easy was creating a jar file with dependencies included. I just left intellij up to that task which was still a struggle for some reason.

I also implemented a few unit test for the model class. I learned how to do unit test over a year ago but I never really had a reason to use them. I decided to try and use them again since maven has a cycle that runs tests.
