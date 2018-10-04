package geral



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class QuitineteController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Quitinete.list(params), model:[quitineteInstanceCount: Quitinete.count()]
    }

    def show(Quitinete quitineteInstance) {
        respond quitineteInstance
    }

    def create() {
        respond new Quitinete(params)
    }

    @Transactional
    def save(Quitinete quitineteInstance) {
        if (quitineteInstance == null) {
            notFound()
            return
        }

        if (quitineteInstance.hasErrors()) {
            respond quitineteInstance.errors, view:'create'
            return
        }

        quitineteInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'quitinete.label', default: 'Quitinete'), quitineteInstance.id])
                redirect quitineteInstance
            }
            '*' { respond quitineteInstance, [status: CREATED] }
        }
    }

    def edit(Quitinete quitineteInstance) {
        respond quitineteInstance
    }

    @Transactional
    def update(Quitinete quitineteInstance) {
        if (quitineteInstance == null) {
            notFound()
            return
        }

        if (quitineteInstance.hasErrors()) {
            respond quitineteInstance.errors, view:'edit'
            return
        }

        quitineteInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Quitinete.label', default: 'Quitinete'), quitineteInstance.id])
                redirect quitineteInstance
            }
            '*'{ respond quitineteInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Quitinete quitineteInstance) {

        if (quitineteInstance == null) {
            notFound()
            return
        }

        quitineteInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Quitinete.label', default: 'Quitinete'), quitineteInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'quitinete.label', default: 'Quitinete'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
