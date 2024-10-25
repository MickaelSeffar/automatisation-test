package java8.ex01;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;
import java8.data.Data;
import java8.data.domain.Pizza;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*; // Importer les matchers Hamcrest

public class ExerciceHamcrest {

    @Test
    public void test_is() throws Exception {
        List<Pizza> pizzas = new Data().getPizzas();

        Boolean result = pizzas.stream().anyMatch(p -> p.getPrice() >= 1300);

        assertThat(result, is(true));
    }

    @Test
    public void test_hasSize() throws Exception {
        List<Pizza> pizzas = new Data().getPizzas();

        List<Pizza> result = pizzas.stream()
                .filter(p -> p.getPrice() >= 1300)
                .collect(toList());

        assertThat(result, hasSize(3));
    }

    @Test
    public void test_hasProperty() throws Exception {
        List<Pizza> pizzas = new Data().getPizzas();

        Pizza result = pizzas.stream().max(Comparator.comparing(Pizza::getPrice)).orElseThrow();

        assertThat(result, hasProperty("name", is("La Cannibale")));
    }

    @Test
    public void test_everyItem() throws Exception {
        List<Pizza> pizzas = new Data().getPizzas();

        List<Pizza> result = pizzas.stream()
                .filter(p -> p.getPrice() >= 1300)
                .collect(toList());

        assertThat(result, everyItem(hasProperty("price", greaterThanOrEqualTo(1300))));
    }
}
