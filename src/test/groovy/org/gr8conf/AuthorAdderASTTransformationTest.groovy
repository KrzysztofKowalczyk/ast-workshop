package org.gr8conf

class AuthorAdderASTTransformationTest extends GroovyTestCase {
    void testThatAuthorExists() {
        assert $AUTHOR == 'Me'
    }

    void testASTTransformationShouldBeDebuggableFromIDE() {
        assertScript '''
            class Foo {
            }
            assert Foo.$AUTHOR == 'Me'
        '''
    }
}
