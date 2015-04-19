package com.ascii

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(AsciiArt)
class AsciiArtSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test constraints"() {
        when: "AsciiArt is created"
            def art1 = new AsciiArt(shape: ShapeType.Square,
                    shapeHeight: 4,
                    artLabel: 'HI',
                    labelRow: 4)

        then: "Values are set"
            art1.shape == ShapeType.Square
            art1.shapeHeight == 4
            art1.artLabel == 'HI'
            art1.labelRow == 4

        when: "AsciiArt is created with bad values"
        def art2 = new AsciiArt(shape: ShapeType.Square,
                shapeHeight: -1,
                labelRow: -1)

        then: "Values are not set set"
        art2.shape == ShapeType.Square
        art2.shapeHeight == -1
        art2.artLabel == 'HI'
        art2.labelRow != -1
    }
}
