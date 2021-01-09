import org.junit.jupiter.api.*;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    LocalTime openingTime, closingTime;

    @BeforeEach
    public void initializeRestaurant(){
        openingTime = LocalTime.parse("10:30:00");
        closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","chennai", openingTime, closingTime);
    }

    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {

        restaurant = service.addRestaurant("Pantry d'or","mumbai", openingTime, closingTime);
        assertEquals("Amelie's cafe", service.findRestaurantByName("Amelie's cafe").getName());
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {

        restaurant = service.addRestaurant("Pantry d'or","mumbai", openingTime, closingTime);
        assertThrows(restaurantNotFoundException.class, ()->service.findRestaurantByName("some restaurant"));

    }

    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("some restaurant"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
}