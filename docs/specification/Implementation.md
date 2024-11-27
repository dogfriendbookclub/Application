# Implementation of Use Cases

## Phase 1
### Enable login function
- The app must  allow users to securely log in to the application and authenticate their identity to access personalized features and content. Our solution to this is simple. When a user opens the app, they are prompted to enter their user name and password, which will then prompt them to the app’s home page 
- FOR TESTING: Enter any values to log in. However, please be sure to keep the initial username and password in order to log back in.

### Enable search function
- Once on the home page, the user will have the option to click on various suggestions provided by the system.
They are able to click on any of the images and be routed to that show's information screen.
At the time of this deliverable, the show screen is not yet populated, but the console will provide the user with the show ID sent through the API.
Additionally, the user can enter a show's title in the fixed search bar located at the top of the page to obtain the same result.

## Phase 2

### User submits review
- Users can access shows and submit reviews to the show as a whole, a season, or a specific episode. This review is maintained in our database
  and is accessible at will through the user profile.
- FOR TESTING: Navigate to the TV show (overall, season, or episode) of your choice and click on the review text box.
- 
### Users must be able to navigate back to the home page
- Once in navigation, the users must be able to click the home button to restart at the home page
- FOR TESTING: Navigate through the various pages and utilize the home button

## Phase 3

### Users have access to individual season information
- Once in the show overview, the user can utilize the season selector to access a specific season for that show. This also makes use of our TMDB API to pull up content.

### Users have access to individual episode information
- Similar to the season information milestone, this will require the user to be in the show overview or season overview for a show. Once in one of these two screens, the user can utilize the episode selector to bring up specific episodes from the API at will.
