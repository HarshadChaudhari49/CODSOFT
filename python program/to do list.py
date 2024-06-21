class TodoList:
    def __init__(self):
        self.tasks = []

    def add_task(self, task):
        self.tasks.append(task)
        print(f"Task '{task}' added.")

    def view_tasks(self):
        if not self.tasks:
            print("No tasks found.")
        else:
            print("Tasks:")
            for index, task in enumerate(self.tasks, start=1):
                print(f"{index}. {task}")

    def update_task(self, index, new_task):
        if index < 1 or index > len(self.tasks):
            print("Invalid task index.")
        else:
            self.tasks[index - 1] = new_task
            print(f"Task {index} updated.")

    def delete_task(self, index):
        if index < 1 or index > len(self.tasks):
            print("Invalid task index.")
        else:
            deleted_task = self.tasks.pop(index - 1)
            print(f"Task '{deleted_task}' deleted.")

def main():
    todo_list = TodoList()

    while True:
        print("\nTODO LIST APPLICATION")
        print("1. Add Task")
        print("2. View Tasks")
        print("3. Update Task")
        print("4. Delete Task")
        print("0. Exit")

        choice = input("Enter your choice: ").strip()

        if choice == '1':
            title = input("Enter task title: ")
            todo_list.add_task(title)

        elif choice == '2':
            todo_list.view_tasks()

        elif choice == '3':
            index = int(input("Enter task index to update: "))
            new_title = input("Enter new task title: ")
            todo_list.update_task(index, new_title)

        elif choice == '4':
            index = int(input("Enter task index to delete: "))
            todo_list.delete_task(index)

        elif choice == '0':
            print("Exiting program. program executed")
            break

        else:
            print("Invalid choice. Please try again.")

if __name__ == "__main__":
    main()
