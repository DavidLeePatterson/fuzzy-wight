package com.ascii



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AsciiArtController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AsciiArt.list(params), model:[asciiArtInstanceCount: AsciiArt.count()]
    }

    def show(AsciiArt asciiArtInstance) {
        respond asciiArtInstance
    }

    def create() {
        respond new AsciiArt(params)
    }

    @Transactional
    def save(AsciiArt asciiArtInstance) {
        if (asciiArtInstance == null) {
            notFound()
            return
        }

        if (asciiArtInstance.hasErrors()) {
            respond asciiArtInstance.errors, view:'create'
            return
        }

        asciiArtInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'asciiArt.label', default: 'AsciiArt'), asciiArtInstance.id])
                redirect asciiArtInstance
            }
            '*' { respond asciiArtInstance, [status: CREATED] }
        }
    }

    def edit(AsciiArt asciiArtInstance) {
        respond asciiArtInstance
    }

    @Transactional
    def update(AsciiArt asciiArtInstance) {
        if (asciiArtInstance == null) {
            notFound()
            return
        }

        if (asciiArtInstance.hasErrors()) {
            if(asciiArtInstance.errors[0]) {
                respond asciiArtInstance.errors, view: 'edit'
            } else {
                respond asciiArtInstance.errors, view: 'edit'
            }
            return
        }

        asciiArtInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'AsciiArt.label', default: 'AsciiArt'), asciiArtInstance.id])
                redirect asciiArtInstance
            }
            '*'{ respond asciiArtInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(AsciiArt asciiArtInstance) {

        if (asciiArtInstance == null) {
            notFound()
            return
        }

        asciiArtInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'AsciiArt.label', default: 'AsciiArt'), asciiArtInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'asciiArt.label', default: 'AsciiArt'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
