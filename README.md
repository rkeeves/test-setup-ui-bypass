# test-setup-ui-bypass

How to setup initial conditions for tests without UI, but with direct calls to the backend instead.

This project uses ToolsQA's tiny [BookStore app](https://demoqa.com/books). Feel free to take a look at the app first!

## Getting started

You can run the tests via:

```shell
mvn clean verify
```

This will:
- start the tests in parallel
- each of the parallels will pick up a feature file
- each of the parallels will open a headless browser (thread local)
- when everything is finished a report will be generated
- to view the report open `target/test-report/index.html` in your browser

The tests simply login and harass a paginated datatable.
Nothing crazy or deep.
The whole point of this example is to set up test initial state via backend calls.

## Overview

Setting up initial conditions for e2e tests can be painful via UI.

For example, take a look at ToolsQA's tiny [BookStore app](https://demoqa.com/books).

What if you wanted to test a certain scenario which requires 5 books already added to the account?

The test must somehow add 5 books, aka initialize the state. What options do we have for initializing the state?

In the worst case we can do it via Selenium.
It is slow, and couples your initialization to the UI.
Aka if devs change the UI for adding new books to an account, then this change will also break all other tests,
which rely on this portion of the UI to setup their initial conditions.

Another way is to prepopulate things (via SQL etc.) on the test server at startup,
and throw the test server away when the tests are done. This is a bit more robust, 
but it requires great care, maintenance, and a bit of resources.

The approach - which was used in this example - is to setup initial conditions via directly calling the backend, 
and only then conduct the test via Selenium.
This means less interactions with the webdriver.
This also means, that - for example - the dumb pagination tests I wrote don't directly use the UI for adding new Books.
Aka if the 'add new book' part of the UI gets a breaking change, it won't break other tests.

### Technologies

This project uses Junit 5 with Cucumber.

The project also uses Spring Boot for context and other quality of life things such as snakeyml based configuration.

I must admit, all of this bloat is unnecessary.
You can yeet out most of these things.
I just wanted some cucumber without `@RunWith`.

### Shared Resources

User creds in plain text in the yml?!
Yes, because the app is public, and you can't really do anything with an account really.
Also, adding secrets etc. would just greatly obfuscate things.
This tiny example is just about initial condition setup. Nothing more nothing less.

There's a more important thing though. In this example we DO NOT create a fresh account for each test.
We use predefined accounts instead (which must be setup manually).
The problem with this is that when you do parallel tests, these accounts are shared resources.
Aka if both test 1 and test 2 work with account 'Ann' then concurrent modification will arise during parallel execution.

In this example I simply used different 'heroes' for different features.
Tests are parallelized in such a way that each thread picks up a feature file and executes scenarios sequentially.
You have some more advanced tools to work around concurrency, but I didn't want to go deeper into this, 
because this example is not about this topic.
