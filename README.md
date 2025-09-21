# Submission

This is my attempt at submitting the project for Critter Scheduler. 
There are some implementations details that are required for the full project to run:

## Database used

I have used PostgreSQL and have used a non-standard port which was 5434 (instead of 5432).

I have provided a docker image showing the configuration I used, however, 
if the reviewer had a valid PostgreSQL running only modifying the application.properties spring.datasource.url (port) will make it run.

The dockerfile I used is located in `/docker/postgresql-udacity-datastore-and-persistance/docker-compose.yml`.

## Postman Collection

There were some bugs in the Postman Collection I provided a minimal changed one and is located in:
`/src/main/resources/Udacity-project-datastore-persistance.postman_collection.json`.

The fixes were:
1. No call to get employee (which was a POST instead of a GET, I didnt correct it since it was not in the requirements)
2. No valid JSON body for create Schedule method.
3. Some ids replaced in other HTTP calls so is possible to run it in one pass (Upper method to lower method).

## Tests

No modification were mede, only added `/src/test/resources/application.properties` to point to H2 in memory database.
