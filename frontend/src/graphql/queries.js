import gql from 'graphql-tag'

export const GET_TASKS = gql`
  query GetTasks {
    tasks {
      id
      title
      description
      completed
      createdAt
      updatedAt
    }
  }
`

export const GET_TASK = gql`
  query GetTask($id: ID!) {
    task(id: $id) {
      id
      title
      description
      completed
      createdAt
      updatedAt
    }
  }
`

export const CREATE_TASK = gql`
  mutation CreateTask($input: TaskInput!) {
    createTask(input: $input) {
      id
      title
      description
      completed
      createdAt
      updatedAt
    }
  }
`

export const UPDATE_TASK = gql`
  mutation UpdateTask($input: TaskUpdateInput!) {
    updateTask(input: $input) {
      id
      title
      description
      completed
      createdAt
      updatedAt
    }
  }
`

export const DELETE_TASK = gql`
  mutation DeleteTask($id: ID!) {
    deleteTask(id: $id)
  }
` 