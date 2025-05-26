# Spring Boot GraphQL Project

This project demonstrates a basic GraphQL API built with Spring Boot. It allows you to manage a list of `Person` objects.

## Features

* **Querying Persons:** Fetch a list of all persons or a specific person by their ID.
* **Adding Persons:** Create new person records.
* **Updating Persons:** Modify existing person records.
* **Deleting Persons:** Remove person records by their ID.


## 📄 schema.graphqls

The `schema.graphqls` file defines the **GraphQL schema** for your application. It acts as the contract between the client and server.

### 🤝 Contract Behavior

- The **client** (e.g., frontend, Postman) agrees to ask only for things defined in the schema.
- The **server** agrees to provide exactly what the schema promises.

### 📋 What the Schema Defines

- The types of objects that can be queried or mutated (e.g., `Person`)
- The fields each type contains
- The available **queries** (for reading data)
- The available **mutations** (for modifying data)



### 🧩 Example schema.graphqls

```graphql
type Person {
  id: ID! # ! means this field is non-nullable ID type is used for unique identifiers
  name: String
  age: Int
}

type Query {
  persons: [Person]
  personById(id: ID!): Person
}

type Mutation {
  savePerson(name: String!, age: Int!): Person
  updatePerson(id: ID!, name: String!, age: Int!): Person
  deletePersonById(id: ID!): String
}
```

## Note:

1. GraphQL typically uses HTTP POST requests for **all** operations (queries and mutations), unlike traditional REST APIs that use different HTTP methods such as GET, POST, PUT, and DELETE.  

2. **Endpoint URL**  
   Send a POST request to the GraphQL endpoint: http://localhost:8080/graphql

3. **No Separate Endpoints Required**  
Unlike REST APIs, you do **not** need to define separate URL mappings (like `/getAll`, `/add`, etc.) for each operation. Instead, you send a GraphQL query or mutation through the request body and specify the data you want.

4. **Example: Fetch All Persons**  
To fetch all persons from the `Person` entity, send the following JSON as the POST request body:

    ```json
   {
     "query": "query { persons { id name age} }"
   }
    ```

5. **Specify Custom Return Types**  
   You can specify **exactly which fields** you want in the response.  
   For example, if you only need the `id` and `name` of all persons:

    ```json
   {
     "query": "query { persons { id name } }"
   }
   ```
   
6. **Example: Custom Fields in Mutation**  
   Similarly, in a mutation, you can control the returned fields.  
   For example, to create a new person and return only the `id`:

   ```json
   {
     "query": "mutation { savePerson(name: \"Alice\", age: 25) { id } }"
   }
  