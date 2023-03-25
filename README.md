# Resort-java-application
develop a java application for Resort to help the administrator manage the resort reservations

A.Problem: Assume that you have been asked to develop a java application for Musandam Resort to help the administrator manage the resort reservations. There are two types of units available for reservation: conference hall, and room unit. Each reservation has the following information: 
● Reservation ID, starts with 0 and auto-increments by 1 at each creation of a new reservation. 
● Number of nights. 
● Night price, and rate. Each reservation has specific features 
● Reservation of type room comes with a set of maximum 3 requirements requested by the user like (coffee machine, TV, internet, etc.). These requirements are specified at the time of reservation. 
● Conference hall reservation should be done with a certain number of guests knowing that the cost of service per guest equals to 15 per night.

B.What is required? The program should display a menu for the administrator to choose any of the following services (see the sample runs): 
● Adding a reservation: The administrator asks the customer about the type of the reservation (conference, or room unit). Then he/she should perform the booking. The program will then display information about the reservation together with the total cost.
Cost per night for a room unit or conference hall should be read from a file called bookingInfo which holds the pricing information for each unit type and then calculate total cost according to the rate and number of nights (look at section C). 
● The administrator can also check for the existence of a specific reservation through its reservation ID and display the details of existing ones. 
● The administrator can also cancel any reservation through its reservation ID. 
● The administrator may display all available reservations with their total costs. 
● Once the administrator chooses to exit the application, the program should stop.

C.Format of input file: Each line in the input file bookingInfo represents the pricing record of either a room unit or a conference hall as the following. Unit type : rate of decrement : night price Where rate of decrement represents the decrement rate in reservation total cost. For example, according to the information of file bookingInfo (fig 1), we can tell that for a reservation of a room unit for 3 nights, the total cost will be 71.19 according to the following equation. Room Total cost= (number of nights * night price)-[rate*( number of nights * night price)]

D.Some programming specifications: Provide the required classes for each type of reservation (room reservation, conference hall reservation) with suitable constructors,and accessors/mutators. All the reservations should be kept in one arrayList. You need to design your solution to include the following classes: 
● Reservation: Encapsulates common data and methods used to manage reservation of a single unit. 
● RoomReservation: Encapsulates data and/or methods specific to room reservation. 
● ConferenceReservation: Encapsulates data and/or methods specific to conference hall reservation 
● MusandamResort: Encapsulates data/methods to perform the actual management of the reservations including making a reservation, cancelling a reservation, reading the information from the input file, keeping track of current reservations, etc.). 
● ResortTester: Encapsulates the data and/or methods for displaying the menu and initiating the operations. The Reservation class and its descendant classes should override the toString method so that it can be used to display appropriate information of the related class according to the following

For room reservation, it will display (reservation ID, reservation type, number of nights, , cost of reservation , and list of requirements) For conference hall reservation (reservation ID, reservation type, number of nights, cost of reservation, and number of guests)
