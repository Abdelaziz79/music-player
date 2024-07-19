# Music Player Project

This project is a simple music player application developed using JavaFX. The music player supports various audio formats and offers a user-friendly interface for playing, pausing, and managing music files.

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)

## Features

- Play audio files.
- Pause and resume playback.
- Simple and intuitive user interface.
- Support for multiple audio formats.
- Volume control.
- Playlist management.
- Progress bar to track playback.

## Requirements

- Java Development Kit (JDK) 11 or higher.
- JavaFX SDK.

## Installation

1. **Clone the repository:**

   ```sh
   git clone https://github.com/Abdelaziz79/music-player.git
   ```

2. **Navigate to the project directory:**

   ```sh
   cd music-player
   ```

3. **Download and install JavaFX SDK:**

   Follow the instructions on the [JavaFX official website](https://openjfx.io/) to download and set up JavaFX.

4. **Set up your IDE:**

   If you are using an IDE like IntelliJ IDEA or Eclipse, configure it to use the JavaFX SDK. Refer to your IDE's documentation for detailed setup instructions.

## Usage

1. **Run the application:**

   You can run the application from your IDE by running the `MusicPlayer` class located in the `src/musicplayer` directory.

   Alternatively, you can compile and run the application from the command line:

   ```sh
   javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml src/musicplayer/MusicPlayer.java
   java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml musicplayer.MusicPlayer
   ```

2. **Load and play music files:**

   Use the interface to open music files and control playback.

## Project Structure

```
music-player/
├── src/
│   └── musicplayer/
│       ├── MusicPlayer.java
│       ├── FXMLDocument.fxml
│       ├── FXMLDocumentController.java
├── README.md
└── ...
```

- `MusicPlayer.java`: The main entry point of the application.
- `FXMLDocument.fxml`: The FXML file defining the user interface layout.
- `FXMLDocumentController.java`: The controller class handling the user interface logic.

