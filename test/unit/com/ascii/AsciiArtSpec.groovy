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

    void "test constraints for valid items"() {

        when: "New AsciiArt is created with given values"
        def art1 = new AsciiArt(shape: ShapeType.Square,
                height: 4,
                label: 'HI',
                labelRow: 4)

        then : "Values are set and will save"
        art1.shape == ShapeType.Square
        art1.height == 4
        art1.label == 'HI'
        art1.labelRow == 4
        art1.save()
        art1.errors.errorCount == 0
        art1.id != null
        AsciiArt.get(art1.id).label == art1.label
    }

    void "test constraints for invalid items"(){
        when: "New AsciiArt is created with all bad values"
            def art1 = new AsciiArt(shape: 'notSquare')

        then: "it will not save"
            !art1.save()
            art1.errors.errorCount > 0
            art1.id == null

        when: "New AsciiArt is created with bad height"
        def art2 = new AsciiArt(shape: ShapeType.Square,
                height: -1)

        then: "it will not save"
        !art2.save()
        art2.errors.errorCount > 0
        art2.id == null

        when: "New AsciiArt is created with bad height"
        def art3 = new AsciiArt(shape: ShapeType.Square,
                height: 2)

        then: "it will not save"
        !art3.save()
        art3.errors.errorCount > 0
        art3.id == null

        when: "New AsciiArt is created with bad height"
        def art4 = new AsciiArt(shape: ShapeType.Square,
                height: 2000)

        then: "it will not save"
        !art4.save()
        art4.errors.errorCount > 0
        art4.id == null
    }

    void "test updating properties"(){

        given:
            def goodArt = new AsciiArt(shape: ShapeType.Square,
                height: 4,
                label: 'HI',
                labelRow: 4)
            goodArt.save()

        when: "properties are changed"
            def foundArt = AsciiArt.get(goodArt.id)
            foundArt.shape = ShapeType.Triangle
            foundArt.save()

        then: "the change is perpetuated"
            def result = AsciiArt.get(goodArt.id)
            result.shape == ShapeType.Triangle
    }

    void "Test drawing different shapes with labels"() {
        //with short label
        when: "A square Ascii String with label is output"
        def goodArt = new AsciiArt(shape: ShapeType.Square,
                height: 5,
                label: 'HI',
                labelRow: 4)
        def result = goodArt.outputShape()

        then: "It should equal a examplar"
            result == '#' * 10 + '\n' + '#HI' + '#' * 7 + '\n' + ('#' * 10 + '\n') * 3

        when: "A rectangle Ascii String with label is output"
        goodArt.shape = ShapeType.Rectangle
        result = goodArt.outputShape()

        then: "It should equal a examplar"
        result == '#' * 15 + '\n' + '#HI' + '#' * 12 + '\n' + ('#' * 15 + '\n') * 3

        when: "A triangle Ascii String with label is output"
        goodArt.shape = ShapeType.Triangle
        result = goodArt.outputShape()

        then: "It should equal a examplar"
        result == '#\n#HI\n###\n####\n#####\n'

        when: "A diamond with even height Ascii String with label is output"
        goodArt.shape = ShapeType.Diamond
        result = goodArt.outputShape()

        then: "It should equal a examplar"
        result == '  #  \n HI# \n#####\n ### \n  #  '

        when: "A diamond with odd height Ascii String with label is output"
        goodArt.shape = ShapeType.Diamond
        goodArt.height = 6
        result = goodArt.outputShape()

        then: "It should equal a examplar"
        result == '  ##  \n #### \n#HI###\n######\n #### \n  ##  '

        //without label
        when: "A square Ascii String without label is output"
        goodArt.shape = ShapeType.Square
        goodArt.height = 5
        goodArt.label = null
        result = goodArt.outputShape()

        then: "It should equal a examplar"
        result == ('#' * 10 + '\n') * 5

        when: "A rectangle Ascii String without label is output"
        goodArt.shape = ShapeType.Rectangle
        result = goodArt.outputShape()

        then: "It should equal a examplar"
        result == ('#' * 15 + '\n') * 5

        when: "A triangle Ascii String without label is output"
        goodArt.shape = ShapeType.Triangle
        result = goodArt.outputShape()

        then: "It should equal a examplar"
        result == '#\n##\n###\n####\n#####\n'

        when: "A diamond with even height Ascii String without label is output"
        goodArt.shape = ShapeType.Diamond
        result = goodArt.outputShape()

        then: "It should equal a examplar"
        result == '  #  \n ### \n#####\n ### \n  #  '

        when: "A diamond with odd height Ascii String without label is output"
        goodArt.shape = ShapeType.Diamond
        goodArt.height = 6
        result = goodArt.outputShape()

        then: "It should equal a examplar"
        result == '  ##  \n #### \n######\n######\n #### \n  ##  '

        //with label but no labelRow
        when: "A square Ascii String with label but null labelRow"
        goodArt.shape = ShapeType.Square
        goodArt.height = 5
        goodArt.label = 'HI'
        goodArt.labelRow = null
        result = goodArt.outputShape()

        then: "It should equal a examplar"
        result == ('#' * 10 + '\n') * 4 + '#HI' + '#' * 7 + '\n'

        when: "A rectangle Ascii String with label but null labelRow"
        goodArt.shape = ShapeType.Rectangle
        result = goodArt.outputShape()

        then: "It should equal a examplar"
        result == ('#' * 15 + '\n') * 4 + '#HI' + '#' * 12 + '\n'

        when: "A triangle Ascii String with label but null labelRow"
        goodArt.shape = ShapeType.Triangle
        result = goodArt.outputShape()

        then: "It should equal a examplar"
        result == '#\n##\n###\n####\n#HI##\n'

        when: "A diamond with even height Ascii String with label but null labelRow"
        goodArt.shape = ShapeType.Diamond
        result = goodArt.outputShape()

        then: "It should equal a examplar"
        result == '  #  \n ### \n#####\n ### \n HI  '

        when: "A diamond with odd height Ascii String with label but null labelRow"
        goodArt.shape = ShapeType.Diamond
        goodArt.height = 6
        result = goodArt.outputShape()

        then: "It should equal a examplar"
        result == '  ##  \n #### \n######\n######\n #### \n HI#  '

        //with long label
        when: "A square Ascii String with long label is output"
        goodArt.shape = ShapeType.Square
        goodArt.height = 5
        goodArt.label = 'HEllOANDGOODBYE'
        goodArt.labelRow = 4
        result = goodArt.outputShape()

        then: "It should equal a examplar"
        result == '#' * 10 + '\n' + '#HEllOANDGOODBYE\n' + ('#' * 10 + '\n') * 3

        when: "A rectangle Ascii String with long label is output"
        goodArt.shape = ShapeType.Rectangle
        result = goodArt.outputShape()

        then: "It should equal a examplar"
        result == '#' * 15 + '\n' + '#HEllOANDGOODBYE\n' + ('#' * 15 + '\n') * 3

        when: "A triangle Ascii String with long label is output"
        goodArt.shape = ShapeType.Triangle
        result = goodArt.outputShape()

        then: "It should equal a examplar"
        result == '#\n#HEllOANDGOODBYE\n###\n####\n#####\n'

        when: "A diamond with even height Ascii String with long label is output"
        goodArt.shape = ShapeType.Diamond
        result = goodArt.outputShape()

        then: "It should equal a examplar"
        result == '  #  \n HEllOANDGOODBYE\n#####\n ### \n  #  '

        when: "A diamond with odd height Ascii String with long label is output"
        goodArt.shape = ShapeType.Diamond
        goodArt.height = 6
        result = goodArt.outputShape()

        then: "It should equal a examplar"
        println '  ##  \n #### \n#HEllOANDGOODBYE\n######\n #### \n  ##  '
        result == '  ##  \n #### \n#HEllOANDGOODBYE\n######\n #### \n  ##  '
    }
}
