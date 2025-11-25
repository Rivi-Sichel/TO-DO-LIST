# Todo Java App

A simple console-based **task management application** written in Java. Tasks are stored in a JSON-style file without using any external libraries.

This project demonstrates a step-by-step development workflow, including commits with minor mistakes and corrections, to simulate a realistic coding process.

---

## Project Structure

todo-java-app/
├─ src/
│ ├─ Task.java
│ ├─ Status.java
│ ├─ TaskRepository.java
│ ├─ TaskService.java
│ └─ Main.java
├─ data/
│ └─ tasks.json
└─ README.md


- **Task.java** — Represents a single task with id, title, description, and status.
- **Status.java** — Enum for task status: NEW, IN_PROGRESS, DONE.
- **TaskRepository.java** — Handles reading and writing tasks to `tasks.json`.
- **TaskService.java** — Contains business logic such as marking tasks done, searching, and sorting.
- **Main.java** — Provides a console menu interface for user interaction.
- **tasks.json** — Stores task data in a simple JSON-like format.

---

## Features

- Add new tasks.
- Update existing tasks.
- Delete tasks.
- Mark tasks as DONE.
- Search tasks by text in the title or description.
- List all tasks.
- List tasks sorted by status (NEW → IN_PROGRESS → DONE).

---

## How to Run

1. Open the project in your IDE (IntelliJ IDEA, VSCode, or another Java IDE).
2. Ensure **JDK** is installed.
3. Run `Main.java`.
4. Tasks are stored and loaded from `data/tasks.json`.

---

## JSON Storage Format

The `tasks.json` file follows a simple JSON-like structure:

```json
[
  { "id": 1, "title": "Buy groceries", "description": "Milk, eggs, bread", "status": "NEW" },
  { "id": 2, "title": "Finish homework", "description": "Math and Java exercises", "status": "IN_PROGRESS" }
]


