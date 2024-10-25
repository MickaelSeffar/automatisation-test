package java8.ex01;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;
import java8.data.Data;
import java8.data.domain.Pizza;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.*;

public class ExerciceAssertJ {

    @Test
    public void test_is() throws Exception {
        List<Pizza> pizzas = new Data().getPizzas();

        Boolean result = pizzas.stream().anyMatch(p -> p.getPrice() >= 1300);

        assertThat(result).isTrue();
    }

    @Test
    public void test_hasSize() throws Exception {
        List<Pizza> pizzas = new Data().getPizzas();

        List<Pizza> result = pizzas.stream()
                .filter(p -> p.getPrice() >= 1300)
                .collect(toList());

        assertThat(result).hasSize(3);
    }

    @Test
    public void test_hasProperty() throws Exception {
        List<Pizza> pizzas = new Data().getPizzas();

        Pizza result = pizzas.stream().max(Comparator.comparing(Pizza::getPrice)).orElseThrow();

        assertThat(result).hasFieldOrPropertyWithValue("name", "La Cannibale"); // Valide la propriété "name" de la pizza avec AssertJ
    }

    @Test
    public void test_everyItem() throws Exception {
        List<Pizza> pizzas = new Data().getPizzas();

        List<Pizza> result = pizzas.stream()
                .filter(p -> p.getPrice() >= 1300)
                .collect(toList());

        assertThat(result).allMatch(p -> p.getPrice() >= 1300);
    }
}
