Feature: Authentication and login can be setup without the UI

  Scenario Outline: The browser can login without any ui interaction
    Given browser is already logged in as <user>
    When Profile page gets visited
    Then Profile page shows details for <user>
    Examples:
      | user        |
      | Login Logan |