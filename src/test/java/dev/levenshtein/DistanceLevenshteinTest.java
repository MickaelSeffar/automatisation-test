package dev.levenshtein;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DistanceLevenshteinTest {

    private DistanceLevenshtein distanceLevenshtein;

    @BeforeEach
    public void setUp() {
        distanceLevenshtein = new DistanceLevenshtein();
    }

    @Test
    @Tag("CalculDistance")
    public void testDistanceLevenshtein() throws AppException {
        String mot1 = "kitten";
        String mot2 = "sitting";
        int distanceAttendue = 3;
        int distanceCalculee = distanceLevenshtein.calculerDistance(mot1, mot2);
        assertEquals(distanceAttendue, distanceCalculee);
    }

    static Object[][] testData = {
            {"chat", "chats", 1},
            {"machins", "machine", 1},
            {"java", "jee", 3}
    };

    @Test
    @Tag("CalculDistance")
    public void testDistanceLevenshteinAdditional() throws AppException {
        for (Object[] data : testData) {
            String mot1 = (String) data[0];
            String mot2 = (String) data[1];
            int distanceAttendue = (int) data[2];
            int distanceCalculee = distanceLevenshtein.calculerDistance(mot1, mot2);
            assertEquals(distanceAttendue, distanceCalculee, "Erreur sur les mots : " + mot1 + " et " + mot2);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/dev/levenshtein/DistanceLevenshtein.csv", numLinesToSkip = 1, delimiter = ';')
    @Tag("CalculDistance")
    public void testDistanceLevenshtein(String mot1, String mot2, int distanceAttendue) throws AppException {
        int distanceCalculee = distanceLevenshtein.calculerDistance(mot1, mot2);
        assertEquals(distanceAttendue, distanceCalculee);
    }

    @Test
    @Tag("ValidationEntree")
    public void testPremierParametreNull() {
        AppException thrown = assertThrows(AppException.class, () -> {
            distanceLevenshtein.calculerDistance(null, "mot");
        });
        assertEquals("le premier paramètre ne peut être null ou vide", thrown.getMessage());
    }

    @Test
    @Tag("ValidationEntree")
    public void testPremierParametreVide() {
        AppException thrown = assertThrows(AppException.class, () -> {
            distanceLevenshtein.calculerDistance("", "mot");
        });
        assertEquals("le premier paramètre ne peut être null ou vide", thrown.getMessage());
    }

    @Test
    @Tag("ValidationEntree")
    public void testSecondParametreNull() {
        AppException thrown = assertThrows(AppException.class, () -> {
            distanceLevenshtein.calculerDistance("mot", null);
        });
        assertEquals("le second paramètre ne peut être null ou vide", thrown.getMessage());
    }

    @Test
    @Tag("ValidationEntree")
    public void testSecondParametreVide() {
        AppException thrown = assertThrows(AppException.class, () -> {
            distanceLevenshtein.calculerDistance("mot", "");
        });
        assertEquals("le second paramètre ne peut être null ou vide", thrown.getMessage());
    }
}
