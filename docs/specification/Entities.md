# Entities in the Application

## User Class

The user is the main input source to the system. The User can search the system and write reviews on TV Shows. The User entity tracks account information on shows that have been favored and reviewed.


## Database API

The API accesses the database which allows queries to be made. It also allows the search engine to function as an entity by returning results.


## Search Engine

This entity allows users to search for specific objects by taking user input and passing the input to the API to retrieve results that match the given input.


## Review Class (work on this)

The Review Class keeps and stores a list of reviews for a show and stores the information in order to allow users to see how the community within the application feels about a certain show, episode or season.


## Shows class

This class stores the information from the database onto a show object. Each object contains the attributes actors, directors, years aired and a synopsis of the show. The class displays its information onto the screen when a user selects a specific show.


## Screen entities

Our application has three separate screens; Home screen, Search Results screen, Show Specific screen. The Home screen displays shows and genres. The Home screen is the main page users see when accessing the application.The Search Result screen shows search results for a given term. When a user selects a show, the Show Specific screen displays information about a particular show.


## MySQL datatable

The SQL data table contains immutable information of each show. The MySQL data table will fill in the attributes of all the classes