# Non-functional Requirements

## Data Integrity
Our database will use constraints to maintain accurate relationships between data. We will implement access controls to limit data modification to authorized personnel only, and encrypt sensitive information to protect against unauthorized access. These measures collectively ensure that the data remains accurate, reliable, and secure.
## Transparency
To ensure transparency, our application will provide clear, accessible information about how user reviews are aggregated and displayed. We will implement a transparent review calculation methodology, which will be documented and made available to all users. Also, we will include a feature where users can view the source of their own reviews and track the changes made to the review data. We will also maintain an open channel for user inquiries regarding review processes and calculation methods.
## Quality
Quality is our third non-functional requirement, we will ensure our application will be tested for performance benchmarks, maintain high reliability and availability, and ensure a user-friendly and accessible interface. Our application will be built with clean, maintainable code, robust security measures, and scalability to handle growth. We will implement effective error handling and recovery, and adjust to relevant industry standards and regulations. Continuous improvement will be driven by user feedback and performance metrics to keep the application up-to-date and effective.
***
# Use Cases

## Use case 1: Search function

### Actors
1. **User (Searcher)**: The individual who uses the search bar to find TV shows.
2. **System**: The application that processes search queries and displays results.

### Use case goal

The goal of this use case is to enable users to search for TV shows by entering keywords into the search bar, retrieve relevant search results, and access detailed information about the selected TV show.

### Primary Actor

User (Searcher)

### Preconditions

1. The user is on the TV-show review application's homepage or any page where the search bar is accessible.
2. The search bar is visible and functional.

### Basic flow

1. The user types a search query into the search bar.
2. The system processes the query and performs a search in the TV show database.
3. The system generates and displays a list of TV shows that match the search criteria.
4. The user selects a TV show from the search results.
5. The system navigates to the selected TV show’s page, displaying detailed information and user reviews.

### Alternative flows

#### Alternative flow 1: No Matches Found

1. The user enters a search query into the search bar.
2. The system processes the query but finds no TV shows that match the search criteria.
3. The system displays a message stating "No results found" and suggests possible actions such as modifying the search terms or returning to the homepage.
4. The user either refines the search query or navigates to another part of the application.

#### Alternative flow 2: Query Contains Special Characters or Errors

1. The user enters a search query with special characters or unusual formatting.
2. The system detects that the query contains invalid characters or errors.
3. The system displays an error message prompting the user to correct the query and try again.
4. The user revises the search query as suggested and resubmits the search.

## Use case 2: Submit Review

### Actors
1. **User (Reviewer)**: The individual who writes and submits a review for a TV show.
2. **System**: The application that handles the review submission, validation, and storage.

### Use case goal

The goal of this use case is to allow users to write and submit their reviews for TV shows, ensuring that their feedback is accurately recorded and displayed on the TV show’s page.

### Primary Actor

User (Reviewer)

### Preconditions

1. The user is registered and logged into the application.
2. The TV show for which the review is to be submitted is available in the system’s database.

### Basic flow

1. The user navigates to the page of the TV show they want to review.
2. The user selects the option to write a review.
3. The user enters a detailed review text, and assigns a rating.
4. The user submits the review.
5. The system validates the review for completeness and adherence to review guidelines (e.g.no links, no prohibited/spoilers content).
6. The system stores the review in the database.
7. The system updates the TV show’s aggregate rating and displays the new review on the TV show’s page.
8. The user receives a confirmation that their review has been successfully submitted.

### Alternative flows

#### Alternative flow 1: Review Submission Failure

1. The user submits the review.
2. The system encounters an issue during submission (e.g., network error, database issue).
3. The system displays an error message informing the user of the failure and prompts them to try again later.
4. The user can either attempt to resubmit the review or cancel the submission.

#### Alternative flow 2: Review Validation Error

1. The user submits the review.
2. The system detects that the review does not meet validation criteria (e.g., review contains a link, or inappropriate/spoiler content).
3. The system displays a validation error message indicating the specific issue with the review.
4. The user corrects the review based on the feedback and resubmits it.
5. The system validates and processes the corrected review as per the basic flow.


## Use case 3: Mark Favorites / Favorites List

### Actors
1. **User (Favorites Manager)**: The individual who marks TV shows as favorites and manages their favorites list.
2. **System**: The application that handles the marking of favorites, updating the list, and displaying the favorites.

### Use case goal

The goal of this use case is to allow users to mark TV shows as favorites and manage their favorites list, enabling them to easily access and keep track of their preferred TV shows.

### Primary Actor

User (Favorites Manager)

### Preconditions

1. The user is registered and logged into the application.
2. The TV show that the user wants to mark as a favorite is available in the system’s database.

### Basic flow

1. The user navigates to the page of the TV show they wish to mark as a favorite.
2. The user selects the option to mark the TV show as a favorite (e.g., clicking a "heart" icon).
3. The system updates the user's favorites list to include the selected TV show.
4. The system provides visual confirmation that the TV show has been added to the favorites list (e.g., the icon changes to indicate the show is now a favorite).
5. The user can navigate to their favorites list to view and manage all their favorite TV shows.
6. The user can remove a TV show from the favorites list by selecting the option to unmark it as a favorite.

### Alternative flows

#### Alternative flow 1: Marking a TV Show as Favorite Fails

1. The user selects the option to mark a TV show as a favorite.
2. The system encounters an issue during the update (e.g., network error, database issue).
3. The system displays an error message informing the user that the action could not be completed.
4. The user can try marking the TV show as a favorite again or contact support for assistance.

#### Alternative flow 2: Managing the Favorites List

1. The user navigates to their favorites list.
2. The system displays the list of TV shows marked as favorites.
3. The user selects a TV show to view its details or remove it from the favorites list.
4. If the user chooses to remove a TV show from the favorites list, the system updates the list accordingly and provides visual confirmation of the change.
5. The user can continue to manage their favorites list or return to browsing other TV shows.



## Use case 4: Add Reactions to Reviews

### Actors
1. **User (Reactor)**: The individual who reacts to reviews with likes, dislikes, or other reaction types.
2. **System**: The application that processes and records reactions to reviews.

### Use case goal

The goal of this use case is to allow users to add reactions (such as likes or dislikes) to reviews posted by others, helping to gauge the sentiment and usefulness of the reviews.

### Primary Actor

User (Reactor)

### Preconditions

1. The user is registered and logged into the application.
2. The user is viewing a review for a TV show that they want to react to.

### Basic flow

1. The user navigates to the TV show’s page and views the list of reviews.
2. The user selects a review to which they want to react.
3. The system displays available reaction options (e.g., like, dislike, helpful, not helpful).
4. The user chooses a reaction option.
5. The system processes the reaction and updates the review's reaction count.
6. The system provides visual feedback confirming the user's reaction (e.g., a visual indicator of the selected reaction).
7. The user's reaction is recorded and displayed alongside the review’s reaction statistics.

### Alternative flows

#### Alternative flow 1: Reaction Submission Failure

1. The user selects a reaction option for a review.
2. The system encounters an issue during the reaction submission (e.g., network error, database issue).
3. The system displays an error message informing the user that the reaction could not be processed.
4. The user can attempt to submit the reaction again or contact support for assistance.

#### Alternative flow 2: User not logged-in

1. The user selects a reaction option for a review.
2. The system detects that the user is not a verified user.
3. The system displays a message informing the user that they need to be logged in.
4. The user can either sign-up or cancel the review reaction.

## Use case 5: User Login / Authentication

### Actors
1. **User (Login)**: The individual who wants to access their account by logging into the application.
2. **System**: The application that handles the authentication process, including verifying credentials and managing user sessions.

### Use case goal

The goal of this use case is to allow users to securely log in to the application and authenticate their identity to access personalized features and content.

### Primary Actor

User (Login)

### Preconditions

1. The user has a registered account with valid credentials (username/email and password).
2. The login page is accessible and functional.

### Basic flow

1. The user navigates to the login page of the application.
2. The user enters their username/email and password into the provided fields.
3. The user selects the option to log in.
4. The system verifies the provided credentials against the stored user data.
5. If the credentials are valid, the system creates a user session and redirects the user to their account dashboard or the last accessed page.
6. The system displays a confirmation message or greeting indicating successful login.
7. If the credentials are invalid, the system displays an error message prompting the user to check their credentials and try again.

### Alternative flows

#### Alternative flow 1: Invalid Credentials

1. The user enters their username/email and password.
2. The system verifies the credentials and finds them to be invalid.
3. The system displays an error message stating "Invalid username or password."
4. The user is prompted to re-enter their credentials or use the "Forgot Password" link to recover their account.

#### Alternative flow 2: Forgotten Password

1. The user selects the "Forgot Password" option on the login page.
2. The system prompts the user to enter their registered email address.
3. The user provides their email address and submits the request.
4. The system sends a password reset link to the provided email address.
5. The user follows the link, creates a new password, and submits the reset request.
6. The system updates the user’s password and allows the user to log in with the new password.

#### Alternative flow 3: Account Locked

1. The user attempts to log in with incorrect credentials multiple times.
2. The system detects multiple failed login attempts and temporarily locks the account for security reasons.
3. The system displays a message informing the user that their account is locked and provides instructions for unlocking it (e.g., contacting support or waiting for a cooldown period).
4. The user can follow the instructions to unlock their account or contact support for further assistance.

## Use case 6: User Profile Management

### Actors
1. **User (Profile Manager)**: The individual who accesses and manages their profile, including their favorite TV shows and liked reviews.
2. **System**: The application that displays the user’s profile information and allows interaction with their favorites and bookmarks.

### Use case goal

The goal is to allow users to view and manage their profile, including accessing and updating their list of favorite TV shows, bookmarks, and reviews they have liked.

### Primary Actor

User (Profile Manager)

### Preconditions

1. The user is registered and logged into the application.
2. The profile page is accessible and functional.

### Basic flow

1. The user navigates to their profile page.
2. The system displays the user’s profile information, including sections for favorites, bookmarks, and liked reviews.
3. The user selects the section they wish to view or manage.
4. For the favorites section, the system displays a list of TV shows the user has marked as favorites.
5. For liked reviews, the system displays reviews the user has expressed a positive reaction to.
6. The user can interact with the lists to view details, remove items from favorites or bookmarks, or update their likes.
7. The system updates the profile information accordingly and provides confirmation of any changes.

### Alternative flows

#### Alternative flow 1: Profile Information Not Loading

1. The user navigates to their profile page.
2. The system encounters an issue and fails to load the profile information (e.g., network error, database issue).
3. The system displays an error message informing the user that their profile information could not be retrieved.
4. The user can try reloading the page or contact support for assistance.

#### Alternative flow 2: No Favorites or Bookmarks

1. The user navigates to their profile page.
2. The user selects the favorites or bookmarks section.
3. The system detects that there are no items in the selected section.
4. The system displays a message indicating that there are no favorites or bookmarks and offers options to explore TV shows or reviews to add to these lists.


