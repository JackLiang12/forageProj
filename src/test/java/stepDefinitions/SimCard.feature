Feature: Sim Card Activation
  As a user of the SIM activation service
  I want to activate SIM cards
  So that I can determine whether the activation is successful

  Scenario: Successful SIM card activation
    Given the SIM card ICCID is "1255789453849037777"
    When I submit the activation request
    Then the activation response should be "Success"
    And the database record should show the SIM card is active

  Scenario: Failed SIM card activation
    Given the SIM card ICCID is "8944500102198304826"
    When I submit the activation request
    Then the activation response should be "Failed"
    And the database record should show the SIM card is not active
