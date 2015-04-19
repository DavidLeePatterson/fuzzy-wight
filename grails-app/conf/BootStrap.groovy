import com.ascii.AsciiArt
import com.ascii.ShapeType

class BootStrap {

    def init = { servletContext ->
        def art = new AsciiArt(shape: ShapeType.Square,
                               shapeHeight: 4,
                               artLabel: 'HI',
                               labelRow: 2)

        if(!art.save()){
            art.errors.allErrors.each{ error ->
                println "An error occured with art: ${error}"
            }
        }
    }
    def destroy = {
    }
}
