# GraphQL Task Manager Demo

A complete demo application using Spring Boot 3, Gradle, MyBatis-Plus, PostgreSQL, GraphQL, and Vue 3.

## Project Structure

- `backend`: Spring Boot 3 application with GraphQL API (Java)
- `frontend`: Vue 3 application with Apollo Client

## Prerequisites

- JDK 17+
- PostgreSQL
- Node.js 16+
- npm or yarn

## Database Setup

1. Create a PostgreSQL database:

```sql
CREATE DATABASE graphql_demo;
```

2. Then import the PostgreSQL database by running the `sql/graphql_demo.sql`.

## Backend Setup

1. Configure the database connection in `src/main/resources/application.yml` if needed.

2. Build and run the Spring Boot application:

```bash
./gradlew bootRun
```

The GraphQL API will be available at http://localhost:8080/graphql
The GraphiQL interface will be available at http://localhost:8080/graphiql

## Frontend Setup

1. Navigate to the frontend directory:

```bash
cd frontend
```

2. Install dependencies:

```bash
npm install
# or
yarn install
```

3. Run the development server:

```bash
npm run dev
# or
yarn dev
```

The Vue application will be available at http://localhost:5173

## Features

- Create, read, update, and delete tasks
- Mark tasks as completed
- GraphQL API with GraphiQL interface for testing
- Responsive Vue 3 frontend with Apollo Client

## Technologies Used

- Backend:
  - Spring Boot 3
  - Gradle
  - MyBatis-Plus
  - PostgreSQL
  - GraphQL Java
  - Java 17

- Frontend:
  - Vue 3
  - Apollo Client
  - Vite

## API Schema

```graphql
type Task {
    id: ID!
    title: String!
    description: String
    completed: Boolean!
    createdAt: String!
    updatedAt: String
}

type Query {
    tasks: [Task]!
    task(id: ID!): Task
}

type Mutation {
    createTask(input: TaskInput!): Task!
    updateTask(input: TaskUpdateInput!): Task
    deleteTask(id: ID!): Boolean!
}
```