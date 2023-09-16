
Example of creating a car :
      localhost:8080/api/v2/car/register
    {
        "brand" : "Audi",
        "model" : "a1",
        "pricePerDay" : 190,
        "seats" : 4
    }

Example of booking a reservation:
        localhost:8080/api/v2/reservation
    {
        "dateIn" : "2023-06-16",
        "dateOut" : "2023-06-19",
        "userId" : 1,
        "carId" : 4
    }

Example of registering a user:
    localhost:8080/api/v2/user/register
    {
        "firstName" : "Yanko",
        "lastName" : "Hasanov",
        "email" : "yanko@gmail.com",
        "password" : "testing1234"
    }