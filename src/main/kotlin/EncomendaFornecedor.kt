import kotlinx.serialization.Serializable
import kotlinx.serialization.Contextual

@Serializable
data class EncomendaFornecedor(
    val idEncomenda: Int,
    val fornecedor: Fornecedor,
    @Contextual
    val itensEncomendados: List<ItemCarrinho>,
    val dataEncomenda: String // formato dd/mm/aaaa
) {
    override fun toString(): String {
        val itensFormatados = itensEncomendados.joinToString("\n    ") { 
            "${it.peca.nome} - ${it.quantidade} unidade(s)" 
        }
        return """
            |Encomenda #$idEncomenda (Fornecedor: ${fornecedor.nome}) - Data: $dataEncomenda
            |Itens:
            |    $itensFormatados
        """.trimMargin()
    }
}