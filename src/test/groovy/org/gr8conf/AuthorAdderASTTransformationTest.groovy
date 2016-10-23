package org.gr8conf

class AuthorAdderASTTransformationTest extends GroovyTestCase {
    void testGlobalAstTransformation() {
        assertScript """
            class Foo {}

            assert Foo.author == "Me"
        """
    }
}
