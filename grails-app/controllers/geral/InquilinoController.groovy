package geral



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class InquilinoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Inquilino.list(params), model:[inquilinoInstanceCount: Inquilino.count()]
    }

    def show(Inquilino inquilinoInstance) {
        respond inquilinoInstance
    }

    def create() {
        respond new Inquilino(params)
    }

    @Transactional
    def save(Inquilino inquilinoInstance) {
        if (inquilinoInstance == null) {
            notFound()
            return
        }

        if (inquilinoInstance.hasErrors()) {
            respond inquilinoInstance.errors, view:'create'
            return
        }

        inquilinoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'inquilino.label', default: 'Inquilino'), inquilinoInstance.id])
                redirect inquilinoInstance
            }
            '*' { respond inquilinoInstance, [status: CREATED] }
        }
    }

    def edit(Inquilino inquilinoInstance) {
        respond inquilinoInstance
    }

    @Transactional
    def update(Inquilino inquilinoInstance) {
        if (inquilinoInstance == null) {
            notFound()
            return
        }

        if (inquilinoInstance.hasErrors()) {
            respond inquilinoInstance.errors, view:'edit'
            return
        }

        inquilinoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Inquilino.label', default: 'Inquilino'), inquilinoInstance.id])
                redirect inquilinoInstance
            }
            '*'{ respond inquilinoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Inquilino inquilinoInstance) {

        if (inquilinoInstance == null) {
            notFound()
            return
        }

        inquilinoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Inquilino.label', default: 'Inquilino'), inquilinoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'inquilino.label', default: 'Inquilino'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
