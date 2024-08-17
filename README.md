# **Map Editor (Grid Painter) Project**

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Grid Painter](https://img.shields.io/badge/Grid%20Painter-0078D4?style=for-the-badge&logo=graphics&logoColor=white)

## **Project Overview**

Welcome to the **Map Editor (Grid Painter)**, a Java-based project designed to create and manipulate grid-based maps through simple keyboard controls. This tool allows users to paint cells within a grid, save their designs, and reload them later for further editing.

---

## **Features**

### 🎨 **Grid-Based Painting**
- **Cursor Movement:** Use the arrow keys to navigate the grid.
- **Paint Cells:** Press the `Space` key to paint or fill the selected cell.
- **Clear Cells:** Press the `C` key to clear the entire grid.

### 💾 **Save and Load Functionality**
- **Save Design:** Press the `S` key to save your painted cells to a file.
- **Load Design:** Press the `L` key to load the last saved design from a file.

### 🛠️ **Simple Graphics Library**
- Built using a custom **Simple Graphics Library** developed by Codeforall_, offering a lightweight and easy-to-use framework for creating and managing grid-based graphics.

### 📄 **File I/O**
- **BufferedReader/BufferedWriter:** Utilized for efficient reading and writing of grid data to and from files, ensuring quick save and load operations.

---

## **How to Use**

1. **Launch the Application:**
   - Run the program to open the grid editor window.

2. **Navigate and Paint:**
   - Use the arrow keys to move the cursor around the grid.
   - Press `Space` to paint the cell at the cursor's position.

3. **Clear the Grid:**
   - Press `C` to clear all painted cells and start fresh.

4. **Save Your Work:**
   - Press `S` to save the current grid state to a file.

5. **Load a Previous Design:**
   - Press `L` to load the last saved grid design and continue editing.

---

## **Installation Instructions**

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/Grid-Painter.git
   ```

2. **Navigate to the Project Directory:**
   ```bash
   cd Grid-Painter
   ```

3. **Run the Application:**
   - If using an IDE like IntelliJ IDEA or Eclipse, import the project and run the `Main.java` file.
   - Alternatively, compile and run using the command line:
     ```bash
     javac Main.java
     java Main
     ```
     
