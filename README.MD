Develop a car rental platform with the following entities: Users, Cars, and Reservations.

User Entity:

-   Users should be able to register.
-   Users should be able to update their information.
-   Users should be able to delete their accounts.
-   Users should be retrievable by their ID.
-   Users should be retrievable by their email.
-   Users should be able to update their passwords.


Car Entity:

-   Cars should be able to be added to the platform.
-   Cars should be retrievable by their ID.
-   Cars should be updatable.
-   Cars should be deletable.


-   Each car should have the following information:

-   Make (Brand)
-   Model
-   Number of Seats
-   Rental Price per Day

Reservation Entity:

-   A reservation represents a booking made by a user for a specific car for a specific period.
-   Reservations should be creatable.
-   Reservations should be retrievable by their ID.
-   Reservations for a specific user should be retrievable.
-   Reservations for a specific car should be retrievable.
-   Reservations for a specific period should be retrievable (you may use @Query).
-   Reservations should be deletable by their ID.
-   Users should be able to change the car in their reservation.
-   Users should be able to change the dates of their reservation.
-   The price of a reservation should be calculated based on the number of days of the reservation and the daily rental price of the car.
-   The number of days should be calculated as the difference between the start and end dates.