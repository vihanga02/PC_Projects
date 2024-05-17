package Facade;

// Interface for different types of menus
interface Menu{}

// Concrete classes for specific types of menus
class VegMenu implements Menu{}
class Both implements Menu{}
class NonVegMenu implements Menu{}

// Interface for different types of hotels (restaurants)
interface Hotel {
    Menu getMenu();
}

// Concrete classes for specific types of restaurants implementing the Hotel interface
class NonVegRestaurant implements Hotel {
    @Override
    public Menu getMenu() {
        return new NonVegMenu();
    }
}

class VegRestaurant implements Hotel {
    @Override
    public Menu getMenu() {
        return new VegMenu();
    }
}

class VegNonBothRestaurant implements Hotel {
    @Override
    public Menu getMenu() {
        return new Both();
    }
}

// Interface for HotelKeeper which provides methods to get different menus
interface HotelKeeper {
    public VegMenu getVegMenu();
    public NonVegMenu getNonVegMenu();
    public Both getVegNonMenu();
}

// Implementation of HotelKeeper interface which acts as a facade
class HotelKeeperImplementation implements HotelKeeper {
    @Override
    public VegMenu getVegMenu() {
        VegRestaurant vegRestaurant = new VegRestaurant();
        return (VegMenu) vegRestaurant.getMenu();
    }

    @Override
    public NonVegMenu getNonVegMenu() {
        NonVegRestaurant nonVegRestaurant = new NonVegRestaurant();
        return (NonVegMenu) nonVegRestaurant.getMenu();
    }

    @Override
    public Both getVegNonMenu() {
        VegNonBothRestaurant vegNonBothRestaurant = new VegNonBothRestaurant();
        return (Both) vegNonBothRestaurant.getMenu();
    }
}

// Main application class to demonstrate the use of facade
public class Application {
    public static void main(String[] args){
        HotelKeeper hotelKeeper = new HotelKeeperImplementation();

        // Using the facade to get different types of menus
        VegMenu vegMenu = hotelKeeper.getVegMenu();
        NonVegMenu nonVegMenu = hotelKeeper.getNonVegMenu();
        Both both = hotelKeeper.getVegNonMenu();
    }
}
