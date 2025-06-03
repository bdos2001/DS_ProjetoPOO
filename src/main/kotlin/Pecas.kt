import kotlinx.serialization.Serializable

@Serializable
data class Peca (
    val id: Int,
    var nome: String,
    var tipoPeca: TipoPeca,
    var preco: Double,
    var stock: Int,
    var compatibilidadeModelos: List<Modelo>
){
    override fun toString(): String {
        val compatibilidadeStr = compatibilidadeModelos.joinToString("\n") { modelo ->
            "        - ${modelo.nome} (${modelo.marca.nome})"
        }

        return """
        ------------------------------
        Id: $id
        Nome: $nome
        Tipo de Peça: ${tipoPeca.nome}
        Preço: $preco
        Stock: $stock
        Compatível com:
$compatibilidadeStr
        ------------------------------
    """.trimIndent()
    }
}
