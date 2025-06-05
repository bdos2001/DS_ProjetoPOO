import kotlinx.serialization.Serializable

@Serializable
data class Fornecedor(
    val id: Int,
    var nome: String,
    var pais: String,
    var numeroTelefone: String
) {
    override fun toString(): String {
        return """
        ==============================
        ID Fornecedor: $id
        Nome: $nome
        País: $pais
        Telefone: $numeroTelefone
        ==============================
        """.trimIndent()
    }
}
