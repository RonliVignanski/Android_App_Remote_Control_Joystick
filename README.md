# Android App - Remote Control Joystick
---
The app provides a GUI (joystick and sliders) for remote control of the aircraft which displayed on the FlightGear simulator.</br>
Android minimum SDK - API 16 Android 4.1 (Jelly Bean)</br>
Written in Kotlin</br>
App architecture - MVVM

## FEATURES

- **The Connection to the FlightGear:**</br> 
The user has to enter correct IP and port in order to connect. 
- **Flight controllers:**</br>
 The sliders:</br> 
-Rudder slider: controls rotation about the vertical axis of an aircraft.</br>
-Throttle slider: controls the speed of the airplane, which means the amount of fuel provided to the engine with which it is associated.</br>
The joystick:</br>
controls the aileron and elevator.</br>
-Aileron: control movement about the longitudinal axis of an aircraft.</br>
-Elevator: controls the height of the airplane.</br>

## Installation instructions:
1. Download and install 'FlightGear' 2020.3.6 application from: https://www.flightgear.org/
2. Open FlightGear, press the “Settings” tab and copy the following line under Additional Settings:
 --telnet=socket,in,10,127.0.0.1,6400,tcp
3. Download and install Android Studio JetBrains's IDE from: 
   https://developer.android.com/studio?gclid=Cj0KCQjwh_eFBhDZARIsALHjIKfWSf3koFU4l6Iz0GpSNmvXtZjP2rA-btB-nZAtZ8bHrY61NgAzX1UaApWKEALw_wcB&gclsrc=aw.ds
4. Open a new project with the name – “RemoteControlJoystick”, change the package name to “com.myapp.remotecontroljoystick”.
5. Install AVD manager, which is an emulator of an android on your computer: Go to tools -> AVD manager -> create virtual device
6. Download the src folder from GitHub: https://github.com/eyalhazi/Android-App-Remote-Control-Joystick.git
7. Extract the three folders: model, viewModel, view from src and insert them to the project at – 
	app-> java -> com.myapp.remotecontroljoystick
8. Extract the “AndroidManifest.xml” file from src folder and copy its content to –
app -> manifests
9. Extract the “activity_main.xml” file from src folder and copy its content to –
app -> res -> layout -> activity_main -> activity_main.xml
10. Extarct the “air” photo from src folder and copy it to – app -> res -> drawable
11. Press the “play” button at the FlightGear app. Run the app from the IDE on the emulator.
12. Insert the right IP addr and the right port(6400) for connecting the FlightGear from the app.


Explanation and demonstrarion video - 
https://youtu.be/t6FotjS7S9g
