@tag
Feature: Purchase the order from the e-commerce website

  Background:
    Given I landed on the E-commerce page

  @tag1
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName>
    And Checkout <productName> and submit the order
    Then "ThANKYOU FOR THE ORDER." message is diplayed on the confirmation page
    Examples:
      | name                  | password    | productName |
      | rahulshetty@gmail.com | IamKing@000 | ZARA COAT 3 |
      | name2                 | 7           |             |

  @tag2
  Scenario: Title
    Given
    And
    When
    And
    Then
    And
