# Pretty Quake Logger

This repository contains the source code for the Preety Quake Logger system, which is a web application for processing and displaying logs of Quake matches in a more user-friendly way.

The system consists of a frontend and a backend, both developed with modern and efficient technologies.

## Technologies Used

- Frontend:
  - React
  - TypeScript
  - HTML
  - CSS

- Backend:
  - Spring Boot
  - Java
  - H2 Database
  - Maven

## Features

Preety Quake Logger has the following features:

- Processing of logs to extract information about matches and deaths.
- Display of match statistics, including total number of deaths, player rankings, and most common means of death.
- Listing of all registered matches.

## Project Structure

- `quakelogger-frontend/`: Contains the source code for the frontend of the application.
- `quakelogger/`: Contains the source code for the backend of the application.

## Development Environment Setup

To set up the development environment, follow the instructions below:

### Frontend

1. Navigate to the `frontend/` folder and run the following command to install the dependencies:
```
npm install
```

2. After the dependencies are installed, run the following command to start the development server:
```
npm start
```

3. The frontend will be available at `http://localhost:3000`.

### Backend

1. Import the backend project into your preferred IDE.

2. Ensure that the Maven dependencies have been downloaded correctly.

3. Run the application using your IDE or the following command:

```
mvn spring-boot:run
```

4. The backend will be available at `http://localhost:8080`.

## Contribution

Feel free to contribute to this project. To do so, follow these steps:

1. Fork this repository.

2. Create a branch with your feature or bug fix:

```git
git checkout -b my-feature
```

3. Make the desired changes and commit them:

```git
git commit -m "My feature"
```

4. Push the changes to your remote repository:

```git
git push origin my-feature
```

5. Open a Pull Request in this repository.

6. Wait for the review and merge process.

## License

This project is licensed under the [MIT License](LICENSE).
