# Search engine

## Purpose

Demonstrate base approaches to work with **kotlin flows**. Get familiar with some flow's operators and how to use them
in coroutine context.

## Introduction

Let's imagine that we need to implement online cinema. It provides access to a lot of video content and other data, which
are related to the content. **Asset** - is the base abstract entity, which represents a content. It can have 
three **Type**'s - _VOD_ (video on demand), _LIVE_ (streams) and _CREW_ (people, who participate in movie 
making process). So, for example, user can find a movie, see small description, take a look on cast and crew
and so on.

### Task description

Your task is to implement search engine, which should work with user's input and understand how and where content should be
searched. Engine MUST be able to:

* parse user's input and transform it to the search [Query]
* search mechanism should ignore a letter's case
* by default, matches should be found with **contains** method.
* recognize special character in the text and modifies search approach:
    * **?** - indicate the type of content the user is looking for. Could be placed ONLY at the end of the text. For
      example: _Thor 3?VOD_.
    * **@** - changes the approach to finding matches to **startWith**. Could be placed ONLY on the beginning of the
      text. For example:
      assets -> [Al Pacino, Pacman], query -> _@pac_. Only [Pacman] should be found.
* depending on the request, the mechanism must search among all content or according to the type specified in the
  request
* when user entered _empty_ or _blank_ string, search shouldn't start and error message should be thrown.
* program should work and make requests to the [SearchEngine] until user types "exit" (in any case)
* when program is started, user should see a greetings message
* when program is started, user should see a tip "how to exit program"
* when program is completed, user should see a farewell message

In the end, the program should look like :
![alt text](<./output_example.png>) 

