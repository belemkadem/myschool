package com.lastgeneration.product;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.lastgeneration.product");

        noClasses()
            .that()
                .resideInAnyPackage("com.lastgeneration.product.service..")
            .or()
                .resideInAnyPackage("com.lastgeneration.product.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.lastgeneration.product.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
