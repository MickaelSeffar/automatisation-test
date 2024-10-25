package dev.service;

import dev.dao.IPlatDao;
import dev.entite.Plat;
import dev.exception.PlatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlatServiceVersion1Test {

    @Mock
    private IPlatDao platDao;

    private PlatServiceVersion1 platService;

    @BeforeEach
    void setUp() {
        platService = new PlatServiceVersion1(platDao);
    }

    @Test
    void listerPlats_CasNominal() {
        // Arrange
        Plat plat1 = new Plat("plat1", 1000);
        Plat plat2 = new Plat("plat2", 2000);
        List<Plat> plats = Arrays.asList(plat1, plat2);
        when(platDao.listerPlats()).thenReturn(plats);

        // Act
        List<Plat> resultat = platService.listerPlats();

        // Assert
        assertThat(resultat)
                .hasSize(2)
                .extracting(Plat::getNom)
                .containsExactly("PLAT1", "PLAT2"); // Vérification des noms en majuscules
    }

    @Test
    void listerPlats_ErreurDao_RetournePlatException() {
        // Arrange
        when(platDao.listerPlats()).thenThrow(new NullPointerException());

        // Act & Assert
        assertThatExceptionOfType(PlatException.class)
                .isThrownBy(() -> platService.listerPlats())
                .withCauseExactlyInstanceOf(NullPointerException.class);
    }
}
