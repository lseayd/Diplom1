package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun mockBun;

    @Mock
    Ingredient mockIngredient;
    
    @Mock
    Ingredient mockIngredient1;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void checkSetBuns() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }

    @Test
    public void checkAddIngredient() {
        burger.addIngredient(mockIngredient);
        burger.addIngredient(mockIngredient1);
        assertEquals(2, burger.ingredients.size());
        assertEquals(burger.ingredients.get(0), mockIngredient);
        assertEquals(burger.ingredients.get(1), mockIngredient1);
    }

    @Test
    public void checkRemoveIngredient() {
        burger.addIngredient(mockIngredient);
        burger.addIngredient(mockIngredient1);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(mockIngredient1, burger.ingredients.get(0));
    }

    @Test
    public void checkMoveIngredient() {
        burger.addIngredient(mockIngredient);
        burger.addIngredient(mockIngredient1);
        burger.moveIngredient(0, 1);
        assertEquals(mockIngredient1, burger.ingredients.get(0));
        assertEquals(mockIngredient, burger.ingredients.get(1));
    }

    @Test
    public void checkGetPrice() {
        Mockito.when(mockBun.getPrice()).thenReturn(50f);
        Mockito.when(mockIngredient.getPrice()).thenReturn(200f);
        burger.addIngredient(mockIngredient);
        burger.setBuns(mockBun);
        assertEquals(300, burger.getPrice(), 0);
    }

    @Test
    public void checkGetReceipt() {
        Mockito.when(mockBun.getName()).thenReturn("black bun");
        Mockito.when(mockIngredient.getPrice()).thenReturn(200f);
        Mockito.when(mockIngredient.getType()).thenReturn(SAUCE);
        Mockito.when(mockIngredient.getName()).thenReturn("sour cream");
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        String receipt = burger.getReceipt();
        assert receipt.contains("black bun");
        assert receipt.contains("sour cream");
    }

}
