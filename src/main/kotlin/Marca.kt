import kotlinx.serialization.Serializable

@Serializable
data class Marca(
    val id: Int,
    var nome: String
){
    override fun toString(): String {
        return """
        ------------------------------
        Id: $id
        Marca: $nome
        ------------------------------
    """.trimIndent()
    }
}