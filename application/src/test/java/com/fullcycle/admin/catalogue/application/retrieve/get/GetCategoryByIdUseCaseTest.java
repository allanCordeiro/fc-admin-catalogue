package com.fullcycle.admin.catalogue.application.retrieve.get;

import com.fullcycle.admin.catalogue.application.category.retrieve.get.CategoryOutput;
import com.fullcycle.admin.catalogue.application.category.retrieve.get.DefaultGetCategoryByIdUseCase;
import com.fullcycle.admin.catalogue.domain.category.Category;
import com.fullcycle.admin.catalogue.domain.category.CategoryGateway;
import com.fullcycle.admin.catalogue.domain.category.CategoryID;
import com.fullcycle.admin.catalogue.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class GetCategoryByIdUseCaseTest {
    @InjectMocks
    private DefaultGetCategoryByIdUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;

    @BeforeEach
    void CleanUp() {
        Mockito.reset(categoryGateway);
    }

    @Test
    public void givenAValidID_whenCallsGetCategory_shouldReturnCategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        final var expectedId = aCategory.getId();

        Mockito.when(categoryGateway.findById(Mockito.eq(expectedId)))
                .thenReturn(Optional.of(aCategory.clone()));

        final var actualCategory = useCase.execute(expectedId.getValue());

        Assertions.assertEquals(CategoryOutput.from(aCategory), actualCategory);
        Mockito.verify(categoryGateway, times(1)).findById(Mockito.argThat(
                aUpdatedCategory ->
                        Objects.equals(expectedName, actualCategory.name())
                                && Objects.equals(expectedDescription, actualCategory.description())
                                && Objects.equals(expectedIsActive, actualCategory.isActive())
                                && Objects.equals(expectedId, actualCategory.id())
                                && Objects.equals(aCategory.getCreatedAt(), actualCategory.createdAt())
                                && Objects.equals(aCategory.getUpdatedAt(), actualCategory.updatedAt())
                                && Objects.isNull(actualCategory.deletedAt())
        ));

    }

    @Test
    public void givenAnInvalidID_whenCallsGetCategory_shouldReturnNotFound() {
        final var expectedId = CategoryID.from("123");
        final var expectedErrorMessage = "Category with ID 123 was not found";
        final var expectedErrorCount = 1;

        Mockito.when(categoryGateway.findById(Mockito.eq(expectedId)))
                .thenReturn(Optional.empty());

        final var actualException =Assertions.assertThrows(
                DomainException.class, () -> useCase.execute(expectedId.getValue()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());

    }

    @Test
    public void givenAValidID_whenGatewayThrowsException_shouldReturnException() {
        final var expectedId = CategoryID.from("123");
        final var expectedErrorMessage = "Gateway error";
        final var expectedErrorCount = 1;

        Mockito.when(categoryGateway.findById(Mockito.eq(expectedId)))
                .thenThrow(new IllegalStateException("Gateway error"));

        final var actualException =Assertions.assertThrows(
                IllegalStateException.class, () -> useCase.execute(expectedId.getValue()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

    }
}
