package geral



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RelogioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Relogio.list(params), model:[relogioInstanceCount: Relogio.count()]
    }

    def show(Relogio relogioInstance) {
        respond relogioInstance
    }

    def create() {
        respond new Relogio(params)
    }

    @Transactional
    def save(Relogio relogioInstance) {
        if (relogioInstance == null) {
            notFound()
            return
        }

        if (relogioInstance.hasErrors()) {
            respond relogioInstance.errors, view:'create'
            return
        }

        relogioInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'relogio.label', default: 'Relogio'), relogioInstance.id])
                redirect relogioInstance
            }
            '*' { respond relogioInstance, [status: CREATED] }
        }
    }

    def edit(Relogio relogioInstance) {
        respond relogioInstance
    }

    @Transactional
    def update(Relogio relogioInstance) {
        if (relogioInstance == null) {
            notFound()
            return
        }

        if (relogioInstance.hasErrors()) {
            respond relogioInstance.errors, view:'edit'
            return
        }

        relogioInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Relogio.label', default: 'Relogio'), relogioInstance.id])
                redirect relogioInstance
            }
            '*'{ respond relogioInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Relogio relogioInstance) {

        if (relogioInstance == null) {
            notFound()
            return
        }

        relogioInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Relogio.label', default: 'Relogio'), relogioInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'relogio.label', default: 'Relogio'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
