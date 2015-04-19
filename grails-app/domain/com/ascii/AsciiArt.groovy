package com.ascii

class AsciiArt implements Serializable{

    ShapeType shape
    Integer shapeHeight
    String artLabel = 'HI'
    Integer labelRow = 4


    static constraints = {
        shape widget: 'select'
        shapeHeight min: 3, max: 100
        artLabel nullable: true, minSize: 0, maxSize: 255
        labelRow nullable: true, min: 0, max: 100
    }
}
