import kotlinx.serialization.Serializable

@Serializable
data class Pecas (
    val id: Int,
    var nome: String,
    var tipoPeca: tipoPeca,
    var preco: Double,
    var stock: Int,
    var compatibilidadeModelos: List<Modelo>
){
    override fun toString(): String {
        return """
        ------------------------------
        Id: $id
        Nome: $nome
        Tipo de Peça: ${tipoPeca.nome}
        Preço: $preco
        Stock: $stock
        ------------------------------
    """.trimIndent()
    }
}

