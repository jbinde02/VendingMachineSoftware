# VendingMachineSoftware
Coding Challenge. An application that takes a JSON file input and displays it like a vending machine would. Items are labeled and the user can attempt to purchase an item or a new item list JSON can be added.
VendingMachineSoftware
Coding Challenge. An application that takes a JSON file input and displays it like a vending machine would. Items are labeled and the user can attempt to purchase an item or a new item list JSON can be added.

This is a realtivly simple project in theory. The main idea is that we have a JSON input that will be decoded and the items will be displayed by row (letters) and column (numbers). The user will be able to select an item and atempt to make a payment. The vending machine will give the user updates on its state such as if it is asking for a selection, asking for a payment, or informing the user of a error. Each action will be logged so someone can go back and review if the machine is working correctly and to see if someone has interacted with it.

There are a few things I haven't done before that I tried on this project. First is working with JSON in Java. This shouldn't be too bad since there are many resources to help me with it. Next is using Maven. The instructions given said that using Maven and open source libraries is encouraged so I decided to give it a shot. It was a learning curve but I think I kind of understand it (at least enough to get something working).

(Note) Not sure if it is intentional or not but the columns value in the config object of the JSON is a string and not a number. I changed the columns value so that it is a number.

I decided to use a model view controller architecture since I have used it before and it seems like it would fit here well. The model repersents the items in the vending machine and it processes the JSON files. The view represents the UI and creates the frame, panels, and buttons. The controller acts as the interface between the model and the view. It processes the inputs from the view and tells the model what to do. It then takes the processed data from the model and updates the view.
