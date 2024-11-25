Feature: Data Driven Test
  Scenario Outline: verify available users in the database
    Given user calls "/users/@id" endpoint with "<id>"
    When user makes get request
    Then verify status code is 200
    And verify response has schema same as "response-schema.json"

    Examples:
    | id      |
    | 7541070 |
    | 7541069 |
    | 7541068 |
    | 7541067 |

