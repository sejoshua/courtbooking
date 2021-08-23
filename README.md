# Tennis Court Booking System

### To Play with It
After the application starts, we can access the Swagger UI by
http://localhost:8080/swagger-ui.html.

There are two key APIs:

| Name | Path | Type | Description |
|------|------|------|-------------|
| Customer | /api/v1/customer | POST | Create new customer by passing in first and last names |
| Booking  | /api/v1/booking  | POST | Create customer and court booking |

### Data Model
There are 4 models:
* [Court](src/main/java/com/example/courtbooking/model/impl/Court.java)
* [Customer](src/main/java/com/example/courtbooking/model/impl/Customer.java)
* [Booking](src/main/java/com/example/courtbooking/model/impl/Booking.java)
* [CustomerBooking](src/main/java/com/example/courtbooking/model/impl/CustomerBooking.java)

`Booking` represents the booking over a court on a particular date, 
while `CustomerBooking` is the booking record for a customer. `Booking`
holds a list of `CustomerBooking`.

### Data Repository
To simulate the database, [DataRepo](src/main/java/com/example/courtbooking/repo/DataRepo.java) is introduced.

### Full Booking Output
This was done at line 80 as in [BookingServiceImpl](src/main/java/com/example/courtbooking/service/impl/BookingServiceImpl.java).

### Assumptions
The system is far from a commercial one, as there are a couple of assumptions and TODOs:
* The data models are highly abstracted, with minimum fields.
* No or little data quality checks for controller input parameters.
* We assume the same user will not book the same court for the same date.