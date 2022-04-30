# dynamodb-services
DynamoDb demo with Spring Boot, Localstacak and NodeJS

## Localstack
Localstack configuration enable us to test our applcation integration wity DynamoDB locally, with no need to deploy the application in AWS. 

- Inside directory infrastructure we have a **docker-compose.yml** file, by running command **docker-compose up -d** localstack container will get started.
- **TIP:** access **localhost:4566/health** if you see a json list of AWS Services localstack is working as expected.

## DynamoDB
An easy way to create a table is by using Schemas that defines the table configuraion.

- The schema for this project is at infrastructure dir **user_table.json**
- By running the following command we create the User table bases on schema:
  - aws --endpoint-url=http://localhost:4566 dynamodb create-table --cli-input-json file://user_table.json
- **TIPs:**
  - Check if table was created: aws --endpoint-url=http://localhost:4566 dynamodb list-tables
  - It's possible to create the json schemas in a easier way online, I used https://dynobase.dev/dynamodb-table-schema-design-tool/
  - Other useful aws cli commands:
    - aws --endpoint-url=http://localhost:4566 dynamodb scan --table-name User 
    - aws --endpoint-url=http://localhost:4566 dynamodb delete-table --table-name User

