Feature: the login fuctionality in KasirDemo

  Background:
    Given user is on login page

    Scenario: user login using valid credential
      When user enter valid username and password
      And user click login button
      Then user must redirect to dashboard page

      Scenario Outline: user login using invalid credential
        When user enter valid <email> and <password>
        And user click login button
        Then system showing error message

        Examples:
          |email            | password |
          |test@email.com   | asasas   |
          |testing@email.com  | btktbbtk |


