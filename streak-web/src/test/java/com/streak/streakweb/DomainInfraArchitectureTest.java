package com.streak.streakweb;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class DomainInfraArchitectureTest {
    @Test
    public void some_architecture_rule() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.streak.streakweb");

        ArchRule rule = classes().that()
                        .resideInAPackage("com.streak.streakweb.domain")
                        .should().onlyDependOnClassesThat()
                        .resideOutsideOfPackage("com.streak.streakweb.infra");
        rule.check(importedClasses);
    }
}
