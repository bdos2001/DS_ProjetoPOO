import kotlinx.serialization.Serializable

@Serializable
class Modelo(
    val id: Int,
    var nome: String,
    var marca: Marca,
) {
    override fun toString(): String {
        return """
        ------------------------------
        Id: $id
        Marca: ${marca.nome}
        Modelo: $nome
        ------------------------------
    """.trimIndent()
    }
}