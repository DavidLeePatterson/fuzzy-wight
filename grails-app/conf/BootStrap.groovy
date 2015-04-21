import com.ascii.AsciiArt
import com.ascii.ShapeType

class BootStrap {

    def init = { servletContext ->
        def art = new AsciiArt(shape: ShapeType.Square,
                               height: 4,
                               label: 'HI',
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
