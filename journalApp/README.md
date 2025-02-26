# Journal App

This is a simple Journal App built using Spring Boot. The application allows users to create, read, update, and delete journal entries.

## Features

- Create a new journal entry
- View all journal entries
- Update an existing journal entry
- Delete a journal entry

## Requirements

- Java 11 or higher
- Maven 3.6.0 or higher
- Spring Boot 2.5.4 or higher

## Getting Started

### Clone the repository

```bash
git clone https://github.com/yourusername/journalApp.git
cd journalApp
```

### Build the project

```bash
mvn clean install
```

### Run the application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## API Endpoints

- `GET /journal` - Get all journal entries
- `POST /journal` - Create a new journal entry
- `GET /journals/id/{id}` - Get a journal entry by ID
- `PUT /journals/{id}` - Update a journal entry by ID
- `DELETE /journals/{id}` - Delete a journal entry by ID

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.
