package dev.service;

import dev.dao.IPlatDao;
import dev.exception.PlatException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PlatServiceVersion2Test {

    @Mock
    private IPlatDao platDao;

    @InjectMocks
    private PlatServiceVersion2 platService;

    @Test
    void ajouterPlat_NomInvalide_LancePlatException() {
        // Act & Assert
        assertThatExceptionOfType(PlatException.class)
                .isThrownBy(() -> platService.ajouterPlat("plat", 2000))
                .withMessage("un plat doit avoir un nom de plus de 5 caractères");
    }

    @Test
    void ajouterPlat_NomEtPrixValides_AppelleDao() {
        // Arrange
        String nom = "platvalide";
        int prix = 2000;

        // Act
        platService.ajouterPlat(nom, prix);

        // Assert
        verify(platDao).ajouterPlat(nom, prix * 2);
    }
}
