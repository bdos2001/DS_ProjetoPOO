import kotlinx.serialization.Serializable

@Serializable
data class Pessoa(
    val idPessoa: Int,
    var nome: String,
    var morada: String,
    var contacto: Int,
    var nif: Int,
    var email: String
) {
    override fun toString(): String {
        return """
        ------------------------------
        Nome: $nome
        Morada: $morada
        Contacto: $contacto
        NIF: $nif
        Email: $email
        ------------------------------
    """.trimIndent()
    }
}