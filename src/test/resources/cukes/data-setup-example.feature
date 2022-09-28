Feature: An account can be setup to have books already without the UI

  Scenario Outline: The users book collection can be setup without the UI
    Given browser is already logged in as MyBooks Maya
    And current account already has <ownedBooks> books
    When Profile page gets visited
    And MyBooks table has <pageCount> pages
    And MyBooks table is set to show <rowsPerPage> rows
    And MyBooks table is set to show page <currentPage>
    Then MyBooks table shows <shownBooks> books on the current page

    Examples:
      | ownedBooks | rowsPerPage | currentPage | pageCount | shownBooks |
      |          0 |           5 |           0 |         1 |          0 |
      |          1 |           5 |           1 |         1 |          1 |
      |          4 |           5 |           1 |         1 |          4 |
      |          5 |           5 |           1 |         1 |          5 |
      |          6 |           5 |           1 |         2 |          5 |
      |          6 |           5 |           2 |         2 |          1 |
      |          8 |           5 |           1 |         2 |          5 |
      |          8 |           5 |           2 |         2 |          3 |
