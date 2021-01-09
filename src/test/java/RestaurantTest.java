import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    public void initializeRestaurant(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){

        restaurant.setCurrentTime(LocalTime.parse("10:30:00"));
        assertTrue(restaurant.isRestaurantOpen());
        restaurant.setCurrentTime(LocalTime.parse("22:00:00"));
        assertTrue(restaurant.isRestaurantOpen());
        restaurant.setCurrentTime(LocalTime.parse("12:26:30"));
        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        restaurant.setCurrentTime(LocalTime.parse("10:29:30"));
        assertFalse(restaurant.isRestaurantOpen());
        restaurant.setCurrentTime(LocalTime.parse("22:01:00"));
        assertFalse(restaurant.isRestaurantOpen());

    }

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }

    //TDD Approach: Select Menu items with name as parameter and returning the value
    @Test
    public void when_items_been_selected_from_the_menu_list_then_total_order_value_should_be_returned() {
        String[] itemSelect = {"French fries", "Vegetable lasagne"};
        int totalOrderValue = restaurant.getOrderValue(itemSelect);
    }
}