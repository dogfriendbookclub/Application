# Entities in the Application

## User Class

The user is an entity in the application which tracks account information on shows they have favored and reviewed. This entity also allows for people to write reviews on TV Shows. 

## Database API

The API is the interface which securely accesses the database of shows allowing the program to have the sufficient information that the user is looking for when viewing a show. It also allows the search engine to function as an entity

## Search Engine

The search engine allows for users to search for specific shows by taking user input and passing the input to the API to retrieve shows that match the user input. 

## Review Class

The purpose of this class is to keep a list of user reviews for a show and store the information in order to allow users to see how the community within the application feels about a certain show, episode or season. 

## Shows class

This class will have all the information from the database stored onto a show object. Each object will have information containing actors, directors, years aired and a synopsis of the show. 

## Screen entities 

These will be the display for all of the shows. We have three separate screens that inlcude Home screen, Search Results screen and Show specific screens. The Home screen is used as the main page users will see when accessing the application, it will display shows and categories of shows based on gneres. The search result screen will be shown when a user inputs a search for a specific title. The show specific is the main page a user will see when they want to view information about a specific show. 

##
