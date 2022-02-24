# SimpleEcommerceApp
This is a simple Android application with three screens developed using Jetpack Compose.

## Screen1 - HomeScreen
1. This screen lists the store info(name of store, open-close time,location, image) and products available in the store.
2. User can add products to cart by incrementing the quantity value of the product.
3. There is a maximum selctable quantity for each product and user can't select more than that.
4. Clicking on the order summary button navigates to order summary screen.

## Screen2 - OrderSummaryScreen
1. This screen list the products selected by the user.
2. The total amount will be listed at the bottom.
3. User have to mandatorly fill in the delivery address.
4. Clicking on cormfim order button places and the order and navigates to order success screen.

## Screen3 - OrderSuccessScreen
1. This screen is a confirmation for user on successful order placement
2. Clicking on okay button navigates to home screen.

# Libraries
1. Jetpack Compose
2. Hilt
3. Coroutine
4. Coil
5. Mockito
6. Junit
7. Compose Navigation
8. Google Truth
9. Google Gson

# Test Cases
  ## UI tests
  
  ![This is an image](/screenshots/Testcases/UiTestCases.png)

  ## Unit tests
  
  ![This is an image](/screenshots/Testcases/UnitTestCases.png)
 

