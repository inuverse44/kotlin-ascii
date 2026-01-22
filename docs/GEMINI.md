# Project Context: ASCII 3D Kotlin Logo Renderer

## Overview
This project is a standalone Command Line Interface (CLI) application written in **Kotlin**. It renders a rotating 3D model of the Kotlin Logo into the terminal using ASCII characters and ANSI escape codes for true-color output. It implements a custom software rendering pipeline including geometry definition, matrix rotations, lighting calculation, perspective projection, and Z-buffering.

## Technology Stack
- **Language:** Kotlin (JVM)
- **Math Library:** JOML (`org.joml.Vector3d`, `Matrix3d`) for vector and matrix operations.
- **Build System:** Gradle (Kotlin DSL).

## Architecture & Rendering Pipeline

The application follows a standard real-time rendering loop structure, implemented entirely in software without a GPU API.

### 1. Initialization (`main.kt`)
- Loads geometry (`KotlinLogo`).
- Sets up lighting (`PointLight`) and projection (`ProjectionConfig`).
- Hides the terminal cursor.

### 2. Main Loop
Runs continuously with a frame delay (`16ms` ~ 60fps).
1.  **Update:** Increments rotation angles (`angleX`, `angleY`).
2.  **Render:** Calls `Renderer.renderFrame`.
3.  **Display:** Uses ANSI escape codes to reset the cursor position and print the frame buffer string.

### 3. Rendering Logic (`Renderer.kt`)
Instead of standard rasterization (triangle scan-conversion), this engine uses a **sampling approach**:
1.  **Face Iteration:** Loops through every `Face` in the `Geometry`.
2.  **Sampling:** Iterates over the 2D surface of the Face using normalized coordinates (-1.0 to 1.0) with a defined step (`Configuration.SAMPLING_STEP`).
3.  **Point Transformation:**
    - Checks if the 2D sample point exists within the polygon (`Face.getPoint`).
    - **Rotation:** Rotates the 3D point and normal vector using Matrix math (`Rotation.kt`).
4.  **Lighting:** Calculates diffuse intensity using Lambert's Cosine Law (`PointLight.kt`).
5.  **Shading:** Determines color (Solid or Gradient) at that specific point (`FaceColor.kt`).
6.  **Projection:** Transforms 3D world coordinates to 2D screen integer coordinates (`Projector.kt`).
7.  **Z-Buffering:** Writes to the `FrameBuffer` only if the new pixel is closer to the camera than existing data (Depth Testing).
8.  **Rasterization:** Maps the pixel's brightness and color to an ASCII character and ANSI color code (`AsciiMapper.kt`).

## Project Structure (`src/main/kotlin/com/app/`)

| Package | Description | Key Classes |
| :--- | :--- | :--- |
| **root** | Entry point and loop orchestration. | `main.kt` |
| **config** | Global constants for physics, resolution, and rendering settings. | `Configuration.kt` |
| **geometry** | 3D primitive definitions and vector math extensions. | `Geometry`, `Face`, `Color`, `Vector2d` |
| **geometry.shapes** | Concrete 3D model definitions. | `KotlinLogo` |
| **lighting** | Light source definitions and math. | `PointLight` |
| **math** | 3D math logic (Rotation matrices, Projection logic). | `Rotation`, `Projector`, `ProjectionConfig` |
| **rendering** | The core engine logic, buffer management, and ASCII mapping. | `Renderer`, `FrameBuffer`, `AsciiMapper` |

## Key Concepts

### Coordinate Systems
- **World Space:** 3D coordinates (Double).
- **Face Space:** Local 2D coordinates normalized to [-1, 1] used for sampling points on a polygon.
- **Screen Space:** 2D Integer coordinates mapping to terminal rows and columns.

### Math Conventions
- **Vectors:** Uses `org.joml.Vector3d` for 3D positions and normals.
- **Rotation:** Explicit rotation matrices (Rotation X then Rotation Y).
- **Z-Axis:** +Z is towards the viewer in some contexts, but `Z_OFFSET` implies camera is offset along Z.

### Color & ASCII
- **Color Depth:** Uses 24-bit TrueColor via ANSI (`\u001b[38;2;R;G;Bm`).
- **Brightness Mapping:** Maps 0.0-1.0 brightness to a char density ramp (`.,-~:;=!*#$@`).
- **Color Dimming:** Darker lighting reduces the RGB values, but `MIN_COLOR_BRIGHTNESS` prevents total blackness to preserve the shape's visibility.

## Development Constraints
- **Performance:** Heavily dependent on `SAMPLING_STEP`. Lower values = higher resolution but higher CPU load (O(N^2) per face).
- **Terminal:** Requires a terminal emulator that supports ANSI TrueColor and cursor movement codes.
