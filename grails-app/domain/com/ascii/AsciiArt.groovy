package com.ascii

import sun.text.resources.cldr.bn.FormatData_bn_IN

class AsciiArt{

    final ASCII_CHAR = '#'

    ShapeType shape
    Integer height
    String label = 'HI'
    Integer labelRow = 4


    static constraints = {
        shape widget: 'select', unique: ['height','label','labelRow']
        height min: 4, max: 100
        label nullable: true, minSize: 0, maxSize: 20
        labelRow nullable: true, min: 1, max: 100, validator:{ row, obj ->
            row <= obj.height
        }
    }

    public String outputShape(){
        String resulting_art = ''
        switch (shape) {

            case ShapeType.Diamond:
                resulting_art = outputDiamond()
                break
            case ShapeType.Rectangle:
                def rectangle_width = 3;
                resulting_art = outputRectangle(rectangle_width)
                break
            case ShapeType.Square:
                //this will make square look square rather than be square if measured
                //by characters wide X characters high
                def square_width = 2;
                resulting_art = outputRectangle(square_width)
                break
            case ShapeType.Triangle:
                resulting_art = outputTriangle()
                break
        }

        resulting_art
    }

    /*
     * outputs rectangles, width a width multiplier to change visual characteristics.
     */
    private String outputRectangle(Integer width_multiplier){
        def result = StringBuilder.newInstance()
        def bottom_row = 1
        def columns_before_label = 1
        //if label is set but labelRow is not, default labelRow to 0
        if( label != null && labelRow == null ) {
            labelRow = 1
        }

        height.downto(bottom_row) {
            if(label != null && it == labelRow){
                result += ASCII_CHAR + label
                if( (ASCII_CHAR + label).length() <  height ) {
                    result += ASCII_CHAR * ((height * width_multiplier) - (ASCII_CHAR + label).length())
                }
                result += '\n'
            } else {
                result += ASCII_CHAR * (height * width_multiplier) + '\n'
            }
        }
        result
    }

    /*
     * outputs triangles.
     */
    private String outputTriangle(){
        def result = StringBuilder.newInstance()
        def bottom_row = 1
        def columns_before_label = 1;
        //if label is set but labelRow is not, default labelRow to 0
        if( label != null && labelRow == null ) {
            labelRow = 1
        }

        height.downto(bottom_row) {
            //will give increasing pyramid row size when subtracted from height, ie. 1, 2, 3, etc
            def row_length = height - it.previous()
            if(label != null && it == labelRow){
                result += ASCII_CHAR + label
                if ((ASCII_CHAR + label).length() <= row_length) {
                    result += ASCII_CHAR * (row_length - label.length().next())
                }
                result += '\n'
            } else {
                result += ASCII_CHAR * row_length + '\n'
            }
        }
        result
    }

    /*
     * outputs triangles.
     */
    private String outputDiamond(){
        def result = StringBuilder.newInstance()
        def isOdd = height % 2
        def halfHeight = height.intdiv(2)
        def bottom_row = 1
        def columns_before_label = 0;
        //if label is set but labelRow is not, default labelRow to 0
        if( label != null && labelRow == null ) {
            labelRow = 1
        }

        //create half the rows if even, half - 1 rows if odd.  Center row for odd heights will be created before
        //duplicating and concatenating halves
        halfHeight.times {
            def rowLength = isOdd ? it.next() + it : it.next()*2
            def row = ASCII_CHAR * rowLength
            if(isOdd) {
                def space = ' ' * (halfHeight - it)
                result += space +  row + space + '\n'
            } else {
                def newLine = (it.next() == halfHeight) ? '' : '\n'
                result += row.center(height) + newLine
            }
        }

        if(isOdd) {
            result += ('#' * height) + (result.reverse())
        } else {
            result += '\n' + result.reverse()
        }

        def splitDiamond = result.split('\n')
        if(label != null){
            def rowString = splitDiamond[labelRow.previous()][columns_before_label] + label
            if (rowString.length() < height) {
                rowString += splitDiamond[labelRow.previous()][label.length().next() + columns_before_label..-1]
            }
            splitDiamond[labelRow.previous()] = rowString
        }

        result = splitDiamond.reverse().join('\n')
    }


}
