# Software Challenge: Getting started with MongoDB

## Tasks to fulfill:

- Task 1 → The server connects to MongoDB (local or online).
- Task 2 → You can see the user model created.
- Task 3 → There is a controller to create the user.
- Task 4 → There is a controller for deleting the user.
- Task 5 → There is a controller for returning the user.
- Task 6 → There is a controller to update the user.
- Task 7 → You can bring up the server and perform the above actions.
- Extra → Add test using Insomnia / Postman.

## Getting Started

### Run locally

1. Create a MongoDB.
2. Clone the repository:<br>
   ```git clone https://github.com/annStein/mongo-db.git```
3. Run the application:<br>
   ```./gradlew bootRun -DCONNECTION_STRING=<YOUR MONGODB CONNECTION STRING>```

### Run locally using Docker

1. Create a MongoDB.
2. Clone the Repository.<br>
   ```git clone https://github.com/annStein/mongo-db.git```
3. Build the app:
   ```./gradlew build```
4. Create Docker image:
   ```docker build -t foo/bar .```
5. Run the image:
   ```docker run -e "CONNECTION_STRING=<YOUR MONGODB CONNECTION STRING>" -p 8080:8080 -t foo/bar```

## Testing

A postman collection is available: ```Getting Started MongoDB.postman_collection.json```.

Unit tests are available. They will be triggered through GitHub Actions after every push and for every pull request.

## Solution Documentation

The service was built following the clean architecture principle.

All endpoints are documented and can be tried using swagger: ```http://localhost:<port>/swagger-ui.html```

## See also:

https://www.mongodb.com/