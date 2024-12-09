# SuperScoreBoard

For all your up to the minute scores from around the globe.

1. ## How to set up and run the project.

    - The project can be opened in Android Studio. 
    - Select File - Open - Use the File explorer to find the project root folder
    - You may get a security message asking if you trust the project.
    - The project will then sync gradle automatically.
    - Once this has completed, you should be able to build and run the project onto your android device / emulator.

2. ## Features implemented and known limitations.
   
    ### Features

   - Fixture List, Match Details and Club Badge retrieval from github endpoint.
   - Navigation from Fixture List to Match Details upon user tap on any Fixture
   - Navigate back to fixtures list to view details of another Match (it's always the same one)
   - Adaptive Layout for mobile and tablet in portrait and landscape.
   - Dark Mode support
   
   - Modularised gradle code base, prompting code reuse, quick build times, and easy scaling.
   - Android Library Modules only used when absolutely necessary enabling simpler unit testing for business logic
   - Jetpack Compose for modern android UI development. 
   - Lint and Detekt baselines for code quality. 
   - Custom Gradle task for installing pre-push hook, to ensure code quality defects are actioned prior to reaching the remote.
   - Unidirectional Data Flow solution in View Models, correctly observing android lifecycle
   
    ### Limitations 
    
    - Runs on Android 12L and upwards only.
    - The fixtures list isn't cached. In a real world example it probably wouldn't change too much once received and so we could cache it locally to reduce network calls. 
    - The match details are always of the same match. However, the project is written to use the fixture id to get the match details (MatchDetailsApi)
    - While error handling has been accommodated, feedback to the user about any problems is non existent. We should provide adequate feedback to the user for any issues encountered. 
    - Accessibility has been ignored so far.

3. ## Any assumptions made.
    
    - All data is "correct". eg
       - there are no checks in the code to ensure that 2 teams are present in each fixture. 
       - there are 11 players in a line up.
       - player names are ready for presentation without manipulation. (Salah's surname).
    - The dates and times mentioned will always be delivered in the same format. (and the same timezone)
    - Upcoming matches are scheduled for today.

4. ## Next Steps

   - App icon / Splash / Branding
   - Filter the Fixtures list. 
   - Introduce Custom Gradle Plugins to reduce boilerplate when adding new modules / features. 
   - Introduce a BaseViewModel to encapsulate the uni-directional data flow solution into a single place, reducing more boiler plate.
   - UI / Integration / Screenshot tests.
