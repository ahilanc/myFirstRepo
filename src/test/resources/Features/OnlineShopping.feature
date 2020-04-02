@web @DemoTest
Feature: Demo - Online Shopping Site

  Scenario Outline: Order T-Shirt and verify in Order History
    Given I login into Online shopping website
    When I click on T-Shirts menu
    And I choose to buy first item
    And I proceed to checkout from Cart
    And I check personal details on checkout page
    And I select my same delivery address and tick the terms and conditions
    And Select the payment method as 'Pay by check'
    When I click on 'I confirm my order' button
    Then I see success message <successMessage>
    And Verify order reference in Order History

    Examples:
    |successMessage                    |
    |Your order on My Store is complete|

  Scenario Outline: Update Personal Information (First Name) in My Account
    Given I login into Online shopping website
    When I press 'My Personal Information' button
    And I enter <firstName> in my personal information
    And I click on Save button
    Then I should see the success message <successMessage>

    Examples:
    | firstName | successMessage                                          |
    | AhilanC   | Your personal information has been successfully updated |
