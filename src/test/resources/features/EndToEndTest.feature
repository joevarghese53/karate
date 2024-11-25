Feature: End to End test
  Scenario: verify user can perform crud operations
    Given user calls "/users" endpoint
    And set header "Content-Type" to "application/json"
    And set header "Authorization" to "Bearer @token"
    And set request body from file "create-user.json" using pojo
    When user makes post request
    Then verify status code is 201
    And verify response has body same as request
    And verify response has schema same as "response-schema.json"
    And store the "id" from response into "user.id"
    Given user calls "/users" endpoint
    When user makes get request
    Then verify status code is 200
    And store the "id" of first two users from response into "user1.id" and "user2.id"
    Given user calls "/users/@id" endpoint with "user1.id"
    When user makes get request
    Then verify status code is 200
    And verify response has schema same as "response-schema.json"
    Given user calls "/users/@id" endpoint with "user1.id"
    And set header "Content-Type" to "application/json"
    And set header "Authorization" to "Bearer @token"
    And set request body from file "update-user.json" using pojo
    When user makes put request
    Then verify status code is 200
    And verify response has body same as request
    And verify response has schema same as "response-schema.json"
    Given user calls "/users/@id" endpoint with "user2.id"
    And set header "Authorization" to "Bearer @token"
    When user makes delete request
    Then verify status code is 204