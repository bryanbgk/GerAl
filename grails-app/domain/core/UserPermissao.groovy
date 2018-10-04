package core

import org.apache.commons.lang.builder.HashCodeBuilder

class UserPermissao implements Serializable {

	private static final long serialVersionUID = 1

	User user
	Permissao permissao

	boolean equals(other) {
		if (!(other instanceof UserPermissao)) {
			return false
		}

		other.user?.id == user?.id &&
		other.permissao?.id == permissao?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (user) builder.append(user.id)
		if (permissao) builder.append(permissao.id)
		builder.toHashCode()
	}

	static UserPermissao get(long userId, long permissaoId) {
		UserPermissao.where {
			user == User.load(userId) &&
			permissao == Permissao.load(permissaoId)
		}.get()
	}

	static boolean exists(long userId, long permissaoId) {
		UserPermissao.where {
			user == User.load(userId) &&
			permissao == Permissao.load(permissaoId)
		}.count() > 0
	}

	static UserPermissao create(User user, Permissao permissao, boolean flush = false) {
		def instance = new UserPermissao(user: user, permissao: permissao)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(User u, Permissao r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = UserPermissao.where {
			user == User.load(u.id) &&
			permissao == Permissao.load(r.id)
		}.deleteAll()

		if (flush) { UserPermissao.withSession { it.flush() } }

		rowCount > 0
	}

	static void removeAll(User u, boolean flush = false) {
		if (u == null) return

		UserPermissao.where {
			user == User.load(u.id)
		}.deleteAll()

		if (flush) { UserPermissao.withSession { it.flush() } }
	}

	static void removeAll(Permissao r, boolean flush = false) {
		if (r == null) return

		UserPermissao.where {
			permissao == Permissao.load(r.id)
		}.deleteAll()

		if (flush) { UserPermissao.withSession { it.flush() } }
	}

	static constraints = {
		permissao validator: { Permissao r, UserPermissao ur ->
			if (ur.user == null) return
			boolean existing = false
			UserPermissao.withNewSession {
				existing = UserPermissao.exists(ur.user.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['permissao', 'user']
		version false
		permissao lazy:false
	}
}
