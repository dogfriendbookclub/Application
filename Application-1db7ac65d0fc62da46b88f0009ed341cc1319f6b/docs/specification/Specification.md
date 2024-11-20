# Application Specification

## Problem Statement
(who?)Internet users (what?)have a lack of choice of television show reviews providers that are transparent and reliable. (why?) This is mainly due to the amount of money television studios spend to advertise within these limited number of sites. (impact?)This problem allows studios to create unreliable reviews of their shows. (hypothesis?) Rotten Potatoes will enable authenticated users to share their reviews online reviews in a simple, transparent, and reliable way. (plan?)This will include the user of user profiles where users can view their reviews and their favorite shows in one convenient place while also contributing in the Rotten Potatoes community.

## Functional Requirements

1. Search by cast, direct, or another attribute that is not the TV show name
2. Submitting Reviews as well as ability to bookmark reviews to read for later
3. Mark Favorites/Favorites List
4. User's Profile
5. Adding Reactions to Reviews
6. Logging/Authentication of Users

## Non-functional

1. Data Integrity
2. Transparency
3. Quality

## Use Cases

### Search Feature

#### Actors
Users & Database API

#### Use Case Goal
To return the search results based on the criteria specified by the user

#### Primary Actor
User

#### Preconditions
The user will have entered a search keyword or keywords in the fixed search bar located at the top of the application.

#### Basic Flow
The user enters a title of a show, the database API takes the search parameter and returns the show which matches the name of the title that the user is searching for.

#### Alternative Flows
The show is not in the database in which the application pops up a screen which says "Show is not found".

## Use Case Diagram

![Use Case Diagram](usecase.png)
